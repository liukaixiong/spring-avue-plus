/**
 * 通用的crud的工具类方法
 * @author liukaixiong
 */

/**
 * 处理DML操作的响应处理
 * @param self
 * @param data
 */
function processDMLResponse(self, data) {
  let successField = self.config['successField'];
  let successKeyword = self.config['successKeyword'];
  let successValue = data[successField];

  if (successValue && successValue + '' === successKeyword) {
    self.$message.success('操作成功');
    return true;
  } else {
    let messageField = self.config['messageField'];
    let consoleMsg = data[messageField] || "messageField 没有找到";
    self.$message.error(consoleMsg);
    return false;
  }
}

/**
 * 补全请求的前缀域名
 * @param self
 * @param path
 * @returns {*}
 */
function completionDomain(self, path) {
  if (!path.startsWith("http")) {
    return self.config.domain + path;
  }
  return path;
}

/**
 * json的格式化
 * @param _self
 * @param name
 */
function jsonFormat(_self, name) {
  let jsonObject = _self.$refs['editor'];
  for (let i = 0; i < jsonObject.length; i++) {
    let attrName = jsonObject[i].$attrs['name'];
    if (name === attrName) {
      jsonObject[i].formatCode();
      let value = jsonObject[i].getValue();
      if (value && !isJson(value)) {
        _self.$message.error("非标准的JSON,请检查!");
      }
    }
  }
}

function isJson(val) {
  try {
    if (JSON.parse(val.trim())) {
      return true;
    }
    return false;
  } catch (e) {
    return false;
  }
}

/**
 * 获取slot的form的name
 * @param item
 * @returns {string}
 */
function getSlotFormName(item) {
  return item + "Form";
}

/**
 * 获取json字符串
 * @param value
 * @returns {string|*}
 */
function getJsonString(value) {
  if (!value) {
    return "";
  }
  if (value instanceof Object) {
    return JSON.stringify(value);
  }
  return value;
}

/**
 * 属性合并，将target的属性合并到source中
 * 如果source已经存在,那么则放弃
 * @param target  指定字符串
 * @param source  合并来源的字符
 * @param allMerge 是否默认直接合并 true or false
 */
function mergeParams(target, source, allMerge) {
  for (let key of Object.keys(target)) {
    if (allMerge || !source[key]) {
      source[key] = target[key];
    }
  }
}

export default {
  processDMLResponse,
  completionDomain,
  getJsonString,
  getSlotFormName,
  jsonFormat,
  mergeParams
}
