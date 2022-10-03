<template>
  <div>
    <el-container>
      <el-header>后台管理</el-header>
      <el-container>
        <!-- 侧边栏 -->
        <el-aside width="200px">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            background-color="#d3dce6"
            router
          >
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>导航菜单</span>
              </template>

              <el-menu-item-group>
                <!-- 修改 index的路由地址 -->
                <el-menu-item index="/course">
                  <i class="el-icon-menu"></i>课程管理
                </el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <!-- 修改 index的路由地址 -->
                <el-menu-item index="/upload">
                  <i class="el-icon-menu"></i>图片上传
                </el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <!-- 修改 index的路由地址 -->
                <el-menu-item index="/test">
                  <i class="el-icon-menu"></i>测试组件的引入
                </el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <!-- 修改 index的路由地址 -->
                <el-menu-item index="/page">
                  <i class="el-icon-menu"></i>分页组件
                </el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <!-- 修改 index的路由地址 -->
                <el-menu-item index="/date">
                  <i class="el-icon-menu"></i>日期控件
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>

        <!-- 主要区域 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.el-container {
  height: 725px;
}
.el-header,
.el-footer {
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
  line-height: 30px;
}
</style>
