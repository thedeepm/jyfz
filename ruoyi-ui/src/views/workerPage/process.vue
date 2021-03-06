<template>
  <div class="main-contianer">
    <header>
      <el-scrollbar class="scrollbar" ref="scroll">
        <div :style="{ width: length * 300 + 'px' }">
          <el-steps
            :active="step"
            align-center
            finish-status="success"
            v-if="show"
          >
            <el-step
              :title="item.taskName"
              v-for="(item, index) in taskList"
              :key="index"
            >
              <div slot="description">
                <div>作业人{{ item.seatId }}</div>
                <div>{{ item.startTime }}至{{ item.endTime }}</div>
                <div>预警时间{{ item.warningTime }}</div>
              </div>
            </el-step>
          </el-steps>
        </div>
      </el-scrollbar>
    </header>
    <main>
      <template v-if="time != undefined">
        <el-tag v-if="username" style="margin-bottom: 10px"
          >当前工作人：{{ username }}</el-tag
        >
        <div class="time">
          <el-progress
            :percentage="percentage"
            :color="customColors"
            :stroke-width="20"
            :format="timeRemaining"
          ></el-progress>
        </div>
        <Zone :cells="cells" :activeSeat="seat" v-if="cellShow" />
        <a :href="file.visitUrl" style="margin-top: 15px"
          ><el-link icon="el-icon-download">{{ file.fileName }}</el-link></a
        >
      </template>
    </main>
    <div class="operation">
      <!-- <el-button
        type="primary"
        @click="next"
        v-if="!finish && time != undefined"
        >下一步</el-button
      > -->
      <FileUpload
        v-if="
          !finish && time != undefined && $store.state.user.userName == username
        "
        v-model="url"
        :id.sync="fileId"
      />
      <el-alert
        v-if="finish"
        title="已完成"
        type="success"
        center
        show-icon
        :closable="false"
      >
      </el-alert>
      <el-alert
        v-if="!finish && time == undefined"
        title="任务暂未开始"
        type="warning"
        center
        show-icon
        :closable="false"
      >
      </el-alert>
    </div>
  </div>
</template>

