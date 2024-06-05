package com.gao.mybatis.type;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TypeAliasRegistry {

    private final Map<String, Class<?>> TYPE_ALIAS = new HashMap<String, Class<?>>();

    public TypeAliasRegistry() {

    }

    public void registerAlias(String alias, Class<?> value) {
        String key = alias.toLowerCase(Locale.ENGLISH);
        TYPE_ALIAS.put(key, value);
    }

    public Class<?> resolveAlias(String alias) {
        String key = alias.toLowerCase(Locale.ENGLISH);
        return TYPE_ALIAS.get(key);
    }
}
