/**
 * 
 *  请求地址:http://wthrcdn.etouch.cn/weather_mini
    请求方法:get
    请求参数:city (要查询的城市名称)
    响应内容:天气信息
 */

var VM = new Vue({
  el: "#app",
  data: {
    city: "",
    //定义数组保存 天气信息
    weatherList: [],
  },
  //编写查询天气的方法
  methods: {
    searchWeather: function () {
      console.log("天气查询");
      console.log(this.city);

      var that = this;

      //调用接口
      axios.get("http://wthrcdn.etouch.cn/weather_mini?city=" + this.city).then(
        function (resp) {
          console.log(resp.data.data.forecast);
          //获取天气信息 保存到weatherList
          that.weatherList = resp.data.data.forecast;
        },
        function (error) {}
      );
    },
  },
});
