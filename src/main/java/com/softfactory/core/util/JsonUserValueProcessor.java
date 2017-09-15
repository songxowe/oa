package com.softfactory.core.util;


import com.softfactory.pojo.User;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


public class JsonUserValueProcessor implements JsonValueProcessor {
  


  public Object processArrayValue(Object value, JsonConfig config) {
    return process(value);
  }

  public Object processObjectValue(String key, Object value, JsonConfig config) {
    return process(value);
  }

  private Object process(Object value) {
    if (value instanceof User) {
     
      return ((User) value).getUserTrueName();
    }
    return value == null ? "" : value.toString();
  }

}
