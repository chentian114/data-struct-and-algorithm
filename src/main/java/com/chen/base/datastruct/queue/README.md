# 队列

队列是一种线性结构

相比数组，队列对应的操作是数组的子集

只能从一端（队尾）添加元素，只能从另一端（队首）取出元素；

队列是一种先进先出的数据结构，First In First Out(FIFO)

## 应用

操作系统中执行任务的排队等；

## 时间复杂度分析

ArrayQueue<E>  数组队列
- void enqueue(E) 往队尾添加元素  O(1) 均摊
- E dequeue()    取出队首元素   O(n)
- E getFront()   获取队首元素   O(1)
- int getSize()  数组队列元素数量  O(1)
- boolean isEmpty()  判断是否为空  O(1)

LoopQueue<E> 循环队列
- void enqueue(E) 往队尾添加元素  O(1) 均摊
- E dequeue()    取出队首元素   O(1)  均摊
- E getFront()   获取队首元素   O(1)
- int getSize()  数组队列元素数量  O(1)
- boolean isEmpty()  判断是否为空  O(1)


## 原理


数组队列 VS 循环队列

数组队列：
- 入队操作，不断往数组尾部添加元素
- 出队操作，将数组array[0]位置元素取出，将剩余元素全部往前移动一位；array[i] = array[i+1];

循环队列：
- 存放元素数组为:data,数组长度为：data.length,队列容量capacity=data.length-1,队列中元素数量size；
- 使用两个变量front,tail分别指向数组中队首和队尾的位置；
- 当front == tail 时，队列为空；数组初始设置front=tail=0位置;
- 当(tail + 1)%data.length == front时，队列满；
- 入队操作，检查队列是否已满，是则对数组进行扩容为原来的2位;resize(2*capacity);将元素放到tail指向的位置，tail = (tail + 1) % data.length;
- 出队操作，将front指向的元素从数组中取出，front = (front + 1) % data.length; 检查数组中元素数量是否为数组容量的1/4且数组容量的除2不等0，对数组进行缩容； if(size == capacity/4 && capacity/2 != 0) resize(capacity/2);
- resize数组扩缩容操作，将原数组中元素从front开始，i=0,取(front+i)%data.length位置元素，i加1，直到取出size个元素，将旧数组中元素全部放入新数组中；for(int i = 0; i < size; i++)newData[i] = data[(i + front) % data.length];

固定数组循环队列实现方式2：
- 存放元素数组为:data,数组长度为：data.length
- 使用3个变量front指向要出队位置的元素，tail-指向要添加的位置；size-数组中存放元素数量；
- 当size = 0时，队列为空；当size == data.length，队列已满；
- 初始: front = tail = 0; size= 0; 队列为空；
- 如果size < data.length,当需要入队时，将元素放到tail位置，当tail来到数组最后一个位置时，tail重置到0位置；tail = tail == data.length-1 ? 0 : tail + 1;
- 如果size != 0 ,当需要出队时，将front位置的元素出队，当front来到数组最后一个位置时，front重置到0位置；front = front == data.length-1 ? 0 : front + 1;


## 实现

Queue<E>
- void enqueue(E) 往队尾添加元素
- E dequeue()    取出队首元素
- E getFront()   获取队首元素
- int getSize()  数组队列元素数量
- boolean isEmpty()  判断是否为空

数组队列
```
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayQueue(){
        array = new Array<>();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e){
        array.addLast(e);
    }

    @Override
    public E dequeue(){
        return array.removeFirst();
    }

    @Override
    public E getFront(){
        return array.getFirst();
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < array.getSize() ; i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
```

循环队列
```java
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;  // 有兴趣的同学，在完成这一章后，可以思考一下：
                       // LoopQueue中不声明size，如何完成所有的逻辑？
                       // 这个问题可能会比大家想象的要难一点点：）

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void enqueue(E e){

        if((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue(){

        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity + 1];
        for(int i = 0 ; i < size ; i ++)
            newData[i] = data[(i + front) % data.length];

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
```

数组队列VS循环队列
```java
import java.util.Random;

public class Main {

    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount){

        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0 ; i < opCount ; i ++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0 ; i < opCount ; i ++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");
    }
}
```

## 参考

- 学习刘宇波《玩转数据结构》课程，整理及实践。
- 学习左程云《牛客网算法初级班》课程，整理及实践。
