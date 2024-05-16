<template>
  <div style="color: #666">
<!--    <div style="margin: 10px 0">-->
<!--      <el-input size="small" style="width: 300px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>-->
<!--      <el-button class="ml-5" type="primary" @click="load" size="small">搜索</el-button>-->
<!--      <el-button type="warning" @click="reset" size="small">重置</el-button>-->
<!--    </div>-->


<!--    <div style="margin: 10px 0">-->
<!--      <div style="padding: 10px 0; border-bottom: 1px dashed #ccc" v-for="item in tableData" :key="item.id">-->
<!--        <div class="pd-10" style="font-size: 20px; color: #3F5EFB; cursor: pointer" @click="$router.push('/front/articleDetail?id=' + item.id)">{{ item.name }}</div>-->
<!--        <div style="font-size: 14px; margin-top: 10px">-->
<!--          <i class="el-icon-user-solid"></i> <span>{{ item.user }}</span>-->
<!--          <i class="el-icon-time" style="margin-left: 10px"></i> <span>{{ item.time }}</span>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->

    <div>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
            label="ID"
            prop="id">
        </el-table-column>
        <el-table-column
            label="Name"
            prop="">
        </el-table-column>
        <el-table-column
            align="right">

          <template slot-scope="scope">
            <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            <el-button
                size="mini"
                @click="handleReset(scope.$index, scope.row)">撤回</el-button>
            <el-button
                size="mini"
                @click="handleSubmit(scope.$index, scope.row)">提交</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>


    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>


  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "Article",
  data() {
    return {
      form: {},
      tableData: [],
      name: '',
      multipleSelection: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      dialogFormVisible: false,
      teachers: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      content: '',
      viewDialogVis: false
    }
  },
  created() {
    this.load()
  },

  methods: {

    handleEdit(index, row) {
      console.log(index, row);

    },
    handleDelete(index, row) {
      console.log(index, row);
      this.request.get("/author/delete", {
        params: {
          id: row.id
        }
      }
      ).then(res => {
        if (res.code === '0') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleReset(index, row) {
      console.log(index, row);
      this.request.get("/author/reset", {
            params: {
              id: row.id
            }
          }
      ).then(res => {
        if (res.code === 0) {
          this.$message.success("撤回成功")
          this.load()
        } else {
          this.$message.error("失败")
        }
      })
    },

    handleSubmit (index, row){
      console.log(index, row);
      this.request.get("/author/submit", {
            params: {
              id: row.id
            }
          }
      ).then(res => {
        if (res.code === 0) {
          this.$message.success("提交成功")
          this.load()
        } else {
          this.$message.error("提交失败")
        }
      })
    },

    view(content) {
      this.content = content
      this.viewDialogVis = true
    },
    // 绑定@imgAdd event
    imgAdd(pos, $file) {
      let $vm = this.$refs.md
      // 第一步.将图片上传到服务器.
      const formData = new FormData();
      formData.append('file', $file);
      axios({
        url: 'http://localhost:9090/file/upload',
        method: 'post',
        data: formData,
        headers: {'Content-Type': 'multipart/form-data'},
      }).then((res) => {
        // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
        $vm.$img2Url(pos, res.data);
      })
    },
    load() {
      this.request.get("/author/list", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          phoneNumber: this.user.phoneNumber
          // name: '19822907518'//this.name,
        }
      }).then(res => {

        this.tableData = res.data.records
        this.total = res.data.total

      })

    },
    changeEnable(row) {
      this.request.post("/article/update", row).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
        }
      })
    },
    // handleAdd() {
    //   this.dialogFormVisible = true
    //   this.form = {}
    // },
    // handleEdit(row) {
    //   this.form = JSON.parse(JSON.stringify(row))
    //   this.dialogFormVisible = true
    // },
    del(id) {
      this.request.delete("/article/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/article/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    save() {
      this.request.post("/article", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    reset() {
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    download(url) {
      window.open(url)
    },
  }
}
</script>

<style scoped>

</style>
