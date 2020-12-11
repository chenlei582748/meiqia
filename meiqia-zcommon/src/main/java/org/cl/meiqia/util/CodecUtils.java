package org.cl.meiqia.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class CodecUtils {

    public static String md5Hex(String data,String salt) {
        if (StringUtils.isEmpty(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data, String salt) {
        if (StringUtils.isEmpty(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data));
    }

    public static void main(String[] args) {
        System.out.println(CodecUtils.md5Hex("123","laobai"));
    }
}
