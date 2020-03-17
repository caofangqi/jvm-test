package com.caofangqi.test.jvmtest.controller;

import com.caofangqi.test.jvmtest.facade.TestFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caofangqi created at 2020/3/17 4:06 PM
 */
@RestController
public class TestController {


    @Resource
    private TestFacade testFacade;

    /**
     * 测试栈溢出
     * @param threshold threshold
     * @return info
     */
    @GetMapping("/testStackOverflow")
    public String testStackOverFlowError(Integer threshold){
        int [] count = new int[]{1};

        try {
            return testFacade.recursion(count,threshold == null ? 0 : threshold);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "第"+count[0]+"次，栈溢出了";
    }

    /**
     * 测试OOM
     * @param count count 数量
     * @return info
     */
    @GetMapping("/testOutOfMemory")
    public String testOutOfMemory(Integer count,Integer byteSize){
        List<byte[]>  byteList = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            byteList.add(new byte[byteSize]);
        }
        return "当前大小"+byteList.size();
    }



}
