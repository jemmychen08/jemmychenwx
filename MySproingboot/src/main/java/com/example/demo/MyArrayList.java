package com.example.demo;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * @Auther: jemmy
 * @Date: 2019/7/9 17:42
 * @Description:
 */
public class MyArrayList extends AbstractList {

    /**
     * 默认初始化容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 如果自定义容量为0，则会默认用它来初始化ArrayList。或者用于空数组替换。
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

     /** 如果没有自定义容量，则会使用它来初始化ArrayList。或者用于空数组比对。
            */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

        /**
            * 这就是ArrayList底层用到的数组

 * 非私有，以简化嵌套类访问
 * transient 在已经实现序列化的类中，不允许某变量序列化
 */
    /**
     * 扩展：什么是序列化
     * 序列化是指：将对象转换成以字节序列的形式来表示，以便用于持久化和传输。
     *
     * 实现方法：实现Serializable接口。然后用的时候拿出来进行反序列化即可又变成Java对象。

     *
     */
    transient Object[] elementData;
    /**
     * * transient关键字解析
     *   Java中transient关键字的作用，简单地说，就是让某些被修饰的成员属性变量不被序列化
     * 有了transient关键字声明，则这个变量不会参与序列化操作，即使所在类实现了Serializable接口，反序列化后该变量为空值。
     *
     * 那么问题来了：ArrayList中数组声明：transient Object[] elementData;，事实上我们使用ArrayList在网络传输用的很正常，并没有出现空值。
     *
     * 原来：ArrayList在序列化的时候会调用writeObject()方法，将size和element写入ObjectOutputStream；反序列化时调用readObject()，从ObjectInputStream获取size和element，再恢复到elementData。
     *
     * 那为什么不直接用elementData来序列化，而采用上诉的方式来实现序列化呢？
     *
     * 原因在于elementData是一个缓存数组，它通常会预留一些容量，等容量不足时再扩充容量，那么有些空间可能就没有实际存储元素，
     * 采用上诉的方式来实现序列化时，就可以保证只序列化实际存储的那些元素，而不是整个数组，从而节省空间和时间。
     */

    /**
     * 实际ArrayList集合大小
     */
    private int size;

    /**
     * 可分配的最大容量
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    /**
     * 根据initialCapacity 初始化一个空数组，如果值为0，则初始化一个空数组:
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 不带参数初始化，默认容量为10
     */
    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 通过集合做参数的形式初始化
     */
/*    public MyArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0){
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class) {
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }*/

    /**
     * 在数组末尾添加元素
     *
     *
     * 看到它首先调用了ensureCapacityInternal()方法.注意参数是size+1,这是个面试考点。
     *
     * 好了，那到这里可以说一下为什么要size+1。
     *
     * size+1代表的含义是：
     *
     * 如果集合添加元素成功后，集合中的实际元素个数。
     *
     * 为了确保扩容不会出现错误。
     *
     * 假如不加一处理，如果默认size是0，则0+0>>1还是0。
     * 如果size是1，则1+1>>1还是1。有人问:不是默认容量大小是10吗?事实上，jdk1.8版本以后，
     * ArrayList的扩容放在add()方法中。之前放在构造方法中。我用的是1.8版本，
     * 所以默认ArrayList arrayList = new ArrayList();后，size应该是0.所以,size+1对扩容来讲很必要.
     *
     */
   /* public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }*/

    /**
     *
     *  这个方法里又嵌套调用了两个方法:计算容量+确保容量
     */

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     *
     *
     * 计算容量：如果elementData是空，则返回默认容量10和size+1的最大值，否则返回size+1
     * @param elementData
     * @param minCapacity
     * @return
     */
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * 计算完容量后，进行确保容量可用：(modCount不用理它，它用来计算修改次数)
     *
     * 如果size+1 > elementData.length证明数组已经放满，则增加容量，调用grow()。
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 增加容量：默认1.5倍扩容。
     *
     * 获取当前数组长度=>oldCapacity
     *
     * oldCapacity>>1 表示将oldCapacity右移一位(位运算)，相当于除2。再加上1，相当于新容量扩容1.5倍。
     *
     * 如果newCapacity&gt;1=1,1&lt;2所以如果不处理该情况，扩容将不能正确完成。
     *
     * 如果新容量比最大值还要大，则将新容量赋值为VM要求最大值。
     *
     * 将elementData拷贝到一个新的容量中。
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0)
          //  newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        {
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
