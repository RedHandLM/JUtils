package lsc.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PatternUtil {

    /*
     * \d   数字 
     * \w   字母
     * +    匹配多个
     * \s   匹配任何不可见字符 
     * \S   匹配任何可见字符
     * \.   匹配除“\r\n”之外的任何单个字符
     * \\   匹配\
     */

    //判断字符串是否是数字或者字母
    private final static Pattern NUMBER_LETTERS = Pattern.compile("[\\d\\w]+");
    private final static Pattern IS_BLANK=Pattern.compile("[\\s]+");
    // 非汉字
    private final static Pattern LETTERS_AND_SYMBOL = Pattern.compile("[\\w+|\\d|\\.|\\+|\\#|\\@]+");
    
    private final static Pattern ANY_ONE = Pattern.compile("[^\\W]+");
    

    /**
     * 判断字符串是否是数字或者字母
     */
    public static boolean isNumberOrLetters(String s) {
        return NUMBER_LETTERS.matcher(s.replace("\\s", "")).matches();
    }
    
    
    public static boolean lettersAndSymbol(String s) {
        return LETTERS_AND_SYMBOL.matcher(s.replace("\\s", "")).matches();
    }
    
    public static boolean anyOne(String s){
        return ANY_ONE.matcher(s).matches();
    }
    
    
    /**
     * 字符数组中是否有重复
     */
    private static boolean isRepeatSpliteBySpace(String[] vals) {
        if(vals==null||vals.length==0){
            return false;
        }
        Set<String> set = new HashSet<String>(vals.length);
        for (String s : vals) {
            if (!set.contains(s)) {
                set.add(s);
            } else {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * 判断单字是否重复
     */
    public static boolean isRepeatSingleWord(String s){
        if(s==null||s.length()==0){
            return false;
        }
        int repeatConunt=0;
        Set<Character> set=new HashSet<Character>(s.length());
        for (int i = 0, len = s.length();i<len; i++) {
            char a=s.charAt(i);
            if(!set.contains(a)){
                set.add(a);
            }else{
                repeatConunt++;
            }
        }
        System.out.println((float) repeatConunt / (float) s.length());
        System.out.println(0.625 >= 0.2);
        return (float) repeatConunt / (float) s.length() >= 0.2;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
//        String [] s={"1","2","2"};
//        System.out.println(isRepeatSpliteBySpace(s));;
        String word="javajava";
        System.out.println(isRepeatSingleWord(word));
    }
    
    
}
