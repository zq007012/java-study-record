<template>
  <div>
    <!-- 使用主件 -->
    <upload-image uploadUrl="https://jsonplaceholder.typicode.com/posts/" :get-url="show"></upload-image>
  </div>
</template>

<script>
//1.导入组件
import UploadImage from "@/components/UploadImage";

export default {
  //注册组件
  components: {
    UploadImage
  },

  methods: {
    show(file) {
      console.log("show 方法被调用了!");
      console.log(file);
    }
  }
};
</script>

<style scoped>
</style>