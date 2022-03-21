var vm = new Vue({
  el: "#app",
  data: {
    city: "",
    weathers: [],
  },
  methods: {
    search: function () {
      var model = this;
      axios.get("http://wthrcdn.etouch.cn/weather_mini?city=" + this.city).then(
        function (response) {
          if (response.data.status === 1000) {
            model.weathers = response.data.data.forecast;
          } else {
            model.weathers = [];
          }
        },
        function (error) {
          model.weathers = [];
          console.log(error);
        }
      );
    },
  },
});
