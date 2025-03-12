/**
 * 通用的行事件定义，通过后端的参数来确定那个事件被触发。这里的方法参数都是固定的，请最好不要更改，而只是逻辑自行处理。
 *
 * @author liukx
 * @date  -
 */

import crudUtil from "@/utils/server-crud";
import _remote from "@/api/crud/remoteApi";
import page from "@/const/page.js";

/**
 * 通用的测试按钮A
 * @param self    全局指向
 * @param item    按钮对象
 * @param row     选择行对象
 * @param index   下标对象
 */
export function testA(self, item, row, index) {
    self.$message.success('testA --> ' + JSON.stringify(item) + ' 值 ' + JSON.stringify(row));
}

/**
 * 通用的测试按钮方法B
 * @param self    全局指向
 * @param item    按钮对象
 * @param row     选择行对象
 * @param index   下标对象
 */
export function testB(self, item, row, index) {
    self.$message.success('testB --> ' + JSON.stringify(item) + ' 值' + JSON.stringify(row));
}


/**
 * 点击跳转到特定页面
 * @param self
 * @param item
 * @param row
 * @param index
 */
export function hrefClick(self, item, row, index) {
    let json = listToJson(item['attrExt']);
    let jumpUrl = json.url;
    jumpUrl = parseTextEnvData(row, jumpUrl);
    if (json) {
        window.open(jumpUrl);
    } else {
        self.message.error("没有配置跳转链接，无法跳转！")
    }
}

/**
 * 确认框, 询问之后,触发远程调用,传递的参数是当前行数据
 * @param self
 * @param item
 * @param row
 * @param index
 */
