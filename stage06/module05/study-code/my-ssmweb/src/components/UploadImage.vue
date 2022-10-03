<template>
  <div>
    <el-upload
      :action="actionUrl"
      list-type="picture-card"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="successUpload"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt />
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      actionUrl: this.uploadUrl
    };
  },

  /**
   * props 组件传参
   *    uploadUrl: 图片上传的路径
   *    https://jsonplaceholder.typicode.com/posts/
   *
   *    getUrl: 函数
   */
  props: ["uploadUrl", "getUrl"],

  methods: {
    handleRemove(file, fileList) {
      alert("图片被移除!!");
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    //图片上传成功之后调用的函数
    successUpload(res, file) {
      this.getUrl(file);
    }
  }
};
</script>