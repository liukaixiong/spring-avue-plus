<template>
  <div>
    <el-dialog
        append-to-body
        :title="dialogOption.title || '弹窗操作'"
        :width="dialogOption.width || '50%'"
        :top="dialogOption.top || '50px' "
        v-model="showDialog"
        width="60%" @close='closeDialog'>
      <avue-form ref="form" :modelValue="objectData" :option="formOption" @reset-change="resetForm" @submit="submit">
        <template #[getSlotFormName(item)]="{column,value}" v-for="item in formOption.registerFieldComponents">
          <!--          &lt;!&ndash; 引入新的组件  json &ndash;&gt;-->
          <span v-if="column.type==='json'">
            <json-editor-vue class="editor" :ref="'editor-'+column.prop" :name="column.prop"
                             :modelValue="JSON.parse(value|| '{}')"
                             @blur="jsonFormat(this,column.prop)"/>
          </span>

        </template>
      </avue-form>
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
      if (!val) {
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
    }
  }
}
</script>
