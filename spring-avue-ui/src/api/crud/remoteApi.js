import $ from 'jquery'

function api(url, method, param, fnCallback) {
    this[method](url, param, fnCallback);
}

/**
 * POST请求
 * @param url
 * @param param
 * @param fnCallback
 */
function post(url, param, fnCallback) {
    ajax(url, param, {type: "POST"}, fnCallback);

}

/**
 * 同步post请求,避免在各种响应式请求中,产生错乱的逻辑
 * @param url
 * @param param
 * @param fnCallback
 */
function syncPost(url, param, fnCallback) {
    ajax(url, param, {async: false, type: "POST"}, fnCallback);
}

function ajax(url, param, customerOption = {}, fnCallback) {

    if (!url.startsWith("http")) {
        url = window.location.origin + url;
    }

    let defaultOptions = {
        url: url,
        type: "POST",
        timeout: 300000,
        // headers: "Access-Control-Allow-Origin:*",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data: JSON.stringify(param)
    };

    let optionConfig = Object.assign(defaultOptions, customerOption);

    $.ajax(optionConfig).done(function (data, textStatus, jqXHR) {
        if (jqXHR.getResponseHeader('sessionStatus') === 'timeout') {
            self.errorMsg('会话已经超时.');
        } else if (!data) {
            self.errorMsg('无返回信息.');
        } else {
            fnCallback(data);
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 0) {
            self.errorMsg('请求异常，网络连接失败！ -> ' + this.url);
        } else if (jqXHR.status === 404) {
            self.errorMsg("请求找不到:" + this.url);
        } else {
            self.errorMsg("请求异常，状态码：" + jqXHR.status + this.url);
        }
    });
}


/**
 * GET请求
 * @param url
 * @param param
 * @param fnCallback
 */
function get(url, param, fnCallback) {
    ajax(url, param, {type: "GET"}, fnCallback);
};

export default {
    post,
    syncPost,
    get,
    api
};
