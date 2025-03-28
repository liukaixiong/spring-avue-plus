import _remote from './remoteApi'
import eventConfig from './on-config';
import * as rowClickEvent from './event/rowClickEvent';

/**
 * 默认的列表查询请求
 * @param _self VUE实例
 * @param data
 * @returns {Promise<unknown>}
 */
export function list(_self, data) {
    if (!_self.config.list) {
        _self.$message.error("请先配置查询接口的后端请求地址!")
    }
    return http(_self.config, _self.config.list, data);
}

/**
 * 默认的列表新增请求
 * @returns {Promise<unknown>}
 */
export function add(_self, data) {
    if (!_self.config.save) {
        _self.$message.error("请先配置新增接口的后端请求地址!")
    }
    return http(_self.config, _self.config.save, data);
}

/**
 * 默认的列表删除请求
 * @returns {Promise<unknown>}
 */
export function del(_self, data) {
    console.log("----------------del---------------------")
    if (!_self.config.del) {
        _self.$message.error("请先配置删除接口的后端请求地址!")
    }
    return http(_self.config, _self.config.del, data);
}

export function update(_self, data) {
    console.log("----------------update---------------------")
    if (!_self.config.update) {
        _self.$message.error("请先配置修改接口的后端请求地址!")
    }
    return http(_self.config, _self.config.update, data);
}

export function login(url, data) {
    console.log("----------------login---------------------")
    return httpPost(url, data);
}

/**
 * 执行远程请求
 * @param config
 * @param path
 * @param data
 * @returns {Promise<unknown>}
 */
function http(config, path, data) {
    let domain = config.domain;
    let url = domain + path;
    return httpPost(url, data);
}

function httpPost(url, data) {
    return new Promise((resolve, reject) => {
        try {
            _remote.post(url, data, (res) => {
                resolve(res);
            });
        } catch (error) {
            console.warn(`url:${url} 请求失败! data:`, data);
            reject(error);
        }
    });
}

/**
 * 渲染crud数据结构
 *
 * crud结构一共分为三层
 * - option : 页面数据
 * - data : 服务端列表数据
 * - page : 服务端分页数据
 *
 * @param self           vue对象
 * @param clientConfig   页面传输对象
 * @param pageQueryObject  页面对象,URL请求参数
 * @param serverInfo     domain对象
 * @param listFunction   调用获取集合的方法
 */
export function renderData(self, clientConfig, pageQueryObject, serverInfo, listFunction) {
    if (!serverInfo) {
        self.$message.error("找不到对应的服务注册编号 请检查请求路径中的: server=" + pageQueryObject.server + " 然后去[系统管理->服务注册]中查找是否有该编号!");
        return;
    }
    let domain = serverInfo.domain;
    let acceptToken = serverInfo.acceptToken;
    let params = {
        "group": pageQueryObject.group,
        "acceptToken": serverInfo.acceptToken
    }

    let configUrl = clientConfig.configUrl || "/crud/config"

    // 拿到配置文件option
    /**
     * 如果不同步的话，在一些需要渲染的组件，比如select的组件属性dicUrl会不生效，不等待服务端返回的配置，默认用空的配置去初始化，导致字典渲染失败
     */
    _remote.syncPost(domain + configUrl, params, (configObject) => {

        // 根据配置文件的信息获取数据
        let responseConfig = clientConfig.res(configObject);

        if (!checkClientConfiguration(self, responseConfig)) {
            return;
        }

        let config = responseConfig.config;
        let responsePage = responseConfig.page;

        // todo 修复page的路径不匹配
        if (responsePage) {
            let pageInfo = {};
            Object.keys(responsePage).forEach(function (key) {
                pageInfo[key] = responsePage[key];
            });
            self.$data.page.info = pageInfo;
            self.$data.page.currentPage = pageInfo.currentNo || 1;
            self.$data.page.pageSize = pageInfo.currentPageSize || 10;
        }

        // 初始化option部分
        self.$data.option = responseConfig.option;
        self.$data.config = config;
        self.$data.config['domain'] = domain;
        self.$data.config['acceptToken'] = acceptToken;
        self.$data.search = pageQueryObject;
        postRenderData(self.$data);

        listFunction();
        // let pageSizeKey = self.getPageInfo(page.pageSize);
        // let pageNumberKey = self.getPageInfo(page.pageNumber);
        // initQuery[pageSizeKey] = 20;
        // initQuery[pageNumberKey] = 1;
        //
        // let queryData = Object.assign(initQuery, self.$route.query);
        //
        // _remote.post(domain + list, queryData || {}, (res) => {
        //     // 初始化data部分,先确认数据的根路径
        //     let rootResponse = self.getRootData(res);
        //     requireMessage(self, rootResponse, "参数不能为空,否则渲染不出页面，参数可以参考@AVuePage的page前缀,确定数据的分页根路径")
        //
        //     self.$data['data'] = rootResponse[self.getPageInfo(page.pageData || 'data')];
        //     requireMessage(self, self.$data['data'], "page.pageData 参数不能为空,否则渲染不出页面，参数可以参考@AVuePage的page前缀")
        //
        //     // 初始化page部分
        //     let pageObject = {
        //         pageSize: rootResponse[self.getPageInfo(page.pageSize) || 'pageSize'],
        //         pagerCount: rootResponse[self.getPageInfo(page.pagerCount) || 'pagerCount'],
        //         total: rootResponse[self.getPageInfo(page.pageTotal) || 'total']
        //     }
        //
        //     requireMessage(self, pageObject.pageSize, "page.pageSize 参数不能为空,否则渲染不出页面，参数可以参考@AVuePage的page前缀")
        //
        //     requireMessage(self, pageObject.total, "page.pageTotal 参数不能为空,否则渲染不出页面，参数可以参考@AVuePage的page前缀")
        //
        //     // self.$data.page = pageObject;
        // })
    });
}

