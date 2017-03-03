package lsc.jdk.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.util.CollectionUtils;

public class ArrayListSu {


    public void lessionListSrc() {
        List<String> list = new ArrayList<String>();
        // =====================================list.add方法=====================================
        list.add("a");
        list.add("b");
        // =====================================list.isEmpty方法=====================================
        list.isEmpty(); // list.size()==0; 防止空指针 使用list!=null||list.size()!=0;

        // =====================================list.contains方法=====================================
        list.contains("a"); // 内部indexof() 同样使用for循环判断是否存在 效率不高

        // =====================================list.iterator方法=====================================
        Iterator<String> b = list.iterator(); // 返回一个迭代器对象 b.hasNext()判断是否还有元素 b.next() 返回下一个元素
        while (b.hasNext()) {
            System.out.println(b.next());
        }
        ListIterator<String> l = list.listIterator();
        /*
         * 一．相同点 都是迭代器，当需要对集合中元素进行遍历不需要干涉其遍历过程时，这两种迭代器都可以使用。 二．不同点
         * 1.使用范围不同，Iterator可以应用于所有的集合，Set、List和Map和这些集合的子类型。而ListIterator只能用于List及其子类型。
         * 2.ListIterator有add方法，可以向List中添加对象，而Iterator不能。
         * 3.ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历，但是ListIterator有hasPrevious
         * ()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator不可以。
         * 4.ListIterator可以定位当前索引的位置，nextIndex()和previousIndex()可以实现。Iterator没有此功能。
         * 5.都可实现删除操作，但是ListIterator可以实现对象的修改，set()方法可以实现。Iterator仅能遍历，不能修改。
         */

        // =====================================list.toArray方法=====================================
        String[] arr = new String[list.size()];
        // arr = (String[]) list.toArray(); // 直接这样转换会出错 需要一个一个拿出来之后转换
        System.out.println("list to array方法" + list.toArray());
        System.out.println("list to array方法" + list.toArray(arr)[0]); // 使用这一个重载方法就无需一个一个取出重新转换

        // =====================================checkForComodification();=====================================
        // 检测结构是否发生变化 使用在多线程环境 如果一个线程正在进行遍历 另外一个线程进行了修改就会引发ConcurrentModificationException异常

        // =====================================list.remove()=====================================
        list.remove("a");
        list.remove("a");
        List<String> ar = new ArrayList<String>(1);
        ar.add("c");
        list.removeAll(ar); // 移除
        // list.retainAll(paramCollection);

        // =====================================System.arraycopy()=====================================

        /*
         * remove源码分析 这段代码牛B的地方有两处， 一：直接将要移动的元素后面的元素直接用数组拷贝前移（保持元素的连续性）， 二：
         * 最后一行代码，这段代码的作用是将元素引用置为null，这样做的目的是避免内存泄漏（否则实际上数组中依然有该引用， gc无法进行垃圾回收，类似Java中堆栈数据结构的实现方式） public boolean
         * remove(Object paramObject) { int i; if (paramObject == null) for (i = 0; i < this.size; ++i) { if
         * (this.elementData[i] != null) continue; fastRemove(i); return true; } else for (i = 0; i < this.size; ++i) {
         * if (!(paramObject.equals(this.elementData[i]))) continue; fastRemove(i); return true; } return false; }
         * private void fastRemove(int paramInt) { this.modCount += 1; int i = this.size - paramInt - 1; if (i > 0)
         * System.arraycopy(this.elementData, paramInt + 1, this.elementData, paramInt, i);
         * this.elementData[(--this.size)] = null; }
         */

        fsatRemove(2);// 移除下标为2的元素
    }

    class LessionSystemArrayCopy {
        public void SysArrayCopy() {
            int[] ids = { 1, 2, 3, 4, 5, 6 };
            int[] ids2 = new int[6];
            System.out.println(Arrays.toString(ids));//
            //
            // 内存块赋值的逻辑了，这样避免很多引用来回倒腾的时间，必然就变快了。

            System.arraycopy(ids, 0, ids2, 0, 6);
            System.out.println(Arrays.toString(ids2));//
        }
    }

