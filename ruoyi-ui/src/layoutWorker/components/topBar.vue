<template>
  <div class="contianer">
    <div class="main">
      <img
        class="logo"
        :src="require('@/assets/logo-title.png')"
        @click="$router.push('/home')"
      />
      <div class="operation">
        <div class="left-menu"></div>

        <div class="right-tools">
          <!-- <search /> -->
          <i class="el-icon-bell icon"></i>
          <!-- <i class="el-icon-plus icon"></i> -->
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              <el-avatar icon="el-icon-user-solid" :size="28"></el-avatar>
              <i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="logOut"
                >退出系统</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import search from "./search";
import store from "@/store";

export default {
  name: "topBar",
  components: {
    search,
  },
  methods: {
    logOut() {
      store.dispatch("LogOut").then(() => {
        this.$router.push({ path: "/" });
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