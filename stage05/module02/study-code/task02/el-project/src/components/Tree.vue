<template>
  <div>
    <el-tree :data="sectionAndLesson" :props="defaultProps">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>(node.id = {{ node.id }})</span>
        <span>(node.level = {{ node.level }})</span>
        <span>(node.label = {{ node.label }})</span>
        <span>[--{{ data.duration || data.description }}--]</span>
      </span>
    </el-tree>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sectionAndLesson: [
        {
          courseLessonList: [
            {
              theme: "第一讲:如何给自己洗脑",
              duration: 10,
              lesson: [
                { name: "第1节" },
                { name: "第2节" },
                { name: "第3节" },
                { name: "第4节" },
              ],
            },
            {
              theme: "第二讲:如何给别人洗脑",
              duration: 10,
            },
          ],
          section_name: "麻式太极第一章",
          description: "麻式太极拳,你动我试试",
        },
        {
          section_name: "麻式太极第二章",
          description: "麻式太极拳,你动我试试",
        },
      ],
      defaultProps: {
        children(data, node) {
          return data.courseLessonList || data.lesson;
        },
        label(data, node) {
          return data.section_name || data.theme || data.name;
        },
      },
    };
  },
};
</script>

<style>
</style>