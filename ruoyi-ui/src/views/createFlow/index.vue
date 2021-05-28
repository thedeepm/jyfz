<template>
  <div class="main-contianer">
    <div class="title">新增流程</div>
    <el-form
      :model="form"
      status-icon
      :rules="rules"
      ref="form"
      label-width="100px"
      label-position="left"
      class="form"
    >
      <el-form-item label="任务文件" prop="fileId">
        <FileUpload v-model="form.fileId" />
      </el-form-item>
      <!-- <el-form-item label="步骤数量" prop="stepNumber">
        <el-input-number
          v-model="form.stepNumber"
          @change="stepsCountChange"
          :min="1"
          :step="1"
          size="small"
        ></el-input-number>
      </el-form-item> -->

      <div class="step-contianer">
        <div
          class="step-item"
          v-for="(item, index) in form.stepsList"
          :key="index"
        >
          <div class="step-name">
            <span>步骤{{ index + 1 }}</span>
          </div>
          <el-form-item
            label="任务名称"
            :prop="'stepsList.' + index + '.taskName'"
            :rules="{
              required: true,
              message: '任务名称不能为空',
              trigger: 'blur',
            }"
          >
            <el-input
              disabled
              v-model="item.eduTask.taskName"
              placeholder="输入任务名称"
            ></el-input>
          </el-form-item>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-form-item label="具体位置">
                <el-col :span="6" style="padding-left: 0">
                  <el-form-item
                    label-width="0"
                    :prop="'stepsList.' + index + '.partitionId'"
                    :rules="{
                      required: true,
                      message: '请选择分区',
                      trigger: 'change',
                    }"
                  >
                    <!-- <el-select
                      v-model="item.partitionId"
                      placeholder="选择分区"
                      filterable
                      @change="getGroup(item)"
                      disabled
                    >
                      <el-option
                        v-for="(item, index) in partitionOptions"
                        :key="index"
                        :label="item.partitionName"
                        :value="item.id"
                      />
                    </el-select>  -->
                    <el-input
                      disabled
                      v-model="item.partition.partitionName"
                    ></el-input> </el-form-item
                ></el-col>
                <el-col :span="6">
                  <el-form-item
                    label-width="0"
                    :prop="'stepsList.' + index + '.groupId'"
                    :rules="{
                      required: true,
                      message: '请选择分组',
                      trigger: 'change',
                    }"
                  >
                    <!-- <el-select
                      v-model="item.groupId"
                      placeholder="选择分组"
                      @change="getSeat(item)"
                      disabled
                    >
                      <el-option
                        v-for="(item, index) in item.groupOptions"
                        :key="index"
                        :label="item.groupName"
                        :value="item.id"
                    /></el-select> -->
                    <el-input
                      disabled
                      v-model="item.group.groupName"
                    ></el-input> </el-form-item
                ></el-col>
                <el-col :span="6">
                  <el-form-item
                    label-width="0"
                    :prop="'stepsList.' + index + '.seatId'"
                    :rules="{
                      required: true,
                      message: '请选择席位编号',
                      trigger: 'change',
                    }"
                  >
                    <!-- <el-select
                      v-model="item.seatId"
                      placeholder="选择席位编号"
                      disabled
                      ><el-option
                        v-for="(item, index) in item.seatOptions"
                        :key="index"
                        :label="item.seatName"
                        :value="item.id"
                    /></el-select> -->
                    <el-input
                      disabled
                      v-model="item.seat.id"
                    ></el-input> </el-form-item
                ></el-col>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item
                label="时间/时长"
                :prop="'stepsList.' + index + '.time'"
                :rules="{
                  required: true,
                  message: '时间不能为空',
                  trigger: 'blur',
                }"
              >
                <el-date-picker
                  v-model="item.eduTask.time"
                  type="datetimerange"
                  :start-placeholder="item.eduTask.startTime"
                  :end-placeholder="item.eduTask.endTime"
                  @change="timePicker(item)"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  disabled
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item
            label="预警提示"
            :prop="'stepsList.' + index + '.warningTime'"
            :rules="{
              required: true,
              message: '预警时间不能为空',
              trigger: 'blur',
            }"
          >
            <el-date-picker
              v-model="item.eduTask.warningTime"
              type="datetime"
              placeholder="预警时间"
              value-format="yyyy-MM-dd HH:mm:ss"
              disabled
            >
            </el-date-picker>
          </el-form-item>
          <div class="tips">
            输出文件名称规范示例：{{ new Date() | parseTime }}多功能作战{{
              index + 1
            }}.docx
          </div>
        </div>
      </div>
      <el-button type="success" @click="addStep">新增步骤</el-button>
    </el-form>

    <el-dialog title="新增步骤" :visible.sync="addShow" width="600px">
      <el-form
        :model="addForm"
        status-icon
        :rules="rules"
        ref="addForm"
        label-width="100px"
        label-position="left"
        class="form add"
      >
        <el-form-item
          label="任务名称"
          prop="taskName"
          :rules="{
            required: true,
            message: '任务名称不能为空',
            trigger: 'blur',
          }"
        >
          <el-input
            v-model="addForm.taskName"
            placeholder="输入任务名称"
          ></el-input>
        </el-form-item>
        <el-row :gutter="24">
          <el-col :span="24">
            <el-form-item label="具体位置">
              <el-col :span="7" style="padding-left: 0">
                <el-form-item
                  label-width="0"
                  prop="partitionId"
                  :rules="{
                    required: true,
                    message: '请选择分区',
                    trigger: 'change',
                  }"
                >
                  <el-select
                    v-model="addForm.partitionId"
                    placeholder="分区"
                    filterable
                    @change="getGroup(addForm)"
                  >
                    <el-option
                      v-for="(item, index) in partitionOptions"
                      :key="index"
                      :label="item.partitionName"
                      :value="item.id"
                    />
                  </el-select> </el-form-item
              ></el-col>
              <el-col :span="7">
                <el-form-item
                  label-width="0"
                  prop="groupId"
                  :rules="{
                    required: true,
                    message: '请选择分组',
                    trigger: 'change',
                  }"
                >
                  <el-select
                    v-model="addForm.groupId"
                    placeholder="分组"
                    @change="getSeat(addForm)"
                  >
                    <el-option
                      v-for="(item, index) in addForm.groupOptions"
                      :key="index"
                      :label="item.groupName"
                      :value="item.id"
                  /></el-select> </el-form-item
              ></el-col>
              <el-col :span="7">
                <el-form-item
                  label-width="0"
                  prop="seatId"
                  :rules="{
                    required: true,
                    message: '选择席位编号',
                    trigger: 'change',
                  }"
                >
                  <el-select v-model="addForm.seatId" placeholder="席位编号"
                    ><el-option
                      v-for="(item, index) in addForm.seatOptions"
                      :key="index"
                      :label="item.seatName"
                      :value="item.id"
                  /></el-select> </el-form-item
              ></el-col>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item
              label="时间/时长"
              prop="time"
              :rules="{
                required: true,
                message: '时间不能为空',
                trigger: 'blur',
              }"
            >
              <el-date-picker
                v-model="addForm.time"
                type="datetimerange"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                @change="timePicker(addForm)"
                value-format="yyyy-MM-dd HH:mm:ss"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item
          label="预警提示"
          prop="warningTime"
          :rules="{
            required: true,
            message: '预警时间不能为空',
            trigger: 'blur',
          }"
        >
          <el-date-picker
            v-model="addForm.warningTime"
            type="datetime"
            placeholder="预警时间"
            value-format="yyyy-MM-dd HH:mm:ss"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitStep">新增</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
