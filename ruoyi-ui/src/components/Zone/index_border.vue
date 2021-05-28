<template>
  <div class="home" :style="scale" ref="home">
    <div
      v-for="(value, index) in cells"
      :key="index"
      class="cell"
      :style="margin(value, index)"
      @mouseenter="show(value, true)"
      @mouseleave="show(value, false)"
    >
      <!-- <input
        type="text"
        v-model="value.group"
        v-show="value.show"
        @mouseenter="show(value, true)"
      /> -->
      <div class="hide" v-if="value.hide" />
    </div>
  </div>
</template>

<script>
import cells from "./cells.json";
export default {
  name: "Home",
  data() {
    return {
      cells: cells,
      width: 400,
    };
  },
  computed: {
    scale() {
      let x = this.width / 400;
      return `transform: scale(${x});height:${this.width}px`;
    },
  },
  mounted() {
    this.width = this.$refs.home.parentElement.clientWidth;
    window.onresize = () => {
      this.width = this.$refs.home.parentElement.clientWidth;
    };
  },
  methods: {
    margin(value, index) {
      let top = "4px";
      let right = "4px";
      let left = "4px";
      let bottom = "4px";
      let topMargin = "0";
      let rightMargin = "0";
      let leftMargin = "0";
      let bottomMargin = "0";
      this.cells[index].hide = false;
      if (index < this.cells.length - 1 && (index + 1) % 8 != 0) {
        if (this.cells[index].group !== this.cells[index + 1].group) {
          right = "0";
          rightMargin = "4px";
        }
      }
      if (index + 8 < 64) {
        if (this.cells[index].group !== this.cells[index + 8].group) {
          bottom = "0";
          bottomMargin = "4px";
          this.cells[index].hide = true;
        }
      }

      return `border-width:${top} ${right} ${bottom} ${left};margin:${topMargin} ${rightMargin} ${bottomMargin} ${leftMargin};`;
    },
    show(value, val) {
      value.show = val;
      this.$forceUpdate();
      console.log(value);
    },
  },
};
</script>
<style lang="scss" scoped>
.home {
  width: 400px;
  display: flex;
  // justify-content: space-between;
  flex-direction: row;
  flex-wrap: wrap;
  transform-origin: left top;

  .cell {
    width: 42px;
    height: 42px;
    background: #e5e6e8;
    // box-sizing: border-box;
    border: 4px solid #e5e6e8;
    line-height: 50px;
    position: relative;
    input {
      width: 20px;
    }
    .hide {
      width: 4px;
      height: 4px;
      position: absolute;
      background: white;
      left: -8px;
      bottom: -4px;
    }
  }
}
</style>