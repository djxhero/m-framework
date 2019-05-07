package com.lancslot.morn.utils.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description List工具
 * @Date 2018/8/7 10:42
 **/
public class ListUtil {

    /**
     * 比较2个List，得出应该删除的 delList，和新增的addList T必须重写 hashCode和equals方法
     *
     * @param dbEntityList
     * @param newDtoList
     * @param addList
     * @param delList
     */
    public static <T> void filterList(List<T> dbEntityList, List<T> newDtoList, List<T> addList, List<T> delList) {
        if (dbEntityList == null) {
            dbEntityList = new ArrayList<T>();
        }

        if (newDtoList == null) {
            newDtoList = new ArrayList<T>();
        }

        // 复制一个原始数组,将作为交集
        List<T> intersecList = new ArrayList<>(newDtoList);
        // 设新集A(newDtoList)，旧集 B(dbEntityList)，求出交集C(intersecList)
        intersecList.retainAll(dbEntityList);

        // A -C ,即addList
        newDtoList.removeAll(intersecList);
        addList.addAll(newDtoList);

        // B -C,即delList
        dbEntityList.removeAll(intersecList);
        delList.addAll(dbEntityList);
    }

    /**
     * 比较2个List，得出应该删除的 delList，和新增的addList,新旧交集remainList。 T必须重写 hashCode和equals方法
     *
     * @param dbEntityList
     * @param newDtoList
     * @param addList
     * @param delList
     */
    public static <T> void filterList(List<T> dbEntityList, List<T> newDtoList, List<T> addList, List<T> delList, List<T> remainList) {
        if (dbEntityList == null) {
            dbEntityList = new ArrayList<T>();
        }

        if (newDtoList == null) {
            newDtoList = new ArrayList<T>();
        }

        // 复制一个原始数组,将作为交集
        List<T> intersecList = new ArrayList<>(newDtoList);
        // 设新集A(newDtoList)，旧集 B(dbEntityList)，求出交集C(intersecList)
        intersecList.retainAll(dbEntityList);

        // A -C ,即addList
        addList.addAll(newDtoList);
        addList.removeAll(intersecList);

        // B -C,即delList
        delList.addAll(dbEntityList);
        delList.removeAll(intersecList);

        remainList.addAll(intersecList);
    }


    public static List<Integer> split2IntegerList(String text, String separatorChars) {
        String[] tagIdArray = StringUtils.split(text, separatorChars);
        List<String> tagIdStrList = Arrays.asList(tagIdArray);
        List<Integer> tagIdList = tagIdStrList.stream().map(Integer::parseInt).collect(Collectors.toList());
        return tagIdList;
    }

    public static List<String> split2StringList(String text, String separatorChars) {
        String[] tagIdArray = StringUtils.split(text, separatorChars);
        List<String> tagStrList = Arrays.asList(tagIdArray);
        return tagStrList;
    }

    /**
     * List<Object>  提取object的属性list
     * @param source
     * @param projection
     * @param <Source>
     * @param <Result>
     * @return
     */
    public static <Source, Result> List<Result> convertAll(List<Source> source,
                                                    Function<Source, Result> projection) {
        ArrayList<Result> results = new ArrayList<Result>();
        for (Source element : source) {
            results.add(projection.apply(element));
        }
        return results;
    }

    public static Set<Integer> split2IntegerSet(String text, String separatorChars) {
        return new TreeSet(split2IntegerList(text,separatorChars));
    }

}
