// import {getServer} from "@/api/system/server";
import crudUtil from "@/utils/server-crud"
import page from '@/const/page.js'
import * as apiJs from '@/api/crud/crud.js'

/**
 * 远程接口配置版, 在原先的基础上做了修改,支持远程的option的配置
 *
 * @param app
 * @param this.option
 * @returns {*|string|{"on-load": mixins.methods.getList, "refresh-change": mixins.methods.refreshChange, "row-update": mixins.methods.rowUpdate, "row-save": mixins.methods.rowSave, "row-del": mixins.methods.rowDel, "search-reset": mixins.methods.searchChange, "search-change": mixins.methods.searchChange}|{data: [], form: {}, api: any, page: {}, params: {}, loading: boolean, config: {}, option: {}}|{mixins}|{ref: string, data: [], tableLoading: boolean, option: {}}}
 * @author liukx
 * @date 2021年8月15日 17:10:27
 */
export default (app, clientOption = {}) => {
    let mixins = {
        async created() {
            await this.initContext();
        },
        data() {
            return {
                config: {},
                data: [],
                form: {},
                params: {},
                api: apiJs,
                // api: require(`@/api/${clientOption.name}`),
                loading: false,
                page: {},
                option: {}
            }
        },
        computed: {
            // option () {
            //    return require(`@/option/${option.name}`).default(this)
            // },
            bindVal() {
                return {
                    ref: 'crud',
                    option: this.option,
                    data: this.data,
                    tableLoading: this.loading
                }
            },
            onEvent() {
                return {
                    // 'on-load': this.getList,
                    'row-save': this.rowSave,
                    'row-update': this.rowUpdate,
                    'row-del': this.rowDel,
                    'refresh-change': this.refreshChange,
                    'search-reset': this.searchChange,
                    'search-change': this.searchChange
                }
            },
            rowKey() {
                return this.getOption('rowKey') || 'id';
            }
        },
        methods: {
            async initContext() {
                let _self = this;
                console.log("页面初始化");
                let query = _self.$route.query;
                debugger;
                if (!query.group) {
                    console.warn("参数有问题，请到菜单配置中查看是否配置server和group参数");
                } else {
                    // 根据请求路径参数获取服务路由编号,根据编号获取domain
                    // let res = await getServer(query.server);
                    let serverInfo = {
                        domain: clientOption.domain,
                        acceptToken: "abc12345"
                    }
                    // // 开始渲染数据
                    apiJs.renderData(_self, clientOption, query, serverInfo, () => this.getList());
                }
            },
            getList() {
                const callback = () => {
                    let _self = this;
                    this.loading = true;
                    let pageParams = {}
                    pageParams[this.getPageInfo(page.pageNumber) || 'pageNumber'] = this.page.currentPage || 1;
                    pageParams[this.getPageInfo(page.pageSize) || 'pageSize'] = this.page.pageSize || 10;
                    crudUtil.mergeParams(this.$route.query, this.params, false);
                    const data = Object.assign(pageParams, this.params)
                    this.data = [];
                    this.api[this.getOption("list") || 'list'](_self, data).then(res => {
                        this.loading = false;
                        let data = this.getRootData(res);
                        this.page.total = data[this.getPageInfo(page.pageTotal) || 'total'];
                        this.data = data[this.getPageInfo(page.pageData) || 'data'];
                        if (this.listAfter) {
                            this.listAfter(data)
                        } else {
                            this.$message.success('获取成功')
                        }
                    })
                }
                if (this.listBefore) {
                    this.listBefore()
                }
                callback();
                if (this.listAfter) {
                    this.listAfter();
                }
            },
            rowSave(row, done, loading) {
                const callback = () => {
                    let _self = this;
                    delete this.form.params;
                    let query = this.$route.query;
                    crudUtil.mergeParams(query, this.form, false);
                    this.api[this.option.add || 'add'](_self, this.form).then((data) => {
                        this.getList();
                        if (this.addAfter) {
                            this.addAfter(row, done, loading, data);
                        } else {
                            this.$message.success('新增成功');
                            done(row, done, loading);
                        }
                    }).catch(() => {
                        loading();
                    })
                }
                if (this.addBefore) {
                    if (!this.addBefore(row, done, loading)) {
                        loading();
                        return;
                    }
                }
                callback()
            },
            rowUpdate(row, index, done, loading) {
                if (!this.updateBefore(row, index, done, loading)) {
                    loading();
                    return;
                }
                const callback = () => {
                    delete this.form.params;
                    let _self = this;
                    this.api[this.option.update || 'update'](_self, this.form).then((data) => {
                        this.getList();
                        if (this.updateAfter) {
                            this.updateAfter(row, index, done, loading, data)
                        } else {
                            this.$message.success('更新成功');
                            done()
                        }
                    }).catch(() => {
                        loading()
                    })
                }
                callback()
            },
            rowDel(row, index) {
                let _self = this;
                const callback = () => {
                    this.api[this.option.del || 'del'](_self, row).then((data) => {
                        this.getList();
                        if (this.delAfter) {
                            this.delAfter(data, row, index)
                        } else {
                            this.$message.success('删除成功')
                        }
                    }, (error) => {
                        this.$message.error(`删除失败${error}`)
                    })
                }
                if (this.delBefore) {
                    this.delBefore(row, index)
                }
                this.$confirm(`此操作将删除序号【${index}】的数据, 是否继续?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    callback()
                })
            },
            searchChange(params, done) {
                if (done) done();
                this.params = params;
                this.page.currentPage = 1;
                this.getList();
            },
            refreshChange() {
                this.getList();
            },
            getOption(key) {
                return this.option[key] || clientOption[key];
            },
            getConfig(key, defaultValue) {
                return this.config[key] || defaultValue;
            },
            getPageInfo(key) {
                return this.page.info && this.page.info[key] || clientOption[key];
            }
            /**
             * 获取符合返回结果参数的配置
             * @param res
             * @returns {*}
             */
            , getRootData(res) {
                let root = this.getPageInfo(page.pageRoot);
                let rootResponse;
                if (root) {
                    if (root.indexOf(".") > 0) {
                        let fields = root.split(".");
                        rootResponse = res[fields[0]];
                        for (let i = 1; i < fields.length; i++) {
                            rootResponse = rootResponse[fields[i]];
                        }
                    } else {
                        rootResponse = res[root];
                    }
                } else {
                    rootResponse = clientOption.res(res);
                }
                return rootResponse;
            }
        }
    }
    app.mixins = app.mixins || [];
    app.mixins.push(mixins)
    return app;
}
