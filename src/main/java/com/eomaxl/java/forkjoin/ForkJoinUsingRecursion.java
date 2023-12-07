package com.eomaxl.java.forkjoin;

import com.eomaxl.java.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.eomaxl.java.util.CommonUtil.*;
import static com.eomaxl.java.util.LoggerUtil.log;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

    private List<String> inputList;
    public ForkJoinUsingRecursion(List<String> inputList){
        this.inputList = inputList;
    }
    public static void main(String[] args){
        stopWatch.start();
        stopWatch.stop();
        List<String> resultList  = new ArrayList<>();
        List<String> names = DataSet.namesList();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(names);
        // submit the task into the forkjoinpool. we have the forkjoinpool instance which has a method called invoke.
        forkJoinPool.invoke(forkJoinUsingRecursion);
        // so once the task is added to the shared queue, all the threads in the forkjoin pool is going to start taking the task and start working on it.
        resultList = forkJoinPool.invoke(forkJoinUsingRecursion);
        log("Final Result : "+resultList);
        log("Total time taken : "+stopWatch.getTime());
    }

    private static String addNameLengthTransform(String name){
        delay(500);
        return name.length()+" - "+name;
    }


    /* Compute method is the place where the actual work happens. Basically this is the place where we are going to do the fork and join operations. First we need to split the list into
    chunks until we get the smallest chunk */
    @Override
    protected List<String> compute(){
        //Base condition
        if(inputList.size() <= 1){
            List<String> resultList  = new ArrayList<>();
            inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
            return resultList;
        }

        int midpoint = inputList.size()/2;
        // fork is the part of the recursive task which is going to return fork join task
        ForkJoinTask<List<String>> leftInputList  =  new ForkJoinUsingRecursion(inputList.subList(0,midpoint)).fork();
        inputList = inputList.subList(midpoint,inputList.size());
        List<String> rightResult = compute(); // recursion happens
        // after the compute is done, next thing we need to do is invoke the join.
        List<String> leftResult =  leftInputList.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }
}
