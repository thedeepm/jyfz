<template>
  <div class="home">
    <el-container>
      <el-header height="100px" class="animate__animated animate__fadeInDown">
        <img
          class="logo"
          src="../../assets/logo-title.png"
          @click="$router.push('/home')"
        />
        <div class="menu"></div>
      </el-header>
      <el-main>
        <div class="menu-box">
          <div class="text animate__animated animate__fadeInUp">
            <h2>地理信息速查</h2>
          </div>
          <div class="menu-icon">
            <el-input
              v-model="keyword"
              class="input-with-select animate__animated animate__flipInX"
              @keyup.native.enter="getList"
            >
              <el-select v-model="type" slot="prepend" popper-class="gis-type">
                <el-option label="全部" value="searchAll"></el-option>
                <el-option label="海洋" value="sea"></el-option>
                <el-option label="国家" value="country"></el-option>
                <el-option label="港口" value="port"></el-option>
                <el-option label="省份" value="province"></el-option>
                <el-option label="城市" value="city"></el-option>
              </el-select>
              <div class="icon" slot="append" @click="getList">
                <img :src="require('../../assets/clc.png')" />
              </div>
            </el-input>
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
      keyword: "",
      type: "searchAll",
    };
  },
  methods: {
    getList() {
      this.$router.push({
        name: "GISList",
        params: { keyword: this.keyword, type: this.type },
      });
    },
  },
};
</script>
<style lang="scss" scoped>
@import "../../../node_modules/animate.css/animate.css";
.home {
  height: 100vh;
  background-image: url("../../assets/home_bg.png");
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
        ::v-deep .el-input__inner {
          height: 60px;
          font-size: 32px;
          line-height: 60px;
          color: #ffffff;
          //   border-color: #2098d1;
          border-color: #ffffff;
          background: transparent;
        }
        ::v-deep .el-input-group__append {
          cursor: pointer;
          //   background: #020c40;
          background: transparent;
          border-color: #ffffff;
          //   border-color: #2098d1;
          padding: 0;
        }
        ::v-deep .el-input-group__prepend {
          background: transparent;
        }
        .el-select {
          width: 150px;
          background: transparent;
        }
        .icon {
          padding: 5px 20px;
          img {
            width: 45px;
            height: 45px;
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
<style lang="scss">
.gis-type {
  background: rgba($color: #030a39, $alpha: 1);
  .el-select-dropdown__item {
    color: #ffffff;
    &.hover {
      background: rgba($color: #1d2974, $alpha: 1);
    }
    &.selected {
      color: #1890ff;
    }
  }
}
</style>