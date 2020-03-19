package com.caofangqi.test.jvmtest.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

/**
 * @author caofangqi created at 2020/3/18 9:49 AM
 */
public class UnsafeUtils {

    public static Unsafe unsafe;

    static {
        try {
            Class<Unsafe> clazz = Unsafe.class;
            Constructor<Unsafe> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            unsafe = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
