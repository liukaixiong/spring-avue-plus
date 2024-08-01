let date = {
    componentType: 'avue-tree',
    data: [
        {
            value: 0,
            label: '一级部门',
            children: [
                {
                    value: 1,
                    label: '一级部门1'
                }, {
                    value: 2,
                    label: '一级部门2'
                }
            ]
        }, {
            value: 3,
            label: '二级部门',
            children: [
                {
                    value: 4,
                    label: '二级部门1'
                }, {
                    value: 5,
                    label: '二级部门2'
                }
            ]
        }
    ],
    option: {
        defaultExpandAll: true,
        formOption: {
            labelWidth: 100,
            column: [{
                label: '自定义项',
                prop: 'label'
            }, {
                label: '测试',
                prop: 'test'
            }]
        }
    }
}
