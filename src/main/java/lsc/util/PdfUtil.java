package lsc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉PDF转换为word
 *
 * @author shichang.liu
 * @date 2017年3月13日下午4:32:28
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PdfUtil {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RtfConverter.class);
    
    @SuppressWarnings("unchecked")
    private static void readPdf(InputStream in){
        try {
            PdfReader reader=new PdfReader(in);
            List<Object> list= SimpleBookmark.getBookmark(reader);
            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> bookmark=(Map<String, Object>) list.get(i);
                System.out.println(bookmark.get("Title"));
                String pageNum=String.valueOf(bookmark.get("Page")!=null?bookmark.get("Page"):"");
                System.out.println(pageNum.replaceAll("FitH", "页码"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream input = new FileInputStream(new File("E:/JavaBook/Java数据结构和算法.pdf"));
        readPdf(input);
        logger.info("");
    }
    

}
