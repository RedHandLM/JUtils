package lsc.util;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉java 的根搜索算法
 *
 * @author shichang.liu
 * @date 2017年3月17日下午2:27:21
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Roots {
    /**
     * 强引用并不能保证对象不被回收。垃圾回收机制除了检查对象是否被引用外，还要看对象是否被至少一个GC roots对象直接或者间接引用。
     * GC roots对象包括以下一些类容：
        1 每个线程当前的函数调用栈，从栈顶到栈底的每个函数里的局部变量。
        2 静态的变量
        3 被JNI(java interface native)中引用到的变量。
                    所以，上面例子中两个循环引用的对象，虽然都存在一个强引用，但是不被任何GC root对象直接或者间接引用到，垃圾回收机制能够发现这个问题。
        
                    这个循环引用是否被回收，就看这个循环引用是否挂在根上，
        A引用B，B引用A，A和Ｂ并没有挂在某个内存元和根上，当他们的生命周期结束的时候，这两个对象都有可能被回收。
                    
     */

    public void buidDog() {
        Dog dog = new Dog();
        Tail tail = new Tail();
        // 循环引用 如果要回收dog 但是tail拿着dog的引用 如果要回收tail 但是dog拿着对tail的引用
        // 事实上两个对象都可以回收
        dog.tail = tail;
        tail.dog = dog;
    }

    class Dog {
        public Tail tail;

    }

    class Tail {
        public Dog dog;

    }

}
