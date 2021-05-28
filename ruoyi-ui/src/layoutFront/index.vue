<template>
  <div class="app-main">
    <el-container>
      <el-header height="70px"><top-bar /> </el-header>
      <el-main>
        <div class="main">
          <div class="header">
            <div class="title">
              <breadcrumb />
            </div>
            <div class="tools" v-if="$route.name == 'caseList'">
              <el-link icon="el-icon-info">模板使用说明</el-link>
              <el-button
                type="primary"
                plain
                size="small"
                class="template"
                @click="$router.push('/templateList')"
                >模板管理</el-button
              >
            </div>
          </div>
          <div class="page-contianer">
            <router-view />
          </div>
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
import breadcrumb from "@/components/Breadcrumb";
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
  .main {
    max-width: 1200px;
    min-height: 600px;
    margin: 0 auto;
    background: #ffffff;
    border-radius: 5px;
    box-shadow: 1px 1px 5px #ededef;
    .header {
      height: 50px;
      border-bottom: 1px solid #f0f0f0;
      box-sizing: border-box;
      display: flex;
      justify-content: space-between;
      .title {
        width: 300px;
        padding-left: 20px;
        text-align: left;
      }
      .tools {
        display: flex;
        align-items: center;
        padding-right: 20px;
        ::v-deep .el-icon-info {
          color: #36a1ff;
        }
        .template {
          margin-left: 25px;
          background: #ffffff;
          color: #1089fe;
          border-color: #1089fe;
        }
      }
    }
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
