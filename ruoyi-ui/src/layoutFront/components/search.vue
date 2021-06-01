<template>
  <div class="search-contianer">
    <el-input
      v-show="show"
      v-model="value"
      style="width: 250px"
      placeholder="输入关键词搜索席位/流程图"
      suffix-icon="el-icon-search"
      size="small"
      @keyup.enter.native="search"
    />
    <div class="search-btn">
      <i v-if="!show" class="el-icon-search icon" @click="show = true"></i>
      <i v-if="show" class="el-icon-close icon" @click="show = false"></i>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      show: false,
    };
  },
  computed: {
    value: {
      get() {
        return this.$store.state.app.keyword;
      },
      set(v) {
        this.$store.commit("app/KEYWORD", v);
      },
    },
  },
  methods: {
    search() {
      this.$router.push("/search").catch((err) => err);
    },
  },
};
</script>

<style scoped>
.search-contianer {
  display: flex;
  align-items: center;
}
.search-btn {
  margin-left: 20px;
}
.icon {
  color: #252b3a;
  font-size: 26px;
  cursor: pointer;
  margin-right: 30px;
}
::v-deep .el-input__inner {
  border-radius: 0;
  box-shadow: 1px 1px 10px #ededef;
}
</style>