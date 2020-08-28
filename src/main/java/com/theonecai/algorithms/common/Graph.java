package com.theonecai.algorithms.common;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: caizhh
 * @Date: Create in 19-1-29 下午6:46
 * @Description:
 */
public class Graph {
    //图中端点个数
    private int vertex;

    //保存与图中端点连接的端点
    private List<Integer> relatedVertexs[];

    public Graph(int vertex) {
        this.vertex = vertex;
        this.relatedVertexs = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++) {
            //保存与图中点i连接的点
            relatedVertexs[i] = new LinkedList<>();
        }
    }

    public int getVertex() {
        return vertex;
    }

    /**
     * 端点vertex与端点vertex2连接
     * @param vertex
     * @param vertex2
     */
    public void addEdge(Integer vertex, Integer vertex2) {
        if (vertex.equals(vertex2)) {
            throw new IllegalArgumentException("vertex can't equal to vertex2");
        }
        relatedVertexs[vertex].add(vertex2);
        relatedVertexs[vertex2].add(vertex);
    }

    public List<Integer> relatedVertexs(int vertex) {
        return relatedVertexs[vertex];
    }
}
