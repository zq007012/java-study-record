<template>
  <div>
    <div style="margin: 5px 0px">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input
            placeholder="请输入课程名称"
            v-model="keywordsSearched.courseName"
            clearable
            @keyup.enter.native="findByCourseNameAndStatus"
          >
            <i
              slot="prefix"
              class="el-input__icon el-icon-search"
              @click="findByCourseNameAndStatus"
            ></i>
          </el-input>
        </el-col>
      </el-row>
    </div>

    <el-table
      :data="courseList"
      stripe
      style="width: 100%"
      v-loading="loading"
      :element-loading-text="loadingText"
    >
      <el-table-column prop="id" label="ID" width="180"> </el-table-column>
      <el-table-column prop="course_name" label="课程名称" width="180">
      </el-table-column>
      <el-table-column prop="price" label="价格"> </el-table-column>
      <el-table-column prop="sort_num" label="排序"> </el-table-column>
      <el-table-column prop="status" label="状态"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: true,
      loadingText: "加载课程信息中...",
      courseList: [],
      keywordsSearched: { courseName: "", status: "" },
    };
  },
  created() {
    this.loadingCourseList();
  },
  methods: {
    findByCourseNameAndStatus() {
      this.loading = true;
      const url = "http://127.0.0.1:8080/lagou_edu_home_zq/course";
      return this.axios
        .get(url, {
          params: {
            methodName: "findByCourseNameAndStatus",
            course_name: this.keywordsSearched.courseName,
            status: this.keywordsSearched.status,
          },
        })
        .then((response) => {
          this.courseList = response.data;
          this.loading = false;
        })
        .catch((error) => {
          this.loadingText = "获取数据失败";
          console.log(error.message);
        });
    },
    loadingCourseList() {
      this.loading = true;
      const url = "http://127.0.0.1:8080/lagou_edu_home_zq/course";
      return this.axios
        .get(url, {
          params: { methodName: "findCourseList" },
        })
        .then((response) => {
          this.courseList = response.data;
          this.loading = false;
        })
        .catch((error) => {
          this.loadingText = "获取数据失败";
          console.log(error.message);
        });
    },
  },
};
</script>

<style scoped></style>
