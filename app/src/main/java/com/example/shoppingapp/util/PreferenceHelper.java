package com.example.shoppingapp.util;

public interface PreferenceHelper {
    void setValue(String key, Object value);
  <T> Object getValue (Class <T> aClass, String key, Object defaultValue);
}
