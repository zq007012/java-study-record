module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: ["plugin:vue/essential", "@vue/standard"],
  parserOptions: {
    parser: "babel-eslint"
  },
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "warn" : "off",
    quotes: "off", //单引号双引号问题
    "no-unused-vars": "off", //未使用过的变量
    semi: "off", //分号问题
    "space-before-function-paren": "off", //定义函数时左括号之前的空格
    "comma-dangle": "off", //逗号问题
    "spaced-comment": "off", //注释中的空格问题
    "no-unused-expressions": "off", //未使用过的表达式引起的问题
    "padded-blocks": "off",
    "no-trailing-spaces": "off",
    "prefer-const": "off",
    eqeqeq: "off"
  },
  overrides: [
    {
      files: [
        "**/__tests__/*.{j,t}s?(x)",
        "**/tests/unit/**/*.spec.{j,t}s?(x)"
      ],
      env: {
        jest: true
      }
    }
  ]
};
