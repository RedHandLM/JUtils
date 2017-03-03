package lsc.jdk.collection.list;

import java.util.Arrays;

import lsc.util.StringTools;

class LessionSystemArrayCopy{
        
    public static void main(String[] args) {
        int[] ids = { 1, 2, 3, 4, 5, 6 };
        int[] ids2 = new int[6];
        System.out.println(Arrays.toString(ids));//
        //
        System.arraycopy(ids, 0, ids2, 0, 6);
        System.out.println(Arrays.toString(ids2));//

        int[] fun = { 0, 1, 2, 3, 4, 5, 6 };
        System.arraycopy(fun, 0, fun, 3, 3);
        System.out.println(Arrays.toString(fun));

        int[] funa = null;
        String res = ArrayToString(funa);
        if (StringTools.isBlank(res)) {
            System.out.println("============***********============" + res);
        }
        System.out.println("++++" + res);
    }
        
        
        
        
        
        public static String ArrayToString(int [] a){
            if(a==null){
                return null;
            }
            int max=a.length-1;
            if(max==-1){
                return "[]";
            }
            StringBuilder b=new StringBuilder();
            b.append("[");
            for (int i = 0; ; i++) {
                b.append(a[i]);
                if(i==max){
                    return b.append("]").toString();
                }
                b.append(",");
            }
        }
        
        
        
        
        
        
        
        
        
        public static String toString(int[] a) {
            if (a == null)
                return "null";
            int iMax = a.length - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(a[i]);
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        }
        
        
        
        
        
        
        
    }