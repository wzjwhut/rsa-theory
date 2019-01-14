package com.wzjwhut.example;

import com.wzjwhut.util.HexUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.util.Base64;

public class SystemRSA {
    private final static Logger logger = LogManager.getLogger(SystemRSA.class);
    private KeyPair keyPair;

    public SystemRSA() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        logger.info("format: {}", publicKey.getFormat());
        logger.info("private key: \r\n{}", HexUtils.dumpString(privateKey.getEncoded(), 16));
        logger.info("public key: \r\n{}", HexUtils.dumpString(publicKey.getEncoded(), 16));
    }

    public byte[] encrypt(byte[] raw) throws Exception {
        Cipher encoder = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encoder.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        encoder.update(raw);
        return encoder.doFinal();
    }

    public byte[] decrypt(byte[] raw) throws Exception {
        Cipher decoder = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decoder.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        decoder.update(raw);
        return decoder.doFinal();
    }

}
