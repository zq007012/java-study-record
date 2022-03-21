var tifa = "蒂法";
console.log("1. " + tifa);

//console.log("2. " + luffy); //error

console.log("3. " + robin);
var robin = "罗宾";
console.log("4. " + robin);

var demo = function () {
  console.log("5. " + burin);
  var burin = "布琳";
  console.log("6. " + burin);

  console.log("7. " + bayonetta);
  if (true) {
    console.log("8. " + burin);
    console.log("9. " + bayonetta);
    var bayonetta = "贝优妮塔";
    console.log("10. " + bayonetta);
  }
  console.log("11. " + bayonetta);
  return 0;
};

var a = demo();

//console.log("12. " + burin); //error
//console.log("13. " + bayonetta);//error

console.log("1.username: " + username);
var username = "穗乃果";
console.log("2.username: " + username);
var username = "不知火舞";
console.log("3.username: " + username);
