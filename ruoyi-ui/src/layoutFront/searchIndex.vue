<template>
  <div class="app-main">
    <el-container>
      <el-header height="70px"><top-bar /> </el-header>
      <el-main>
        <div class="main">
          <router-view />
        </div>
      </el-main>
      <el-footer height="110px"
        >&copy; Copyright 2007~2020, 国防科技大学</el-footer
      >
    </el-container>
  </div>
</template>
<script>
import topBar from "./components/topBar.vue";
import breadcrumb from "@/components/Breadcrumb/index2";
export default {
  name: "Layout",
  components: {
    topBar,
    breadcrumb,
  },
  created() {},
};
</script>
<style lang="scss" scoped>
.app-main {
  text-align: center;
  min-width: 880px;
}
.el-header {
  padding: 0;
  width: 100%;
  position: fixed;
  z-index: 99;
}
.el-main {
  min-height: calc(100vh - 180px);
  margin-top: 70px;
  background: #f5f6f8;
  padding: 0;
  .main {
    .page-contianer {
    }
  }
}
.el-footer {
  height: 110px;
  line-height: 110px;
  box-sizing: border-box;
  border-top: 1px solid #e5e5e5;
  color: #8b8b8b;
  background: #ededed;
}
</style>
