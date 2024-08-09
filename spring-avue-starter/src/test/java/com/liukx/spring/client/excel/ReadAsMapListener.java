package com.liukx.spring.client.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadAsMapListener extends AnalysisEventListener<Map<Integer, Object>> {
    public List<Map<String, Object>> dataList = new ArrayList<>();
    private Map<Integer, String> headMap = new HashMap<>();

    public void invoke(Map<Integer, Object> data, AnalysisContext context) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Integer key : data.keySet()) {
            String s = this.headMap.get(key);
            if (StringUtils.hasLength(s)) {
                resultMap.put(s, data.get(key));
            }
        }
        dataList.add(resultMap);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap = headMap;
    }

    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("read finish");
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public Map<Integer, String> getHeadMap() {
        return headMap;
    }
}
