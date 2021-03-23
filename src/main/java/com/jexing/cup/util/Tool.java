package com.jexing.cup.util;

import java.util.HashMap;

public class Tool {
    public static String hash(String key,int level){
        char[] chars = key.toCharArray();
        char[] answer = new char[level];
        int len = chars.length;
        for (int i = 0; i < level; i++) {
            answer[i] = chars[i];
            for (int j = i + level; j < len; j+= level) {
                answer[i] ^= chars[j];
            }
            answer[i] = (char) (answer[i]%26 + 'a');
        }
        return new String(answer);
    }

    public static HashMap<String,String> getTypeMap(){
        HashMap<String,String> map= new HashMap<>();
        map.put("image/png",".png");
        map.put("image/jpeg",".jpg");
        map.put("image/gif",".gif");
        map.put("image/x-icon",".ico");
        return map;
    }
}
