package com.caofangqi.test.jvmtest.facade;

import com.alibaba.fastjson.JSONObject;
import com.caofangqi.test.jvmtest.utils.UnsafeUtils;
import org.springframework.stereotype.Component;
import sun.misc.Unsafe;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author caofangqi created at 2020/3/18 9:47 AM
 */
@Component
public class MemoryFacade {

    List<ByteBuffer> byteBuffers = new CopyOnWriteArrayList<>();
    List<Long> addressList = new CopyOnWriteArrayList<>();

    public String memoryAllocate(int size){
       ByteBuffer byteBuffer =   ByteBuffer.allocateDirect(size);
        byteBuffer.putInt(1);
        byteBuffers.add(byteBuffer);
     return JSONObject.toJSONString(byteBuffers.size());
    }

    public String memoryAllocateByUnsafe(int size){
        long address = UnsafeUtils.unsafe.allocateMemory(size);
        Unsafe unsafe  = UnsafeUtils.unsafe;
        addressList.add(address);
        for (int i = 0; i < (int)size/4; i++) {
            address = address +4;
            unsafe.putChar(address,'a');
        }
        return JSONObject.toJSONString(addressList);
    }

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeUtils.unsafe;
        long address = unsafe.allocateMemory(100);
        unsafe.putChar(address,'a');
        System.out.println(">>>>>>>>>>:"+unsafe.getChar(address));
        unsafe.putChar(address+4,'b');
        System.out.println(">>>>>>>>>>:"+unsafe.getChar(address));
        System.out.println(">>>>>>>>>>:"+unsafe.getChar(address+4));

    }



}
