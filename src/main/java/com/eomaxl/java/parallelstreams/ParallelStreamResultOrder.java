package com.eomaxl.java.parallelstreams;

import java.util.*;
import java.util.stream.Collectors;

import static com.eomaxl.java.util.LoggerUtil.log;

public class ParallelStreamResultOrder {
    public static void main(String[] args){
        List<Integer> inputList = List.of(1,2,3,4,5,6,7,8,9);
        log("InputList :"+inputList);
        List<Integer> result = listOrder(inputList);
        log("result : "+result);

        Set<Integer> inputSet = Set.of(1,2,3,4,5,6,7,8);
        log("Set input : "+inputSet);
        Set<Integer> setResult = setOrder(inputSet);
        log("result :"+setResult);
    }

    public static Set<Integer> setOrder(Set<Integer> inputList){
        return inputList.parallelStream().map(integer -> integer * 2).collect(Collectors.toSet());
    }


    public static List<Integer> listOrder(List<Integer> inputList){
        return inputList.parallelStream().map(integer-> integer * 2).collect(Collectors.toList());
    }
}
