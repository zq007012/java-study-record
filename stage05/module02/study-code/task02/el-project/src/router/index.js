import Vue from "vue";
import VueRouter from "vue-router";
import index from "../components/index.vue";
import course from "../components/course.vue";
import Tree from "../components/Tree";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/login",
  },

  {
    path: "/axios-test",
    name: "AxiosTest",
    component: () => import("../components/AxiosTest"),
  },

  {
    path: "/login",
    name: "login",
    component: () => import("../components/login.vue"),
  },
  {
    path: "/index",
    name: "index",
    component: index,
    children: [
      {
        path: "/course",
        name: "course",
        component: course,
      },
      {
        path: "/tree",
        name: "tree",
        component: Tree,
      },
    ],
  },
  /* {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( '../views/AboutView.vue')
  }*/
];

const router = new VueRouter({
  routes,
});

export default router;