    public static void fsatRemove(int paramInt) {
        Object[] o = new Object[0];
        List<String> l = new ArrayList<String>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        l.add("5");
        l.add("6");
        int size = l.size();
        int i = size - paramInt - 1;
        if (i > 0) {
            // 源数组 ,源数组要复制的起始位置, 目标数组 , 目标数组的起始位置, 复制长度
            // 有趣的是这个函数可以实现自己到自己复制，
            System.arraycopy(o, paramInt + 1, o, paramInt, i); // java Native interface JNI方式实现类
        }
        o[(size--)] = null;
        for (String s : l) {
            System.out.println("剩余元素" + s);
        }
    }

    // =====================================containsAll(Collection<?>
    // paramCollection)=====================================
    public static void containsAllSu() {
        List<String> srcList = new ArrayList<String>(2);
        List<String> desList = new ArrayList<String>(5);
        srcList.add("a");
        srcList.add("b");
        desList.add("a");
        desList.add("b");
        desList.add("c");
        desList.add("d");
        desList.add("e");
        System.out.println(desList.containsAll(srcList));
        ;
    }

    /**
     * containsAll底层使用for 循 环中 indexof方法 来 判断是否相等 下面是indexof方法
     */
    @SuppressWarnings("unused")
    private static int indexof(Object[] op, Object o) {
        if (op == null || op.length == 0) {
            return -1;
        }
        if (o == null) {
            for (int i = 0; i < op.length; i++) {
                if (op[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < op.length; i++) {
                if (o.equals(op[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    
    /**
     * 
     * sublist方法返回的是一个原list的子列表 arraylist的一个名为sublist的内部类，它 继承AbstractList<E> implements RandomAccess，
     * 同样提供set add remove等方法 是一个源列表的视图，它的所有操作都会作用到源列表， 但是在视图形成之后，
     * 就不可以在修改原list的结构 比如改变modcount的操作 比如增加删除等操作，这样会引起ConcurrentModificationException，
     * 因为sublist的会在每次调用方法时检查list的modconunt 如果视图modcount和原list的modcount不同则会抛出异常
     * 
     */
    
    public static void subList() {
        List<Integer> src = new ArrayList<Integer>(10);
        for (int i = 0; i < 10; i++) {
            src.add(i);
        }
        int i = 0;
        int x = src.size();
        List<Integer> des = src.subList(i, x);
        src.add(0);
        System.out.println(src.size());
        System.out.println(des.size());

    }
    
    /*
     * retainAll  求交集
     */
    public static void retainAllSu() {
        List<Integer> list = new ArrayList<Integer>(4);
        List<Integer> list2 = new ArrayList<Integer>(4);
        for (int i = 0; i < 10; i++) {
            @SuppressWarnings("unused")
            boolean unUse = (i >= 4) ? list.add(i) : list2.add(i);
        }
        list.add(1);
        batchRemove(list,list2, true);
        System.out.println(list);
    }
    
    
    private static boolean batchRemove(Collection<?> c,Collection<?> d, boolean complement) {
        int modCount=0;
        if(CollectionUtils.isEmpty(c)){
            return false;
        }
        int size=c.size();
        final Object[] elementData = new Object[0];
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            if (r != size) {
                System.arraycopy(elementData, r,
                                 elementData, w,
                                 size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }
    
    
    
    
    
    
    
    
    
   
    public static void main(String[] args) {
        // String a="dfsaf";
        // System.out.println(a.subSequence(0, 2));
        // System.out.println(a.indexOf("s"));
//        containsAllSu();
//        subList();
        retainAllSu();

    }
    
    
    public static void smallMethod(){
        List<Integer> list = new ArrayList<Integer>(4);
        list.add(1);
        list.add(2);
        list.set(0, 90);
        System.out.println(list.hashCode());;
    }
    
    
    
    

    // public abstract void clear();
    //
    // public abstract boolean equals(Object paramObject);
    //
    // public abstract int hashCode();
    //
    // public abstract E get(int paramInt);
    //
    // public abstract E set(int paramInt, E paramE);
    //
    // public abstract void add(int paramInt, E paramE);
    //
    // public abstract E remove(int paramInt);
    //
    // public abstract int indexOf(Object paramObject);
    //
    // public abstract int lastIndexOf(Object paramObject);
    //
    // public abstract ListIterator<E> listIterator();
    //
    // public abstract ListIterator<E> listIterator(int paramInt);
    //
    // public abstract List<E> subList(int paramInt1, int paramInt2);

}
