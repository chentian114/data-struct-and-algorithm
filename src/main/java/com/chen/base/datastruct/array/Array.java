package com.chen.base.datastruct.array;

/**
 * @author: Chentian
 * @date: Created in 2020/4/13 7:21
 * @desc
 */
public class Array<E> {
    /** 存放的元素数量 */
    private int size ;
    /** 存放元素数组 */
    private E[] data;

    public Array(int capacity){
        size = 0 ;
        data = (E[])new Object[capacity];
    }

    public Array(){
        this(10);
    }

    /**
     * 在index索引的位置插入一个新元素e
     * @param index
     * @param e
     */
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("index is error!");
        }

        if(size == data.length){
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= index ; i-- ){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    public void addLast(E e){
        add(size,e);
    }

    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }

        E e = data[index];

        for (int i = index; i < size ; i++){
            data[i] = data[i+1];
        }
        size -- ;
        data[size] = null;

        if(size == data.length / 4 && data.length /2 != 0){
            resize(data.length/2);
        }
        return e;
    }

    public E removeLast(){
        return remove(size-1);
    }

    public E removeFirst(){
        return remove(0);
    }

    public void removeElement(E e){
        int index = find(e);
        if(index == -1){
            return;
        }
        remove(index);
    }

    public int find(E e){
        for (int i = 0; i < size ; i++){
            if(e.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E e){
        return find(e) != -1;
    }

    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }
        return data[index];
    }

    public void set(int index,E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }
        data[index] = e;
    }

    /**
     * 将数组空间的容量变成newCapacity大小
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size ; i++){
            newArray[i] = data[i];
        }
        data = newArray;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("Array: size=").append(size)
           .append(", current capacity=").append(data.length)
           .append(", [");
        for (int i = 0; i < size ; i++){
            sbr.append(data[i]);
            if( i != size -1){
                sbr.append(", ");
            }
        }
        sbr.append("]");
        return sbr.toString();
    }

    public static void main(String[] args) {

        Array<String> myArr = new Array<>();
        myArr.addFirst("hello");
        myArr.add(1,"world");

        for (int i = 0 ; i< 10 ; i++){
            myArr.addLast(String.valueOf(i));
        }
        System.out.println(myArr);

        System.out.println(myArr.contains("hello"));
        System.out.println(myArr.contains("10"));

        for (int i = 9 ; i > 2 ; i--){
            myArr.remove(i);
        }

        System.out.println(myArr);

        myArr.remove(2);
        System.out.println(myArr);

    }
}
