//传统
var demo1 = function(a, b){
    var result = a * b;
    return result;
}
console.log(demo1(1, 10));

//箭头函数
var demo2 = (a , b) => {
    var result = a * b;
    return result;
}
console.log(demo2(2,10));
//当形参只有一个时, 形参列表外的小括号可以省略
var demo3 = a => {
    var result = a * 10;
    return result;
}
console.log(demo3(3));
//当函数体只有一行return语句时, 大括号和return可以省略
var demo4 = (a , b) => a * b;
console.log(demo3(4,10));
