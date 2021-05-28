<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="简介" prop="briefIntroduction">
        <el-input
          v-model="queryParams.briefIntroduction"
          placeholder="请输入简介"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位信息" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入职位信息"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联席位id" prop="seatId">
        <el-input
          v-model="queryParams.seatId"
          placeholder="请输入关联席位id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联文件id" prop="fileId">
        <el-input
          v-model="queryParams.fileId"
          placeholder="请输入关联文件id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联文件id" prop="tbc1">
        <el-input
          v-model="queryParams.tbc1"
          placeholder="请输入关联文件id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联文件id" prop="tbc2">
        <el-input
          v-model="queryParams.tbc2"
          placeholder="请输入关联文件id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联文件id" prop="tbc3">
        <el-input
          v-model="queryParams.tbc3"
          placeholder="请输入关联文件id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联文件id" prop="tbc4">
        <el-input
          v-model="queryParams.tbc4"
          placeholder="请输入关联文件id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联文件id" prop="tbc5">
        <el-input
          v-model="queryParams.tbc5"
          placeholder="请输入关联文件id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['edu:info:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['edu:info:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['edu:info:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['edu:info:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="infoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="人员id" align="center" prop="id" />
      <el-table-column label="简介" align="center" prop="briefIntroduction" />
      <el-table-column label="职位信息" align="center" prop="position" />
      <el-table-column label="关联席位id" align="center" prop="seatId" />
      <el-table-column label="其他信息" align="center" prop="remark" />
      <el-table-column label="关联文件id" align="center" prop="fileId" />
      <el-table-column label="更新时间" align="center" prop="tbc1" />
      <el-table-column label="更新时间" align="center" prop="tbc2" />
      <el-table-column label="更新时间" align="center" prop="tbc3" />
      <el-table-column label="更新时间" align="center" prop="tbc4" />
      <el-table-column label="更新时间" align="center" prop="tbc5" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['edu:info:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['edu:info:remove']"
            >删除</el-button
          >
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

    <!-- 添加或修改人员信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="简介" prop="briefIntroduction">
          <el-input v-model="form.briefIntroduction" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="职位信息" prop="position">
          <el-input v-model="form.position" placeholder="请输入职位信息" />
        </el-form-item>
        <el-form-item label="关联席位id" prop="seatId">
          <el-input v-model="form.seatId" placeholder="请输入关联席位id" />
        </el-form-item>
        <el-form-item label="其他信息" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="关联文件id" prop="fileId">
          <el-input v-model="form.fileId" placeholder="请输入关联文件id" />
        </el-form-item>
        <el-form-item label="关联文件id" prop="tbc1">
          <el-input v-model="form.tbc1" placeholder="请输入关联文件id" />
        </el-form-item>
        <el-form-item label="关联文件id" prop="tbc2">
          <el-input v-model="form.tbc2" placeholder="请输入关联文件id" />
        </el-form-item>
        <el-form-item label="关联文件id" prop="tbc3">
          <el-input v-model="form.tbc3" placeholder="请输入关联文件id" />
        </el-form-item>
        <el-form-item label="关联文件id" prop="tbc4">
          <el-input v-model="form.tbc4" placeholder="请输入关联文件id" />
        </el-form-item>
        <el-form-item label="关联文件id" prop="tbc5">
          <el-input v-model="form.tbc5" placeholder="请输入关联文件id" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listInfo,
  getInfo,
  delInfo,
  addInfo,
  updateInfo,
  exportInfo,
} from "@/api/edu/info";

export default {
  name: "Info",
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 人员信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        briefIntroduction: null,
        position: null,
        seatId: null,
        fileId: null,
        tbc1: null,
        tbc2: null,
        tbc3: null,
        tbc4: null,
        tbc5: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询人员信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then((response) => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        briefIntroduction: null,
        position: null,
        seatId: null,
        remark: null,
        fileId: null,
        createTime: null,
        updateTime: null,
        tbc1: null,
        tbc2: null,
        tbc3: null,
        tbc4: null,
        tbc5: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加人员信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改人员信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then((response) => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除人员信息编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delInfo(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有人员信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportInfo(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        });
    },
  },
};
</script>
