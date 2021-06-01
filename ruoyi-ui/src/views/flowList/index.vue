<template>
  <div class="main-contianer">
    <div class="operation">
      <div class="left">流程管理</div>
      <div class="right">
        <el-button type="primary" size="small" @click="create"
          >新增流程</el-button
        >
      </div>
    </div>
    <el-table :data="tableData" v-loading="loading" size="small">
      <el-table-column prop="name" label="流程名称" align="center">
      </el-table-column>
      <el-table-column prop="step" label="审批步骤" align="center">
      </el-table-column>
      <el-table-column prop="time" width="150" label="创建时间" align="center">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" class="operation-btn" icon="el-icon-edit-outline">编辑</el-button>
          <el-button type="text" class="operation-btn" icon="el-icon-delete">删除</el-button>
          <el-button type="text" class="operation-btn" icon="el-icon-link">关联流程</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>


<script>
export default {
  props: {
    step: [String, Number],
  },
  data() {
    return {
      loading: false,
      tableData: [],
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    create() {
      this.$emit("update:step", 4);
    },
    getList() {
      this.loading = true;

      // 发起请求 ajax
      setTimeout(() => {
        this.tableData = [
          {
            step: "创建项目-部门初审-财务复审-审批完成",
            name: "测试流程1",
            time: "20210228 12:28",
          },
          {
            step: "创建项目-部门初审-参谋审批-财务复审-审批完成",
            name: "测试流程2",
            time: "20210228 12:28",
          },
        ];
        this.loading = false;
      }, 500);
    },
  },
};
</script>
<style lang="scss" scoped>
.main-contianer {
  padding: 15px 0px!important;
  .operation {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
    .left,
    .right {
      display: flex;
      .el-input {
        margin-right: 15px;
      }
    }
  }
  .operation-btn{
      color:#606266;
  }
}
</style>