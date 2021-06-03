<template>
  <div class="home">
    <el-container>
      <el-header height="100px" class="animate__animated animate__fadeInDown">
        <img
          class="logo"
          src="../../assets/logo-title.png"
          @click="$router.push('/home')"
        />
        <div class="menu">
          <div class="menu-icon">
            <el-input
              placeholder="请输入内容"
              v-model="queryParams.keyword"
              class="input-with-select animate__animated animate__flipInX"
              @keyup.native.enter="getList"
            >
              <el-select v-model="type" slot="prepend" popper-class="gis-type">
                <el-option label="全部" value="searchAll"></el-option>
                <el-option label="海洋" value="sea"></el-option>
                <el-option label="国家" value="country"></el-option>
                <el-option label="港口" value="port"></el-option>
                <el-option label="省份" value="province"></el-option>
                <el-option label="城市" value="city"></el-option>
              </el-select>
              <div class="icon" slot="append" @click="getList">
                <img :src="require('../../assets/clc.png')" />
              </div>
            </el-input>
          </div>
        </div>
      </el-header>
      <el-main>
        <el-scrollbar class="left-scrollbar">
          <div class="menu-box">
            <div
              class="item animate__animated animate__zoomInUp"
              v-for="item in list"
              :key="item.id"
            >
              <div class="info">
                <h4>{{ item.chineseName }}({{ item.foreignName }})</h4>
                <p v-html="propertiesFormatter(item.properties)"></p>
              </div>
              <div class="img">
                <el-image
                  style="width: 160px; height: 160px"
                  :src="url"
                  :preview-src-list="srcList"
                >
                </el-image>
              </div>
            </div>
          </div>
          <div
            v-if="list.length == 0"
            class="empty animate__animated animate__flipInX"
          >
            <div class="icon">
              <i class="el-icon-document" style="font-size: 150px"></i>
            </div>
            <h3>暂无相关结果</h3>
          </div>
        </el-scrollbar>
        <pagination
          layout="total, prev, pager, next"
          v-show="total > 0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { search } from "@/api/gis";
export default {
  name: "Home",
  data() {
    return {
      type: "searchAll",
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        keyword: "",
      },
      total: 0,
      list: [],
      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      srcList: [
        "https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg",
        "https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg",
      ],
    };
  },
  created() {
    this.queryParams.keyword =
      this.$route.params.keyword ?? this.queryParams.keyword;
    this.type = this.$route.params.type ?? this.type;
  },
  mounted() {
    this.getList();
  },
  //   filters: {
  //     propertiesFormatter(val) {
  //       if (val) return val.replace(/--/g, "<br/>");
  //       return val;
  //     },
  //   },
  methods: {
    propertiesFormatter(val) {
      if (val) return val.replace(/--/g, "<br/>");
      return val;
    },
    getList() {
      const loading = this.$loading({
        lock: true,
        text: "搜索中",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
        customClass: "gis-loading",
      });
      search(this.type, this.queryParams).then((res) => {
        this.total = res.total;
        this.list = res.rows;
        loading.close();
      });
    },
  },
};
</script>
<style lang="scss" scoped>
@import "../../../node_modules/animate.css/animate.css";
.home {
  height: 100vh;
  background-image: url("../../assets/home_bg.png");
  background-repeat: no-repeat;
  background-size: 150% 150%;
  background-position: center;
  animation: bg 60s infinite;
  text-align: center;
  .menu-icon {
    display: flex;
    justify-content: flex-end;
    ::v-deep .el-input__inner {
      height: 40px;
      font-size: 22px;
      line-height: 40px;
      color: #ffffff;
      //   border-color: #2098d1;
      border-color: #ffffff;
      background: transparent;
    }
    ::v-deep .el-input-group__append {
      cursor: pointer;
      //   background: #020c40;
      background: transparent;
      border-color: #ffffff;
      //   border-color: #2098d1;
      padding: 0;
    }
    ::v-deep .el-input-group__prepend {
      background: transparent;
    }
    .el-select {
      width: 100px;
      background: transparent;
    }
    .icon {
      padding: 5px 20px;
      img {
        width: 25px;
        height: 25px;
      }
    }
  }
  .el-main {
    height: calc(100vh - 100px);
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 0 20px;

    .left-scrollbar {
      max-width: 1000px;
      width: 100%;
      height: calc(100% - 100px);
      overflow: hidden;
      ::v-deep .el-scrollbar__wrap {
        overflow-x: hidden;
      }
    }
    .menu-box {
      .item {
        width: 100%;
        min-height: 100px;
        background: rgba($color: #030a39, $alpha: 0.6);
        border: 1px solid #ffffff;
        border-radius: 5px;
        margin-bottom: 15px;
        display: flex;
        justify-content: space-between;
        color: #ffffff;
        box-shadow: 1px 1px 5px #ccc inset;
        .info {
          flex: 1;
          text-align: left;
          padding: 10px 20px;
          h4 {
            font-size: 22px;
            margin: 10px 0;
          }
          p {
            line-height: 26px;
            margin: 0;
          }
        }
        .img {
          min-width: 100px;
          padding: 10px;
          //   display: flex;
          //   align-items: center;
        }
      }
    }
  }
  .el-header {
    display: flex;
    align-items: center;
    justify-content: center;

    .logo {
      width: 250px;
    }
    .menu {
      width: 800px;
      display: flex;
      justify-content: flex-end;
      align-items: center;
      .menu-item {
        height: 40px;
        color: #ffffff;
        font-size: 24px;
        line-height: 40px;
        cursor: pointer;
        &.active {
          font-size: 30px;
        }
      }
    }
  }
}
.pagination-container {
  width: 100%;
  background: transparent;
  ::v-deep .el-pagination {
    left: 50%;
    transform: translateX(-50%);
    .el-pagination__total {
      color: #ffffff;
    }
  }
}
.empty {
  margin-top: 200px;
  color: #ffffff;
}
@keyframes bg {
  0% {
    background-size: 150% 150%;
  }
  20% {
    background-size: 100% 100%;
  }
  80% {
    background-size: 100% 100%;
  }
  100% {
    background-size: 150% 150%;
  }
}
</style>
<style lang="scss">
.gis-loading {
  font-size: 60px;
  .el-loading-text {
    font-size: 22px;
  }
}
.gis-type {
  background: rgba($color: #030a39, $alpha: 1);
  .el-select-dropdown__item {
    color: #ffffff;
    &.hover {
      background: rgba($color: #1d2974, $alpha: 1);
    }
    &.selected {
      color: #1890ff;
    }
  }
}
</style>