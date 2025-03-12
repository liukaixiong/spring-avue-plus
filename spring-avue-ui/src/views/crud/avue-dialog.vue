<template>
  <div>
    <el-dialog
        append-to-body
        :title="dialogOption.title || '弹窗操作'"
        :width="dialogOption.width || '50%'"
        :top="dialogOption.top || '50px' "
        v-model="showDialog"
        width="60%" @close='closeDialog'>
      <span v-if="dialogOption.type === undefined || dialogOption.type === 'form'">
        <avue-form ref="form" :modelValue="objectData" :option="formOption" @reset-change="resetForm" @submit="submit">
          <!--    #[item] -> https://avuejs.com/form/form-slot.html#%E8%87%AA%E5%AE%9A%E4%B9%89%E5%86%85%E5%AE%B9    -->
          <template #[item]="{column,value}" v-for="item in formOption.registerFieldComponents">
            <!--          &lt;!&ndash; 引入新的组件  json &ndash;&gt;-->
            <span v-if="column.type === 'json'">
              <json-editor-vue class="editor" :ref="'editor-'+column.prop" :name="column.prop"
                               :modelValue="JSON.parse(value|| '{}')"
                               @blur="jsonFormat(this,column.prop)"/>
            </span>
          </template>
        </avue-form>
      </span>

      <span v-if="dialogOption.type === 'table'">
       <avue-crud :data="objectData.data||[]"
                  :option="formOption||[]"
                  v-model:page="objectData.page"
                  v-model:search="objectData.search"
                  @search-change="searchChange"
                  @search-reset="searchChange"
                  @size-change="pageSizeChange"
                  @current-change="currentPageChange"
       ></avue-crud>
      </span>
      <!--      <json-editor-vue class="editor" ref="editor-" name="column.prop"-->
      <!--                       :modelValue="JSON.parse( '{}')"-->
      <!--                       @blur="jsonFormat(this,'{}')"/>-->
    </el-dialog>


  </div>
</template>

<script>
import crudUtil from "@/utils/server-crud"
import JsonEditorVue from 'json-editor-vue3'

export default {
  name: "avue-dialog",
  props: {
    // 是否显示弹层
    showDialogProps: {
      type: Boolean,
      required: false
    },
    // 展示默认值数据
    objectData: {
      type: Object,
      required: false,
      default: {}
    },
    // form的option参数
    formOption: {
      type: Object,
      required: true,
    },
    dialogOption: {
      default: {}
    }, getList: {
      required: false,
      type: Function
    }
  },
  data() {
    return {
      sizeValue: 'small',
      showDialog: false
    }
  },
  components: {
    JsonEditorVue
  },
  watch: {
    // 当关闭弹窗的时候，清空里面的值
    showDialogProps(val) {
      this.showDialog = val;
      if (!val && this.$refs.form) {
        this.$refs.form.resetForm();
      }
    }
  },
  computed: {},
  mounted() {
  },
  methods: {
    resetForm() {
      this.$emit("resetForm");
    },
    submit(row, hide) {
      this.$emit("submit", row, hide);
    }, closeDialog() {
      this.$emit("closeDialog");
    }, getJsonString(value) {
      return crudUtil.getJsonString(value);
    }, jsonFormat(name) {
      crudUtil.jsonFormat(this, name);
    }, getSlotFormName(item) {
      return crudUtil.getSlotFormName(item);
    },// 当前页码发生变化
    currentPageChange(val) {
      this.objectData.page.currentPage = val;
      this.objectData.getList();
    },
    // 当前页码发生变化
    pageSizeChange(val) {
      this.objectData.page.pageSize = val;
      this.objectData.getList();
    }, searchChange(params, done) {
      if (done) done();
      this.objectData.search = params;
      this.objectData.getList();
    }
  }
}
</script>
