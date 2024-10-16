<!--
  该模版是用来调试静态JSON情况的，不和后台服务挂钩，全部都是静态参数，调试完毕之后，可以将代码迁移至server-crud.vue中
-->
<template>
  <div>
    <!--    <el-card class="basic-container__card">-->
    <h3>固定的JSON参数测试</h3>
    <!--
    @search-change : 搜索发生改变回调的方法   https://avuejs.com/crud/crud-search.html#%E6%99%AE%E9%80%9A%E7%94%A8%E6%B3%95
    -->
    <avue-crud :option="option"
               :page="page"
               ref="crud"
               :data="data"
               @row-update="updateBefore" @row-del="delBefore" @search-change="listAfter">
      <!-- https://avuejs.com/crud/crud-btn.html 参考 -->
      <!--
        slot取值的几种方式:
          - menu : 每一行的操作位置
          - menuForm : 新增修改右下角的位置 -> 新增->  v-if="type=='add'"  修改-> v-if="type=='edit'"
          - menuLeft: 表格左上角的新增位置
          - menuRight : 表格的右上角内容
          - searchMenu: 搜索的位置 > https://avuejs.com/crud/crud-search.html#%E6%99%AE%E9%80%9A%E7%94%A8%E6%B3%95
      -->
      <template v-if="option.tableRowButtons && option.tableRowButtons.length > 0" slot-scope="{row,index}" slot="menu">
        <span v-for="item in option.tableRowButtons">
<!--          <el-button type="primary" :disabled="index==0" @click="$refs.crud.rowEdit(row,index)">编辑</el-button>-->

          <el-button :type="item.type || 'text'"
                     :icon="item.icon || 'el-icon-edit'"
                     :size="item.size || 'small'"
                     plain
                     @click="btnClick(item,row,index)">{{ item.btnName }}
          </el-button>
           <router-link v-if="item.methodName === 'openTabLink'"
                        :to="btnClickOpenTab(item,row,index)">
                       <el-button :type="item.type || 'text'"
                                  :icon="item.icon || 'el-icon-edit'"
                                  :size="item.size || 'small'"
                                  plain>
                         {{ item.btnName }}
                        </el-button>
           </router-link>
        </span>
      </template>

      <template v-if="option.tableTopLeftButtons && option.tableTopLeftButtons.length>0" slot-scope="{row,index}"
                slot="menuLeft">
        <span v-for="item in option.tableTopLeftButtons">
          <el-button :type="item.type || 'text'"
                     :icon="item.icon || 'el-icon-edit'"
                     :size="item.size || 'small'"
                     @click="btnClick(item,row,index)">{{ item.btnName }}
          </el-button>
        </span>
      </template>

      <template v-if="option.tableTopRightButtons && option.tableTopRightButtons.length > 0" slot-scope="{row,index}"
                slot="menuRight">
        <span v-for="item in option.tableTopRightButtons">
          <el-button :type="item.type || 'text'"
                     :icon="item.icon || 'el-icon-edit'"
                     :size="item.size || 'small'"
                     plain
                     @click="btnClick(item,row,index)">{{ item.btnName }}
          </el-button>
        </span>
      </template>

      <!-- 表单层 特定属性 -->
      <template
        slot-scope="{ column,value,size,disabled,type}" v-for="item in option.registerFieldComponents"
        :slot="getSlotName(item)">
        <el-tag>{{ column }}</el-tag>
        <!-- 引入新的组件  json  JSON.stringify(jsonStr,null,2)-->
        <span v-if="column.type === 'json'">
          <b-code-editor :name="column.prop" :value="getJsonString(value)"
                         :smart-indent="true"
                         theme="idea"
                         :indent-unit="4" :lint="true"
                         :line-wrap="false" ref="editor"
                         @on-change="onJsonChange(column.prop,value)"
                         @on-init="initJsonValue(column.prop,value)"
                         @on-blur="jsonFormat(column.prop)"
          >
          </b-code-editor>
          <p>
            <el-button @click="jsonFormat(column.prop)">手动触发格式化</el-button>
          </p>
      </span>

        <span v-if="column.type === 'table'">
          <el-tag>{{ value }}</el-tag>
          <avue-crud ref="crud" :option="column.children" :data="value || []" @row-update="updateBefore"
                     @row-del="delBefore"
                     @search-change="listAfter">
          </avue-crud>
        </span>
      </template>

    </avue-crud>

    <avue-dialog ref="avueDialogForm" :dialog-option="dialogConfig.dialogOption"
                 :show-dialog-props="dialogConfig.showDialogProps"
                 :form-option="dialogConfig.formOption" :object-data="dialogConfig.objectData"
                 @submit="testDialogClickSubmit" @closeDialog="testDialogClickClose" @resetForm="testDialogResetForm">
    </avue-dialog>

  </div>
</template>

<script>

import * as eventMethod from "@/api/crud/event/rowClickEvent.js"
import aVueDialog from "../avue-dialog.vue"
import dialogSubmitEvent from "@/api/crud/event/dialogSubmitEvent.js"
// 注册组件

