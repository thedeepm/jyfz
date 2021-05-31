package com.xjy.edu.websocket;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.xjy.edu.domain.EduCaseTask;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.service.IEduCaseTaskService;
import com.xjy.edu.service.IEduSeatService;
import com.xjy.edu.service.IEduTaskService;
import com.xjy.edu.service.IEduTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * WebSocket获取实时任务状态并输出到Web页面
 * @author wuzh
 */
@Component
@ServerEndpoint(value = "/websocket/flow/{caseId}", configurator = MyEndpointConfigure.class)
public class FlowWSServer {

    @Autowired
    private IEduTaskService eduTaskService;

    @Autowired
    private IEduTemplateService iEduTemplateService;

    @Autowired
    private IEduSeatService iEduSeatService;

    @Autowired
    private IEduCaseTaskService eduCaseTaskService;

    private static final Logger log = LoggerFactory.getLogger(FlowWSServer.class);

    private final ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    private final int OPERATE_DELAY_TIME = 100;

    /**
     * 连接集合
     * 根据caseid查询关联表出任务列表
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("caseId") Long caseId) {
        //添加到集合中
        sessionMap.put(session.getId(), session);
        executor.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                log.info("FlowWSServer 任务开始");
                ObjectMapper mapper = new ObjectMapper();
                EduSeat eduSeat = new EduSeat();
                Map<String,Object> map = new ConcurrentHashMap<String,Object>();
                EduTask eduTask = new EduTask();
                //当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                //设置日期格式
                mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                Long seconds = 0L;
                Long totalSeconds = 0L;
                Instant nowInstant;
                Instant startInstant;
                Instant endInstant;
                while (sessionMap.get(session.getId()) != null) {
                    List<EduTask> eduTaskList = new ArrayList<>();
                    try {
                        if(caseId == null){
                            send(session,  mapper.writeValueAsString("no caseId"));
                            break;
                        }
                        EduCaseTask eduCaseTask = new EduCaseTask();
                        eduCaseTask.setCaseId(caseId);
                        List<EduCaseTask> eduCaseTaskList = eduCaseTaskService.selectEduCaseTaskList(eduCaseTask);

                        if(eduCaseTaskList != null && eduCaseTaskList.size() > 0){
                            for (int i = 0; i < eduCaseTaskList.size(); i++){
                                eduTask = new EduTask();
                                eduTask = eduTaskService.selectEduTaskById(eduCaseTaskList.get(i).getTaskId());
                                eduTask.setCompleted(eduCaseTaskList.get(i).getCompleted());
                                eduTask.setId(eduCaseTaskList.get(i).getId());
                                eduTaskList.add(eduTask);
                            }
                        }else{
                            send(session,  mapper.writeValueAsString("the template do not have task!"));
                        }
                        nowInstant = Instant.now();
                        for (int i = 0; i< eduCaseTaskList.size(); i++){
                            if(eduCaseTaskList.get(i).getCompleted() == 0L){
                                eduTask = eduTaskService.selectEduTaskById(eduCaseTaskList.get(i).getTaskId());
                                eduSeat = iEduSeatService.selectEduSeatById(eduTask.getSeatId());
                                startInstant = eduTask.getStartTime().toInstant();
                                endInstant = eduTask.getEndTime().toInstant();
                                if(startInstant.isBefore(nowInstant)){
                                    seconds = Duration.between(nowInstant, endInstant).getSeconds();
                                    map.put("seconds", seconds);
                                }
                                 totalSeconds = Duration.between(startInstant, endInstant).getSeconds();
                                break;
                            }
                        }
                        map.put("eduSeat", eduSeat);
                        map.put("eduTaskList", eduTaskList);
                        map.put("currentEduTask", eduTask);
                        map.put("totalSeconds", totalSeconds);
                        send(session,  mapper.writeValueAsString(map));
                        //休眠五秒
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        //输出到日志文件中
                        log.error(e.getMessage());
                    }
                }
                log.info("FlowWSServer 任务结束");
                }
            }, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        //从集合中删除
        sessionMap.remove(session.getId());
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //输出到日志文件中
        log.error(error.getMessage());
    }

    /**
     * 服务器接收到客户端消息时调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Long caseId = new Long(message);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = new ConcurrentHashMap<String,Object>();
        EduSeat eduSeat = new EduSeat();
        //当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //设置日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        EduTask eduTask = new EduTask();
        Long seconds = 0L;
        Long totalSeconds = 0L;
        List<EduTask> eduTaskList = new ArrayList<>();
        Instant nowInstant;
        Instant startInstant;
        Instant endInstant;
        try {
            if(caseId == null){
                send(session,  mapper.writeValueAsString("no caseId"));
            }
            EduCaseTask eduCaseTask = new EduCaseTask();
            eduCaseTask.setCaseId(caseId);
            List<EduCaseTask> eduCaseTaskList = eduCaseTaskService.selectEduCaseTaskList(eduCaseTask);
            if(eduCaseTaskList != null && eduCaseTaskList.size() > 0){
                for (int i = 0; i < eduCaseTaskList.size(); i++){
                    eduTaskList.add(eduTaskService.selectEduTaskById(eduCaseTaskList.get(i).getTaskId()));
                }
            }else{
                send(session,  mapper.writeValueAsString("the template do not have task!"));
            }

            nowInstant = Instant.now();
            for (int i = 0; i< eduCaseTaskList.size(); i++){
                if(eduCaseTaskList.get(i).getCompleted() == 0L){
                    eduTask = eduTaskService.selectEduTaskById(eduCaseTaskList.get(i).getTaskId());
                    eduSeat = iEduSeatService.selectEduSeatById(eduTask.getSeatId());
                    startInstant = eduTask.getStartTime().toInstant();
                    endInstant = eduTask.getEndTime().toInstant();
                    if(startInstant.isBefore(nowInstant)){
                        seconds = Duration.between(nowInstant, endInstant).getSeconds();
                        map.put("seconds", seconds);
                    }
                    totalSeconds = Duration.between(startInstant, endInstant).getSeconds();
                    break;
                }
            }
            map.put("eduSeat", eduSeat);
            map.put("eduTaskList", eduTaskList);
            map.put("currentEduTask", eduTask);
            map.put("totalSeconds", totalSeconds);
            send(session,  mapper.writeValueAsString(map));
        } catch (Exception e) {
            //输出到日志文件中
            log.error(e.getMessage());
        }
    }

    /**
     * 封装一个send方法，发送消息到前端
     */
    private void send(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            //输出到日志文件中
            log.error(e.getMessage());
        }
    }
}
