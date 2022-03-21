/**
 * Override convention configuration
 * https://cli.vuejs.org/config/
 */

module.exports = {
  // relative path for dev
  publicPath:
    process.env.NODE_ENV === "production"
      ? "/edu-boss-zq/" /*这个值必须跟项目名相同*/
      : "/",
  // for gh-pages
  indexPath: "index.html" /*必须设置成这个值*/,
  assetsDir: "static" /*必须设置成这个值*/,
  lintOnSave: process.env.NODE_ENV !== "production",
  productionSourceMap: false,
  css: {
    // sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer: {
    open: true,
    port: 8081
  }
};
