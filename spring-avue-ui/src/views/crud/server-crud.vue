<template>
  <div>
    <avue-crud v-bind="bindVal"
               v-on="onEvent"
               v-model="form"
               ref="crud"
               :page.sync="page"
    >
      <template is="option.tableRowButtons && option.tableRowButtons.length > 0" #menu="{row,index}" slot="menu">
        <span v-for="item in option.tableRowButtons">
          <span v-if="item.methodName !== 'openTabLink'">
            <el-button :type="item.type || ''"
                       :icon="item.icon || 'el-icon-edit'"
                       :size="item.size || 'small'"
                       plain
                       @click="btnClick(item,row,index)">{{ item.btnName }}
            </el-button>
          </span>

          <span v-if="item.methodName === 'openTabLink'">
             <router-link :to="btnClickOpenTab(item,row,index)">
              <el-button :type="item.type || ''"
                         :icon="item.icon || 'el-icon-edit'"
                         :size="item.size || 'small'"
                         plain>
                {{ item.btnName }}
              </el-button>
            </router-link>
          </span>

        </span>
      </template>

      <template is="option.tableTopLeftButtons && option.tableTopLeftButtons.length > 0" #menu-left="{row,index}"
                slot="menuLeft">
        <span v-for="item in option.tableTopLeftButtons">
          <el-button :type="item.type || 'primary'"
                     :icon="item.icon || 'el-icon-edit'"
                     :size="item.size || 'small'"
                     @click="btnClick(item,row,index)">{{ item.btnName }}
          </el-button>
        </span>
      </template>

      <template is="option.tableTopRightButtons && option.tableTopRightButtons.length > 0" #menu-right="{row,index}"
                slot="menuRight">
        <span v-for="item in option.tableTopRightButtons">
          <el-button :type="item.type || 'primary'"
                     :icon="item.icon || 'el-icon-edit'"
                     :size="item.size || 'small'"
                     plain
                     @click="btnClick(item,row,index)">{{ item.btnName }}
          </el-button>
        </span>
      </template>


<!--      <template v-for="item in option.registerFieldComponents" #[getSlotFormName(item)]="{column,value}">-->
<!--        <span>{{ column }} - {{ value }} - {{ item }}</span>-->
<!--      </template>-->


      <!-- 表单层 值组件展示层 getSlotFormName 这里主要是为了通过函数匹配字段改变组件展示-->
      <template
          v-for="item in option.registerFieldComponents" #[getSlotFormName(item)]="{column,value}">
        <!-- 引入新的组件  json: https://github.com/guyue88/json-editor-vue3 -->
        <span v-if="column.type === 'json'">
            <span>{{ column }} - {{ value }} - {{ item }}</span>
            <json-editor-vue class="editor" ref="editor" :name="column.prop" :options="jsonEditorOption" :modelValue="JSON.parse(value|| '{}')" @blur="jsonFormat(column.prop)" />
          <!--                    <p>-->
          <!--            <el-button @click="jsonFormat(column.prop)">格式化验证JSON字符串</el-button>-->
          <!--          </p>-->
        </span>
        <span v-if="column.type === 'table'">
            <avue-crud ref="crud" :option="column.children" :data="value || []" v-on="onEvent" v-bind="bindVal">
                <template is="column.children.tableRowButtons && column.children.tableRowButtons.length > 0"
                          slot-scope="{row,index}" slot="menu">
                  <span v-for="nodeItem in column.children.tableRowButtons">
                    <el-button :type="nodeItem.type || ''"
                               :icon="nodeItem.icon || 'el-icon-edit'"
                               :size="nodeItem.size || 'small'"
                               plain
                               @click="btnClick(nodeItem,row,index)">{{ nodeItem.btnName }}
                    </el-button>
                  </span>
                </template>
            </avue-crud>
        </span>
      </template>
    </avue-crud>

    <!-- 弹层组件 -->
    <avue-dialog ref="avueDialogForm" :dialog-option="dialogConfig.dialogOption"
                 :show-dialog-props="dialogConfig.showDialogProps"
                 :form-option="dialogConfig.formOption" :object-data="dialogConfig.objectData"
                 @submit="componentsDialogClickSubmit" @closeDialog="componentsDialogClickClose"
                 @resetForm="componentsDialogResetForm">
    </avue-dialog>
  </div>
</template>

<script>

import * as eventMethod from "@/api/crud/event/rowClickEvent"
import crudUtil from "@/utils/server-crud"
import aVueDialog from "./avue-dialog.vue"
import dialogSubmitEvent from "@/api/crud/event/dialogSubmitEvent"
import serverCrud from '@/mixins/server-crud.js'
import JsonEditorVue from 'json-editor-vue3'

