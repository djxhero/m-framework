package com.lancslot.morn.config;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SdfCryptoUtil {

    private static final String DEFAULT_SECRET_KEY = "mornframeworksecretkey";
    private static final String SECRETALGORITHM = "PBEWithMD5AndDES";
    private static final byte[] SALT = new byte[]{-34, 51, 16, 18, -34, 51, 16, 18};
    public static final String DEFAULT_SECRET_KEY_PROPERTY_FILE_NAME = "secretKey";
    public static final String DEFAULT_SECRET_KEY_NAME = "sdf.secret.key";

    public SdfCryptoUtil() {
    }

    public static void main(String[] args) throws Exception {
        print2Client();

        while(true) {
            String inputString;
            do {
                System.out.println("请输入：");
                Scanner sc = new Scanner(System.in);
                inputString = sc.nextLine();
            } while(inputString == null);

            if (inputString.length() > 0) {
                if ("exit".equalsIgnoreCase(inputString)) {
                    System.exit(0);
                } else {
                    String[] inputArray = inputString.split(",");
                    if (inputArray.length < 2) {
                        System.out.println("Invalid Paramter!");
                        print2Client();
                    } else {
                        String encryptedString;
                        if ("e".equalsIgnoreCase(inputArray[0])) {
                            System.out.println("Original String: " + inputArray[1]);
                            encryptedString = "";
                            if (inputArray.length == 3) {
                                if (inputArray[2] != null && inputArray[2].length() > 0) {
                                    encryptedString = encrypt(inputArray[1], inputArray[2]);
                                }
                            } else {
                                encryptedString = encrypt(inputArray[1]);
                            }

                            System.out.println("Encrypted String: " + encryptedString);
                        } else if ("d".equalsIgnoreCase(inputArray[0])) {
                            encryptedString = inputArray[1];
                            System.out.println("Encrypted String: " + encryptedString);
                            String decryptedString = "";
                            if (inputArray.length == 3) {
                                if (inputArray[2] != null && inputArray[2].length() > 0) {
                                    decryptedString = decrypt(encryptedString, inputArray[2]);
                                }
                            } else {
                                decryptedString = decrypt(encryptedString);
                            }

                            System.out.println("Decrypted String: " + decryptedString);
                        } else {
                            System.out.println("Invalid Paramter!");
                            print2Client();
                        }
                    }
                }
            }
        }
    }

    private static void print2Client() {
        System.out.println("===============================================");
        System.out.println("加密例子：e,original string[,secret key]");
        System.out.println("解密例子：d,encryted string[,secret key]");
        System.out.println("退出：exit");
        System.out.println("===============================================");
    }

    private static Cipher getCipher(int mode, String secretKey) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRETALGORITHM);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(secretKey.toCharArray()));
        Cipher pbeCipher = Cipher.getInstance(SECRETALGORITHM);
        pbeCipher.init(mode, key, new PBEParameterSpec(SALT, 20));
        return pbeCipher;
    }

    public static String retrieveSecretKey(String propertyFileName, String keyName) {
        String secretKey = null;

        try {
            ResourceBundle bundle = PropertyResourceBundle.getBundle(propertyFileName);
            secretKey = bundle.getString(keyName);
            return secretKey;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String property) throws Exception {
        Cipher pbeCipher = getCipher(2, DEFAULT_SECRET_KEY);
        return new String(pbeCipher.doFinal(base64Decode(property)));
    }

    public static String decrypt(String property, String secretKey) throws Exception {
        Cipher pbeCipher = getCipher(2, secretKey);
        return new String(pbeCipher.doFinal(base64Decode(property)));
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.decodeBase64(property);
    }

    public static String encrypt(String property) throws Exception {
        Cipher pbeCipher = getCipher(1, DEFAULT_SECRET_KEY);
        return new String(base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8"))));
    }

    public static String encrypt(String property, String secretKey) throws Exception {
        Cipher pbeCipher = getCipher(1, secretKey);
        return new String(base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8"))));
    }

    private static byte[] base64Encode(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }
}