<script>
import Zone from "@/components/Zone";
import { getTemplate, updateTask } from "@/api/jxfz/template";
import { taskCompleted, getWork, getFile } from "@/api/jxfz/process";
import FileUpload from "./FileUpload";
import { formatDate } from "@/utils";
export default {
  components: {
    Zone,
    FileUpload,
  },
  data() {
    return {
      step: 0,
      id: null,
      userId: this.$store.state.user.id,
      show: false,
      cellShow: false,
      websocket: null,
      taskList: [],
      length: 0,
      cells: null,
      seat: {
        warning: false,
        groupIndex: null,
      },
      taskId: null,
      finish: false,
      customColors: [
        { color: "#f56c6c", percentage: 20 },
        { color: "#e6a23c", percentage: 40 },
        { color: "#5cb87a", percentage: 60 },
      ],
      time: 0,
      totalTime: 0,
      file: {},
      username: null,
      url: "",
      fileId: "",
    };
  },
  computed: {
    percentage() {
      if (this.time < 0) {
        return 0;
      } else {
        return this.time / this.totalTime;
      }
    },
  },
  created() {
    getWork({ userId: this.userId }).then((res) => {
      if (res.rows[0]) {
        this.id = res.rows[0].id;
        this.templateId = res.rows[0].templateId;
        getTemplate(this.templateId).then((res) => {
          this.cells = JSON.parse(res.data.tbc1);
          // setTimeout(() => {
            this.cellShow = true;
          // }, 500);
        });
        getFile({ caseId: res.rows[0].id }).then((res) => {
          this.file = res.data;
        });
        this.initSocket();
      }
    });
  },

  beforeDestroy() {
    this.websocket?.close();
  },
  methods: {
    initSocket() {
      //判断当前浏览器是否支持WebSocket
      if ("WebSocket" in window) {
        //动态获取域名或ip
        // let hostname = window.location.hostname;
        // port = window.location.port;

        this.websocket = new WebSocket(
          process.env.APP_SOCKET + "/websocket/flow/" + this.id
        );
      } else {
        console.error("不支持WebSocket");
      }

      //连接发生错误的回调方法
      this.websocket.onerror = (e) => {
        console.error("WebSocket连接发生错误");
      };

      //连接成功建立的回调方法
      this.websocket.onopen = () => {
        console.log("WebSocket连接成功");
      };

      //接收到消息的回调方法
      this.websocket.onmessage = (event) => {
        //追加
        console.log(event);
        if (event.data) {
          let monitonJson = JSON.parse(event.data);
          // this.taskList = monitonJson.eduTaskList;
          this.username = monitonJson.userName;
          this.time = monitonJson.seconds;
          this.totalTime = monitonJson.totalSeconds;
          if (
            new Date().getTime() >
            new Date(monitonJson.currentEduTask.warningTime).getTime()
          ) {
            this.seat.warning = true;
          } else {
            this.seat.warning = false;
          }
          this.length = monitonJson.eduTaskList.length;
          for (let i = 0; i < monitonJson.eduTaskList.length; i++) {
            if (monitonJson.eduTaskList[i].completed == 0) {
              this.taskId = monitonJson.eduTaskList[i].caseTaskId;
              this.nextTaskId = monitonJson.eduTaskList[i + 1]?.id;
              this.step = i;
              break;
            } else if (i == monitonJson.eduTaskList.length - 1) {
              this.step = i + 1;
              this.finish = true;
            }
          }
          // this.step=monitonJson.currentEduTask.stepLevel-1;

          if (this.taskList.length == 0) {
            this.$set(
              this,
              "taskList",
              JSON.parse(JSON.stringify(monitonJson.eduTaskList))
            );
          }
          this.$set(
            this.seat,
            "groupIndex",
            monitonJson.eduSeat.groupIndex - 1
          );
          setTimeout(() => {
            this.show = true;
            this.$nextTick(() => {
              const scroll = this.$refs["scroll"].wrap;
              scroll.scrollLeft = (this.step - 1) * 300;
            });
          }, 100);

          this.$forceUpdate();
        }
      };

      //连接关闭的回调方法
      this.websocket.onclose = () => {
        console.log("WebSocket连接关闭");
      };
    },
    next() {
      this.$confirm("提交并完成作业, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        taskCompleted({
          completed: 1,
          id: this.taskId,
        }).then((res) => {
          this.websocket.send(this.id);
          updateTask({
            id: this.nextTaskId,
            startTime: formatDate(new Date()),
          });
        });
      });
    },
    timeRemaining() {
      let time = 0;
      let warning = false;
      let label = "剩余时间：";
      if (this.time < 0) {
        time = Math.abs(this.time);
        label = "已超时：";
      } else {
        time = this.time;
      }
      let theTime = parseInt(time); // 需要转换的时间秒
      let theTime1 = 0; // 分
      let theTime2 = 0; // 小时
      let theTime3 = 0; // 天
      if (theTime > 60) {
        theTime1 = parseInt(theTime / 60);
        theTime = parseInt(theTime % 60);
        if (theTime1 > 60) {
          theTime2 = parseInt(theTime1 / 60);
          theTime1 = parseInt(theTime1 % 60);
          if (theTime2 > 24) {
            //大于24小时
            theTime3 = parseInt(theTime2 / 24);
            theTime2 = parseInt(theTime2 % 24);
          }
        }
      }
      let result = "";
      if (theTime > 0) {
        result = "" + parseInt(theTime) + "秒";
      }
      if (theTime1 > 0) {
        result = "" + parseInt(theTime1) + "分" + result;
      }
      if (theTime2 > 0) {
        result = "" + parseInt(theTime2) + "小时" + result;
      }
      if (theTime3 > 0) {
        result = "" + parseInt(theTime3) + "天" + result;
      }
      return label + result;
    },
  },
};
</script>

<style lang="scss" scoped>
.main-contianer {
  header {
    width: 100%;
    // height: 150px;
    background: #fafafa;
    // display: flex;
    // align-items: center;
    padding: 20px 0 5px;
    .el-steps {
      width: 100%;
      ::v-deep .el-step__icon {
        background: #fafafa;
      }
      ::v-deep .el-step__description {
        padding: 0 5px 15px;
      }
    }
  }
  main {
    padding: 50px 20px 40px;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    .time {
      width: 30%;
      margin-bottom: 40px;
    }
  }
  ::v-deep .el-progress-bar {
    padding-right: 0;
    margin-right: 0;
  }
  ::v-deep .el-progress__text {
    margin-top: 10px;
  }
  ::v-deep .el-progress-bar__inner {
    left: unset;
    right: 0;
  }
  .operation {
    padding: 0 50px 40px;
  }
}
</style>