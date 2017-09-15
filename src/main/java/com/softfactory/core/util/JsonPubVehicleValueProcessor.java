package com.softfactory.core.util;



import com.softfactory.pojo.PubVehicle;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonPubVehicleValueProcessor implements JsonValueProcessor {
    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }

    private Object process(Object value) {
        if (value instanceof PubVehicle) {

            return ((PubVehicle) value).getVNum();
        }
        return value == null ? "" : value.toString();
    }
}
