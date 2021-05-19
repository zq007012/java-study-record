const http = require("http");
http.createServer(function(request, response){
    response.writeHead(200, {"Content-type":"text/plain"});
response.end("Hello, Welcome");
}).listen(8888);
console.log("服务器已启动, 请访问`http://127.0.0.1:8888'");