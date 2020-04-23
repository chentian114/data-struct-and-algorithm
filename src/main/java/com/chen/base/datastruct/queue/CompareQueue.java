package com.chen.base.datastruct.queue;

import com.chen.base.datastruct.array.Array;

import java.util.Random;

/**
 * @author: Chentian
 * @date: Created in 2020/4/24 7:15
 * @desc 循环队列 VS 数组队列
 */
public class CompareQueue {

    /**
     * 测试使用queue运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
     * @param queue 队列
     * @param opCount 操作次数
     * @return 耗时（秒）
     */
    public static double testQueue(Queue<Integer> queue , int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0 ; i < opCount ; i++){
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0 ; i < opCount ; i++){
            queue.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue,opCount);
        System.out.println("ArrayQueue time:" + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue,opCount);
        System.out.println("LoopQueue time:" + time2 + " s");
    }
}
