package lsc.jdk.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastSu {
    /**
     * 如何避免Fast-fail机制
     * 
     * 任何对array在结构上有所改变的操作（add、remove、clear等），CopyOnWriterArrayList都会copy现有的数据，
     * 再在copy的数据上修改，这样就不会影响COWIterator中的数据了，修改完成之后改变原有数据的引用即可。
     * 同时这样造成的代价就是产生大量的对象，同时数组的copy也是相当有损耗的。
     */

    private static CopyOnWriteArrayList<Integer> threadList = new CopyOnWriteArrayList<Integer>();

    private static List<Integer> list = new ArrayList<Integer>();

    private static class threadOne extends Thread {
        @Override
        public void run() {
            Iterator<Integer> iterator = threadList.iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                System.out.println("ThreadOne  sysout" + i);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class threadTwo extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 6) {
                System.out.println("ThreadTwo  sysout" + i);
                if (i == 3) {
                    threadList.remove(i);
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }
        new threadOne().start();
        new threadTwo().start();
    }

}
