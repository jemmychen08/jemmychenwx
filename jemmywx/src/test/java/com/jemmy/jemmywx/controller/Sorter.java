package com.jemmy.jemmywx.controller;

import java.util.Comparator;

/**
 * @Auther: Administrator
 * @Date: 2018/12/12 16:09
 * @Description:排序器接口(策略模式: 将算法封装到具有共同接口的独立的类中使得它们可以相互替换)
 */
public interface Sorter {

    /**
     *
     * @Description:
     *
     * @auther: jemmy_chen
     * @param: [list] 待排序的数组
     * @return: void
     * @date: 2018/12/12 16:14
     */
    <T extends Comparable<T>> void sort(T[] list);

    /**
     *
     * @Description:
     *
     * @auther: jemmy_chen
     * @param: [list:待排序的数组, comp:比较两个对象的比较器]
     * @return: void
     * @date: 2018/12/12 16:14
     */
    <T> void sort(T[] list, Comparator<T> comp);
}
