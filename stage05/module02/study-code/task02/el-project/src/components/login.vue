<template>
  <div>
    <el-dialog
      :show-close="false"
      title="用户登录"
      :visible.sync="dialogFormVisible"
    >
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input
            placeholder="请输入用户名"
            v-model="form.username"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth">
          <el-input
            type="password"
            placeholder="请输入用户密码"
            v-model="form.password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="login">登 录</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogFormVisible: true,
      form: {
        username: "",
        password: "",
      },
      formLabelWidth: "120px",
    };
  },
  methods: {
    login() {
      const url =
        "https://307db15e-1462-4bda-86fa-39a664bb3dad.mock.pstmn.io/login";
      this.axios
        .post(url, this.form)
        .then((response) => {
          if (response.data.status === 0) {
            this.$notify({
              title: "登录成功",
              message: "账号和密码正确, 即将跳转页面...",
              type: "success",
            });
            this.dialogFormVisible = false;
            this.$router.push("/index");
          } else {
            this.$notify({
              title: "登录失败",
              message: "账号或密码错误",
              type: "warning",
            });
          }
        })
        .catch((error) => {
          this.$notify.error({
            title: "错误",
            message: error.message,
            duration: 0,
          });
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
