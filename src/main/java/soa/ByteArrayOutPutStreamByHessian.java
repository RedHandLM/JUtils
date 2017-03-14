package soa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import lsc.bean.resume.Talent;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉Hessian RPC架构中的序列化
 *
 * @author shichang.liu
 * @date 2017年3月14日上午10:46:01
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ByteArrayOutPutStreamByHessian {

    private static final Logger logger = LoggerFactory.getLogger(ByteArrayOutPutStreamByHessian.class);

    public static void main(String[] args) {
        serializableForJava();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉java内置对象序列化
     *
     * 2017年3月14日上午10:50:01
     * 
     * @author shichang.liu
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void serializableForJava() {
        Talent talent = new Talent();
        talent.setName("金");
        // 字节数组输出流
        ByteArrayOutputStream os = null;
        // 对象输出流
        HessianInput hi = null;
        HessianOutput ho = null;
        try {
            os = new ByteArrayOutputStream();
            ho = new HessianOutput(os);
            ho.writeObject(talent);
            // 字符数组输入流
            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            // hessian反序列化 从流中读取对象
            hi = new HessianInput(is);
            Talent t = (Talent) hi.readObject();
            ;
            if (t == null) {
                return;
            }
            logger.info("反序列化之后对象内容============>>{}", t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (hi != null) {
                hi.close();
            }
            if (ho != null) {
                try {
                    ho.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
