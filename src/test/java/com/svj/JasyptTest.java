package com.svj;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void getSensitiveInfoEncryptor(){
        PooledPBEStringEncryptor encryptor= new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config= new SimpleStringPBEConfig();
        config.setPassword("svj");  // private key
        config.setAlgorithm("PBEWithMD5AndDES"); // default is PBEWITHHMACSHA512ANDAES_256
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // default is org.jasypt.salt.RandomSaltGenerator
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        System.out.println(encryptor.encrypt("root"));
    }
}
