package com.theonecai.algorithms;

import com.theonecai.algorithms.common.Graph;

import java.util.Stack;

/**
 * 深度优先
 * Created by caizh on 19-1-29.
 */
public class DFS {

    public static void main(String[] args) {
        Graph graph = new Graph(10);

        //初始化tu图
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        dfs(graph, 2, 8);
    }

    private static void dfs(Graph graph, int start, int end) {
        //已访问的端点,visitedVertex[i] = true,表示端点i已访问
        boolean[]  visitedVertex = new boolean[graph.getVertex()];

        //存储已经被访问、但相连的顶点还没有被访问的顶点
        Stack<Integer> stack = new Stack<>();

        //搜索路径,通过顶点i的邻接表访问到顶点j，那prev[j]就等于i
        int[] path = new int[graph.getVertex()];

        for (int i = 0; i < visitedVertex.length; i++) {
            visitedVertex[i] = false;
            path[i] = -1;
        }

        visitedVertex[start] = true;

        stack.push(start);

        boolean found = false;
        while(!stack.isEmpty()) {
            //取栈顶端点(不移除)
            Integer currentVertex = stack.peek();

            if (currentVertex == end) {
                found = true;
                printPath(path, start, end);
                break;
            }

            //当前端点是否可以继续前进
            boolean deadEnd = true;
            for (Integer v : graph.relatedVertexs(currentVertex)) {
                if (!visitedVertex[v]) {
                    visitedVertex[v] = true;
                    stack.push(v);
                    path[v] = currentVertex;
                    deadEnd = false;
                }
            }
            //当前端点无法继续前进，移除
            if (deadEnd) {
                stack.pop();
            }
        }

        if (!found) {
            System.out.println("path not found");
        }

    }

    private static void printPath(int[] prev, int startVertex, int endVertex) {
        if (prev[endVertex] != -1 && endVertex != startVertex) {
            printPath(prev, startVertex, prev[endVertex]);
        }
        System.out.print(endVertex + " ");
    }
}
