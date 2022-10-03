import Vue from "vue";
import App from "./App.vue";
import router from "./router";

//导入elementUI组件库
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

//引入axios
import axios from "axios";

//Vue对象使用axios
Vue.prototype.axios = axios;

Vue.config.productionTip = false;

//配置EL到vue上
Vue.use(ElementUI);

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
