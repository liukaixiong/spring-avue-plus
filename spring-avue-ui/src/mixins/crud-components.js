/**
 *
 * crud中的新增组件使用方式
 * @author liukx
 * @date  -
 */
import utils from "@/utils/crud-utils.js";

let components = {

    'json-editor-vue': {
        /**
         * 前置操作
         * @param self
         * @param form
         * @param done
         * @param loading
         * @returns {boolean}
         */
        before(self, form, done, loading) {
            // json 组件
            let jsonObjectList = utils.getPrefixRefsList(self, 'editor-');

            let check = false
            // 检测json是否符合,并重新设置值
            jsonObjectList.forEach(vars => {
                let value = vars.editor.getText();
                if (value && !utils.isJson(value)) {
                    self.$message.error("非标准的JSON,请检查!");
                    return check;
                }
                if (value) {
                    let name = vars.$attrs['name'];
                    self.form[name] = value;
                }
                check = true;
            })
            return check;
        },

        /**
         * 后置操作
         * @param self
         * @param form
         * @param done
         * @param loading
         */
        after(self, form, done, loading) {
            done();
        },
        jsonFormat(self, name) {
            debugger;
            let jsonObject = utils.getPrefixNameRefsList(self, 'editor-', name);
            if (jsonObject) {
                let value = jsonObject[0].editor.getText();
                if (value && !utils.isJson(value)) {
                    this.$message.error("非标准的JSON,请检查!");
                }
            }
        }
    }
}

export default components;
