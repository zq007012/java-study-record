var arr = ["Mercy", "黑百合", "小美"];

//传统的js
let a = arr[0];
let b = arr[1];
let c = arr[2];
console.log(a,b,c);

//ES6的结构
var [x, y, z] = arr;
console.log(x, y, z);

let [l, m, n] = arr;
console.log(l, m, n);