export function confirmClickRemoteApi(self, item, row, index) {
    let param = listToJson(item['attrExt']);
    let title = parseTextEnvData(row, param.title) || '您确认执行该操作吗?';
    let url = parseTextEnvData(row, param.url);
    let method = param.method || 'post';

    if (!url) {
        self.$message.error("该操作会触发远程调用,请回填url参数!");
        return;
    } else {
        url = crudUtil.completionDomain(self, url);
    }

    // 触发远程调用操作
    self.$confirm(title, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(() => {
        _remote.api(url, method, row, (data) => {
            if (crudUtil.processDMLResponse(self, data)) {
                // 刷新表格
                self.$refs.crud.refreshChange();
            }
        });
    })
}

/**
 * 打开一个弹层，输入json参数给后端
 * @param self
 * @param item
 * @param row
 * @param index
 * @returns {{}}
 */
export function openTabLink(self, item, row, index) {
    let param = listToJson(item['attrExt']);
    let requestParams = param["query"];
    let param2Obj = convertQueryParam(requestParams, row);
    let routeLink = {};
    let query = self.$route.query;

    let requestJson = param2Obj || {};
    requestJson["server"] = param.server || query.server;
    // requestJson["group"]=param.group;
    requestJson["group"] = param.group;

    // 需要配置一个临时承载页
    routeLink["path"] = "/temp/link";
    // routeLink["name"]="abc";
    routeLink["query"] = requestJson;
    return routeLink;
    // routeLink["params"]="";
}

/**
 * 复制字段
 *
 * @param self
 * @param item
 * @param row
 * @param index
 */
export function copyField(self, item, row, index) {
    let param = listToJson(item['attrExt']);
    let copyObject = row;
    if (param) {
        let fieldName = param["name"];
        if (fieldName) {
            copyObject = row[fieldName];
        }
    }

    if (typeof copyObject !== 'string') {
        copyObject = JSON.stringify(copyObject);
    }

    self.$Clipboard({
        text: copyObject
    }).then(() => {
        self.$message.success('复制成功! -> ' + copyObject)
    }).catch(() => {
        self.$message.error('复制失败,请手动复制!')
    });
}


/**
 * 打开一个弹层，输入json参数给后端
 * @param self
 * @param item
 * @param row
 * @param index
 */
export function openWindowJsonRemote(self, item, row, index) {
    // 显示弹窗窗口
    let param = listToJson(item['attrExt']);
    // 提交按钮指向的后端地址
    let submitUrl = parseTextEnvData(row, param.submitUrl);
    // 提交事件
    let submitEventName = param.submitEventName;
    // 配置模版
    let group = param.group;
    let fieldConvertMap = param.fieldConvertMap;
    // 做一层值转换
    if (fieldConvertMap) {
        let convertObject = parseKeyValuePairs(fieldConvertMap);
        Object.entries(convertObject).forEach(([k, v]) => {
            row[v] = row[k];
        });
    }

    let requestUrl = crudUtil.completionDomain(self, param.url || '/avue/crud');
    // 先清空对象
    // self.dialogConfig.objectData = {};

    let acceptToken = self.config.acceptToken;
    let requestBody = {
        "group": group,
        "acceptToken": acceptToken
    }
    _remote.post(requestUrl, requestBody, (res) => {
        // 定义后端的请求路径
        // self.dialogConfig.config = res.config;
        self.dialogConfig.config = {};
        self.dialogConfig.config["submitUrl"] = submitUrl;
        self.dialogConfig.config["submitEventName"] = submitEventName;
        self.dialogConfig.formOption = res.option;
        self.dialogConfig.dialogOption = {
            title: res.option.title || '弹窗操作'
        };
        self.dialogConfig.objectData = row;
        self.dialogConfig.showDialogProps = true;
    });
}

/**
 * 打开一个弹层，弹层内容为另一个模版
 * @param self
 * @param item
 * @param row
 * @param index
 */
export function openWindowTableList(self, item, row, index) {
    // 显示弹窗窗口
    let param = listToJson(item['attrExt']);
    // 提交按钮指向的后端地址
    let submitUrl = parseTextEnvData(row, param.submitUrl);
    // 提交事件
    let submitEventName = param.submitEventName;
    // 配置模版
    let group = param.group;

    let fieldConvertMap = param.fieldConvertMap;
    let varRow = row;
    // 做一层值转换
    if (fieldConvertMap) {
        let convertObject = parseKeyValuePairs(fieldConvertMap);
        Object.entries(convertObject).forEach(([k, v]) => {
            varRow[v] = row[k];
        });
    }

    let requestUrl = crudUtil.completionDomain(self, param.url || '/avue/crud');
    // 先清空对象
    // self.dialogConfig.objectData = [];

    let acceptToken = self.config.acceptToken;
    let requestBody = {
        "group": group,
        "acceptToken": acceptToken
    }
    self.dialogConfig.objectData.page = {};
    self.dialogConfig.objectData.page.currentPage = 1;
    self.dialogConfig.objectData.search = varRow;
    _remote.post(requestUrl, requestBody, (res) => {
        // 构建后端路径
        let listUrl = crudUtil.completionDomain(self, res.config.list);
        self.dialogConfig.objectData.page.url = listUrl;
        self.dialogConfig.objectData.page.row = varRow;
        self.dialogConfig.objectData.getList = () => {
            // 构建分页参数
            let pageParams = {}
            pageParams[self.getPageInfo(page.pageNumber) || 'pageNumber'] = self.dialogConfig.objectData.page.currentPage || 1;
            pageParams[self.getPageInfo(page.pageSize) || 'pageSize'] = self.dialogConfig.objectData.page.pageSize || 10;
            let data = Object.assign(pageParams, varRow);
            data = Object.assign(data, self.dialogConfig.objectData.search);
            _remote.post(listUrl, data, (list_res) => {
                let listObject = self.getRootData(list_res);
                self.dialogConfig.objectData.data = listObject[self.getPageInfo(page.pageData) || 'data'];
                self.dialogConfig.objectData.page.total = listObject[self.getPageInfo(page.pageTotal) || 'total'];
            });
        }

        self.dialogConfig.config = {};
        self.dialogConfig.config["submitUrl"] = submitUrl;
        self.dialogConfig.config["submitEventName"] = submitEventName;
        self.dialogConfig.formOption = res.option;
        self.dialogConfig.dialogOption = {
            title: res.option.title || '弹窗操作',
            type: 'table'
        };
        self.dialogConfig.showDialogProps = true;
        self.dialogConfig.objectData.getList();
    });
}

/**
 * 转换请求参数
 * @param search
 * @param objectData
 * @returns {{}}
 */
function convertQueryParam(search, objectData) {
    const obj = {}
    const searchArr = search.split('&')
    searchArr.forEach(v => {
        const index = v.indexOf('=')
        if (index !== -1) {
            const name = v.substring(0, index)
            const val = v.substring(index + 1, v.length)
            obj[name] = objectData[parseTag(val, "#{", "}")];
        }
    })
    return obj;
}

function parseTextEnvData(row, text) {
    let textBody = text;
    if (row && textBody) {
        while (textBody.includes("#{") && textBody.includes("}")) {
            let start = textBody.indexOf("#{");
            let end = textBody.indexOf("}");
            let key = textBody.substring(start + 2, end);
            let value = row[key];
            textBody = textBody.replace("#{" + key + "}", value);
        }
    }
    return textBody;
}

/**
 * 解析特殊标记
 * @param val
 * @param start
 * @param end
 */
function parseTag(val, start, end) {
    return val.replace(start, '').replace(end, '');
}

/**
 * 将List转化为json对象
 * @param valueList
 */
function listToJson(valueList) {
    if (valueList && Array.isArray(valueList)) {
        let result = {};
        for (let i = 0; i < valueList.length; i++) {
            let name = valueList[i].name;
            result[name] = valueList[i].value;
        }
        return result;
    }
    return valueList;
}

function parseKeyValuePairs(str) {
    str = str.replaceAll("&&", "&");
    const pairs = str.split('&');
    const result = {};

    pairs.forEach(pair => {
        const [key, value] = pair.split('=');
        if (key && value) {
            result[key.trim()] = value.trim();
        }
    });

    return result;
}
