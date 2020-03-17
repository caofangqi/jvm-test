package com.caofangqi.test.jvmtest.facade;

import org.springframework.stereotype.Component;

/**
 * @author caofangqi created at 2020/3/17 4:12 PM
 */
@Component
public class TestFacade {







    public String recursion(int [] count,int threshold){
        if(count[0] == threshold){
            return "递归"+count[0]+"次";
        }
        count[0] = count[0]+1;
        return recursion(count,threshold);
    }


}
