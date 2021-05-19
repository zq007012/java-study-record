var hero = {
    heroName : "Mercy",
    heroGender : "女",
    heroSaying:"Hero's never die."
};

// 传统的js
let theName = hero.heroName;
let gender = hero.heroGender;
let saying = hero.heroSaying;
console.log("姓名: " + theName, "性别: " + gender, "口头禅: " + saying);

//ES6的结构
let {heroName, heroGender, heroSaying} = hero;
console.log("姓名: " + heroName, "性别: " + heroGender, "口头禅: " + heroSaying);