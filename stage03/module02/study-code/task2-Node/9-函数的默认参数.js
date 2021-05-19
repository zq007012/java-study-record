function test(name, age = 18 ){
    console.log(arguments);
    console.log(`我叫${name}, 今年${age}岁了`)
}

test("黑百合", 33);
test("Mercy");
test();
test("小美", null);
test("穗乃果", "")
test("红叶", undefined)