/**
 * 检查客户端的配置是否符合
 * @param responseConfig
 */
function checkClientConfiguration(self, responseConfig) {
    if (!responseConfig) {
        self.msgError("返回结果有误");
        return false;
    } else if (!responseConfig.config) {
        self.msgError("请回填@AVueConfig注解的参数");
        return false;
    } else if (!responseConfig.option) {
        self.msgError("页面渲染参数option无效");
        return false;
    }
    return true;
}


function requireMessage(self, obj, msg) {
    if (!obj) {
        self.msgError(msg);
        console.log(msg)
    }
}

/**
 * 后置处理渲染数据
 * @param aVueData
 */
function postRenderData(aVueData) {

    let columns = aVueData.option.column;

    function processColumns(columns) {
        for (let i = 0; i < columns.length; i++) {
            let column = columns[i];
            // 处理domain路径补全的字段
            let domainElement = eventConfig.option.column.domain[column.type];
            if (domainElement) {
                for (let j = 0; j < domainElement.length; j++) {
                    completionDomainUrl(aVueData, column, domainElement[j]);
                }
            }

            // 行事件补充字段处理
            let rowEventList = eventConfig.option.column.event;

            if (rowEventList) {
                for (let rowEvent in rowEventList) {
                    let columnValue = column[rowEvent];
                    if (columnValue) {
                        column[rowEventList[rowEvent]] = rowClickEvent[columnValue];
                    }
                }
            }
            if (column.type === 'dynamic') {
                let childrenList = columns[i].children;
                if (childrenList.column) {
                    processColumns(childrenList.column);
                }
            }
        }
    }

    processColumns(columns);
}

/**
 * 处理select的类型的组件值
 * @param aVueData
 * @param index
 * @param column
 */
function handlerSelectColumn(aVueData, index, column) {
    let dicUrl = column.dicUrl;
    // 补全domain
    completionDomainUrl(aVueData, column, 'dicUrl');
}

/**
 * 处理upload的类型组件值
 * @param aVueData
 * @param index
 * @param column
 */
function handlerUploadColumn(aVueData, index, column) {
    completionDomainUrl(aVueData, column, 'action');
}

/**
 * 默认补全domain对象
 * @param aVueData      组件对象
 * @param column
 * @param name
 */
function completionDomainUrl(aVueData, column, name) {
    // 如果非http的url默认按照domain的前缀访问
    let columnObject = column[name];
    if (columnObject && !columnObject.startsWith("http")) {
        let domain = aVueData.config['domain'];
        column[name] = domain + "/" + columnObject;
    }
}

