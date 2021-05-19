function test(...args){
    console.log(args.toString());
}


test("黑百合");
test();
test("黑百合", "蒂法", "艾丽丝", "红叶","不知火舞");

function user(name, age, ...args){
    console.log(name);
    console.log(age);
    console.log(`姓名:${name}, 年龄:${age}岁, 习惯:${args.toString()}`)

}

user("于谦", 60, `抽烟`,`烫头`,`喝酒`);