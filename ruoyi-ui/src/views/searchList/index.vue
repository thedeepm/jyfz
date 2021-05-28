<template>
  <div class="search-page">
    <header>
      <div class="head-contianer">
        <el-input
          class="search-input"
          placeholder="输入关键词搜索席位/流程图"
          v-model="value"
          @keyup.enter.native="getList"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getList"
          ></el-button>
        </el-input>

        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="tabChange"
        >
          <el-menu-item index="">全部（{{ total }}）</el-menu-item>
          <el-menu-item index="flowRefrence">人员信息（15）</el-menu-item>
          <el-menu-item index="seat">流程图（6）</el-menu-item>
        </el-menu>
      </div>
    </header>
    <main>
      <div class="result-list">
        <div class="result" v-for="(item, index) in list" :key="index">
          <el-image
            @click.native="openDetails"
            :class="item.type ? 'flow' : ''"
            :src="item.img"
            fit="cover"
          ></el-image>
          <div class="content">
            <div class="title">
              <h4 @click="openDetails">{{ item.name }}</h4>
              <div class="tag" v-if="item.position">{{ item.position }}</div>
              <div class="tag" v-if="item.level">{{ item.level }}</div>
            </div>
            <p class="text">简介：{{ item.desc }}</p>
            <p class="text">其他信息：{{ item.other }}</p>
          </div>
        </div>
        <div class="pagination">
          <pagination
            v-show="total > 0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import { searchList } from "@/api/jxfz/search";
export default {
  data() {
    return {
      activeIndex: "",
      category: "",
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      total: 0,
      list: [],
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
  mounted() {
    this.getList();
  },
  methods: {
    tabChange(index) {
      this.category = index;
      this.getList();
    },
    handleCurrentChange() {},
    getList() {
      // const loading = this.$loading({
      //   lock: true,
      //   spinner: "el-icon-loading",
      //   background: "rgba(0, 0, 0, 0.7)",
      // });
      searchList({
        keyword: this.value,
        category: this.category,
        ...this.queryParams,
      }).then((res) => {
        this.total = res.total;
        this.list = res.rows;
        // loading.close();
      });
    },
    openDetails() {
      this.$router.push("/search/details");
    },
  },
};
</script>

<style lang="scss" scoped>
.search-page {
}
header {
  background: #eef1f6;
  .head-contianer {
    height: 160px;
    margin: 0 auto;
    max-width: 1200px;
    padding: 0 10px;
    box-sizing: border-box;
    text-align: left;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
    .search-input {
      max-width: 500px;
      margin-top: 40px;
    }
    ::v-deep .el-input__inner {
      border-radius: 0;
      box-shadow: 1px 1px 10px #ceceda;
    }
    ::v-deep .el-input-group__append {
      width: 90px;
      border-radius: 0;
      color: #ffffff;
      font-size: 24px;
      background: #1890ff;
      box-sizing: border-box;
      text-align: center;
      box-shadow: 1px 1px 10px #ceceda;
    }
    .el-menu {
      background: #eef1f6;
      border-bottom: none;
    }
    .el-menu--horizontal > .el-menu-item {
      height: 40px;
      line-height: 40px;
    }
    .el-menu--horizontal > .el-menu-item:not(.is-disabled):focus,
    .el-menu--horizontal > .el-menu-item:not(.is-disabled):hover,
    .el-menu--horizontal > .el-submenu .el-submenu__title:hover {
      background-color: #eef1f6;
    }
  }
}
main {
  min-height: calc(100vh - 340px);
  background: #ffffff;
  .result-list {
    margin: 0 auto;
    max-width: 1200px;
    .result {
      height: 125px;
      border-bottom: 1px solid #eaeaea;
      display: flex;
      align-items: center;
      padding: 20px;
      box-sizing: border-box;
      .el-image {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        &.flow {
          border-radius: 5px;
        }
      }
      .content {
        flex: 1;
        padding: 0 20px;
        text-align: left;
        .title {
          display: flex;
          align-items: center;
          padding-bottom: 10px;
        }
        h4 {
          margin: 0 15px 0 0;
          cursor: pointer;
          transition: all 0.2s;
          &:hover {
            color: #0088fe;
          }
        }
        .tag {
          height: 22px;
          padding: 0 12px;
          line-height: 22px;
          font-size: 14px;
          color: #0088fe;
          background: #cfe9ff;
          text-align: center;
          border-radius: 12px;
          display: inline-block;
          margin-right: 15px;
        }
        .text {
          margin: 0;
          color: #9b9b9b;
          font-size: 12px;
          &:first-of-type {
            margin-bottom: 10px;
          }
        }
      }
    }
  }
  .pagination {
    padding: 30px 0;
    text-align: left;
  }
}
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 2px solid #1890ff;
  color: #303133;
}
</style>