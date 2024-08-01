package com.liukx.spring.client.model;

import com.liukx.spring.client.annotation.AVueConfig;
import com.liukx.spring.client.annotation.AVueRouteKey;
import com.liukx.spring.client.annotation.AVueTableOption;
import com.liukx.spring.client.annotation.column.AVueTable;
import com.liukx.spring.client.controller.AVueConfigControllerTest;

/**
 * 模拟子表单功能构建
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/17 - 13:02
 */
@AVueRouteKey(groupKey = "test-table")
@AVueTableOption(title = "这是一个测试")
@AVueConfig(list = AVueConfigControllerTest.LIST_URL, update = AVueConfigControllerTest.UPDATE_URL, save = AVueConfigControllerTest.UPDATE_URL)
public class AVueTableModel {

    @AVueTable(prop = "nodeModel", label = "子表单测试")
    private AVueNodeModel nodeModel;

    public AVueNodeModel getNodeModel() {
        return nodeModel;
    }

    public void setNodeModel(AVueNodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }
}
