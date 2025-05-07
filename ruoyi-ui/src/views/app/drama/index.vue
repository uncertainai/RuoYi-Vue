<template>
  <div class="app-container">
    <el-tabs v-model="activeTab" type="border-card">
      <!-- 基本信息标签页 -->
      <el-tab-pane label="基本信息" name="basic">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="标题" prop="title">
            <el-input
              v-model="queryParams.title"
              placeholder="请输入标题"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="类型" prop="genreId">
            <el-input
              v-model="queryParams.genreId"
              placeholder="请输入类型"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="发布日期" prop="releaseDate">
            <el-date-picker clearable
              v-model="queryParams.releaseDate"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择发布日期">
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
              v-hasPermi="['app:drama:add']"
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
              v-hasPermi="['app:drama:edit']"
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
              v-hasPermi="['app:drama:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['app:drama:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="dramaList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="主键" align="center" prop="id" />
          <el-table-column label="标题" align="center" prop="title" />
          <el-table-column label="类型" align="center" prop="genreId" />
          <el-table-column label="发布日期" align="center" prop="releaseDate" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.releaseDate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="语种数量" align="center" width="100">
            <template slot-scope="scope">
              <el-button
                type="text"
                @click="handleViewLanguages(scope.row)"
              >{{ getLanguageCount(scope.row.id) }}</el-button>
            </template>
          </el-table-column>
          <el-table-column label="开放地区" align="center" width="120">
            <template slot-scope="scope">
              <el-button
                type="text"
                @click="handleManageRegions(scope.row)"
              >{{ getRegionCount(scope.row.id) }}</el-button>
            </template>
          </el-table-column>
          <el-table-column label="观看记录" align="center" width="100">
            <template slot-scope="scope">
              <el-button
                type="text"
                @click="handleViewRecords(scope.row)"
              >{{ getWatchCount(scope.row.id) }}</el-button>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['app:drama:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['app:drama:remove']"
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
      </el-tab-pane>

      <!-- 内容管理标签页 -->
      <el-tab-pane label="内容管理" name="contents">
        <el-form :model="contentQueryParams" ref="contentQueryForm" size="small" :inline="true" v-show="showContentSearch" label-width="68px">
          <el-form-item label="剧目" prop="dramaId">
            <el-input
              v-model="contentQueryParams.dramaId"
              placeholder="请输入剧目"
              clearable
              @keyup.enter.native="handleContentQuery"
            />
          </el-form-item>
          <el-form-item label="语言" prop="languageCode">
            <el-input
              v-model="contentQueryParams.languageCode"
              placeholder="请输入语言"
              clearable
              @keyup.enter.native="handleContentQuery"
            />
          </el-form-item>
          <el-form-item label="标题" prop="title">
            <el-input
              v-model="contentQueryParams.title"
              placeholder="请输入标题"
              clearable
              @keyup.enter.native="handleContentQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleContentQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetContentQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleContentAdd"
              v-hasPermi="['app:drama_contents:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="contentSingle"
              @click="handleContentUpdate"
              v-hasPermi="['app:drama_contents:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="contentMultiple"
              @click="handleContentDelete"
              v-hasPermi="['app:drama_contents:remove']"
            >删除</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showContentSearch" @queryTable="getContentList"></right-toolbar>
        </el-row>

        <el-table v-loading="contentLoading" :data="contentList" @selection-change="handleContentSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="主键" align="center" prop="id" />
          <el-table-column label="剧目" align="center" prop="dramaId" />
          <el-table-column label="语言" align="center" prop="languageCode" />
          <el-table-column label="标题" align="center" prop="title" />
          <el-table-column label="描述" align="center" prop="description" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleContentUpdate(scope.row)"
                v-hasPermi="['app:drama_contents:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleContentDelete(scope.row)"
                v-hasPermi="['app:drama_contents:remove']"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        
        <pagination
          v-show="contentTotal>0"
          :total="contentTotal"
          :page.sync="contentQueryParams.pageNum"
          :limit.sync="contentQueryParams.pageSize"
          @pagination="getContentList"
        />
      </el-tab-pane>

      <!-- 显示规则标签页 -->
      <el-tab-pane label="显示规则" name="rules">
        <el-form :model="rulesQueryParams" ref="rulesQueryForm" size="small" :inline="true" v-show="showRulesSearch" label-width="68px">
          <el-form-item label="剧目" prop="dramaId">
            <el-input
              v-model="rulesQueryParams.dramaId"
              placeholder="请输入剧目"
              clearable
              @keyup.enter.native="handleRulesQuery"
            />
          </el-form-item>
          <el-form-item label="国家编码" prop="countryCode">
            <el-input
              v-model="rulesQueryParams.countryCode"
              placeholder="请输入国家编码"
              clearable
              @keyup.enter.native="handleRulesQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleRulesQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetRulesQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleRulesAdd"
              v-hasPermi="['app:drama_rules:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="rulesSingle"
              @click="handleRulesUpdate"
              v-hasPermi="['app:drama_rules:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="rulesMultiple"
              @click="handleRulesDelete"
              v-hasPermi="['app:drama_rules:remove']"
            >删除</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showRulesSearch" @queryTable="getRulesList"></right-toolbar>
        </el-row>

        <el-table v-loading="rulesLoading" :data="rulesList" @selection-change="handleRulesSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="主键" align="center" prop="id" />
          <el-table-column label="剧目" align="center" prop="dramaId" />
          <el-table-column label="国家编码" align="center" prop="countryCode" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleRulesUpdate(scope.row)"
                v-hasPermi="['app:drama_rules:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleRulesDelete(scope.row)"
                v-hasPermi="['app:drama_rules:remove']"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        
        <pagination
          v-show="rulesTotal>0"
          :total="rulesTotal"
          :page.sync="rulesQueryParams.pageNum"
          :limit.sync="rulesQueryParams.pageSize"
          @pagination="getRulesList"
        />
      </el-tab-pane>

      <!-- 观看记录标签页 -->
      <el-tab-pane label="观看记录" name="records">
        <el-form :model="recordsQueryParams" ref="recordsQueryForm" size="small" :inline="true" v-show="showRecordsSearch" label-width="68px">
          <el-form-item label="用户" prop="userId">
            <el-input
              v-model="recordsQueryParams.userId"
              placeholder="请输入用户"
              clearable
              @keyup.enter.native="handleRecordsQuery"
            />
          </el-form-item>
          <el-form-item label="剧目" prop="dramaId">
            <el-input
              v-model="recordsQueryParams.dramaId"
              placeholder="请输入剧目"
              clearable
              @keyup.enter.native="handleRecordsQuery"
            />
          </el-form-item>
          <el-form-item label="第几秒" prop="dramaSecond">
            <el-input
              v-model="recordsQueryParams.dramaSecond"
              placeholder="请输入第几秒"
              clearable
              @keyup.enter.native="handleRecordsQuery"
            />
          </el-form-item>
          <el-form-item label="观看时间" prop="watchedAt">
            <el-date-picker clearable
              v-model="recordsQueryParams.watchedAt"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择观看时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleRecordsQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetRecordsQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-table v-loading="recordsLoading" :data="recordsList" @selection-change="handleRecordsSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="主键id" align="center" prop="id" />
          <el-table-column label="用户" align="center" prop="userId" />
          <el-table-column label="剧目" align="center" prop="dramaId" />
          <el-table-column label="第几秒" align="center" prop="dramaSecond" />
          <el-table-column label="观看时间" align="center" prop="watchedAt" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.watchedAt, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleRecordsUpdate(scope.row)"
                v-hasPermi="['app:watch_records:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleRecordsDelete(scope.row)"
                v-hasPermi="['app:watch_records:remove']"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        
        <pagination
          v-show="recordsTotal>0"
          :total="recordsTotal"
          :page.sync="recordsQueryParams.pageNum"
          :limit.sync="recordsQueryParams.pageSize"
          @pagination="getRecordsList"
        />
      </el-tab-pane>
    </el-tabs>

    <!-- 添加或修改剧目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="类型" prop="genreId">
          <el-input v-model="form.genreId" placeholder="请输入类型" />
        </el-form-item>
        <el-form-item label="发布日期" prop="releaseDate">
          <el-date-picker clearable
            v-model="form.releaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改剧目内容对话框 -->
    <el-dialog :title="contentTitle" :visible.sync="contentOpen" width="500px" append-to-body>
      <el-form ref="contentForm" :model="contentForm" :rules="contentRules" label-width="80px">
        <el-form-item label="剧目" prop="dramaId">
          <el-input v-model="contentForm.dramaId" placeholder="请输入剧目" />
        </el-form-item>
        <el-form-item label="语言" prop="languageCode">
          <el-input v-model="contentForm.languageCode" placeholder="请输入语言" />
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="contentForm.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="contentForm.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitContentForm">确 定</el-button>
        <el-button @click="cancelContent">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改剧目显示规则对话框 -->
    <el-dialog :title="rulesTitle" :visible.sync="rulesOpen" width="500px" append-to-body>
      <el-form ref="rulesForm" :model="rulesForm" :rules="rulesRules" label-width="80px">
        <el-form-item label="剧目" prop="dramaId">
          <el-input v-model="rulesForm.dramaId" placeholder="请输入剧目" />
        </el-form-item>
        <el-form-item label="国家编码" prop="countryCode">
          <el-input v-model="rulesForm.countryCode" placeholder="请输入国家编码" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRulesForm">确 定</el-button>
        <el-button @click="cancelRules">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改观看记录对话框 -->
    <el-dialog :title="recordsTitle" :visible.sync="recordsOpen" width="500px" append-to-body>
      <el-form ref="recordsForm" :model="recordsForm" :rules="recordsRules" label-width="80px">
        <el-form-item label="用户" prop="userId">
          <el-input v-model="recordsForm.userId" placeholder="请输入用户" />
        </el-form-item>
        <el-form-item label="剧目" prop="dramaId">
          <el-input v-model="recordsForm.dramaId" placeholder="请输入剧目" />
        </el-form-item>
        <el-form-item label="第几秒" prop="dramaSecond">
          <el-input v-model="recordsForm.dramaSecond" placeholder="请输入第几秒" />
        </el-form-item>
        <el-form-item label="观看时间" prop="watchedAt">
          <el-date-picker clearable
            v-model="recordsForm.watchedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择观看时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRecordsForm">确 定</el-button>
        <el-button @click="cancelRecords">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 语种列表对话框 -->
    <el-dialog :title="languageTitle" :visible.sync="languageOpen" width="800px" append-to-body>
      <el-table v-loading="languageLoading" :data="languageList">
        <el-table-column label="语言" align="center" prop="languageCode" />
        <el-table-column label="标题" align="center" prop="title" />
        <el-table-column label="描述" align="center" prop="description" />
      </el-table>
    </el-dialog>

    <!-- 地区管理对话框 -->
    <el-dialog :title="regionTitle" :visible.sync="regionOpen" width="800px" append-to-body>
      <el-form :model="regionForm" ref="regionForm" :rules="regionRules" label-width="80px">
        <el-form-item label="国家编码" prop="countryCode">
          <el-input v-model="regionForm.countryCode" placeholder="请输入国家编码" />
        </el-form-item>
      </el-form>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleRegionAdd"
          >新增</el-button>
        </el-col>
      </el-row>
      <el-table v-loading="regionLoading" :data="regionList">
        <el-table-column label="国家编码" align="center" prop="countryCode" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleRegionDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelRegion">关 闭</el-button>
      </div>
    </el-dialog>

    <!-- 观看记录列表对话框 -->
    <el-dialog :title="watchTitle" :visible.sync="watchOpen" width="800px" append-to-body>
      <el-table v-loading="watchLoading" :data="watchList">
        <el-table-column label="用户" align="center" prop="userId" />
        <el-table-column label="第几秒" align="center" prop="dramaSecond" />
        <el-table-column label="观看时间" align="center" prop="watchedAt" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.watchedAt, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { listDrama, getDrama, delDrama, addDrama, updateDrama } from "@/api/app/drama"
import { listDrama_contents, getDrama_contents, delDrama_contents, addDrama_contents, updateDrama_contents } from "@/api/app/drama_contents"
import { listDrama_rules, getDrama_rules, delDrama_rules, addDrama_rules, updateDrama_rules } from "@/api/app/drama_rules"
import { listWatch_records, getWatch_records, delWatch_records, addWatch_records, updateWatch_records } from "@/api/app/watch_records"

export default {
  name: "Drama",
  data() {
    return {
      // 当前激活的标签页
      activeTab: "basic",
      // 遮罩层
      loading: true,
      contentLoading: true,
      rulesLoading: true,
      recordsLoading: true,
      // 选中数组
      ids: [],
      contentIds: [],
      rulesIds: [],
      recordsIds: [],
      // 非单个禁用
      single: true,
      contentSingle: true,
      rulesSingle: true,
      recordsSingle: true,
      // 非多个禁用
      multiple: true,
      contentMultiple: true,
      rulesMultiple: true,
      recordsMultiple: true,
      // 显示搜索条件
      showSearch: true,
      showContentSearch: true,
      showRulesSearch: true,
      showRecordsSearch: true,
      // 总条数
      total: 0,
      contentTotal: 0,
      rulesTotal: 0,
      recordsTotal: 0,
      // 剧目表格数据
      dramaList: [],
      contentList: [],
      rulesList: [],
      recordsList: [],
      // 弹出层标题
      title: "",
      contentTitle: "",
      rulesTitle: "",
      recordsTitle: "",
      // 是否显示弹出层
      open: false,
      contentOpen: false,
      rulesOpen: false,
      recordsOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        genreId: null,
        releaseDate: null,
      },
      contentQueryParams: {
        pageNum: 1,
        pageSize: 10,
        dramaId: null,
        languageCode: null,
        title: null,
      },
      rulesQueryParams: {
        pageNum: 1,
        pageSize: 10,
        dramaId: null,
        countryCode: null,
      },
      recordsQueryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        dramaId: null,
        dramaSecond: null,
        watchedAt: null,
      },
      // 表单参数
      form: {},
      contentForm: {},
      rulesForm: {},
      recordsForm: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
      },
      contentRules: {
        dramaId: [
          { required: true, message: "剧目不能为空", trigger: "blur" }
        ],
        languageCode: [
          { required: true, message: "语言不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
      },
      rulesRules: {
        dramaId: [
          { required: true, message: "剧目不能为空", trigger: "blur" }
        ],
        countryCode: [
          { required: true, message: "国家编码不能为空", trigger: "blur" }
        ],
      },
      recordsRules: {
        userId: [
          { required: true, message: "用户不能为空", trigger: "blur" }
        ],
        dramaId: [
          { required: true, message: "剧目不能为空", trigger: "blur" }
        ],
        dramaSecond: [
          { required: true, message: "第几秒不能为空", trigger: "blur" }
        ],
      },
      // 语种相关
      languageOpen: false,
      languageTitle: "",
      languageLoading: false,
      languageList: [],
      // 地区相关
      regionOpen: false,
      regionTitle: "",
      regionLoading: false,
      regionList: [],
      regionForm: {
        dramaId: null,
        countryCode: null
      },
      regionRules: {
        countryCode: [
          { required: true, message: "国家编码不能为空", trigger: "blur" }
        ]
      },
      // 观看记录相关
      watchOpen: false,
      watchTitle: "",
      watchLoading: false,
      watchList: [],
      // 统计数据
      languageCounts: {},
      regionCounts: {},
      watchCounts: {}
    }
  },
  created() {
    this.getList()
    this.getContentList()
    this.getRulesList()
    this.getRecordsList()
  },
  methods: {
    /** 查询剧目列表 */
    getList() {
      this.loading = true
      listDrama(this.queryParams).then(response => {
        this.dramaList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 查询剧目内容列表 */
    getContentList() {
      this.contentLoading = true
      listDrama_contents(this.contentQueryParams).then(response => {
        this.contentList = response.rows
        this.contentTotal = response.total
        this.contentLoading = false
        this.updateStatistics()
      })
    },
    /** 查询剧目显示规则列表 */
    getRulesList() {
      this.rulesLoading = true
      listDrama_rules(this.rulesQueryParams).then(response => {
        this.rulesList = response.rows
        this.rulesTotal = response.total
        this.rulesLoading = false
        this.updateStatistics()
      })
    },
    /** 查询观看记录列表 */
    getRecordsList() {
      this.recordsLoading = true
      listWatch_records(this.recordsQueryParams).then(response => {
        this.recordsList = response.rows
        this.recordsTotal = response.total
        this.recordsLoading = false
        this.updateStatistics()
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    cancelContent() {
      this.contentOpen = false
      this.resetContent()
    },
    cancelRules() {
      this.rulesOpen = false
      this.resetRules()
    },
    cancelRecords() {
      this.recordsOpen = false
      this.resetRecords()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        genreId: null,
        releaseDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    resetContent() {
      this.contentForm = {
        id: null,
        dramaId: null,
        languageCode: null,
        title: null,
        description: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm("contentForm")
    },
    resetRules() {
      this.rulesForm = {
        id: null,
        dramaId: null,
        countryCode: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm("rulesForm")
    },
    resetRecords() {
      this.recordsForm = {
        id: null,
        userId: null,
        dramaId: null,
        dramaSecond: null,
        watchedAt: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm("recordsForm")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    handleContentQuery() {
      this.contentQueryParams.pageNum = 1
      this.getContentList()
    },
    handleRulesQuery() {
      this.rulesQueryParams.pageNum = 1
      this.getRulesList()
    },
    handleRecordsQuery() {
      this.recordsQueryParams.pageNum = 1
      this.getRecordsList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    resetContentQuery() {
      this.resetForm("contentQueryForm")
      this.handleContentQuery()
    },
    resetRulesQuery() {
      this.resetForm("rulesQueryForm")
      this.handleRulesQuery()
    },
    resetRecordsQuery() {
      this.resetForm("recordsQueryForm")
      this.handleRecordsQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleContentSelectionChange(selection) {
      this.contentIds = selection.map(item => item.id)
      this.contentSingle = selection.length!==1
      this.contentMultiple = !selection.length
    },
    handleRulesSelectionChange(selection) {
      this.rulesIds = selection.map(item => item.id)
      this.rulesSingle = selection.length!==1
      this.rulesMultiple = !selection.length
    },
    handleRecordsSelectionChange(selection) {
      this.recordsIds = selection.map(item => item.id)
      this.recordsSingle = selection.length!==1
      this.recordsMultiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加剧目"
    },
    handleContentAdd() {
      this.resetContent()
      this.contentOpen = true
      this.contentTitle = "添加剧目内容"
    },
    handleRulesAdd() {
      this.resetRules()
      this.rulesOpen = true
      this.rulesTitle = "添加剧目显示规则"
    },
    handleRecordsAdd() {
      this.resetRecords()
      this.recordsOpen = true
      this.recordsTitle = "添加观看记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getDrama(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改剧目"
      })
    },
    handleContentUpdate(row) {
      this.resetContent()
      const id = row.id || this.contentIds
      getDrama_contents(id).then(response => {
        this.contentForm = response.data
        this.contentOpen = true
        this.contentTitle = "修改剧目内容"
      })
    },
    handleRulesUpdate(row) {
      this.resetRules()
      const id = row.id || this.rulesIds
      getDrama_rules(id).then(response => {
        this.rulesForm = response.data
        this.rulesOpen = true
        this.rulesTitle = "修改剧目显示规则"
      })
    },
    handleRecordsUpdate(row) {
      this.resetRecords()
      const id = row.id || this.recordsIds
      getWatch_records(id).then(response => {
        this.recordsForm = response.data
        this.recordsOpen = true
        this.recordsTitle = "修改观看记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDrama(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addDrama(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    submitContentForm() {
      this.$refs["contentForm"].validate(valid => {
        if (valid) {
          if (this.contentForm.id != null) {
            updateDrama_contents(this.contentForm).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.contentOpen = false
              this.getContentList()
            })
          } else {
            addDrama_contents(this.contentForm).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.contentOpen = false
              this.getContentList()
            })
          }
        }
      })
    },
    submitRulesForm() {
      this.$refs["rulesForm"].validate(valid => {
        if (valid) {
          if (this.rulesForm.id != null) {
            updateDrama_rules(this.rulesForm).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.rulesOpen = false
              this.getRulesList()
            })
          } else {
            addDrama_rules(this.rulesForm).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.rulesOpen = false
              this.getRulesList()
            })
          }
        }
      })
    },
    submitRecordsForm() {
      this.$refs["recordsForm"].validate(valid => {
        if (valid) {
          if (this.recordsForm.id != null) {
            updateWatch_records(this.recordsForm).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.recordsOpen = false
              this.getRecordsList()
            })
          } else {
            addWatch_records(this.recordsForm).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.recordsOpen = false
              this.getRecordsList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除剧目编号为"' + ids + '"的数据项？').then(function() {
        return delDrama(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleContentDelete(row) {
      const ids = row.id || this.contentIds
      this.$modal.confirm('是否确认删除剧目内容编号为"' + ids + '"的数据项？').then(function() {
        return delDrama_contents(ids)
      }).then(() => {
        this.getContentList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleRulesDelete(row) {
      const ids = row.id || this.rulesIds
      this.$modal.confirm('是否确认删除剧目显示规则编号为"' + ids + '"的数据项？').then(function() {
        return delDrama_rules(ids)
      }).then(() => {
        this.getRulesList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleRecordsDelete(row) {
      const ids = row.id || this.recordsIds
      this.$modal.confirm('是否确认删除观看记录编号为"' + ids + '"的数据项？').then(function() {
        return delWatch_records(ids)
      }).then(() => {
        this.getRecordsList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('app/drama/export', {
        ...this.queryParams
      }, `drama_${new Date().getTime()}.xlsx`)
    },
    /** 获取语种数量 */
    getLanguageCount(dramaId) {
      return this.languageCounts[dramaId] || 0
    },
    /** 获取地区数量 */
    getRegionCount(dramaId) {
      return this.regionCounts[dramaId] || 0
    },
    /** 获取观看次数 */
    getWatchCount(dramaId) {
      return this.watchCounts[dramaId] || 0
    },
    /** 查看语种列表 */
    handleViewLanguages(row) {
      this.languageTitle = "语种列表 - " + row.title
      this.languageOpen = true
      this.languageLoading = true
      // 过滤出当前剧目的内容
      this.languageList = this.contentList.filter(item => item.dramaId === row.id)
      this.languageLoading = false
    },
    /** 管理地区 */
    handleManageRegions(row) {
      this.regionTitle = "地区管理 - " + row.title
      this.regionOpen = true
      this.regionLoading = true
      this.regionForm.dramaId = row.id
      // 过滤出当前剧目的规则
      this.regionList = this.rulesList.filter(item => item.dramaId === row.id)
      this.regionLoading = false
    },
    /** 查看观看记录 */
    handleViewRecords(row) {
      this.watchTitle = "观看记录 - " + row.title
      this.watchOpen = true
      this.watchLoading = true
      // 过滤出当前剧目的观看记录
      this.watchList = this.recordsList.filter(item => item.dramaId === row.id)
      this.watchLoading = false
    },
    /** 新增地区 */
    handleRegionAdd() {
      this.$refs["regionForm"].validate(valid => {
        if (valid) {
          addDrama_rules(this.regionForm).then(response => {
            this.$modal.msgSuccess("新增成功")
            this.getRulesList()
            this.regionForm.countryCode = null
          })
        }
      })
    },
    /** 删除地区 */
    handleRegionDelete(row) {
      this.$modal.confirm('是否确认删除该地区？').then(() => {
        return delDrama_rules(row.id)
      }).then(() => {
        this.getRulesList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 取消地区管理 */
    cancelRegion() {
      this.regionOpen = false
      this.regionForm = {
        dramaId: null,
        countryCode: null
      }
    },
    /** 更新统计数据 */
    updateStatistics() {
      // 更新语种数量
      this.languageCounts = {}
      this.contentList.forEach(item => {
        if (!this.languageCounts[item.dramaId]) {
          this.languageCounts[item.dramaId] = new Set()
        }
        this.languageCounts[item.dramaId].add(item.languageCode)
      })
      Object.keys(this.languageCounts).forEach(key => {
        this.languageCounts[key] = this.languageCounts[key].size
      })

      // 更新地区数量
      this.regionCounts = {}
      this.rulesList.forEach(item => {
        if (!this.regionCounts[item.dramaId]) {
          this.regionCounts[item.dramaId] = 0
        }
        this.regionCounts[item.dramaId]++
      })

      // 更新观看次数
      this.watchCounts = {}
      this.recordsList.forEach(item => {
        if (!this.watchCounts[item.dramaId]) {
          this.watchCounts[item.dramaId] = 0
        }
        this.watchCounts[item.dramaId]++
      })
    }
  }
}
</script>
