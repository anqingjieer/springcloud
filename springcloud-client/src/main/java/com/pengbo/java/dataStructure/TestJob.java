package com.pengbo.java.dataStructure;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:
 * @date 2019/10/21 22:29 创建
 **/
public class TestJob {
    public static Integer first(String s) {
        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            for (int j = 1; j < c.length; j++) {
                int count;
                if (c[i] == c[j]) {
                    continue;
                }
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(first("dadsfsa"));
    }
}
