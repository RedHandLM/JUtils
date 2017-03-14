package lsc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.EditorKit;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉rtf文件转换为html和text
 *
 * @author shichang.liu
 * @date 2017年3月13日下午4:31:44
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RtfConverter {
    
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RtfConverter.class);


    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉读取RTF文件
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String readRTF2Text(InputStream inputStream) {
        String text = null;
        RTFEditorKit rtf = new RTFEditorKit();
        DefaultStyledDocument dsd = new DefaultStyledDocument();
        try {
            rtf.read(inputStream, dsd, 0);
            text = new String(dsd.getText(0, dsd.getLength()));
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉读取RTF文件
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String readRTF2HTML(InputStream inputStream) {
        JEditorPane p = new JEditorPane();
        p.setContentType("text/rtf");
        EditorKit kitRtf = p.getEditorKitForContentType("text/rtf");
        try {
            kitRtf.read(inputStream, p.getDocument(), 0);
            kitRtf = null;
            EditorKit kitHtml = p.getEditorKitForContentType("text/html");
            Writer writer = new StringWriter();
            kitHtml.write(writer, p.getDocument(), 0, p.getDocument().getLength());
            return writer.toString();
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换成text文本
     */
    public static String convert2Text(InputStream inputStream) {
        // return WebFormatter.html2text(convert2Html(inputStream));
        return readRTF2Text(inputStream);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream input = new FileInputStream(new File("C:/Users/Hunteron/Downloads/白永奎_156549_20170308.doc"));
        // readRTF2Text(input);
        // logger.info(convert2Text(input));
        logger.info(readRTF2HTML(input));
    }
}
