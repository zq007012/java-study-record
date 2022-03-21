<template>
  <section class="course-item">
    <!-- 头部 -->
    <div class="header">
      <!-- 返回上一页 -->
      <el-page-header
        @back="() => this.$router.back()"
        :content="course.title"
      />
      <el-button type="primary" @click="handleSave">保存</el-button>
    </div>

    <!-- 表单开始 -->
    <el-form ref="form" :model="course" :rules="rules" label-width="120px">
      <el-card
        shadow="never"
        v-loading="loading"
        element-loading-text="数据加载中..."
      >
        <header slot="header">基本信息</header>
        <el-form-item label="名称" prop="course_name">
          <el-input
            v-model="course.course_name"
            type="text"
            maxlength="50"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="简介" prop="brief">
          <el-input
            v-model="course.brief"
            type="text"
            maxlength="100"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="讲师姓名" prop="teacher_name">
          <el-input
            v-model="course.teacher_name"
            type="text"
            maxlength="50"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="讲师简介" prop="teacher_title">
          <el-input
            v-model="course.teacher_info"
            type="text"
            maxlength="100"
            show-word-limit
          />
        </el-form-item>
        <el-form-item
          label="课程概述"
          prop="preview_first_field"
          class="form-control-summary"
        >
          <el-input
            v-model="course.preview_first_field"
            type="text"
            maxlength="20"
            show-word-limit
          />
          <el-input
            v-model="course.preview_second_field"
            type="text"
            maxlength="20"
            show-word-limit
          />
        </el-form-item>
      </el-card>

      <el-card
        shadow="never"
        v-loading="loading"
        element-loading-text="数据加载中..."
      >
        <header slot="header">销售信息</header>
        <el-form-item label="售卖价格" prop="discounts">
          <el-input v-model="course.discounts" type="number">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="商品原价">
          <el-input v-model="course.price" type="number">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="活动标签">
          <el-input
            v-model="course.price_tag"
            type="text"
            maxlength="4"
            show-word-limit
          />
        </el-form-item>
      </el-card>

      <el-card
        shadow="never"
        v-loading="loading"
        element-loading-text="数据加载中..."
      >
        <header slot="header">分享信息</header>
        <!-- 上传图片部分 -->
        <el-form-item label="分享小图" prop="share_image_title">
          <el-input v-model="course.share_image_title" type="text">
            <!-- :auto-upload="false",取消自动上传, :on-change="onchange" 调用onchange进行处理 -->
            <el-upload
              slot="prepend"
              :auto-upload="false"
              :on-change="onchange"
              action
              :limit="1"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-input>
        </el-form-item>
        <el-form-item label="分享标题" prop="share_title">
          <el-input
            v-model="course.share_title"
            type="text"
            maxlength="40"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="分享简介" prop="share_description">
          <el-input
            v-model="course.share_description"
            type="text"
            maxlength="60"
            show-word-limit
          />
        </el-form-item>
      </el-card>

      <el-card
        shadow="never"
        v-loading="loading"
        element-loading-text="数据加载中..."
      >
        <header slot="course_description">课程详情</header>
        <editor v-model="course.course_description" />
      </el-card>

      <div class="actions">
        <el-button type="primary" @click="handleSave">保存</el-button>
      </div>
    </el-form>

    <!-- 表单结束 -->
  </section>
</template>

<script>
//引入富文本编辑器 和 axios
import Editor from "@/components/Editor.vue";
import { axios } from "../utils";

