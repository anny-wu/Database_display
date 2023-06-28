package com.annyw.util;

public class Unboxed {
    public static boolean isBoxed(Class<?> type) {
        return (type == Integer.class || type == Long.class || type == Double.class || type == Float.class
            || type == Boolean.class || type == Byte.class || type == Character.class || type == Short.class);
    }
    
    public static Class<?> toPrimitives(Class<?> type) {
        if (type == Integer.class) {
            return int.class;
        } else if (type == Long.class) {
            return long.class;
        } else if (type == Double.class) {
            return double.class;
        } else if (type == Float.class) {
            return float.class;
        } else if (type == Boolean.class) {
            return boolean.class;
        } else if (type == Byte.class) {
            return byte.class;
        } else if (type == Character.class) {
            return char.class;
        } else if (type == Short.class) {
            return short.class;
        } else {
            String string = "class '" + type.getName() + "' is already a primitive";
            throw new IllegalArgumentException(string);
        }
    }
}
