<template>
  <div>
    <el-container>
      <el-header>后台管理</el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
            background-color="#d3dce6"
            default-active="2"
            :router="true"
            @open="handleOpen"
            @close="handleClose"
          >
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"> </i>
                <span>导航菜单</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/course">
                  <i class="el-icon-menu"></i>
                  <span>课程管理</span>
                </el-menu-item>
                <el-menu-item index="/tree">
                  <i class="el-icon-view"></i>
                  <span>树形控件</span>
                </el-menu-item>
                <el-menu-item index="/axios-test">
                  <i class="el-icon-view"></i>
                  <span>axios test</span>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style scoped>
.el-container {
  height: 720px;
}
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 1.5em;
}
</style>
