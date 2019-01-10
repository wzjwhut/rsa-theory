package com.wzjwhut.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/** 一个简单的用于演示RSA过程 */
public class MyRSA {
    private final static Logger logger = LogManager.getLogger(MyRSA.class);
    private BigInteger d, e, n;

    private final int NBytes = 2048/8;

    public MyRSA(){
        BigInteger p, q;
        p = BigInteger.probablePrime(1024, new SecureRandom());
        q = p.nextProbablePrime();
        n = p.multiply(q);
        logger.info("p, bits: {}\r\n{}", p.bitLength(), p);
        logger.info("q, bits: {}\r\n{}", q.bitLength(), q);
        logger.info("n, bits: {}, {}\r\n{}", n.bitLength(),n.bitCount(), n);

        byte[] bytes = n.toByteArray();
        logger.info("n length,\r\n {}", bytes.length);
        logger.info("n digits count, {}", n.toString().length());
        logger.info("n bytes,\r\n {}", bytes);

        BigInteger p_1 = p.subtract(BigInteger.valueOf(1));
        BigInteger q_1 = q.subtract(BigInteger.valueOf(1));
        BigInteger q_1_multi_q_1 = p_1.multiply(q_1);

        d = q.max(p).nextProbablePrime();
        e = d.modInverse(q_1_multi_q_1);

        logger.info("d, bits: {}\r\n{}", d.bitLength(), d);
        logger.info("e, bits: {}\r\n{}", e.bitLength(), e);
    }

    public byte[] encrypt(byte[] raw){
        if(raw.length>=NBytes){
            throw new RuntimeException("not support long data");
        }
        return new BigInteger(raw).modPow(e, n).toByteArray();
    }

    public byte[] decrypt(byte[] encrypted){
           return new BigInteger(encrypted).modPow(d, n).toByteArray();
    }
}
