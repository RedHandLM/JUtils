package lsc.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class test extends Thread implements Runnable {

    static Pattern regexLetter = Pattern.compile("[\\S|\\s|\n]*?([a-zA-Z|\\+|\\#|\\d|\\s]+)[\\S|\\s|\n]*\\S*");

    public static String getMainWordFromTitle(String line) {
        return getRegexString(line, regexLetter);
    }

    public static String getRegexString(String line, Pattern p) {
        if (StringUtils.isEmpty(line)) {
            return "";
        }
        try {
            Matcher ma = p.matcher(line.replaceAll("\\s+", " "));
            while (ma.matches()) {
                return ma.group(1);
            }
            return ma.group(1);
        } catch (Exception e) {
            return "";
        }
    }

    
    
    
    
    
    
    
    /**
     * 
     * @param list
     * @param field
     * @param operator
     * @return ${field}:(${list.get(0)} ${operator} ${list.get(1)})
     */
    public static <T> String buildQuery(List<T> list, String field, String operator) {
        StringBuilder query = new StringBuilder("");
        String op = " " + operator + " ";
        if (list != null && list.size() > 0) {
            query.append("(");
            boolean isFirst = true;
            for (T t : list) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    query.append(op);
                }
                query.append(field).append(":").append(String.valueOf(t));
            }
            query.append(")");
        }
        return query.toString();
    }

    public static void main(String[] args) {
        // System.out.println(getMainWordFromTitle("java-软件工程师   j"));
        // List<String> jd = new ArrayList<String>();
        // jd.add("10201");
        // jd.add("10202");
        // jd.add("10203");
        // System.out.println(buildString(jd, "industry_ids", "OR"));
        // (industry_ids:10201 OR industry_ids:10202 OR industry_ids:10203)
        
        String line="javazho "
                + "gn";
        System.out.println(getMainWordFromTitle(line));
        
        
    }
    

    public static <T> String buildString(List<T> list, String cloum, String operator) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            sb.append("(");
            String op = " " + operator + " ";
            boolean isFirst = true;
            for (T t : list) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(op);
                }
                sb.append(cloum).append(":").append(t);
            }
            sb.append(")");
        }
        return sb.toString();
    }

}
