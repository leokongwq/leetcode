package com.meililinc.mls.datastruct;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/10/26
 * Time: 下午6:20
 * Email:jiexiu@mogujie.com
 */
public class TestLinkedHashMap {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true);
        map.put("apple", "苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");

        map.get("banana");
        map.get("apple");

        map.entrySet();
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
