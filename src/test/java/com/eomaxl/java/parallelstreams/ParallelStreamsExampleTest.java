package com.eomaxl.java.parallelstreams;

import com.eomaxl.java.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.xml.crypto.Data;

import java.util.List;

import static com.eomaxl.java.util.CommonUtil.*;
import static com.eomaxl.java.util.LoggerUtil.log;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    //need instance of the actual class
    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {
        List<String> inputList = DataSet.namesList();

        //when
        startTime();
        List<String> resultList  = parallelStreamsExample.stringTransform(inputList);
        timeTaken();

        //then
        assertEquals(4,resultList.size());
        resultList.forEach(name -> {
            assertTrue(name.contains(("-")));
        });
    }

    @ParameterizedTest
    @ValueSource(booleans = {false,true})
    void stringTransform1(boolean isParallel) {
        List<String> inputList = DataSet.namesList();

        //when
        stopWatch.start();
        List<String> resultList  = parallelStreamsExample.stringTransform1(inputList,isParallel);
        stopWatch.stop();
        log("The time taken was : "+stopWatch.getTime());
        stopWatch.reset();

        //then
        assertEquals(4,resultList.size());
        resultList.forEach(name -> {
            assertTrue(name.contains(("-")));
        });
    }
}