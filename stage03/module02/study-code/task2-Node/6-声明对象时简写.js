let userName = "穗乃果";
let age = 24;

//传统
let user1 = {
    userName : userName,
    age :age
}
console.log(user1);
console.log(`------------------`);
//es6新语法中的简写
let user2 = {userName, age};
console.log(user2);