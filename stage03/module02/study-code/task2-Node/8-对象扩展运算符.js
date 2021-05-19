let user1 = {
    name : "黑百合",
    age:33, 
    saying(){return `一枪一个`}};
console.log(user1);

let user2 = {
    name : "WidowMaker",
    team : `黑爪`
}
console.log(user2);

let user3 = {...user1 , ...user2};
console.log(user3);

let user4 = {usedName : "艾米莉"};
let user5 = {...user1 , ...user2 , ...user4};
console.log(user5);