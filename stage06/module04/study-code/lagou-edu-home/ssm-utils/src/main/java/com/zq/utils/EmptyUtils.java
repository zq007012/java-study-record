package com.zq.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 用来检测某个引用是否为空, 或者是个空数组、空集合、空字符串
 *
 * @author zq007
 * @version V1.0
 * @date 2021/5/19 13:44
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
                isEmpty = ((String) obj).trim().isEmpty();
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

    /**
     * 判断所有的参数是否都是空的, 值为null或者是空数组、空集合、空字符串
     * @param objs
     * @return
     */
    public static boolean allIsEmpty(Object... objs) {
        for (Object obj : objs) {
            if (!isEmpty(obj)){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断所有的参数是否都是不是空的, 值为null或者是空数组、空集合、空字符串
     * @param objs
     * @return
     */
    public static boolean allNotEmpty(Object...objs){
        for (Object obj : objs) {
            if (isEmpty(obj)){
                return false;
            }
        }
        return true;
    }
}
