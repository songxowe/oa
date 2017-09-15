package com.softfactory.core.util;

import com.softfactory.pojo.HrDept;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDeptValueProcessor implements JsonValueProcessor {
    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }

    private Object process(Object value) {
        if (value instanceof HrDept) {

            return ((HrDept) value).getDeptName();
        }
        return value == null ? "" : value.toString();
    }
}
