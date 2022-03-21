<template>
  <div class="color">
    {{ msg }}
    <p><img src="../assets/Tifa.jpg" /></p>
  </div>
</template>

<script>
export default {
  name: "Title",
  data: function () {
    return {
      msg: "tifa",
    };
  },
  /* props: {
    msg: String,
  }, */
};
</script>

<style scoped>
.color {
  color: rgba(189, 110, 126, 0.432);
}

img {
  height: 300px;
}
</style>
