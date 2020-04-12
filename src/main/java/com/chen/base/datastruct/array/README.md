# 数组

把元素码成一排进行存放，利用索引访问。

数组最大的优点：快速查询，利用索引查询，时间复杂度为：O(1)；

1. 时间复杂度分析
2. 原理
3. 编写自己的数组类


## 时间复杂度分析

常见算法时间复杂度有O(1),O(n),O(logn),O(nlogn),O(n^2)

大O描述的是算法的运行时间和输入数据之间的关系。忽略常数、不要低价项，只要高阶项；再根据具体数据样本分析；

动态数组的时间复杂度
- 添加操作  O(n)
  - addLast(e) O(1)
  - addFirst(e) O(n)
  - add(index,e) O(n/2) = O(n)
  - resize()  O(n)

- 删除操作  O(n)
  - removeLast(e)  O(1)
  - removeFirst(e)  O(n)
  - remove(index,e) O(n/2) = O(n)
  - resize()  O(n)

- 修改操作  O(1)
  - set(index,e)  O(1)

- 查找操作  O(1) / O(n)
  - get(index)  O(1)
  - contains(e)  O(n)
  - find(e)  O(n)

在动态数组中，添加和删除操作都有可能触发resize()操作，通过均摊复杂度分析综合分析来确定。

动态数组通过resize()操作，在删除或新增操作时，根据现有数组中元素数量来自动调节数组容量大小(capacity)；

执行reszie()操作要防止复杂度震荡问题，如当数组容量满后，
- 添加一个元素后，会使用resize()进行扩容(时间复杂度为O(n))；
- 之后又删除一个元素，若又立即执行resize()进行缩容(时间复杂度为0(n))
- 如此反复，会让数组的增加和删除操作时间复杂度都为O(n); 
- 解决方法：Lazy，当数组中元素数量==capacity/4时，才将capacity减半；

### 原理

定义
- size : 数组已存储元素的数量
- data : 动态数组用于存储元素的数组
- data.length : 动态数组用于存储元素的数组的容量

1.resize数组容量动态扩容
- 当添加一个新的元素时，数组已存储元素数量与数组容量相等时，对数组进行扩容操作； if(size == data.length) resize(2*data.length)
- 创建一个现有数组容量2倍的新数组，并将原数组中所有元素复制到新数组中；newData[i] = data[i]
- 再执行数组元素添加；data[size] = e; size++;

2.resize数组容量动态缩容
- 当删除一个数组中的元素后，数组已存储元素数量是数组容量的1/4，将数组容量缩容为原容量的一半且不等于0；if(size == data.length/4 && data.length/2 !=0) resize(data.length/2);
- 创建一个现有数组容量1/2的新数组，并将原数组中所有元素复制到新数组中；newData[i] = data[i]

3.向数组末尾添加元素
- 检查数组空间是否已满，如果已满进行扩容操作；if(size==data.length) resize(2*data.length); 
- 直接在数组末尾添加元素，data[size] = e; size++;

4.向数组头部/指定索引位置添加元素
- 检查数组空间是否已满，如果已满进行扩容操作；if(size==data.length) resize(2*data.length); 
- 将数组中从末尾到头部/指定索引位置元素全部往后移动一个位置；data[i+1] = data[i];
- 将新的元素添加到数组头部/指定索引位置；

5.删除数组头部/指定索引位置元素
- 将数组末尾到头部/指定索引位置的后一个位置所有元素往前移动一个位置；data[i-1] = data[i];
- 检查数组空间容量是否需要进行缩容。


### 编写自己的数组类

- 封装数组类

1. 判断是否为空  public boolean isEmpty()
2. 获取数组中元素个数  public int getSize()
3. 获取数组容量  public int getCapacity()

- 向数组中添加元素

1. 在指定索引位置添加元素  public void add(int index, E e)
2. 在头部添加元素  public void addLast(E e)
3. 在尾部添加元素  public void addFirst(E e)

- 在数组中查询元素

1. 获取索引位置元素  public E get(int index)
2. 根据指定元素查找索引  public int find(E e)
3. 查找数组中是否包含指定元素  public boolean contains(E e)

- 在数组中修改和删除元素
1. 修改指定索引位置元素  public void set(int index, E e)
2. 删除指定索引位置元素  public E remove(int index)
3. 删除第一个元素  public E removeFirst()
4. 删除最后一个元素  public E removeLast()
5. 删除指定元素  public void removeElement(E e)

- 数组扩展

1. 对数组中元素支持泛型  
2. 扩展数组类为动态数组  private void resize(int newCapacity)


## 对数器

1. 有一个你想要测的方法a；
2. 实现一个绝对正确但是复杂度不好的方法b；
3. 实现一个随机样本产生器；
4. 实现比对的方法；
5. 把方法a和方法b比对很多次来验证方法a是否正确；
6. 如果有一个样本使得比对出错，打印样本分析是哪个方法出错；
7. 当样本数量很多时比对测试依然正确，可以确定方法a已经正确。

## 实现

```java

public class Array<E> {

    private E[] data;
    private int size;

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    // 无参数的构造函数，默认数组的容量capacity=10
    public Array(){
        this(10);
    }

    // 获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize(){
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if(size == data.length)
            resize(2 * data.length);

        for(int i = size - 1; i >= index ; i --)
            data[i + 1] = data[i];

        data[index] = e;

        size ++;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e){
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }

    // 获取index索引位置的元素
    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for(int i = index + 1 ; i < size ; i ++)
            data[i - 1] = data[i];
        size --;
        data[size] = null; // loitering objects != memory leak

        if(size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    // 将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i ++)
            newData[i] = data[i];
        data = newData;
    }
}
```

## 参考

学习刘宇波《玩转数据结构》课程，整理及实践。
