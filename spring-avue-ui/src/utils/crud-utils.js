/**
 * 普通工具类
 *
 * @author liukx
 * @date  -
 */


let utils = {

    /**
     * 判断是否是json
     * @param str
     * @returns {boolean}
     */
    isJson(str) {
        try {
            if (JSON.parse(str.trim())) {
                return true;
            }
            return false;
        } catch (e) {
            return false;
        }
    },
    getSlotFormName(item) {
        return item + "Form";
    },
    getJsonString(value) {
        if (!value) {
            return "";
        }
        if (value instanceof Object) {
            return JSON.stringify(value);
        }
        return value;
    },
    getPrefixRefsList(self, prefix) {
        let refs = self.$refs;
        let list = [];
        Object.entries(refs).forEach(([key, value]) => {
            if (key.startsWith(prefix)) {
                list.push(value);
            }
        });
        return list;
    },
    getPrefixNameRefsList(self, prefix, name) {
        let refs = self.$refs;
        let list = [];
        Object.entries(refs).forEach(([key, value]) => {
            if (value) {
                if (key.startsWith(prefix) && value.$attrs['name'] === name) {
                    list.push(value);
                }
            }
        });
        return list;
    }


}

export default utils;
