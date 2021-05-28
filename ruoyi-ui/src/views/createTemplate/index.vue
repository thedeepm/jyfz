<template>
  <div class="main-contianer">
    <template v-if="step == 1">
      <el-form
        :model="form"
        status-icon
        :rules="rules"
        ref="form"
        label-width="80px"
        label-position="left"
        class="form"
      >
        <el-form-item label="展厅名称" prop="hallName">
          <el-input
            v-model="form.hallName"
            placeholder="请输入展厅名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="总席位数" prop="totalSeats">
          <!-- <el-input
            v-model="form.totalSeats"
            placeholder="请输入席位数量"
          ></el-input> -->
          <el-input-number
            v-model="form.totalSeats"
            :step="1"
            step-strictly
            :min="0"
          ></el-input-number>
        </el-form-item>
        <div class="partition-contianer">
          <h4>分区设置</h4>
          <el-form-item label="分区数量" prop="partitionNumber">
            <!-- <el-select
              v-model="form.partitionNumber"
              placeholder="请选择分区数量"
              @change="partitionNumberChange"
            >
              <el-option :value="1" label="1个分区" />
              <el-option :value="2" label="2个分区" />
              <el-option :value="3" label="3个分区" />
            </el-select> -->
            <el-input-number
              v-model="form.partitionNumber"
              @change="partitionNumberChange"
              :step="1"
              step-strictly
              :min="0"
            ></el-input-number>
          </el-form-item>
          <div class="partition-list">
            <div
              class="partition-item"
              v-for="(item, index) in form.partitionsList"
              :key="index"
            >
              <div class="partition-name">
                <el-form-item
                  label-width="0"
                  :prop="'partitionsList.' + index + '.partitionName'"
                  :rules="{
                    required: true,
                    message: '分区名称不能为空',
                    trigger: 'blur',
                  }"
                  style="margin-bottom: 0"
                >
                  <el-input v-model="item.partitionName"></el-input>
                </el-form-item>
                <!-- <el-button type="text">修改名称</el-button> -->
              </div>
              <el-row :gutter="24">
                <el-col :span="10">
                  <el-form-item
                    label="分区座位"
                    :prop="'partitionsList.' + index + '.partitionInterval'"
                    :rules="{
                      required: true,
                      message: '分区座位不能为空',
                      trigger: 'blur',
                    }"
                  >
                    <el-input
                      v-model="item.partitionInterval"
                      placeholder="书写方式如：1-5,10-20"
                      @change="resetPartitions(item)"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item
                    label="席位数量"
                    :prop="'partitionsList.' + index + '.totalSeats'"
                    :rules="{
                      required: true,
                      message: '席位数量不能为空',
                      trigger: 'blur',
                    }"
                  >
                    <el-input-number
                      v-model="item.totalSeats"
                      :step="1"
                      step-strictly
                      :min="0"
                    ></el-input-number>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item
                    label="颜色选择"
                    :prop="'partitionsList.' + index + '.color'"
                    :rules="{
                      required: true,
                      message: '请选择颜色',
                      trigger: 'blur',
                    }"
                  >
                    <el-color-picker
                      v-model="item.color"
                      :predefine="predefineColors"
                    ></el-color-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item label="职能属性" prop="functionAttribute">
                <el-input
                  v-model="item.functionAttribute"
                  placeholder="请输入职能属性"
                ></el-input>
              </el-form-item>
            </div>
          </div>
        </div>
        <el-form-item>
          <el-button type="primary" @click="submitForm('form')"
            >下一步</el-button
          >
        </el-form-item>
      </el-form>
    </template>
    <template v-if="step == 2">
      <div class="group-setting">
        <div class="zone-contianer">
          <Zone
            :cells="cells"
            :width="400"
            v-if="zoneReload"
            @getGroup="getGroupId"
            mode="edit"
          />
        </div>
        <el-form
          :model="groupSelecter"
          status-icon
          :rules="rules"
          ref="groupsform"
          label-width="80px"
          label-position="left"
          class="form"
        >
          <div class="partition-contianer">
            <h4>{{ groupSelecter.partitionName }}-分组设置</h4>
            <el-form-item label="分组数量" prop="groupCount">
              <!-- <el-select
                v-model="groupSelecter.groupCount"
                placeholder="请选择分组数量"
                @change="groupsCountChange"
              >
                <el-option :value="1" label="1个分组" />
                <el-option :value="2" label="2个分组" />
                <el-option :value="3" label="3个分组" />
              </el-select> -->
              <el-input-number
                v-model="groupSelecter.groupCount"
                @change="groupsCountChange"
                :step="1"
                step-strictly
                :min="0"
              ></el-input-number>
            </el-form-item>
            <div class="partition-list">
              <div
                class="partition-item"
                v-for="(item, index) in groupSelecter.eduGroupList"
                :key="index"
              >
                <div class="partition-name">
                  <el-form-item
                    label-width="0"
                    :prop="'eduGroupList.' + index + '.groupName'"
                    :rules="{
                      required: true,
                      message: '分组名称不能为空',
                      trigger: 'blur',
                    }"
                    style="margin-bottom: 0"
                  >
                    <el-input v-model="item.groupName"></el-input>
                  </el-form-item>
                  <!-- <el-button type="text">修改名称</el-button> -->
                </div>
                <el-row :gutter="24">
                  <el-col :span="10">
                    <el-form-item
                      label="分组座位"
                      :prop="'eduGroupList.' + index + '.groupInterval'"
                      :rules="{
                        required: true,
                        message: '分组座位不能为空',
                        trigger: 'blur',
                      }"
                    >
                      <el-input
                        v-model="item.groupInterval"
                        placeholder="书写方式如：1-5,10-20"
                        @change="groupsParse(item)"
                      ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item
                      label="席位数量"
                      :prop="'eduGroupList.' + index + '.totalSeats'"
                      :rules="{
                        required: true,
                        message: '席位数量不能为空',
                        trigger: 'blur',
                      }"
                    >
                      <el-input-number
                        v-model="item.totalSeats"
                        :step="1"
                        step-strictly
                        :min="0"
                      ></el-input-number>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item
                      label="颜色选择"
                      :prop="'partitionsList.' + index + '.color'"
                      :rules="{
                        required: true,
                        message: '请选择颜色',
                        trigger: 'blur',
                      }"
                    >
                      <el-color-picker
                        v-model="item.color"
                        :predefine="predefineColors"
                        @change="groupsParse(item)"
                      ></el-color-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-form-item label="职能属性" prop="functionAttribute">
                  <el-input
                    v-model="item.functionAttribute"
                    placeholder="请输入职能属性"
                  ></el-input>
                </el-form-item>
              </div>
            </div>
          </div>
        </el-form>
        <el-button type="info" @click="step--">上一步</el-button>
        <el-button type="primary" @click="submitGroup">下一步</el-button>
      </div>
    </template>
    <!-- <template v-if="step == 3">
      <flowList :step.sync="step" />
      <div class="save-template">
        <el-button type="info" @click="step--">上一步</el-button>
        <el-button type="primary" @click="">保存模板</el-button>
      </div>
    </template> -->
    <template v-if="step == 3">
      <createFlow ref="flow" :step.sync="step" :id="form.id" :data="form" />
      <el-button type="info" @click="step--">上一步</el-button>
      <el-button type="primary" @click="saveTemplate">保存模板</el-button>
    </template>
  </div>
