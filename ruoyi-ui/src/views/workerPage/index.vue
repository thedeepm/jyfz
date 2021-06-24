<template>
  <div class="main-contianer">
    <div class="operation">
      <div class="left"></div>
      <div class="right">
        <el-input
          placeholder="演示流程名称、类型"
          size="small"
          v-model="queryParams.searchValue"
          @keyup.enter.native="getList"
        />
        <el-button type="primary" size="small" @click="getList">搜索</el-button>
      </div>
    </div>
    <el-table :data="tableData" v-loading="loading">
      <el-table-column prop="caseName" label="演示流程名称" align="center">
      </el-table-column>
      <el-table-column prop="type" label="演示流程类型" align="center">
      </el-table-column>
      <el-table-column prop="subarea" label="分区组信息" align="center">
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-video-play"
            @click="
              $router.push({
                path: '/process',
                query: { id: scope.row.id, templateId: scope.row.templateId },
              })
            "
            >开始</el-button
          >
          <el-button
            type="text"
            icon="el-icon-edit-outline"
            @click="edit(scope.row.id)"
            >编辑</el-button
          >
          <el-button
            type="text"
            icon="el-icon-delete"
            @click="remove(scope.row.id)"
            >删除</el-button
          >
          <!-- <el-button type="text">查看流程</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>


<script>
import { getCaseList, removeCase } from "@/api/jxfz/case";
export default {
  data() {
    return {
      loading: false,
      tableData: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        searchValue: "",
        type: "",
      },
      total: 0,
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      getCaseList(this.queryParams).then((res) => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    edit(id) {
      this.$router.push({ path: "/updateCase", query: { id } });
    },
    remove(id) {
      this.$confirm("此操作将永久删除该演示流程, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          removeCase(id).then((res) => {
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            this.getList();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>
<style lang="scss" scoped>
.main-contianer {
  padding: 15px 30px;
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
}
.el-pagination {
  margin-top: 20px;
}
</style>