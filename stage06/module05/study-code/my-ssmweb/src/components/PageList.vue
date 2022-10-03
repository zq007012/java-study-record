<template>
  <div class="app-container">
    <div class="table-container">
      <el-table
        ref="homeAdvertiseTable"
        :data="list"
        style="width: 100%;"
        v-loading="listLoading"
        border
      >
        <el-table-column label="id" width="120" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="广告名称" align="center">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>

        <el-table-column label="广告图片" width="120" align="center">
          <template slot-scope="scope">
            <img style="height: 80px" :src="scope.row.img" />
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handlePageSizeChange"
        @current-change="handleCurrentPageChange"
        layout="total, sizes,prev, pager, next,jumper"
        :current-page="page"
        :page-sizes="[5,10, 20]"
        :page-size="size"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
export default {
  //数据部分
  data() {
    return {
      list: [], //广告数据   后台
      page: 1, //当前页     前端
      size: 5, //每页显示条数 前端
      total: 0, //总条数     后台
      listLoading: true
    };
  },
  //钩子函数
  created() {
    this.loadList();
  },

  methods: {
    //获取广告数据
    loadList() {
      return this.axios
        .get(
          "http://10.1.192.146:8080/ssm-web/PromotionAd/findAllPromotionAd",
          {
            params: {
              currentPage: this.page,
              pageSize: this.size
            }
          }
        )
        .then(res => {
          this.list = res.data.content.list;
          this.total = res.data.content.total;

          this.listLoading = false;
        })
        .catch(err => {
          this.$message("数据获取失败!");
        });
    },

    //显示条数变化时触发
    handlePageSizeChange(size) {
      this.size = size;
      this.loadList();
    },

    //当前页发生变化时触发
    handleCurrentPageChange(page) {
      this.page = page;
      this.loadList();
    }
  }
};
</script>