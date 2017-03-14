package algorithm.chapter1;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉数据结构与算法 第一章
 *
 * @author shichang.liu
 * @date 2017年3月13日下午4:27:47
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Summarize {
    private static void foundKey() {
        int j;
        int array[] = new int[10];
        array[0] = 77;
        array[1] = 99;
        array[2] = 44;
        array[3] = 55;
        array[4] = 22;
        array[5] = 88;
        array[6] = 11;
        array[7] = 00;
        array[8] = 33;
        array[9] = 66;
        int nElems = 10;
        for (j = 0; j < nElems; j++) {
            System.out.println("array[" + j + "]==" + array[j]);
        }
        // -------------------------------------查找key66
        int searchKey = 66;
        for (j = 0; j < nElems; j++) {
            if (array[j] == searchKey) {
                break;
            }
        }
        if (j == nElems) {
            System.out.println("没有找到");
        } else {
            System.out.println("key是" + array[j]);
        }
        // -------------------------------------查找key55
        searchKey = 55;
        for (j = 0; j < nElems; j++) {
            if (array[j] == searchKey) {
                break;
            }
        }
        for (int k = j; k < nElems; k++) {
            array[k] = array[k + 1];
        }
        nElems--;
        // -------------------------------------查找key55
        for (j = 0; j < nElems; j++) {
            System.out.println(array[j] + " ");
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉测试方法
     *
     * 2017年3月13日下午6:00:26 Hunteron
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        foundKey();
    }

}
