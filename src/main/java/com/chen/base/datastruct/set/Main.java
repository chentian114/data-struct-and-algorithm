package com.chen.base.datastruct.set;

import com.chen.base.datastruct.bst.BST;

import java.util.ArrayList;

/**
 * @author: Chentian
 * @date: Created in 2020/5/20 6:55
 * @desc
 */
public class Main {

    private static double testSet(Set<String> set, String filename){
        long start = System.nanoTime();
        System.out.println(filename);

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;

    }

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/files/a-tale-of-two-cities.txt";

        Set<String> linkedSet = new LinkedSet<>();
        double time1 = testSet(linkedSet,filePath);

        BSTSet<String> bstSet = new BSTSet<>();
        double time2 = testSet(bstSet,filePath);
        System.out.println("linkedSet time cost:"+time1+"ms bstSet time cost:"+time2+"ms");

        System.out.println("-------------------");

        filePath = projectPath + "/files/pride-and-prejudice.txt";
        linkedSet = new LinkedSet<>();
        time1 = testSet(linkedSet,filePath);

        bstSet = new BSTSet<>();
        time2 = testSet(bstSet,filePath);
        System.out.println("linkedSet time cost:"+time1+"ms bstSet time cost:"+time2+"ms");


    }
}
