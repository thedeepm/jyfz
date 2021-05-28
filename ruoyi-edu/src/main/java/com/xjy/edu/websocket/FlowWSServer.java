package com.xjy.edu.websocket;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.EduTask;
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
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * WebSocket获取实时任务状态并输出到Web页面
 * @author wuzh
 */
@Component
@ServerEndpoint(value = "/websocket/flow/{templateId}", configurator = MyEndpointConfigure.class)
public class FlowWSServer {

    @Autowired
    private IEduTaskService eduTaskService;

    @Autowired
    private IEduTemplateService iEduTemplateService;

    @Autowired
    private IEduSeatService iEduSeatService;

    private static final Logger log = LoggerFactory.getLogger(FlowWSServer.class);

    private final ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    private final int OPERATE_DELAY_TIME = 100;

    /**
     * 连接集合
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("templateId") Long templateId) {
        //添加到集合中
        sessionMap.put(session.getId(), session);
        EduTask eduTask = new EduTask();

        executor.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                log.info("FlowWSServer 任务开始");
                ObjectMapper mapper = new ObjectMapper();
                List<EduTask> eduTaskList;
                //List<EduSeat> eduSeatList = new ArrayList<>();
                EduSeat eduSeat = new EduSeat();
                Map<String,Object> map = new ConcurrentHashMap<String,Object>();
                //当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                //设置日期格式
                mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                while (sessionMap.get(session.getId()) != null) {
                    try {
                        if(templateId == null){
                            send(session,  mapper.writeValueAsString("no templateId"));
                            break;
                        }
                        //获取任务状态和席位 发送
                        Long flowId = iEduTemplateService.selectEduTemplateById(templateId).getFlowId();
                        if(flowId == null){
                            send(session,  mapper.writeValueAsString("no flowId"));
                            break;
                        }
                        eduTask.setFlowId(flowId);
                        eduTaskList = eduTaskService.selectEduTaskList(eduTask);
                        for (int i = 0; i< eduTaskList.size(); i++){
                            if(eduTaskList.get(i).getCompleted() == 0L){
                                eduSeat = iEduSeatService.selectEduSeatById(eduTaskList.get(i).getSeatId());
                                break;
                            }
                        }
                        map.put("eduSeat", eduSeat);
                        map.put("eduTaskList", eduTaskList);
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
        Long templateId = new Long(message);
        ObjectMapper mapper = new ObjectMapper();
        List<EduTask> eduTaskList;
        Map<String,Object> map = new ConcurrentHashMap<String,Object>();
        EduSeat eduSeat = new EduSeat();
        //当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //设置日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        EduTask eduTask = new EduTask();
        try {
            if(templateId == null){
                send(session,  mapper.writeValueAsString("no templateId"));
            }
            //获取任务状态和席位 发送
            Long flowId = iEduTemplateService.selectEduTemplateById(templateId).getFlowId();
            if(flowId == null){
                send(session,  mapper.writeValueAsString("no flowId"));
            }
            eduTask.setFlowId(flowId);
            eduTaskList = eduTaskService.selectEduTaskList(eduTask);
            for (int i = 0; i< eduTaskList.size(); i++){
                if(eduTaskList.get(i).getCompleted() == 0L){
                    eduSeat = iEduSeatService.selectEduSeatById(eduTaskList.get(i).getSeatId());
                    break;
                }
            }
                map.put("eduSeat", eduSeat);
                map.put("eduTaskList", eduTaskList);
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