export default {
  name: "CourseItem",
  title: "营销信息",
  components: { Editor },
  data() {
    //表单校验规则
    const rules = {
      course_name: [
        { required: true, message: "请输入课程名称", trigger: "blur" },
        { min: 2, max: 50, message: "长度在 2 到 50 个字符", trigger: "blur" },
      ],
      brief: [
        { required: true, message: "请输入课程简介", trigger: "change" },
        {
          min: 5,
          max: 100,
          message: "长度在 5 到 100 个字符",
          trigger: "blur",
        },
      ],
      teacher_name: [
        { required: true, message: "请输入讲师姓名", trigger: "change" },
        { min: 2, max: 50, message: "长度在 2 到 50 个字符", trigger: "blur" },
      ],
      teacher_info: [
        { required: true, message: "请输入讲师简介", trigger: "change" },
        { min: 5, max: 50, message: "长度在 5 到 50 个字符", trigger: "blur" },
      ],
      preview_first_field: [
        { required: true, message: "请输入课程概述", trigger: "change" },
        { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" },
      ],
      preview_second_field: [
        { required: true, message: "请输入课程概述", trigger: "change" },
        { min: 2, max: 20, message: "长度在 2 到 20 个字符", trigger: "blur" },
      ],
      price: [{ required: true, message: "请输入课程售价", trigger: "change" }],
      share_img: [
        { required: true, message: "请上传分享图片", trigger: "change" },
      ],
      share_title: [
        { required: true, message: "请输入分享标题", trigger: "change" },
      ],
      share_description: [
        { required: true, message: "请输入分享简介", trigger: "change" },
      ],
    };

    //数据部分
    return {
      rules, //规则
      course: {}, //课程
      loading: false,
      params: {},
    };
  },
  //钩子函数
  created() {
    //1.显示当前页面在网站中的位置, element-ui中的面包屑控件
    this.$breadcrumbs = [
      { name: "Courses", text: "课程管理" },
      { text: "营销信息" },
    ];

    //2. 判断路由是否携带courseId参数, 如果没有, 就跳转到错误页面
    const courseId = this.$route.query.courseId;
    if (!courseId) return this.redirectToError();

    //3. 创建FormData对象, 用来将表单中的图片和键值对一起上传
    this.params = new FormData();

    //4. 路由携带了courseId参数, 值为new则是新建课程, 值不是new, 则是修改课程
    if (courseId === "new") {
      this.course.title = "新建课程";
    } else {
      this.course.title = "修改课程";
      this.loadCourse(courseId);
    }
  },
  methods: {
    //方法1: 保存和修改课程信息
    handleSave() {
      //1 验证表单是否能通过验证规则
      this.$refs.form.validate((valid) => {
        // 1.2 没通过验证, 就结束函数, 社么也不做
        if (!valid) return false;

        // 1.2 通过了验证
        // 1.2.1 将表当中的键值对添加到params这个FormData对象中
        for (let key in this.course) {
          //debugger;
          //console.log(key + " : " + this.course[key]);
          this.params.append(key, this.course[key]);
        }

        // 1.2.2 提交数据
        return axios
          .post("/courseSalesInfo", this.params, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((response) => {
            if (response.data.status === 0) {
              //this.$router.push("/courses");
              this.$router.back();
            } else if (response.data.status === 1) {
              this.$notify.error({
                title: "保存失败",
                message: response.data.msg,
                duration: 5000,
              });
            }
          })
          .catch((error) => {
            this.$notify.error({
              title: "后台请求失败",
              message: error.message,
              duration: 5000,
            });
          });
      });
    },

    //文件上传
    onchange(file) {
      if (file != null) {
        this.params.append("file", file.raw, file.name);
      }
    },

    //方法2: 根据ID 回显课程信
    loadCourse(id) {
      //数据成功获取前显示加载控件
      console.log(id);
      this.loading = true;
      axios
        .get("/course", {
          params: {
            methodName: "findCourseById",
            id: id,
          },
        })
        .then((response) => {
          console.log(response.data);
          //获取到数据
          //给data中的course赋值

          this.course = response.data;
          //将id键值对保存到做为FormData对象的parms中, 以便提交到后台后, 后台可以根据id的值来判断是新建课程还是修改课程
          this.params.set("id", id);
          //取消加载控件的显示
          this.loading = false;
        })
        .catch((error) => {
          this.$notify.error({
            title: "后台请求失败",
            message: error.message,
            duration: 5000,
          });
        });
    },

    //跳转到错误页面
    redirectToError() {
      // TODO: Error components
      return this.$router.replace({ path: "/not-found" });
      //console.log("error");
    },
  },
};
</script>

<style lang="scss">
.course-item {
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .el-card {
    margin-top: 20px;
  }

  .form-control-summary {
    .el-form-item__content {
      display: flex;
      justify-content: space-between;

      .el-input {
        width: 49%;
      }

      &:before,
      &:after {
        display: none;
      }
    }
  }

  .actions {
    display: flex;
    justify-content: center;

    .el-button {
      margin: 40px 20px;
    }
  }
}
</style>
