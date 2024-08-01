package com.liukx.spring.client.attrDate;

import com.liukx.spring.client.enums.AVueAttrLevel;
import com.liukx.spring.client.enums.DicEnumData;
import com.liukx.spring.client.helper.DicEnumsHelper;
import com.liukx.spring.client.model.PropsModel;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 本地枚举回调方法
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/8/19 - 16:07
 */
public class DicEnumCallback implements DictionaryDataCallback<PropsModel> {

    @Override
    public String callName() {
        return "";
    }

    @Override
    public List<PropsModel> call(AVueAttrLevel level, AnnotatedElement element, Map<String, Object> attrMap) {
        String dicData = attrMap.get("dicData").toString();
        return getPropsModels(DicEnumsHelper.getINSTANCE().get(dicData));
    }

    private List<PropsModel> getPropsModels(Class<? extends Enum> e) {
        if (e == null) {
            return null;
        }
        return Arrays.stream(e.getEnumConstants()).map(enums -> {
            if (enums instanceof DicEnumData) {
                DicEnumData dicEnumData = (DicEnumData)enums;
                PropsModel propsModel = new PropsModel();
                propsModel.setLabel(dicEnumData.getLabel());
                propsModel.setValue(dicEnumData.getCode());
                return propsModel;
            } else {
                PropsModel propsModel = new PropsModel();
                propsModel.setLabel(enums.name());
                propsModel.setValue(enums.name());
                return propsModel;
            }
        }).collect(Collectors.toList());
    }
}
