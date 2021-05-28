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
            <!-- <el-step title="asasd" />
            <el-step title="asasd" />
            <el-step title="asasd" /> -->
          </el-steps>
        </div>
      </el-scrollbar>
    </header>
    <main>
      <Zone :cells="cells" :activeSeat="seat.groupIndex - 1" />
    </main>
    <div class="operation">
      <el-button type="primary" @click="next" v-if="!finish">下一步</el-button>
      <el-alert
        v-else
        title="已完成"
        type="success"
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
import { getTemplate } from "@/api/jxfz/template";
import { taskCompleted } from "@/api/jxfz/process";
export default {
  components: {
    Zone,
  },
  data() {
    return {
      step: 0,
      id: null,
      show: false,
      websocket: null,
      taskList: [],
      length: 0,
      cells: null,
      seat: {
        groupIndex: null,
      },
      taskId: null,
      finish: false,
    };
  },
  watch: {
    // taskList: {
    //   handler(val) {
    //     for (let i = 0; i < val.length; i++) {
    //       if (val[i].completed == 0) {
    //         this.step = i;
    //         break;
    //       }
    //     }
    //   },
    //   deep: true,
    // },
  },
  created() {
    this.id = this.$route.query.id;
    getTemplate(this.id).then((res) => {
      this.cells = JSON.parse(res.data.tbc1);
    });
    this.initSocket();
  },
  computed: {
    // taskId() {
    //   let id = null;
    //   for (let i = 0; i < this.taskList.length; i++) {
    //     if (this.taskList[i].completed == 0) {
    //       id = this.taskList[i].id;
    //       break;
    //     }
    //   }
    //   return id;
    // },
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
          "ws://192.168.31.20:" + 8080 + "/websocket/flow/" + this.id
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
          this.length = monitonJson.eduTaskList.length;
          this.$set(this, "taskList", monitonJson.eduTaskList);
          for (let i = 0; i < this.taskList.length; i++) {
            if (this.taskList[i].completed == 0) {
              this.taskId = this.taskList[i].id;
              this.step = i;
              break;
            } else if (i == this.taskList.length - 1) {
              this.step = i + 1;
              this.finish = true;
            }
          }
          this.$set(this, "seat", monitonJson.eduSeat);
          setTimeout(() => {
            this.show = true;
            this.$nextTick(() => {
              const scroll = this.$refs["scroll"].wrap;
              scroll.scrollLeft = (this.length - 1) * 300;
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
      this.$confirm("进入下一步, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        taskCompleted({
          completed: 1,
          id: this.taskId,
        }).then((res) => {
          this.websocket.send(this.id);
        });
      });
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
  }
  .operation {
    padding: 0 50px 40px;
  }
}
</style>