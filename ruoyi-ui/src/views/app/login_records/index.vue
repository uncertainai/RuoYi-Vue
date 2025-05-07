<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登录时间" prop="loginTime">
        <el-date-picker clearable
          v-model="queryParams.loginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择登录时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="登录ip" prop="ipAddress">
        <el-input
          v-model="queryParams.ipAddress"
          placeholder="请输入登录ip"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备信息" prop="deviceInfo">
        <el-input
          v-model="queryParams.deviceInfo"
          placeholder="请输入设备信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="浏览器信息" prop="browserInfo">
        <el-input
          v-model="queryParams.browserInfo"
          placeholder="请输入浏览器信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作系统" prop="osInfo">
        <el-input
          v-model="queryParams.osInfo"
          placeholder="请输入操作系统"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="国家" prop="locationCountry">
        <el-input
          v-model="queryParams.locationCountry"
          placeholder="请输入国家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="州/省" prop="locationRegion">
        <el-input
          v-model="queryParams.locationRegion"
          placeholder="请输入州/省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市" prop="locationCity">
        <el-input
          v-model="queryParams.locationCity"
          placeholder="请输入城市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登录结果" prop="success">
        <el-input
          v-model="queryParams.success"
          placeholder="请输入登录结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="失败原因" prop="failureReason">
        <el-input
          v-model="queryParams.failureReason"
          placeholder="请输入失败原因"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会话ID" prop="sessionId">
        <el-input
          v-model="queryParams.sessionId"
          placeholder="请输入会话ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="双因素认证" prop="twoFactorUsed">
        <el-input
          v-model="queryParams.twoFactorUsed"
          placeholder="请输入双因素认证"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登录持续时长" prop="loginDuration">
        <el-input
          v-model="queryParams.loginDuration"
          placeholder="请输入登录持续时长"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="新设备" prop="isNewDevice">
        <el-input
          v-model="queryParams.isNewDevice"
          placeholder="请输入新设备"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="新地理位置" prop="isNewLocation">
        <el-input
          v-model="queryParams.isNewLocation"
          placeholder="请输入新地理位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['app:login_records:add']"
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
          v-hasPermi="['app:login_records:edit']"
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
          v-hasPermi="['app:login_records:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['app:login_records:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="login_recordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="登录时间" align="center" prop="loginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.loginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="登录ip" align="center" prop="ipAddress" />
      <el-table-column label="设备类型" align="center" prop="deviceType" />
      <el-table-column label="设备信息" align="center" prop="deviceInfo" />
      <el-table-column label="浏览器信息" align="center" prop="browserInfo" />
      <el-table-column label="操作系统" align="center" prop="osInfo" />
      <el-table-column label="user_agent" align="center" prop="userAgent" />
      <el-table-column label="国家" align="center" prop="locationCountry" />
      <el-table-column label="州/省" align="center" prop="locationRegion" />
      <el-table-column label="城市" align="center" prop="locationCity" />
      <el-table-column label="登录方法" align="center" prop="loginMethod" />
      <el-table-column label="登录结果" align="center" prop="success" />
      <el-table-column label="失败原因" align="center" prop="failureReason" />
      <el-table-column label="会话ID" align="center" prop="sessionId" />
      <el-table-column label="双因素认证" align="center" prop="twoFactorUsed" />
      <el-table-column label="登录持续时长" align="center" prop="loginDuration" />
      <el-table-column label="新设备" align="center" prop="isNewDevice" />
      <el-table-column label="新地理位置" align="center" prop="isNewLocation" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['app:login_records:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['app:login_records:remove']"
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

    <!-- 添加或修改用户登录记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="登录时间" prop="loginTime">
          <el-date-picker clearable
            v-model="form.loginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择登录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="登录ip" prop="ipAddress">
          <el-input v-model="form.ipAddress" placeholder="请输入登录ip" />
        </el-form-item>
        <el-form-item label="设备信息" prop="deviceInfo">
          <el-input v-model="form.deviceInfo" placeholder="请输入设备信息" />
        </el-form-item>
        <el-form-item label="浏览器信息" prop="browserInfo">
          <el-input v-model="form.browserInfo" placeholder="请输入浏览器信息" />
        </el-form-item>
        <el-form-item label="操作系统" prop="osInfo">
          <el-input v-model="form.osInfo" placeholder="请输入操作系统" />
        </el-form-item>
        <el-form-item label="user_agent" prop="userAgent">
          <el-input v-model="form.userAgent" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="国家" prop="locationCountry">
          <el-input v-model="form.locationCountry" placeholder="请输入国家" />
        </el-form-item>
        <el-form-item label="州/省" prop="locationRegion">
          <el-input v-model="form.locationRegion" placeholder="请输入州/省" />
        </el-form-item>
        <el-form-item label="城市" prop="locationCity">
          <el-input v-model="form.locationCity" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="登录结果" prop="success">
          <el-input v-model="form.success" placeholder="请输入登录结果" />
        </el-form-item>
        <el-form-item label="失败原因" prop="failureReason">
          <el-input v-model="form.failureReason" placeholder="请输入失败原因" />
        </el-form-item>
        <el-form-item label="会话ID" prop="sessionId">
          <el-input v-model="form.sessionId" placeholder="请输入会话ID" />
        </el-form-item>
        <el-form-item label="双因素认证" prop="twoFactorUsed">
          <el-input v-model="form.twoFactorUsed" placeholder="请输入双因素认证" />
        </el-form-item>
        <el-form-item label="登录持续时长" prop="loginDuration">
          <el-input v-model="form.loginDuration" placeholder="请输入登录持续时长" />
        </el-form-item>
        <el-form-item label="新设备" prop="isNewDevice">
          <el-input v-model="form.isNewDevice" placeholder="请输入新设备" />
        </el-form-item>
        <el-form-item label="新地理位置" prop="isNewLocation">
          <el-input v-model="form.isNewLocation" placeholder="请输入新地理位置" />
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
import { listLogin_records, getLogin_records, delLogin_records, addLogin_records, updateLogin_records } from "@/api/app/login_records"

export default {
  name: "Login_records",
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
      // 用户登录记录表格数据
      login_recordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        loginTime: null,
        ipAddress: null,
        deviceType: null,
        deviceInfo: null,
        browserInfo: null,
        osInfo: null,
        userAgent: null,
        locationCountry: null,
        locationRegion: null,
        locationCity: null,
        loginMethod: null,
        success: null,
        failureReason: null,
        sessionId: null,
        twoFactorUsed: null,
        loginDuration: null,
        isNewDevice: null,
        isNewLocation: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询用户登录记录列表 */
    getList() {
      this.loading = true
      listLogin_records(this.queryParams).then(response => {
        this.login_recordsList = response.rows
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
        userId: null,
        loginTime: null,
        ipAddress: null,
        deviceType: null,
        deviceInfo: null,
        browserInfo: null,
        osInfo: null,
        userAgent: null,
        locationCountry: null,
        locationRegion: null,
        locationCity: null,
        loginMethod: null,
        success: null,
        failureReason: null,
        sessionId: null,
        twoFactorUsed: null,
        loginDuration: null,
        isNewDevice: null,
        isNewLocation: null,
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
      this.title = "添加用户登录记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getLogin_records(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改用户登录记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLogin_records(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addLogin_records(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户登录记录编号为"' + ids + '"的数据项？').then(function() {
        return delLogin_records(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('app/login_records/export', {
        ...this.queryParams
      }, `login_records_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
