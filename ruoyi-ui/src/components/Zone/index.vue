<template>
  <div class="home" :style="homeStyle" ref="home">
    <div
      v-for="(value, index) in cells"
      :key="index"
      class="cell"
      :style="style(value, index)"
      @click="getGroup(value)"
    >
      <el-tooltip
        class="item"
        v-if="mode == 'edit'"
        effect="light"
        :enterable="false"
        :content="`${value.groupName ? value.groupName + '：' : ''} ${
          index + 1
        }`"
        placement="top"
        ><site
          :style="siteStyle"
          :width="(width * 25) / 400 + 'px'"
          :color="value.siteColor"
      /></el-tooltip>
      <template v-else>
        <el-badge
          is-dot
          v-if="index == activeSeat.groupIndex && activeSeat.groupIndex != null"
          :class="activeSeat.warning ? 'warning' : ''"
        >
          <site
            :style="siteStyle"
            :width="(width * 25) / 400 + 'px'"
            :color="value.siteColor"
          />
        </el-badge>
        <site
          v-else
          :style="siteStyle"
          :width="(width * 25) / 400 + 'px'"
          :color="value.siteColor"
        />
      </template>
      <div class="hide" v-if="value.hide" :style="hideStyle" />
    </div>
  </div>
</template>

<script>
import cells from "./cells.json";
import site from "./site.vue";
export default {
  name: "cells",
  components: {
    site,
  },
  props: {
    width: {
      type: Number,
      default: 400,
    },
    cells: {
      type: Array,
      default() {
        return cells;
      },
    },
    mode: {
      type: String,
      default: "view", //edit 编辑模式 显示tooltips
    },
    activeSeat: {
      type: Object,
      default() {
        return { warning: false, groupIndex: null };
      },
    },
  },
  data() {
    return {};
  },

  computed: {
    homeStyle() {
      return `width: ${this.width}px;height:${this.width}px`;
    },
    cellStyle() {
      return `width: ${this.width / 8}px;height:${this.width / 8}px`;
    },
    hideStyle() {
      let w = (this.width * 5) / 400;
      return `width: ${w}px;height: ${w}px;left:-${w}px;bottom:-${w}px;`;
    },
    siteStyle() {
      let w = (this.width * 12) / 400;
      return `margin: ${w}px 0 0 ${w}px`;
    },
  },
  mounted() {},
  methods: {
    style(value, index) {
      let top = "0px";
      let right = "0px";
      let left = "0px";
      let bottom = "0px";
      this.cells[index].hide = false;

      if (index < this.cells.length - 1 && (index + 1) % 8 != 0) {
        if (this.cells[index].group !== this.cells[index + 1].group) {
          right = (this.width * 5) / 400 + "px";
        }
      }
      if (index + 8 < 64) {
        if (this.cells[index].group !== this.cells[index + 8].group) {
          bottom = (this.width * 5) / 400 + "px";
          if (this.cells[index - 1]?.group == this.cells[index + 7].group) {
            this.cells[index].hide = true;
          }
        }
      }

      return (
        `border-width:${top} ${right} ${bottom} ${left};background:${value.color};` +
        this.cellStyle
      );
    },
    show(value, val) {
      value.show = val;
      this.$forceUpdate();
      console.log(value);
    },
    getGroup(value) {
      this.$emit("getGroup", value.group);
    },
  },
};
</script>
<style lang="scss" scoped>
.home {
  width: 400px;
  display: flex;
  justify-content: flex-start;
  flex-direction: row;
  flex-wrap: wrap;
  transform-origin: left top;

  .cell {
    width: 50px;
    height: 50px;
    background: #e5e6e8;
    box-sizing: border-box;
    border-color: #fff;
    border-style: solid;
    // line-height: 50px;
    text-align: left;
    position: relative;
    // display: flex;
    // justify-content: center;
    // align-items: center;

    .hide {
      width: 5px;
      height: 5px;
      position: absolute;
      background: #ffffff;
      left: -5px;
      bottom: -5px;
    }
  }
}
::v-deep .el-badge__content.is-fixed.is-dot {
  top: 12px;
}
::v-deep .el-badge__content {
  background-color: #13ce66;
  animation: changeColor 2s infinite;
}
.warning ::v-deep .el-badge__content {
  background-color: red;
  animation: changeWarningColor 2s infinite;
}
@keyframes changeColor {
  0% {
    background-color: #13ce66;
  }
  50% {
    background-color: #dff0d9;
  }
  100% {
    background-color: #13ce66;
  }
}
@keyframes changeWarningColor {
  0% {
    background-color: red;
  }
  50% {
    background-color: #dff0d9;
  }
  100% {
    background-color: red;
  }
}
</style>