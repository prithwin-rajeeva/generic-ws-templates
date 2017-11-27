package com.generic.project.test.util;

import java.lang.reflect.Field;

/**
 * utility methods for tests
 */
public class TestUtil {
    public static void setValue(Object o , Object v,String n) throws Exception {
        Field f = o.getClass().getDeclaredField(n);
        f.setAccessible(true);
        f.set(o , v);
    }

    public static String generateFile(long randomNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        for(long i = 0 ; i < randomNumber ; i++) sb.append("<img src=\"lookforimg.png\">");
        sb.append("</html>");
        return sb.toString();
    }
}
