<template>
  <div class="block">
    <span class="demonstration">带快捷选项</span>
    <el-date-picker
      v-model="dateTime"
      type="daterange"
      align="right"
      unlink-panels
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      :picker-options="pickerOptions"
    ></el-date-picker>

    <el-button type="primary" @click="getDate">查询</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      dateTime: ""
    };
  },

  methods: {
    getDate() {
      const params = {};

      params.startCreateTime = this.dateTime[0];
      params.startCreateTime.setHours(0);
      params.startCreateTime.setMinutes(0);
      params.startCreateTime.setSeconds(0);

      params.endCreateTime = this.dateTime[1];
      params.endCreateTime.setHours(23);
      params.endCreateTime.setMinutes(59);
      params.endCreateTime.setSeconds(59);

      console.log(params);
    }
  }
};
</script>