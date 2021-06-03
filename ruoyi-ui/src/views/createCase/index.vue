<template>
  <div class="main-contianer">
    <el-form
      :model="form"
      status-icon
      :rules="rules"
      ref="form"
      label-width="80px"
      label-position="left"
      class="form"
    >
      <el-form-item label="案例名称" prop="caseName">
        <el-input
          v-model="form.caseName"
          placeholder="请输入案例名称"
          maxlength="15"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="案例类型" prop="type">
        <el-select placeholder="请选择案例类型" v-model="form.type">
          <el-option value="类型A" />
        </el-select>
      </el-form-item>
      <el-form-item label="展厅模板" prop="templateId">
        <el-select
          placeholder="请选择展厅模板"
          v-model="form.templateId"
          filterable
        >
          <el-option
            v-for="(item, index) in templateOptions"
            :value="item.id"
            :label="item.hallName"
            :key="index"
          />
        </el-select>
        <el-button
          type="text"
          icon="el-icon-info"
          @click="$router.push('/templateList')"
          style="margin-left: 20px"
          >模板管理</el-button
        >
      </el-form-item>
      <el-form-item label="案例说明" prop="description">
        <el-input
          type="textarea"
          v-model.number="form.description"
          maxlength="100"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import { createCase, updateCase, getCase } from "@/api/jxfz/case";
import { getTemplateList } from "@/api/jxfz/template";

export default {
  data() {
    return {
      form: {
        caseName: "",
        type: "",
        templateId: "",
        description: "",
      },
      templateOptions: [],
      rules: {
        caseName: [
          { required: true, message: "请选择活动区域", trigger: "change" },
        ],
        type: [
          { required: true, message: "请选择案例类型", trigger: "change" },
        ],
        templateId: [
          { required: true, message: "请选择展厅模板", trigger: "change" },
        ],
      },
    };
  },
  created() {
    if (this.$route.query.id) {
      getCase(this.$route.query.id).then((res) => {
        this.form = res.data;
      });
    }
    getTemplateList({ occupied: true }).then((res) => {
      this.templateOptions = res.rows;
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.form.id) {
            updateCase(this.form).then((res) => {
              this.$message({
                message: "修改案例成功",
                type: "success",
              });
              this.$router.push("/caseList");
            });
          } else {
            createCase(this.form).then((res) => {
              this.$message({
                message: "新增案例成功",
                type: "success",
              });
              this.$router.push("/caseList");
            });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.main-contianer {
  padding: 15px 30px;
  .form {
    width: 600px;
    text-align: left;
    .el-input,
    .el-select {
      width: 380px;
    }
  }
}
</style>