export default serverCrud({
      data() {
        return {
          jsonEditorOption:{
            onBlur: function(){
              debugger;
            }
          },
          dialogConfig: {
            config: {},
            showDialogProps: false,
            formOption: {},
            objectData: {},
            dialogOption: {}
          },
          option: {
            dialogDrag: true, // 这里会有一个延迟问题,如果通过接口返回的话,即便为true也不会生效
          }
        }
      },
      components: {
        'avue-dialog': aVueDialog,
        JsonEditorVue
      },
      methods: {
        //列表前操作方法
        async listBefore() {
        },

        //列表后操作方法
        listAfter() {
        },

        //新增前操作方法
        addBefore(form, index, done, loading) {
          let check = this.componentsBefore(form, done, loading);
          console.log("添加 提交后端的值:", form);
          return check;
        },
        //新增后操作方法
        addAfter(row, done, loading, data) {
          // this.processDML(data, done, loading);
          if (crudUtil.processDMLResponse(this, data)) {
            done();
          } else {
            loading();
          }
        },

        //修改前操作方法
        updateBefore(form, index, done, loading) {
          this.form.updateUser = 'small'
          let check = this.componentsBefore(form, done, loading);
          console.log("修改 提交后端的值:", form);
          return check;
        },

        //修改后操作方法
        updateAfter(row, index, done, loading, data) {
          // this.processDML(data, done, loading);
          if (crudUtil.processDMLResponse(this, data)) {
            done();
          } else {
            loading();
          }
        },

        // //删除前操作方法
        // delBefore() {
        // },

        //删除后操作方法
        delAfter() {
        },
        // 按钮点击触发的方法
        btnClick(item, row, index) {
          eventMethod[item.methodName](this, item, row, index);
        },
        btnClickOpenTab(item, row, index) {
          return eventMethod[item.methodName](this, item, row, index);
        },
        getSlotFormName(item) {
          return item + "-form";
        },
        isJson(val) {
          try {
            if (JSON.parse(val.trim())) {
              return true;
            }
            return false;
          } catch (e) {
            return false;
          }
        },
        jsonComponents(form, done, loading) {
          // json 组件
          let jsonObject = this.$refs['editor'];

          let value = jsonObject.json;
          if (value && !this.isJson(JSON.stringify(value))) {
            this.$message.error("非标准的JSON,请检查!");
            return false;
          }
          if (value) {
            let name = jsonObject.$attrs['name'];
            this.form[name] = JSON.stringify(value);
          }
          return true;
        },
        // json的格式化插件
        jsonFormat(name) {
          debugger;
          let jsonObject = this.$refs['editor'];
          for (let i = 0; i < jsonObject.length; i++) {
            let attrName = jsonObject[i].$attrs['name'];
            if (name === attrName) {
              jsonObject[i].formatCode();
              let value = jsonObject[i].getValue();
              if (value && !this.isJson(value)) {
                this.$message.error("非标准的JSON,请检查!");
              }
            }
          }
        },
        // 初始化的value可能是没有值的
        getJsonString(value) {
          if (!value) {
            return "";
          }
          if (value instanceof Object) {
            return JSON.stringify(value);
          }
          return value;
        },

        /**
         * 前置组件检查，后续特殊的组件获取可以先通过该检查之后在提交
         * @param form
         * @param done
         * @param loading
         * @returns {boolean}
         */
        componentsBefore(form, done, loading) {
          return this.jsonComponents(form, done, loading);
        },
        componentsDialogClickSubmit(row, hide) {
          dialogSubmitEvent[this.dialogConfig.config["submitEventName"] || 'defaultSubmit'](this, row, hide);
        },
        closeDialog() {
          this.dialogConfig.showDialogProps = false;
        }, componentsDialogClickClose() {
          this.closeDialog();
        }, componentsDialogResetForm() {
        },
        debugMethod(row) {
          debugger;
        }
      }
    },
    {
      name: 'crud/crud',//模块名字
      configUrl: "/avue/crud",
      // domain:'', // 服务访问根路径
      // list: 'list',//列表接口名字
      // update: 'update',//更新接口名字
      // add: 'add',//新增接口名字
      // del: 'del',//删除接口名字
      // rowKey: 'id',//主键
      // 下面是分页与后端字段属性的适配
      pageNumber: 'pageNumber',//页码
      pageTotal: 'total', // 页总数
      pageSize: 'pageSize',//页数
      pageData: 'data', // 数据对象的名称
      res: (data) => { // 这里设置函数处理
        // 应对服务接口数据结构不一致的转换
        // let response = {
        //   data: data.data,
        //   total: data.total
        // }
        return data;
      },

//列表的结构
// total: 'total',//总页数
// data: 'data'//列表属性
    }
)
</script>
<style lang="scss" scoped>
</style>
