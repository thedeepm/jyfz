<template>
  <div class="contianer">
    <div class="main">
      <img
        class="logo"
        :src="require('@/assets/logo-title.png')"
        @click="$router.push('/home')"
      />
      <div class="operation">
        <div class="left-menu">
          <el-menu router :default-active="activeMenu" mode="horizontal">
            <el-menu-item index="/caseList">演示流程</el-menu-item>
            <el-menu-item index="/templateList">模板管理</el-menu-item>
          </el-menu>
        </div>
        <div class="right-tools">
          <search />
          <i class="el-icon-bell icon"></i>
          <!-- <i class="el-icon-plus icon"></i> -->

          <el-dropdown trigger="click" v-if="$store.state.user.token">
            <span class="el-dropdown-link">
              <el-avatar icon="el-icon-user-solid" :size="28"></el-avatar>
              <i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="$router.push('/index')"
                >后台管理</el-dropdown-item
              >
              <el-dropdown-item
                icon="el-icon-circle-close"
                divided
                @click.native="logout"
                >退出系统</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
          <el-button type="text" v-else @click="$router.push('/login')"
            >登录</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import search from "./search";
export default {
  name: "topBar",
  components: {
    search,
  },
  computed: {
    // 默认激活的菜单
    activeMenu() {
      const path = this.$route.path;
      // debugger
      // let activePath = this.routers[0].path;
      // if (path.lastIndexOf("/") > 0) {
      //   const tmpPath = path.substring(1, path.length);
      //   activePath = "/" + tmpPath.substring(0, tmpPath.indexOf("/"));
      // } else if ("/index" == path || "" == path) {
      //   if (!this.isFrist) {
      //     this.isFrist = true;
      //   } else {
      //     activePath = "index";
      //   }
      // }
      // var routes = this.activeRoutes(activePath);
      // if (routes.length === 0) {
      //   activePath = this.currentIndex || this.routers[0].path;
      //   this.activeRoutes(activePath);
      // }
      return path;
    },
    async logout() {
      this.$confirm("确定注销并退出系统吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$store.dispatch("LogOut").then(() => {
          this.$router.push({ path: "/" });
        });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.contianer {
  width: 100%;
  min-width: 880px;
  height: 70px;
  background: #ffffff;
  box-shadow: 1px 1px 10px #ededef;
  .main {
    max-width: 1200px;
    height: 100%;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 10px;
  }
  .logo {
    width: 180px;
    cursor: pointer;
  }
  .operation {
    flex: 1;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 100px;
  }
  .right-tools {
    display: flex;
    align-items: center;
    .icon {
      color: #252b3a;
      font-size: 26px;
      cursor: pointer;
      margin-right: 30px;
    }
    ::v-deep .el-dropdown-selfdefine {
      display: flex;
      align-items: center;
    }
  }
}
.left-menu {
  .el-menu--horizontal > .el-menu-item {
    color: #252b3a;
    height: 70px;
    line-height: 70px;
    &.is-active {
      color: #409eff !important;
    }
  }
  .el-menu--horizontal > .el-menu-item.is-active {
    border-bottom: 2px solid #1890ff;
  }
}
</style>