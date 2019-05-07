package com.lancslot.morn.utils.util;

import java.util.Random;

/**
 * 生成五位随机数
 **/
public class RandomChar {

    public static String getRandomChar() {
        String str = "";
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int num = rand.nextInt(2);
            switch (num) {
                case 0:
                    char c2 = (char) (rand.nextInt(26) + 'A');//生成随机大写字母
                    str += c2;
                    break;
                case 1:
                    str += rand.nextInt(10);//生成随机数字
                    break;
                case 2:
                    char c1 = (char) (rand.nextInt(26) + 'a');//生成随机小写字母
                    str += c1;
                    break;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        int i = 100;
        while (i > 0) {
            System.out.println("生成的5个随机验证码是:" + getRandomChar());
            i--;
        }
    }
}
