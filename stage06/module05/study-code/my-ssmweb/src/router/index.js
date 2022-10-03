import Vue from "vue";
import VueRouter from "vue-router";

//导入布局组件
import Index from "@/components/Index";

//引入图片上传组件
import Upload from "@/components/UploadImage";

import TestUpload from "@/components/TestUpload";

import PageList from "@/components/PageList";

import TestDate from "@/components/TestDate";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "index",
  },
  //布局路由
  {
    path: "/index",
    name: "index",
    component: Index,
    //添加子路由,使用 children属性 来表示子路由
    children: [
      //子路由
      // {
      //   path: "/course",
      //   name: "course",
      //   component: Course,
      // },
      {
        path: "/upload",
        name: "upload",
        component: Upload,
      },
      {
        path: "/test",
        name: "test",
        component: TestUpload,
      },
      {
        path: "/page",
        name: "page",
        component: PageList,
      },
      {
        path: "/date",
        name: "date",
        component: TestDate,
      },
    ],
  },
];

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

const router = new VueRouter({
  routes,
});

export default router;
