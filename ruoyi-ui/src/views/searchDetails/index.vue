<template>
  <div class="main">
    <div class="header">
      <div class="title">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: 'list' }"
            >搜索列表</el-breadcrumb-item
          >
          <el-breadcrumb-item>详情</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>
    <div class="page-contianer">
      <div class="info">
        <el-image
          style="width: 180px; height: 210px"
          :src="url"
          :preview-src-list="srcList"
          fit="cover"
        >
        </el-image>
        <div class="content">
          <h4>XXXXXX</h4>
          <div class="text">
            <span class="label">个人标签：</span
            ><el-tag size="small">XXXX</el-tag
            ><el-tag size="small">XXXX</el-tag>
            <el-tag size="small">XXXX</el-tag>
          </div>
          <div class="text"><span class="label">工作职务：</span>XXXXXXX</div>
          <div class="text"><span class="label">所属单位：</span>XXXXXXX</div>
          <div class="text"><span class="label">其他：</span>XXXXXXX</div>
        </div>
      </div>
      <div class="details">
        <div class="details-title">简介详情</div>
        <div class="details-content">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      url: require("@/assets/liuchen.png"),
      srcList: [require("@/assets/liuchen.png")],
    };
  },
};
</script>

<style lang="scss" scoped>
.main {
  max-width: 1200px;
  min-height: 600px;
  margin: 20px auto;
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
  }
  .info {
    padding: 20px;
    display: flex;
    .content {
      text-align: left;
      padding: 0 20px;
      h4 {
        margin-top: 0;
      }
      .text {
        margin-bottom: 18px;
        .label {
          width: 80px;
          text-align: right;
          display: inline-block;
        }
        &:last-of-type {
          margin-bottom: 0;
        }
        .el-tag {
          margin-right: 10px;
        }
      }
    }
  }
  .details {
    padding: 0 20px;
    text-align: left;

    .details-title {
      line-height: 40px;
      background: #f8f8f8;
      border-radius: 4px;
      padding: 0 20px;
    }
    .details-content {
      padding: 40px;
    }
  }
}
.el-breadcrumb {
  display: inline-block;
  font-size: 16px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    // color: #97a8be;
    cursor: text;
    color: #323232;
  }
}
</style>