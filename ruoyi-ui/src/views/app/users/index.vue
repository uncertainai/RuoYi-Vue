<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名称" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="email邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入email邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="头像url" prop="profilePicture">
        <el-input
          v-model="queryParams.profilePicture"
          placeholder="请输入头像url"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入手机号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供商id" prop="providerId">
        <el-input
          v-model="queryParams.providerId"
          placeholder="请输入提供商id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="国家" prop="countryCode">
        <el-input
          v-model="queryParams.countryCode"
          placeholder="请输入国家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="语种" prop="languageCode">
        <el-input
          v-model="queryParams.languageCode"
          placeholder="请输入语种"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="expires_at" prop="expiresAt">
        <el-date-picker clearable
          v-model="queryParams.expiresAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择expires_at">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['app:users:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['app:users:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['app:users:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['app:users:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="usersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="用户名称" align="center" prop="username" />
      <el-table-column label="email邮箱" align="center" prop="email" />
      <el-table-column label="头像url" align="center" prop="profilePicture" />
      <el-table-column label="手机号码" align="center" prop="phoneNumber" />
      <el-table-column label="提供商" align="center" prop="provider" />
      <el-table-column label="提供商id" align="center" prop="providerId" />
      <el-table-column label="国家" align="center" prop="countryCode" />
      <el-table-column label="语种" align="center" prop="languageCode" />
      <el-table-column label="access_token" align="center" prop="accessToken" />
      <el-table-column label="refresh_token" align="center" prop="refreshToken" />
      <el-table-column label="expires_at" align="center" prop="expiresAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expiresAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['app:users:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['app:users:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改社交媒体用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名称" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="email邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入email邮箱" />
        </el-form-item>
        <el-form-item label="头像url" prop="profilePicture">
          <el-input v-model="form.profilePicture" placeholder="请输入头像url" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="提供商id" prop="providerId">
          <el-input v-model="form.providerId" placeholder="请输入提供商id" />
        </el-form-item>
        <el-form-item label="国家" prop="countryCode">
          <el-input v-model="form.countryCode" placeholder="请输入国家" />
        </el-form-item>
        <el-form-item label="语种" prop="languageCode">
          <el-input v-model="form.languageCode" placeholder="请输入语种" />
        </el-form-item>
        <el-form-item label="access_token" prop="accessToken">
          <el-input v-model="form.accessToken" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="refresh_token" prop="refreshToken">
          <el-input v-model="form.refreshToken" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="expires_at" prop="expiresAt">
          <el-date-picker clearable
            v-model="form.expiresAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择expires_at">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUsers, getUsers, delUsers, addUsers, updateUsers } from "@/api/app/users"

export default {
  name: "Users",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 社交媒体用户表格数据
      usersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        email: null,
        profilePicture: null,
        phoneNumber: null,
        provider: null,
        providerId: null,
        countryCode: null,
        languageCode: null,
        accessToken: null,
        refreshToken: null,
        expiresAt: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        providerId: [
          { required: true, message: "提供商id不能为空", trigger: "blur" }
        ],
        countryCode: [
          { required: true, message: "国家不能为空", trigger: "blur" }
        ],
        languageCode: [
          { required: true, message: "语种不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询社交媒体用户列表 */
    getList() {
      this.loading = true
      listUsers(this.queryParams).then(response => {
        this.usersList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        username: null,
        email: null,
        profilePicture: null,
        phoneNumber: null,
        provider: null,
        providerId: null,
        countryCode: null,
        languageCode: null,
        accessToken: null,
        refreshToken: null,
        expiresAt: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加社交媒体用户"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getUsers(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改社交媒体用户"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUsers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addUsers(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除社交媒体用户编号为"' + ids + '"的数据项？').then(function() {
        return delUsers(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('app/users/export', {
        ...this.queryParams
      }, `users_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
