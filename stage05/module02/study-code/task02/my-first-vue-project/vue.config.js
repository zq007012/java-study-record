const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host: "127.0.0.1",// 自动启动浏览器时访问的网址
    port: 8081, // 端口
    https: false, // 启用https
    open: true, //启动本项目后会自动启动浏览器
  },
});
