package com.wzjwhut.example;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.math.BigInteger;

public class Main {
    private final static Logger logger = LogManager.getLogger(Main.class);

    private final static byte[] rawMessage = "hello world".getBytes();

    public static void main(String[] args) throws Exception {
        {
            MyRSA rsa = new MyRSA();
            logger.info("[my] raw message: {}", StringUtils.join(rawMessage, ','));
            byte[] encrypted = rsa.encrypt(rawMessage);
            logger.info("[my] encrypted message: {}", StringUtils.join(encrypted, ','));
            byte[] decrypted = rsa.decrypt(encrypted);
            logger.info("[my] decrypted message: {}", StringUtils.join(decrypted, ','));
        }

        {
            SystemRSA rsa = new SystemRSA();
            logger.info("[system] raw message: {}", StringUtils.join(rawMessage, ','));
            byte[] encrypted = rsa.encrypt(rawMessage);
            logger.info("[system] encrypted message: {}", StringUtils.join(encrypted, ','));
            byte[] decrypted = rsa.decrypt(encrypted);
            logger.info("[system] decrypted message: {}", StringUtils.join(decrypted, ','));

        }

        LogManager.shutdown();
    }
}
