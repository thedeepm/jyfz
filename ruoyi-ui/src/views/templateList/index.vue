<template>
  <div class="main-contianer">
    <div class="operation">
      <div class="left">
        <el-button
          type="primary"
          size="small"
          @click="$router.push('/createTemplate')"
          >新增模板</el-button
        >
        <el-button type="success" size="small" @click="inputTemplateShow = true"
          >导入模板</el-button
        >
      </div>
    </div>
    <div class="templateList">
      <div
        class="item"
        v-for="(item, index) in list"
        :key="index"
        :style="{ margin: margin + 'px' }"
      >
        <div class="templateItem">
          <div class="template">
            <Zone
              :width="width"
              :cells="JSON.parse(item.tbc1)"
              v-if="item.tbc1"
            />
            <Zone :width="width" v-if="!item.tbc1" />
          </div>
        </div>
        <div class="info">
          <div class="left">{{ item.hallName }}</div>
          <div class="right">
            <el-button
              type="text"
              icon="el-icon-edit-outline"
              @click="edit(item.id)"
              >编辑</el-button
            >
            <el-button
              type="text"
              icon="el-icon-delete"
              @click="remove(item.id)"
              >删除</el-button
            >
          </div>
        </div>
      </div>
    </div>

    <pagination
      v-show="total > 0"
      :total="total"
      layout="total, prev, pager, next"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <el-dialog title="导入模板" :visible.sync="inputTemplateShow" width="400px">
      <el-upload
        class="upload-demo"
        drag
        action="https://jsonplaceholder.typicode.com/posts/"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">
          只能上传excel文件
          <el-button type="text" @click="downloadTemplate">模板下载</el-button>
        </div>
      </el-upload>
      <span slot="footer" class="dialog-footer">
        <el-button @click="inputTemplateShow = false">取 消</el-button>
        <el-button type="primary" @click="inputTemplateShow = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Zone from "@/components/Zone";
import { getTemplateList, removeTemplate } from "@/api/jxfz/template";
export default {
  components: {
    Zone,
  },
  data() {
    return {
      width: 400,
      margin: 0,
      inputTemplateShow: false,
      total: 0,
      list: [],
      queryParams: {
        pageNum: 1,
        pageSize: 6,
      },
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.width = document.querySelector(".templateList").clientWidth * 0.3 - 40;
    this.margin =
      (document.querySelector(".templateList").clientWidth * 0.1) / 6;
    window.onresize = () => {
      this.width =
        document.querySelector(".templateList").clientWidth * 0.3 - 40;
      this.margin =
        (document.querySelector(".templateList").clientWidth * 0.1) / 6;
    };
  },
  methods: {
    downloadTemplate() {
      window.open("./template.xlsx");
    },
    getList() {
      // const loading = this.$loading({
      //   lock: true,
      //   spinner: "el-icon-loading",
      //   background: "rgba(0, 0, 0, 0.7)",
      // });
      getTemplateList(this.queryParams).then((res) => {
        this.list = res.rows;
        this.total = res.total;
        // loading.close();
      });
    },
    edit(id) {
      this.$router.push({ path: "/updateTemplate", query: { id } });
    },
    remove(id) {
      this.$confirm("此操作将永久删除该模板, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          removeTemplate(id).then((res) => {
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
  .templateList {
    display: flex;
    justify-content: flex-start;
    flex-direction: row;
    flex-wrap: wrap;
    min-height: 350px;
    .item {
      width: 30%;
      &:hover {
        .templateItem {
          border-color: #1890fe;
        }
      }
    }
    .templateItem {
      width: 100%;
      padding: 20px;
      box-sizing: border-box;
      border: 1px solid #e5e5e5;
      transition: all 0.5s;
      .template {
        width: 100%;
        height: 100%;
      }
    }
    .info {
      height: 40px;
      padding: 0 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
    }
  }
  .pagination-container ::v-deep .el-pagination {
    position: relative;
    top: -10px;
  }
}
</style>