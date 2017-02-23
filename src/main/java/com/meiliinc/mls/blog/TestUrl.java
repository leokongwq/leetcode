package com.meiliinc.mls.blog;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/31
 * Time: 下午11:14
 * Email:jiexiu@mogujie.com
 */
public class TestUrl {

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String, String>(16,0.75f,true);
        map.put("apple", "苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");

        map.get("banana");
        map.get("apple");

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        Map currentMap = new HashMap();
        currentMap.put(12, null);
    }
}