const jsonData = `{
    "employees": [{
      "firstName": "Bill",
      "lastName": "Gates"
    }, {
      "firstName": "George",
      "lastName": "Bush"
    }, {
      "firstName": "Thomas",
      "lastName": "Carter"
    }]
  }`

export default {
  beforeCreate() {

  },
  components: {
    'avue-dialog': aVueDialog
  },
  created() {
    let option = this.option;
    let column = option.column;
    let _self = this;

  },
  data() {
    return {
      jsonTemp: "",
      jsonStr: jsonData,
      dialogConfig: {
        config: {},
        showDialogProps: false,
        formOption: {},
        objectData: {},
        dialogOption: {}
      },
      "customize": {
        "description": "负责维护一些缓存的开关，控制某些key是否动态走缓存。",
        "groupKey": "dynamicCacheList",
        "img": "https://avuejs.com/images/logo.png",
        "title": "动态缓存key维护"
      },

      "option": {
        "addBtn": true,
        "addBtnIcon": "",
        "addBtnText": "新增",
        "addTitle": "新增",
        "border": false,
        "cancelBtn": true,
        "cancelBtnIcon": "",
        "cancelBtnText": "取消",
        "columnBtn": true,
        "copyBtn": false,
        "defaultExpandAll": false,
        // "defaultSort": "ascending",
        "delBtn": true,
        "delBtnIcon": "",
        "delBtnText": "删除",
        "dialogClickModal": false,
        "dialogCloseBtn": true,
        "dialogCustomClass": "",
        "dialogDirection": "rtl",
        "dialogDrag": false,
        "dialogEscape": true,
        "dialogFullscreen": false,
        "dialogMenuPosition": "right",
        "dialogModal": true,
        "dialogTop": 25,
        "dialogType": "dialog",
        "dialogWidth": "60%",
        "editBtn": true,
        "editBtnIcon": "",
        "editBtnText": "修改",
        "editTitle": "修改",
        "emptyBtn": true,
        "emptyBtnIcon": "",
        "emptyBtnText": "清空",
        "emptyText": "暂无数据",
        "enter": false,
        "excelBtn": false,
        "expand": false,
        "expandClassName": "",
        "expandFixed": "true",
        "expandLabelClassName": "",
        "expandWidth": 50,
        "filterBtn": false,
        "fit": true,
        "gridBackground": "",
        "gridBackgroundImage": "",
        "gridBtn": true,
        "gridSpan": 8,
        "header": false,
        "height": "",
        "highlightCurrentRow": false,
        "index": false,
        "indexClassName": "",
        "indexFixed": "true",
        "indexLabelClassName": "",
        "indexWidth": 50,
        "labelPosition": "left",
        "labelSuffix": ":",
        "labelWidth": "80",
        "lazy": false,
        "menu": true,
        "menuAlign": "center",
        "menuBtn": true,
        "menuClassName": "",
        "menuFixed": "true",
        "menuHeaderAlign": "center",
        "menuLabelClassName": "",
        "menuPosition": "center",
        "menuSpan": 24,
        "menuTitle": "标题",
        "menuType": "text",
        "menuWidth": 220,
        "printBtn": false,
        "refreshBtn": true,
        "reserveSelection": false,
        "rowKey": "id",
        "rowParentKey": "parentId",
        "saveBtn": false,
        "saveBtnIcon": "",
        "saveBtnText": "保存",
        "searchBtn": true,
        "searchBtnIcon": "",
        "searchBtnText": "搜索",
        "searchIcon": false,
        "searchIndex": 2,
        "searchLabelPosition": "left",
        "searchLabelWidth": 80,
        "searchMenuPosition": "center",
        "searchPlaceholder": "",
        "searchShow": true,
        "searchShowBtn": true,
        "searchSpan": 8,
        "selection": false,
        "selectionClassName": "",
        "selectionFixed": "true",
        "selectionLabelClassName": "",
        "selectionWidth": 50,
        "showHeader": true,
        "showOverflowTooltip": false,
        "showSummary": false,
        "size": "small",
        "stripe": false,
        "submitBtn": true,
        "submitText": "提交",
        "tabs": false,
        "title": "动态缓存key维护111",
        "tooltipEffect": "dark",
        // "tooltipOptions": "left",
        "updateBtn": true,
        "updateBtnIcon": "",
        "updateBtnText": "修改",
        "viewBtn": true,
        "viewBtnIcon": "",
        "viewBtnText": "查看",
        "viewTitle": "查看",
        "column": [
          {
            "addDetail": false,
            "addDisabled": false,
            "addDisplay": true,
            "align": "left",
            "bind": "",
            "className": "",
            "clearable": false,
            "disabled": false,
            "display": false,
            "editDetail": false,
            "editDisabled": false,
            "editDisplay": true,
            "filters": false,
            "fixed": "",
            "headerAlign": "left",
            "hide": false,
            "label": "缓存key",
            "labelClassName": "",
            "labelTip": "",
            "maxRows": 4,
            "minRows": 2,
            "onBlur": "",
            "onChange": "",
            "onClick": "",
            "onFocus": "",
            "order": 0,
            "placeholder": "",
            "prefixIcon": "",
            "prop": "cacheKey",
            "readonly": false,
            "row": true,
            "rules": [
              {
                "message": "缓存key 是必填项!",
                "required": true
              }
            ],
            "search": true,
            "searchRequired": false,
            "searchRequireds": [],
            "showPassword": true,
            "showWordLimit": true,
            "sortable": false,
            "span": 12,
            "suffixIcon": "",
            "tip": "",
            "type": "input",
            "width": "",
            "searchRules": [
              {
                "message": "缓存key 是必填项!",
                "required": true
              }
            ]
          },
          {
            "addDetail": false,
            "addDisabled": false,
            "addDisplay": true,
            "align": "left",
            "bind": "",
            "className": "",
            "clearable": false,
            "disabled": false,
            "display": false,
            "editDetail": false,
            "editDisabled": false,
            "editDisplay": true,
            "filters": false,
            "fixed": "",
            "headerAlign": "left",
            "hide": false,
            "label": "是否开启",
            "labelClassName": "",
            "labelTip": "",
            "maxRows": 4,
            "minRows": 2,
            "onBlur": "",
            "onChange": "",
            "onClick": "",
            "onFocus": "",
            "order": 0,
            "placeholder": "",
            "prefixIcon": "",
            "prop": "cacheValue",
            "readonly": false,
            "row": true,
            "rules": [
              {
                "message": "是否开启 是必填项!",
                "required": true
              }
            ],
            "search": false,
            "searchRequired": false,
            "searchRequireds": [],
            "showPassword": true,
            "showWordLimit": true,
            "sortable": false,
            "span": 12,
            "suffixIcon": "",
            "tip": "",
            "type": "input",
            "width": "",
            "value": false,
            "searchRules": [
              {
                "message": "是否开启 是必填项!",
                "required": true
              }
            ]
          }
        ]
      },
      "config": {
        "del": "",
        "list": "/debug/avue/dynamicCacheList",
        "messageField": "message",
        "save": "/debug/avue/editDynamicCache",
        "successField": "success",
        "successKeyword": "true",
        "update": "/debug/avue/editDynamicCache"
      }
    };
  }, methods: {
    commitUpdate(row, index, done, loading) {
      console.log("提交更新", row);
      this.$message.success('修改成功')
    },
    eventDel(row, index) {
    },
    //列表后操作方法
    listAfter(row, done) {
      debugger;
    },

    //新增前操作方法
    addBefore(row, done, loading, data) {
      this.form.createUser = 'small'
      debugger;
    },
    //新增后操作方法
    addAfter(row, done, loading, data) {
      this.$message.success('新增成功');
    },
    //修改前操作方法
    updateBefore(form, index, done, loading) {
      this.form.updateUser = 'small';
      debugger;
    },

    //修改后操作方法
    updateAfter() {
    },

    //删除前操作方法
    delBefore(row, done, loading, data) {
      debugger;
      this.$message.success("点击了删除按钮 : " + JSON.stringify(row));
    },

    //删除后操作方法
    delAfter(row, done, loading, data) {
      debugger;
      this.$message.success("点击了删除按钮 : " + JSON.stringify(row));
    },
    // 按钮点击触发的方法
    btnClick(item, row, index) {
      eventMethod[item.methodName](this, item, row, index);
    },
    btnClickOpenTab(item, row, index) {
      return eventMethod[item.methodName](this, item, row, index);
    },
    getSlotName(item) {
      return item + "Form";
    }
    , onJsonChange(name, value) {
      this.jsonTemp = value;
      debugger;
    }
    , initJsonValue(name, value) {
      if (value) {
        this.jsonFormat(name);
      }
    }
    , getJsonString(value) {
      if (!value) {
        return "";
      }
      if (value instanceof Object) {
        return JSON.stringify(value);
      }
      return value;
    },
    jsonFormat(name) {
      let self = this;
      let jsonObject = self.$refs['editor'];
      for (let i = 0; i < jsonObject.length; i++) {
        let attrName = jsonObject[i].$attrs['name'];
        if (name === attrName) {
          jsonObject[i].formatCode();
        }
      }
      debugger;
    },
    getTestJsonValue(val) {
      if (val) {
        debugger;
      }

      return val;
    },

    openDialog() {
      this.dialogConfig.showDialogProps = true;
    },
    closeDialog() {
      this.dialogConfig.showDialogProps = false;
    },
    btnTestClick(value, column) {
      this.$message.success('click事件查看控制台')
      console.log('点击事件', value, column)
    },
    testDialogClickSubmit(row, hide) {
      dialogSubmitEvent[this.dialogConfig.config["submitEventName"] || 'defaultSubmit'](this, row, hide);
    },
    testDialogClickClose() {
      this.closeDialog();
    },
    testDialogResetForm() {

    }
  }
}
;
</script>

<style lang="scss">
</style>
