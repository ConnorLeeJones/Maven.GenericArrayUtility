package com.zipcodewilmington.arrayutility;

import org.junit.Ignore;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility <T> {
    private List<T> thisList;
    private HashMap<T, Integer> thisMap;
    private T[] thisArr;
    private Class<T> thisClass;


    public ArrayUtility(T[] arr) {
        this.thisList = new ArrayList<>(asList(arr));
        this.thisMap = new HashMap<>();
        this.thisClass = (Class<T>) arr[0].getClass();
        this.thisArr = arr;
    }

    public T getMostCommonFromMerge(T[] arr){
        thisList.addAll(Arrays.asList(arr));
        mapSetUp();
        T result = arr[0];
        Integer max = 0;
        for (Map.Entry<T, Integer> entry : thisMap.entrySet()){
            if (entry.getValue() > max){
                result = entry.getKey();
                max = entry.getValue();
            }
        }
        return result;

    }

    public Integer countDuplicatesInMerge(T[] arr, T value){
        thisList.addAll(Arrays.asList(arr));
        mapSetUp();
        return thisMap.get(value);
    }

    public T returnMost() {
        Integer max = 0;
        T result = thisList.get(0);
        for (Map.Entry<T, Integer> entry : thisMap.entrySet()){
            if (entry.getValue() > max){
                result = entry.getKey();
                max = entry.getValue();
            }
        }
        return result;
    }

    public Integer getNumberOfOccurrences(T item) {
        mapSetUp();
        return thisMap.getOrDefault(item, null);
    }

    public T[] removeValue(T item) {
        int index = 0;
        for (int i = 0; i < thisArr.length; i++) {
            if (thisArr[i] != item) {
                thisArr[index++] = thisArr[i];
            }
        }
        return Arrays.copyOf(thisArr, index);
    }


    public void mapSetUp(){
        Integer count;
        for (T element : this.thisList){
            if (thisMap.get(element) == null){
                thisMap.put(element, 1);
            } else {
                count = thisMap.get(element);
                thisMap.put(element, count + 1);
            }
        }
    }



    public static <T> T[] filterObjects(List<T> items, Predicate<T> p){
        return (T[]) items.stream().filter(p::test).collect(Collectors.toList()).toArray();

    }









}
