package com.zyzy.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.Configuration;

public class OrderNumberGenerator {
    private static final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private static final CountDownLatch latch = new CountDownLatch(1);
    /**
    * 每毫秒生成订单号数量最大值，约定取整百，整千。
    */
    public static final int maxPerMSECSize = 1000;//Configuration.root().getInt("maxPerMSECSize", 1000);

    private static void init() {
        for (int i = 0; i < maxPerMSECSize; i++) {
            queue.offer(i);
        }
        latch.countDown();
    }

    public static Integer poll() {
        try {
            if (latch.getCount() > 0) {
                init();
                latch.await(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer i = queue.poll();
        queue.offer(i);
        return i;
    }

    public static String get() {
        long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        String number = maxPerMSECSize + poll() + "";
        return nowLong + number.substring(1);
    }
}
