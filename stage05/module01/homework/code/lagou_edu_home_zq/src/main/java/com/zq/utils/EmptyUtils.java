package com.zq.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 用来检测某个引用是否为空, 或者是个空数组、空集合、空字符串
 * @author      zq007
 * @date        2021/5/19 13:44
 * @version     V1.0
 */
public class EmptyUtils {
    /**
     * 用来检测某个引用是否为空, 或者是否为空数组、空集合、空字符串
     *
     * @param obj 需要检测的引用
     * @return 返回true则表示这个引用为空, 或者是否为空数组、空集合、空字符串
     */
    public static boolean isEmpty(Object obj) {
        boolean isEmpty = obj == null;
        if (!isEmpty) {
            if (obj instanceof String) {
                isEmpty = ((String) obj).isEmpty();
            } else if (obj instanceof Object[]) {
                isEmpty = ((Object[]) obj).length == 0;
            } else if (obj instanceof Collection) {
                isEmpty = ((Collection) obj).isEmpty();
            } else if (obj instanceof Map) {
                isEmpty = ((Map) obj).isEmpty();
            }
        }

        return isEmpty;
    }
}
