package com.theonecai.algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 合并多个已排序的文件的内容
 * @Author: theonecai
 * @Date: Create in 2020/6/23 19:05
 * @Description:
 */
public class MergeSortedFiles {

    public static void mergeFiles(String[] files, String outFile) throws Exception {
        Map<String, BufferedReader> bufferedReaderMap = new HashMap<>(files.length);
        BufferedWriter bw = null;
        try {
            for (String fileName : files) {
                bufferedReaderMap.put(fileName, new BufferedReader(new FileReader(new File(fileName))));
            }
            bw = new BufferedWriter(new FileWriter(new File(outFile)));

            ArrayHeap<Node> minHeap = new ArrayHeap<>(files.length, false);
            minHeap.heapify(getFirstFromEachFile(bufferedReaderMap), files.length);

            Node newNode = null;
            while (minHeap.size() > 0) {
                Node top = minHeap.top();
                bw.write(top.data);
                bw.write('\n');

                // 所有文件都读完了
                if (bufferedReaderMap.size() == 0) {
                    minHeap.poll();
                    continue;
                }

                newNode = getNextNode(top.fileName, bufferedReaderMap);
                // 文件都读完了
                if (newNode == null) {
                    minHeap.poll();
                    continue;
                }

                minHeap.updateTop(newNode);
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (!bufferedReaderMap.isEmpty()) {
                bufferedReaderMap.values().forEach(bf -> {
                     try {
                         bf.close();
                     } catch (IOException e) {
                         System.out.println(e.getMessage());
                     }
                 });
                bufferedReaderMap.clear();
            }
        }
    }

    private static Node getNextNode(String currentFile, Map<String, BufferedReader> bufferedReaderMap) throws IOException {
        if (!bufferedReaderMap.containsKey(currentFile)) {
            return null;
        }
        Node node = null;
        String line = bufferedReaderMap.get(currentFile).readLine();
        if (line != null) {
            node = new Node(currentFile, line);
            return node;
        }

        // 当前文件读完了
        BufferedReader bf = bufferedReaderMap.remove(currentFile);
        bf.close();

        return null;
    }

    private static Node[] getFirstFromEachFile(Map<String, BufferedReader> bufferedReaderMap) throws IOException {
        Node[] data = new Node[bufferedReaderMap.size()];
        int index = 0;
        for (Map.Entry<String, BufferedReader> entry : bufferedReaderMap.entrySet()) {
            try {
                Node n = new Node(entry.getKey(), entry.getValue().readLine());
                data[index++] = n;
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return data;
    }

    static class Node implements Comparable<Node> {
        String fileName;
        String data;

        public Node(String fileName, String data) {
            this.fileName = fileName;
            this.data = data;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return 1;
            }
            return this.data.compareTo(o.data);
        }
    }

    public static void main(String[] args) throws Exception {
        String[] files = {"1.txt", "2.txt", "3.txt", "4.txt", "5.txt", "6.txt"};
        String outFile = "out.txt";

        genDataFile(files);

        long a = System.nanoTime();
        MergeSortedFiles.mergeFiles(files, outFile);
        System.out.println("costtime:" + (System.nanoTime() - a));

        checkResultOrder(outFile);
    }

    private static void checkResultOrder(String outFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(outFile)))) {
            String pre = br.readLine();
            String current = br.readLine();
            while (current != null || !current.trim().equals("")) {
                current = br.readLine();
                if (current == null) {
                    break;
                }
                if (pre.compareTo(current) > 0) {
                    System.err.println("pre=" + pre + ", current=" + current);
                    break;
                }
                pre = current;
            }
        }
    }

    private static void genDataFile(String[] files) throws IOException {
        for (int j = 0; j < files.length; j++) {
            genDataFile(files[j]);
        }
    }

    private static void genDataFile(String file) throws IOException {
        int len = 100;
        QuickSort<String> strSort = new QuickSort<>();
        Random random = new Random();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file)))) {

            List<String> data = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                data.add(RandomStringUtil.randomString(128 + random.nextInt(15)));
            }

            String[] d = data.toArray(new String[len]);
            strSort.quickSort(d);
            for (String datum : d) {
                bw.write(datum);
                bw.write('\n');
            }
            data.clear();
            bw.flush();
        }
    }
}