</template>


<script>
let cells = require("@/components/Zone/cells.json");
import Zone from "@/components/Zone";
import flowList from "@/views/flowList";
import createFlow from "@/views/createFlow";
import {
  createTemplate,
  createGroup,
  getGroupList,
  updateFlow,
  getTemplate,
  getPartitionList,
} from "@/api/jxfz/template";
export default {
  components: {
    Zone,
    flowList,
    createFlow,
  },
  data() {
    return {
      step: 1,
      form: {
        hallName: "",
        totalSeats: null,
        partitionNumber: null,
        partitionsList: [
          // {
          //   tbc1: "1",
          //   partitionName: "分区1",
          //   partitionInterval: "1-25",
          //   totalSeats: 10,
          //   color: "red",
          //   functionAttribute: "",
          // },
        ],
      },
      rules: {
        name: [
          { required: true, message: "请选择活动区域", trigger: "change" },
        ],
        totalSeats: [
          { required: true, message: "请输入席位数量", trigger: "change" },
        ],
        partitionNumber: [
          { required: true, message: "请选择分区数量", trigger: "change" },
        ],
      },
      groupSelecter: {},
      cells: JSON.parse(JSON.stringify(cells)),
      zoneReload: true,
      predefineColors: [
        "#ff4500",
        "#ff8c00",
        "#ffd700",
        "#90ee90",
        "#00ced1",
        "#1e90ff",
        "#c71585",
        "rgba(255, 69, 0, 0.68)",
        "rgb(255, 120, 0)",
        "hsv(51, 100, 98)",
        "hsva(120, 40, 94, 0.5)",
        "hsl(181, 100%, 37%)",
        "hsla(209, 100%, 56%, 0.73)",
        "#c7158577",
      ],
    };
  },
  created() {
    if (this.$route.query.id) {
      getTemplate(this.$route.query.id).then((res) => {
        this.$set(this, "form", res.data);
        getPartitionList({
          templateId: this.$route.query.id,
        }).then((res) => {
          this.$set(this.form, "partitionsList", res.rows);
        });
      });
    }
  },
  methods: {
    submitGroup() {
      createTemplate(
        { id: this.form.id, tbc1: JSON.stringify(this.cells) },
        this.form.id
      ).then((res) => {
        // this.form.partitionsList = res.eduPartitionList;
        // this.form.id = res.templateId;
        createGroup(this.form.partitionsList).then((res) => {
          this.step++;
        });
      });
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //发起ajax请求
          createTemplate(this.form, this.form.id).then((res) => {
            this.form.partitionsList = res.eduPartitionList;
            this.form.id = res.templateId;
            this.form.partitionsList.forEach((item) => {
              getGroupList({ partitionId: item.id }).then((res) => {
                this.$set(item, "eduGroupList", res.rows);
                this.$set(
                  item,
                  "groupCount",
                  res.rows.length ? res.rows.length : null
                );
                this.$forceUpdate();
              });
              this.partitionsParse(item);
            });
            this.$set(this, "groupSelecter", this.form.partitionsList[0]);
            this.step++;
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    partitionsParse(val) {
      //解析分区座位字符串
      let partitions = val.partitionInterval.split(",");
      if (partitions[0]) {
        partitions.forEach((item) => {
          let seat = item.split("-");
          if (seat.length == 2) {
            for (let i = Number(seat[0]); i <= Number(seat[1]); i++) {
              this.cells[i - 1] = {
                group: val.tbc1,
                groupName: val.groupName,
                color: val.color,
              };
            }
          } else if (seat.length == 1) {
            this.cells[Number(seat[0]) - 1] = {
              group: val.tbc1,
              groupName: val.groupName,
              color: val.color,
            };
          }
        });
      }
    },
    resetPartitions(val) {
      this.cells.filter((item) => {
        if (item.group == val.tbc1) {
          this.$set(item, "group", "");
          this.$set(item, "groupName", "");
          this.$set(item, "color", "");
          this.$set(item, "siteGroup", "");
          this.$set(item, "siteColor", "#ffffff");
        }
      });
    },
    groupsParse(val) {
      //解析分组座位字符串
      let groups = val.groupInterval.split(",");
      if (groups[0]) {
        this.zoneReload = false;
        this.cells.filter((item) => {
          if (item.siteGroup == val.tbc1) {
            this.$set(item, "siteGroup", "");
            this.$set(item, "siteColor", "#ffffff");
          }
        });
        groups.forEach((item) => {
          let seat = item.split("-");
          if (seat.length == 2) {
            for (let i = Number(seat[0]); i <= Number(seat[1]); i++) {
              this.$set(this.cells[i - 1], "siteGroup", val.tbc1);
              this.$set(this.cells[i - 1], "siteColor", val.color);
            }
          } else if (seat.length == 1) {
            this.$set(this.cells[Number(seat[0]) - 1], "siteGroup", val.tbc1);
            this.$set(this.cells[Number(seat[0]) - 1], "siteColor", val.color);
          }
        });
        this.$nextTick(() => {
          this.zoneReload = true;
        });
      }
    },
    partitionNumberChange(val) {
      this.$set(this.form, "partitionsList", []);

      for (let i = 1; i <= val; i++) {
        this.form.partitionsList.push({
          tbc1: i,
          partitionName: "分区" + i,
          partitionInterval: "",
          totalSeats: "",
          color: "",
          functionAttribute: "",
        });
      }
    },
    groupsCountChange(val) {
      this.$set(this.groupSelecter, "eduGroupList", []);
      this.zoneReload = false;
      this.cells.filter((item) => {
        if (this.groupSelecter.tbc1 == item.siteGroup) {
          this.$set(item, "siteGroup", "");
          this.$set(item, "siteColor", "#ffffff");
        }
      });
      this.$nextTick(() => {
        this.zoneReload = true;
      });
      for (let i = 1; i <= val; i++) {
        this.groupSelecter.eduGroupList.push({
          tbc1: this.groupSelecter.tbc1 + "-" + i,
          groupName: "分组" + i,
          groupInterval: "",
          totalSeats: "",
          color: "",
          functionAttribute: "",
        });
      }
    },
    getGroupId(id) {
      console.log(id);
      let index = this.form.partitionsList.findIndex((item) => item.tbc1 == id);
      if (index != -1) this.groupSelecter = this.form.partitionsList[index];
      console.log(index);
    },
    saveTemplate() {
      updateFlow({
        id: this.$refs.flow.data.flowId,
        // fileId: this.$refs.flow.form.fileId,
        fileId: 12,
      }).then((res) => {
        this.$message.success("新增模板成功");
        this.$router.push("/templateList");
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.main-contianer {
  padding: 15px 30px;
  .form {
    width: 100%;
    text-align: left;
    .el-input,
    .el-select {
      width: 380px;
    }
  }
  .partition-contianer {
    width: 100%;
    background: #fafafa;
    padding: 10px;
    margin-bottom: 20px;
    h4 {
      margin: 10px 0;
      color: #484e5c;
    }
    .partition-list {
      width: calc(100% - 80px);
      margin-left: 80px;
      padding: 20px;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 5px;
      background: #ffffff;
    }
    .partition-item {
      .partition-name {
        display: flex;
        justify-content: space-between;
        line-height: 40px;
        background: #f8f8f8;
        border-radius: 4px;
        // padding: 0 20px;
        margin-bottom: 20px;
        ::v-deep .el-input__inner {
          background-color: transparent;
          border: none;
        }
      }
      .el-input {
        width: 100%;
      }
    }
  }
  .group-setting {
    text-align: left;
  }
  .zone-contianer {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }
  .save-template {
    text-align: left;
  }
}
</style>