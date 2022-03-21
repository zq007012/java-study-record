<template>
  <div>
    <section class="course-task-item">
      <div class="header">
        <el-page-header
          @back="() => this.$router.back()"
          :content="content.title"
        />
        <el-button type="primary" icon="el-icon-plus" @click="addLesson()"
          >添加课时</el-button
        >
      </div>
      <el-table
        :data="lessons"
        v-loading="loading"
        element-loading-text="数据加载中..."
      >
        <el-table-column
          prop="id"
          label="ID"
          width="100"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="theme"
          label="标题"
          min-width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="duration"
          label="时长"
          :formatter="addUnit"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="order_num"
          label="排序"
          width="100"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="is_free"
          label="是否免费"
          align="center"
          width="100"
          :formatter="isFreeFormatter"
        >
        </el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button size="small" @click.stop="updateLesson(scope.row)"
              >编辑</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </section>

    <!-- 添加或修改课时的dialog表单 -->
    <el-dialog
      class="add-dialog"
      title="课时信息"
      :visible.sync="showAddOrUpdateLesson"
    >
      <el-form
        label-position="right"
        label-width="80px"
        :model="addOrUpdateLessonForm"
      >
        <el-form-item label="课程名称">
          <el-input v-model="content.course_name" disabled></el-input>
        </el-form-item>
        <el-form-item label="章节名称">
          <el-input v-model="content.section_name" disabled></el-input>
        </el-form-item>
        <el-form-item label="课时名称">
          <el-input v-model="addOrUpdateLessonForm.theme"></el-input>
        </el-form-item>
        <el-form-item label="时长">
          <el-input
            v-model="addOrUpdateLessonForm.duration"
            type="number"
          ></el-input>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-select
            v-model="addOrUpdateLessonForm.is_free"
            placeholder="请选择"
          >
            <el-option
              v-for="item in isFreeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="章节排序">
          <el-input v-model="addOrUpdateLessonForm.order_num" type="number">
            <template slot="append">数字控制排序，数字越大越靠后</template>
          </el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="showAddOrUpdateLesson = false">取 消</el-button>
        <el-button type="primary" @click="handleAddOrUpDateLesson"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <!-- 添加或修改章节 -->
  </div>
</template>

<script>
import { axios } from "../utils";
export default {
  name: "CourseTaskItem",
  title: "课时配置",
  data() {
    const content = {
      course_name: "",
      section_name: "",
      title: "",
    };

    const isFreeOptions = [
      {
        label: "付费",
        value: 0,
      },
      {
        label: "免费",
        value: 1,
      },
    ];

    const addOrUpdateLessonForm = {
      methodName: "saveOrUpdateLesson",
      id: "",
      course_id: "",
      section_id: "",
      theme: "",
      duration: 0,
      is_free: 0,
      order_num: 0,
    };
    return {
      content, // 课程名和章节名
      addOrUpdateLessonForm, //添加或修改章节的表单
      lessons: [], //所有的课时信息
      loading: false, //表格正在加载数据的动画
      isFreeOptions, //是否免费的选择器的 选项
      showAddOrUpdateLesson: false, //是否显示添加后修改课时信息的表单dialog
    };
  },
  created() {
    // 1. 获取route中携带的参数
    const courseId = this.$route.query.courseId;
    const sectionId = this.$route.query.sectionId;
    //console.log(courseId);
    //console.log(sectionId);

    // 2. 判断是路由中的参数是否合规
    if (!courseId) this.toError();
    if (!sectionId) this.toError();

    // 3. 保存route中携带的数据, 获取课程名和章节名以及标题名
    this.addOrUpdateLessonForm.course_id = courseId;
    this.addOrUpdateLessonForm.section_id = sectionId;
    this.loadContentTitle(courseId, sectionId);

    this.$breadcrumbs = [
      { name: "Courses", text: "课程管理" },
      { name: "CourseTasks", params: { courseId }, text: "课程结构" },
      { text: "课时配置" },
    ];

    this.loadData();
  },
  methods: {
    loadData() {
      this.loading = true;
      return axios
        .get("/courseContent", {
          params: {
            methodName: "findLessonsBySectionId",
            section_id: this.addOrUpdateLessonForm.section_id,
          },
        })
        .then((response) => {
          this.lessons = response.data;
          this.loading = false;
        })
        .catch((error) => {
          this.$notify.error({
            title: "后端请求失败",
            message: error.message,
            duration: 5000,
          });
        });
    },

    getCourseName(courseId) {
      return axios.get("/courseContent", {
        params: {
          methodName: "findCourseById",
          course_id: courseId,
        },
      });
    },

    getSectionName(sectionId) {
      return axios.get("/courseContent", {
        params: {
          methodName: "findSectionById",
          section_id: sectionId,
        },
      });
    },

    loadContentTitle(courseId, sectionId) {
      return Promise.all([
        this.getCourseName(courseId),
        this.getSectionName(sectionId),
      ])
        .then((results) => {
          this.content.course_name = results[0].data.course_name;
          this.content.section_name = results[1].data.section_name;
          this.content.title =
            this.content.course_name + " / " + this.content.section_name;
        })
        .catch((error) => {
          this.$notify.error({
            title: "后台请求失败",
            message: error.message,
            duration: 8000,
          });
        });
    },

    addLesson() {
      this.addOrUpdateLessonForm.id = undefined;
      this.addOrUpdateLessonForm.theme = "";
      this.addOrUpdateLessonForm.duration = 0;
      this.addOrUpdateLessonForm.is_free = 0;
      this.addOrUpdateLessonForm.order_num = 0;
      this.showAddOrUpdateLesson = true;
    },

    handleAddOrUpDateLesson() {
      let action = !this.addOrUpdateLessonForm.id ? "新建课时" : "编辑课时";
      console.log(this.addOrUpdateLessonForm.is_free);
      return axios
        .post("courseContent", this.addOrUpdateLessonForm)
        .then((response) => {
          if (response.data.status == 0) {
            this.$notify({
              title: action + "成功",
              message: response.data.msg,
              type: "success",
            });
            this.loadData();
            this.showAddOrUpdateLesson = false;
          } else {
            this.$notify({
              title: action + "失败",
              message: response.data.msg,
              type: "warning",
            });
          }
        })
        .catch((error) => {
          this.$notify.error({
            title: action + "后台请求失败",
            message: error.message,
            duration: 8000,
          });
        });
    },

    updateLesson(lesson) {
      this.addOrUpdateLessonForm.id = lesson.id;
      this.addOrUpdateLessonForm.theme = lesson.theme;
      this.addOrUpdateLessonForm.duration = lesson.duration;
      this.addOrUpdateLessonForm.is_free = lesson.is_free;
      this.addOrUpdateLessonForm.order_num = lesson.order_num;
      this.showAddOrUpdateLesson = true;
    },

    addUnit(row, column, value, index) {
      //console.log(row);
      //console.log(column);
      //console.log(index);
      return value + "分钟";
    },

    toError() {
      this.$router.replace({ path: "/not-found" });
    },

    isFreeFormatter(row, column, value, index) {
      return value === 0 ? "付费" : "免费";
    },
  },
};
</script>

<style lang="scss">
.course-task-item {
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .el-table {
    margin-top: 20px;
  }
}
</style>
