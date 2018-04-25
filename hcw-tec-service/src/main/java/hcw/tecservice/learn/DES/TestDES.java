package hcw.tecservice.learn.DES;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/22 15:10
 * Description: 文件加密
 * Others:
 */
public class TestDES {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 使用AES对文件进行加密和解密
     *
     */
    private static String type = "AES";

    Key key;

    public TestDES(String str) {
        getKey(str);
    }

    /**
     * 根据参数生成KEY
     *
     * @param str
     */
    public void getKey(String str) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
            keyGenerator.init(128,new SecureRandom(str.getBytes()));
            this.key = keyGenerator.generateKey();
            logger.info("key : [{}]", key.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }


    /**
     * 对文件file进行加密并保存目标文件destFile中
     *
     * @param file
     * @param destFile
     * @throws Exception
     */
    public void encrypt(String file, String destFile) throws Exception {
        Cipher cipher = Cipher.getInstance(type + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(destFile);

            crypt(fis, fos, cipher);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 文件采用AES算法解密文件
     *
     * @param file 已加密的文件
     * @param dest 解密后存放的文件
     * @throws Exception
     */
    public void decrypt(String file, String dest) throws Exception {
        Cipher cipher = Cipher.getInstance(type + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(dest);

            crypt(fis, fos, cipher);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 加密解密流
     * @param in        加密解密前的流
     * @param out       加密解密后的流
     * @param cipher    加密解密
     * @throws IOException
     * @throws GeneralSecurityException
     */
    private static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException {
        int blockSize = cipher.getBlockSize() * 1000;
        int outputSize = cipher.getOutputSize(blockSize);

        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        int inLength = 0;
        boolean more = true;
        while (more) {
            inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLength);
            } else {
                more = false;
            }
        }
        if (inLength > 0)
            outBytes = cipher.doFinal(inBytes, 0, inLength);
        else
            outBytes = cipher.doFinal();
        out.write(outBytes);
    }


    public static void main(String[] args) throws Exception {
        TestDES td = new TestDES("qwer246ysa5dstewt2stertrtij9tyujgh5j4ty6jty3o54u3tp2413yiu2ot3o4uietyqwh");
        td.encrypt("d:\\testConfig.xml", "d:\\testConfig加密.xml"); //加密
        td.decrypt("d:\\testConfig加密.xml", "d:\\testConfig解密.xml"); //解密
    }
}
