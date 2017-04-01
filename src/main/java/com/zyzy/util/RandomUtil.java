package com.zyzy.util;

import java.util.Random;

/**
 * 随机工具类
 * @author BillZhao
 *
 */
public final class RandomUtil {

    private static Random random = new Random();
      
    /**
     * 限定最大值的随机数
     * @param max
     * @return
     */
    public static int randomMax(int max) {
        return (int) (Math.random() * max);
    }

    /**
     * 在指定范围内产生一个随机数
     * @param lower
     * @param upper
     * @return
     */
    public static int random(int lower, int upper) {
        if (lower < 0 || upper < 0) {
            throw new IllegalArgumentException();
        }
        
        int len = String.valueOf(upper).length();
        int base = 1;
        for (int i = 0; i < len; i++)
            base *= 10;

        int num;
        do
            num = (int) (Math.random() * (double) base);
        while (num < lower || num > upper);
        return num;
    }

    /**
     * 产生指定长度数字+大些字母字符串
     * @param len
     * @return
     */
    public static String random(int len) {
        String sys = "23456789ABCDEFGHIJKLMNQRSTUWXYZ";
        String re = "";
        for (int i = 0; i < len; i++) {
            int n = random.nextInt(31);
            re += sys.substring(n, n + 1);
        }
        return re;
    }
    
    /**
     * 产生指定长度数字字符串
     * @param len
     * @return
     */
    public static String randomDegital(int len) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < len; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 产生指定长度随机字符串
     * @param len
     * @return
     */
    public static String randomString(int len) {
        String result = "";

        for (int i = 0; i < len; i++) {
            result = result + randomChar();
        }
        return result;
    }

    /**
     * 产生随机字符（去除0/o,l/i不容易辨认）
     * @return char
     */
    public static char randomChar() {
        /*
         * printable characters are ascii 32 - 126 (that's 95 characters) so we
         * generate 0-95 and add 32 to get this range
         */
        int val = random.nextInt(95) + 32;
        // 不要空格，0/o,1/i等不容易辨认的字符
        if(val == 32 || val == 48 || val == 79 || val == 105 || val == 111)
            val ++;
        return (char) val;
    }
    
    /** 
     * 随机指定范围内N个不重复的数 
     * 使用场景：一元夺宝，从某件商品的奖池号码中选择其中几个；
     * @param min 指定范围最小值 
     * @param max 指定范围最大值 
     * @param n 随机数个数 
     */  
    public static int[] randomArea(int min, int max, int n){  
        if (n > (max - min + 1) || max < min) {  
               return null;  
           }  
        int[] result = new int[n];  
        int count = 0;  
        while(count < n) {  
            int num = (int) (Math.random() * (max - min)) + min;  
            boolean flag = true;  
            for (int j = 0; j < n; j++) {  
                if(num == result[j]){  
                    flag = false;  
                    break;  
                }  
            }  
            if(flag){  
                result[count] = num;  
                count++;  
            }  
        }  
        return result;  
    }  
    
    public static void main(String[] args){
    	System.out.println("method-random:" +  RandomUtil.random(6));
    	System.out.println("method-random:" +  RandomUtil.random(6));
    	System.out.println("method-randomDigital:" +  RandomUtil.randomDegital(6));
    	System.out.println("method-randomString:" +  RandomUtil.randomString(6));
    	System.out.println("method-randomChar:" +  RandomUtil.randomChar());
    	System.out.println("method-randomArea:" +  StringUtil.getStringFromIntArr(RandomUtil.randomArea(500,1000,5)));
    }
}
