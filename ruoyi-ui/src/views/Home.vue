<template>
  <div class="home">
    <el-container>
      <el-header height="100px" class="animate__animated animate__fadeInDown">
        <img class="logo" src="../assets/logo-title.png" />
        <div class="menu">
          <!-- <div class="menu-item active hvr-shrink">首页</div>
          <div
            class="menu-item hvr-shrink"
            v-for="(item, index) in menuList"
            :key="index"
            @click="$router.push(item.path)"
          >
            {{ item.name }}
          </div> -->
        </div>
      </el-header>
      <el-main>
        <div class="menu-box">
          <div class="text animate__animated animate__fadeInUp">
            <h2>教学辅助系统</h2>
            <!-- <h3>以吾少年意气 许国万里恒昌</h3> -->
          </div>
          <div class="menu-icon">
            <!-- <div
              class="menu-icon-item animate__animated animate__flipInX"
              v-for="(item, index) in menuList"
              :key="index"
              @click="$router.push(item.path)"
            >
              <div class="icon">
                <img :src="item.img" />
              </div>
              <h3>{{ item.name }}</h3>
              <h4>{{ item.desc }}</h4>
            </div>
          </div> -->
            <div
              class="menu-icon-item animate__animated animate__flipInX"
              @click="$router.push('/gis')"
            >
              <div class="icon">
                <img :src="require('../assets/clc.png')" />
              </div>
              <h3>地理信息速查</h3>
            </div>
            <div
              class="menu-icon-item animate__animated animate__flipInX"
              @click="$router.push('/caseList')"
            >
              <div class="icon">
                <img :src="require('../assets/klc.png')" />
              </div>
              <h3>教学辅助系统</h3>
            </div>
            <div class="menu-icon-item animate__animated animate__flipInX">
              <div class="icon">
                <img :src="require('../assets/ylc.png')" />
              </div>
              <h3>作业管理平台</h3>
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      menuList: [
        {
          img: require("../assets/clc.png"),
          name: "查流程",
          desc: "流程列表界面",
          path: "/caseList",
        },
        {
          img: require("../assets/klc.png"),
          name: "看流程",
          desc: "模板管理界面",
          path: "/templateList",
        },
        {
          img: require("../assets/ylc.png"),
          name: "用流程",
          desc: "新增流程界面",
          path: "/createCase",
        },
      ],
    };
  },
  methods: {},
};
</script>
<style lang="scss" scoped>
@import "../../node_modules/animate.css/animate.css";
.home {
  height: 100vh;
  background-image: url("../assets/home_bg.png");
  background-repeat: no-repeat;
  background-size: 150% 150%;
  background-position: center;
  animation: bg 60s infinite;
  text-align: center;
  .el-main {
    height: calc(100vh - 100px);
    display: flex;
    justify-content: center;
    align-items: center;
    .menu-box {
      width: 1000px;
      height: 400px;
      margin-top: -150px;
      .text {
        color: #ffffff;
        h2 {
          font-size: 63px;
          letter-spacing: 14px;
          margin: 0;
        }
        h3 {
          font-size: 26px;
          letter-spacing: 4px;
          font-weight: normal;
          margin: 20px;
        }
      }
      .menu-icon {
        display: flex;
        justify-content: space-evenly;
        padding-top: 120px;
        .menu-icon-item {
          text-align: center;
          cursor: pointer;
          .icon {
            img {
              width: 95px;
              height: 95px;
            }
          }
          h3 {
            color: #ffffff;
            font-size: 32px;
            letter-spacing: 4px;
            font-weight: normal;
            margin: 30px 0 20px;
          }
          h4 {
            color: #a4a6b0;
            font-size: 24px;
            font-weight: normal;
            margin: 0;
          }
        }
      }
    }
  }
  .el-header {
    display: flex;
    align-items: center;
    justify-content: center;

    .logo {
      width: 250px;
    }
    .menu {
      width: 800px;
      display: flex;
      justify-content: space-evenly;
      .menu-item {
        height: 40px;
        color: #ffffff;
        font-size: 24px;
        line-height: 40px;
        cursor: pointer;
        &.active {
          font-size: 30px;
        }
      }
    }
  }
}
@keyframes bg {
  0% {
    background-size: 150% 150%;
  }
  20% {
    background-size: 100% 100%;
  }
  80% {
    background-size: 100% 100%;
  }
  100% {
    background-size: 150% 150%;
  }
}
</style>