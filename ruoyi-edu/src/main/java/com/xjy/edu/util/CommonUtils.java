package com.xjy.edu.util;

import java.util.HashSet;
import java.util.List;

public class CommonUtils {
    /*
     * 判断数组中是否有重复的值
     */
    public static boolean cheakIsRepeat(List<String> array) {
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < array.size(); i++) {
            hashSet.add(array.get(i));
        }
        if (hashSet.size() == array.size()) {
            return true;
        } else {
            return false;
        }
    }
}
