package com.eomaxl.java.util;

import org.apache.commons.lang3.time.StopWatch;

import static java.lang.Thread.sleep;
import static java.rmi.server.LogStream.log;

public class CommonUtil {
    public static StopWatch stopWatch = new StopWatch();
    public static void delay(long delayMilliSeconds){
        try{
            sleep(delayMilliSeconds);
        } catch (Exception e){
            LoggerUtil.log("Exception is :"+e.getMessage());
        }
    }

    public static String transform(String s){
        CommonUtil.delay(500);
        return s.toUpperCase();
    }

    public static void startTime(){
        stopWatch.start();
    }

    public static void timeTaken(){
        stopWatch.stop();
        log("Total time taken :"+stopWatch.getTime());
    }

    public static void stopWatchReset(){
        stopWatch.reset();
    }

    public static int noOfCores(){
        return Runtime.getRuntime().availableProcessors();
    }


}
