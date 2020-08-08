package com.leokongwq.algorithm.geektime.advanced;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author jiexiu
 * created 2020/8/8 - 15:59
 * <p>
 * //评测题目: 无
 * /*
 * {
 * "algorithms" : ["data structures"],
 * "calculus": ["linear algebra"],
 * "compliers": [
 * "data structures",
 * "formal languages",
 * "computer organization",
 * ],
 * "data structures":["mathematics"],
 * }
 * 以上的map中的每一门学科名作为key，某些学科的学习需要先学习其他学科，依赖的学科是map中的value表示；
 * 请打印出要全部学习所有学科的先后顺序；
 */
public class AliInterviewProblem {

    /**
     * 离散图的拓扑排序
     *
     * @param graph 表示一个离散的图；key 表示需要学习的学科， value表示该学科的前置学科
     */
    private static void topoSort(Map<String, List<String>> graph) {
        Map<String, Boolean> visited = new HashMap<>();
        // 深度优先遍历图
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            //没有访问过
            if (visited.get(entry.getKey()) == null) {
                visited.put(entry.getKey(), true);
                dfs(entry.getKey(), graph, visited);
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
        }
        System.out.print(" ->" + subject);
    }

    /**
     * 离散图的拓扑排序
     *
     * @param graph 表示一个离散的图；key 表示需要学习的学科， value表示该学科的后置学科
     */
    private static void topoSortV1(Map<String, List<String>> graph) {
        Map<String, Integer> inDegree = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            for (String item : entry.getValue()) {
                if (inDegree.containsKey(item)) {
                    inDegree.put(item, inDegree.get(item) + 1);
                } else {
                    inDegree.put(item, 1);
                }
            }
        }

        for (String key : graph.keySet()) {
            if (!inDegree.containsKey(key)) {
                inDegree.put(key, null);
            }
        }

        LinkedList<String> queue = new LinkedList<>();
        //入度为0的点
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == null) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            String item = queue.pollFirst();
            if (graph.get(item) != null) {
                for (String key : graph.get(item)) {
                    Integer num = inDegree.get(key);
                    if (num != null) {
                        if (num - 1 <= 0) {
                            inDegree.put(key, null);
                            queue.add(key);
                        } else {
                            inDegree.put(key, num - 1);
                        }
                    }
                }
            }
            System.out.print(" -> " + item);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("algorithms", Lists.newArrayList("data structures"));
        graph.put("calculus", Lists.newArrayList("linear algebra"));

        graph.put("compliers", Lists.newArrayList("data structures", "formal languages", "computer organization"));

        graph.put("data structures", Lists.newArrayList("mathematics"));

//        topoSort(graph);

        topoSortV1(graph);
    }
}
