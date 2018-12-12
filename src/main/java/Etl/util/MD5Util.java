package Etl.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description 对名字进行加密的md5加密类
 * @Author dtygfn
 * @Date: 2018/12/12 14:25
 */
public class MD5Util {
    public static String getMD5(String str) {
        try {
          if (StringUtils.isNotEmpty(str)){
              // 生成一个MD5加密计算摘要
              MessageDigest md = MessageDigest.getInstance("MD5");
              // 计算md5函数
              md.update(str.getBytes());
              // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
              // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
              return new BigInteger(1, md.digest()).toString(16);
          }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
