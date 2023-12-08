package com.eomaxl.java.parallelstreams;

import com.eomaxl.java.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eomaxl.java.util.CommonUtil.delay;
import static com.eomaxl.java.util.CommonUtil.stopWatch;
import static com.eomaxl.java.util.LoggerUtil.log;

public class ParallelStreamsExample {
    public List<String> stringTransform(List<String> namesList){
        return namesList
                //.stream()
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> stringTransform1(List<String> namesList, boolean isParalell){

        Stream<String> namesStream = namesList.stream();

        if(isParalell){
            namesStream.parallel();
        }
        return namesStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args){
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        stopWatch.start();
        List<String> resultList = parallelStreamsExample.stringTransform(namesList);
        log(" Result list : "+resultList);
        stopWatch.stop();
        log("Time taken : "+stopWatch.getTime());
    }

    private String addNameLengthTransform(String name){
        delay(500);
        return name.length()+" - "+name;
    }
}
