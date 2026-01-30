package com.paypal.bdd.hooks;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        CONTEXT.get().put(key, value);
    }

    public static Object get(String key) {
        return CONTEXT.get().get(key);
    }

    public static void reset() {
        CONTEXT.get().clear();
    }
}
