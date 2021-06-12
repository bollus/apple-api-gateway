package com.easysign.gateway.common.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.common.utils
 * @ClassName: Sha1withRSAUtil
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 4:49
 */
@Slf4j
@Component
@SuppressWarnings("unused")
public class Sha1withRSAUtil {
    private static String publicKeyFileName;
    private static String privateKeyFileName;
    private static final String pfxPassword = "pass123";//私钥文件获取时设置的密钥
    private static final String aliasName = "003";//alias名称

//    @Value("${rsa.dir}")
    public void setDir(String dir){
        publicKeyFileName = dir + File.separator + "pubkey.cer";
        privateKeyFileName = dir + File.separator + "private.pfx";
    }

    /**
     * 签名
     *
     * @return 签名后经过base64处理的字符串
     */
    public static String sign(String str) {
        String base64Sign = "";
        InputStream fis = null;
        try {
            fis = new FileInputStream(privateKeyFileName);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] pscs = pfxPassword.toCharArray();
            keyStore.load(fis, pscs);
            PrivateKey priKey = (PrivateKey) (keyStore.getKey(aliasName, pscs));
            // 签名
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(priKey);
            byte[] bysData = str.getBytes(StandardCharsets.UTF_8);
            sign.update(bysData);
            byte[] signByte = sign.sign();
            BASE64Encoder encoder = new BASE64Encoder();
            base64Sign = encoder.encode(signByte);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64Sign;
    }

    /**
     * 数据验证
     *
     * @param signStr 加密后的数据
     * @param verStr  原始字符
     */
    public static boolean verify(String signStr, String verStr) {
        boolean verfy = false;
        InputStream fis = null;
        try {
            fis = new FileInputStream(publicKeyFileName);
            CertificateFactory cf = CertificateFactory.getInstance("x509");
            Certificate cerCert = cf.generateCertificate(fis);
            PublicKey pubKey = cerCert.getPublicKey();
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] signed = decoder.decodeBuffer(signStr);
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initVerify(pubKey);
            sign.update(verStr.getBytes(StandardCharsets.UTF_8));
            verfy = sign.verify(signed);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return verfy;
    }


    /**
     * 通过公钥文件进行加密数据
     *
     * @return 加密后经过base64处理的字符串
     */
    public static String encrypt(String source) throws Exception {
        InputStream fis = null;
        try {
            fis = new FileInputStream(publicKeyFileName);
            CertificateFactory cf = CertificateFactory.getInstance("x509");
            Certificate cerCert = cf.generateCertificate(fis);
            PublicKey pubKey = cerCert.getPublicKey();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] sbt = source.getBytes();
            byte[] epByte = cipher.doFinal(sbt);
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(epByte);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过私钥文件进行解密数据
     *
     * @return 解密后的明文字符串
     */
    public static String decode(String source) throws Exception {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(source);
        InputStream fis = null;
        try {
            fis = new FileInputStream(privateKeyFileName);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] pscs = pfxPassword.toCharArray();
            keyStore.load(fis, pscs);
            PrivateKey priKey = (PrivateKey) (keyStore.getKey(aliasName, pscs));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] epByte = cipher.doFinal(keyByte);
            return new String(epByte, StandardCharsets.UTF_8);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
