<template>
  <div>axios test</div>
</template>
<script>
export default {
  data() {
    return {};
  },
  created() {
    Promise.all([this.req1(), this.req2()])
      .then((results) => {
        console.log(results[0]);
        console.log(results[1]);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    req1() {
      return this.axios.get(
        "http://localhost:8080/lagou_edu_home_zq/course?methodName=findCourseList"
      );
    },
    req2() {
      return this.axios.get("http://www.zq.com");
    },
  },
};
</script>
<style>
</style>