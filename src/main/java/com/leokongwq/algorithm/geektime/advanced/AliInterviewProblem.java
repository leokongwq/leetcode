package com.leokongwq.algorithm.geektime.advanced;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/8/8 - 15:59
 *
 * //评测题目: 无
 * /*
 * {
 *   "algorithms" : ["data structures"],
 *   "calculus": ["linear algebra"],
 *   "compliers": [
 *        "data structures",
 *        "formal languages",
 *        "computer organization",
 *      ],
 *  "data structures":["mathematics"],
 *  }
 * 以上的map中的每一门学科名作为key，某些学科的学习需要先学习其他学科，依赖的学科是map中的value表示；
 * 请打印出要全部学习所有学科的先后顺序；
 *
 */
public class AliInterviewProblem {

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();

        map.put("algorithms", Lists.newArrayList("data structures"));
        map.put("calculus", Lists.newArrayList("linear algebra"));

        map.put("compliers", Lists.newArrayList("data structures", "formal languages", "computer organization"));

        map.put("data structures", Lists.newArrayList("mathematics"));

        Map<String, Boolean> visited = new HashMap<>();
        // 深度优先遍历图
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            //没有访问过
            if (visited.get(entry.getKey()) == null) {
                visited.put(entry.getKey(), true);
                dfs(entry.getKey(), map, visited);
            }
        }
    }

    private static void dfs(String subject, Map<String, List<String>> map, Map<String, Boolean> visited) {
        if (map.get(subject) != null) {
            for (String preSubject : map.get(subject)) {
                if (visited.get(preSubject) != null) {
                    continue;
                }
                visited.put(preSubject, true);
                dfs(preSubject, map, visited);
            }
        }        System.out.print(" ->" + subject);
    }
}
