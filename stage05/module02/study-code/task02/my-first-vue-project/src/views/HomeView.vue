<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <Demo />
  </div>
</template>

<script>
// @ is an alias to /src
import Demo from "@/components/Demo.vue";

export default {
  name: "HomeView",
  components: {
    Demo,
  },
};
</script>
