package lsc.jdk.collection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 深入复制和普通复制 基本数据类型和String自动实现深度复制，因为String被设计为final 实现深度复制的两种方法： 第一种：适用于简单对象 对象数据类型需要是哪cloneable接口 并重写clone方法 实现深度复制
 * 第二种：适用于复杂对象 使用序列化的方式进行深度copy 对象的引用传递和值传递
 */
public class DeepCopyAndCopy {

    public static void main(String[] args) {
        // copyArray();
        // deepCopy();
        copydata();
    }

    public static void copyArray() {
        // 基本数据类型自动实现深度复制
        int[] ids = { 1, 2, 3, 4, 5, 6 };
        int[] ids1 = Arrays.copyOf(ids, ids.length);
        System.out.println(Arrays.toString(ids1));
        ids[0] = 9;
        System.out.println("改变原数组==浅复制" + Arrays.toString(ids1));
        // 影子复制
        List<Demo1> sourceCollection = new ArrayList<Demo1>();
        sourceCollection.add(new Demo1(0));
        // 创建一个新对象
        List<Demo1> destCollection = new ArrayList<Demo1>();
        destCollection.addAll(sourceCollection);// 内部实现用的就是System.arraycopy(src, 0, des, 0, 1); 影子复制
        destCollection.get(0).setNum(7);// 重新赋值
        System.out.println("================源数组打印================");
        for (Demo1 demo : sourceCollection) {
            System.out.println("元素==" + demo.getNum()); // 源数组被改变
        }
    }

    public static void deepCopy() {
        // 影子复制
        List<Demo> sourceCollection = new ArrayList<Demo>();
        List<Demo> destCollection = new ArrayList<Demo>();
        sourceCollection.add(new Demo(0));
        try {
            for (Demo demo : sourceCollection) {
                destCollection.add(demo.clone()); // 深度克隆
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        destCollection.get(0).setNum(7);// 重新赋值
        System.out.println("================源数组打印================");
        for (Demo demo : sourceCollection) {
            System.out.println("元素==" + demo.getNum()); // 源数组不被改变
        }
        System.out.println("================目标数组打印================");
        for (Demo demo : destCollection) {
            System.out.println("目标元素==" + demo.getNum()); // 目标数组被改变
        }
        // 深度复制 同时在栈中和堆中的数据进行拷贝，这样，其拷贝的集合和被拷贝的集合已经没有任何关系
    }

    public static void copydataNoChange(List<Demo> pa) {
        List<Demo> list = new ArrayList<Demo>();
        try {
            for (Demo demo1 : pa) {
                list.add(demo1.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        list.get(0).setNum(4);
    }

    public static void copydataChange(List<Demo1> pa) {
        List<Demo1> list = new ArrayList<Demo1>();
        // 第一种方法进行copy 浅复制
        for (Demo1 demo1 : pa) {
            list.add(demo1);
        }
        // 第二种方法进行copy 浅复制
        // list.addAll(pa);

        list.get(0).setNum(4);
    }

    /**
     * 测试当数据被当做参数进行传递后进行操作 是否会改变元数据的值 如果元数据有改变，怎么解决这个问题
     */
    public static void copydata() {
        List<Demo> sourceCollection = new ArrayList<Demo>();
        sourceCollection.add(new Demo(0));
        List<Demo1> sourceCollection1 = new ArrayList<Demo1>();
        sourceCollection1.add(new Demo1(0));
        int[] data = { 1, 2, 3, 4, 5 };
        // 引用传递 导致数据源改变
        copyIntNoChange(data);
        copydataNoChange(sourceCollection);
        copydataChange(sourceCollection1);
        System.out.println("=========类实现clone方法 并在方法中使用深度复制==========" + sourceCollection.get(0).getNum());
        System.out.println("==========类无实现clone方法 浅复制导致元数据发生变化=========" + sourceCollection1.get(0).getNum());
        System.out.println("==========基础数据类型自动实现深度复制   元数据不会发生变化=========" + Arrays.toString(data));
    }

    /**
     * 基础数据类型 自动实现深度复制 不会产生变化
     */
    private static void copyIntNoChange(int[] data) {
        int[] a = new int[5];
        System.arraycopy(data, 0, a, 0, 5);
        a[0] = 9;
        System.out.println("从data copy出来的数据" + Arrays.toString(a));
    }

    /**
     * 
     * 通过序列化实现深度复制
     *  序列化会将对象读入流中，此时jvm中的对象仍然不会改变，流中的对象是一份独立的copy 通过反序列化后写出来   克隆后的对象相互独立 互不影响
     */
    public static void deepCopyBySerializable() {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        

    }

}

/*
 * 实现cloneable接口 实现深度复制
 */
class Demo implements Cloneable {
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    protected Demo clone() throws CloneNotSupportedException {
        return (Demo) super.clone();
    }

    public Demo(int num) {
        super();
        this.num = num;
    }
}

class Demo1   {
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Demo1(int num) {
        super();
        this.num = num;
    }
}

class ser implements Serializable {
    /**
     */
    private static final long serialVersionUID = 1L;
    
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ser(int num) {
        super();
        this.num = num;
    }
}