import { parseTime } from "@/utils/ruoyi";
import {
  createFlow,
  getPartitionList,
  getGroupList,
  getSeatList,
  createTask,
  getFlow,
  getTask,
} from "@/api/jxfz/template";
import FileUpload from "@/components/FileUpload";
export default {
  props: {
    step: [String, Number],
    id: [String, Number],
    data: Object,
  },
  components: {
    FileUpload,
  },
  data() {
    return {
      form: {
        fileId: "",
        stepNumber: 1,
        stepsList: [
          // {
          //   taskName: "",
          //   partitionId: "",
          //   groupId: "",
          //   seatId: "",
          //   time: null,
          //   warningTime: null,
          //   startTime: "",
          //   endTime: "",
          // },
        ],
      },
      rules: {
        name: [
          { required: true, message: "请选择活动区域", trigger: "change" },
        ],
      },
      addShow: false,
      addForm: {
        taskName: "",
        partitionId: "",
        groupId: "",
        seatId: "",
        time: null,
        warningTime: null,
        startTime: "",
        endTime: "",
      },
      partitionOptions: [],
      groupOptions: [],
      seatOptions: [],
    };
  },
  filters: {
    parseTime(val) {
      return parseTime(val);
    },
  },
  created() {
    getPartitionList({ templateId: this.id }).then((res) => {
      this.partitionOptions = res.rows;
    });
  },
  mounted() {
    if (!this.data.flowId) {
      createFlow({ id: this.id }).then((res) => {
        this.data.flowId = res.data.id;
      });
    } else {
      getFlow(this.data.flowId).then((res) => {
        this.$set(this.form, "fileId", res.data.fileId);
      });
      getTask({ flowId: this.data.flowId }).then((res) => {
        this.$set(this.form, "stepsList", res.rows);
      });
    }
  },
  methods: {
    timePicker(val) {
      val.startTime = val.time[0];
      val.endTime = val.time[1];
    },
    getGroup(item) {
      getGroupList({ partitionId: item.partitionId }).then((res) => {
        item.groupOptions = res.rows;
        this.$forceUpdate();
      });
    },
    getSeat(item) {
      getSeatList({ groupId: item.groupId }).then((res) => {
        item.seatOptions = res.rows;
        this.$forceUpdate();
      });
    },
    addStep() {
      this.addShow = true;
      this.$refs.addForm.resetFields();
    },
    submitStep() {
      createTask({
        flowId: this.data.flowId,
        ...this.addForm,
        stepLevel: this.form.stepsList.length + 1,
      }).then((res) => {
        this.form.stepsList.push(res);
        this.addShow = false;
      });
    },
    stepsCountChange(val) {
      this.$set(this.form, "stepsList", []);

      for (let i = 1; i <= val; i++) {
        this.form.stepsList.push({
          taskName: "",
          partitionId: "",
          groupId: "",
          seatId: "",
          time: null,
          warningTime: null,
          startTime: "",
          endTime: "",
        });
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //发起ajax请求
          setTimeout(() => {
            this.$message({
              message: "新增流程成功",
              type: "success",
            });
            this.$emit("update:step", 3);
          }, 500);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    cancle() {
      this.$emit("update:step", 3);
    },
  },
};
</script>
<style lang="scss" scoped>
.main-contianer {
  padding: 15px 0px !important;
  .title {
    text-align: left;
    font-weight: bold;
    font-size: 14px;
    margin-bottom: 20px;
  }
  .form {
    width: 90%;
    text-align: left;
    &.add {
      width: 100%;
    }
    .el-input {
      width: 400px;
    }
  }
  .step-contianer {
    width: 100%;
    .tips {
      color: #aaadb4;
      font-size: 14px;
    }
    h4 {
      margin: 10px 0;
      color: #484e5c;
    }

    .step-item {
      background: #fafafa;
      padding: 20px;
      margin-bottom: 20px;

      .step-name {
        font-weight: bold;
        font-size: 14px;
        margin-bottom: 20px;
      }
      .el-input {
        max-width: 100%;
      }
    }
  }
}
</style>