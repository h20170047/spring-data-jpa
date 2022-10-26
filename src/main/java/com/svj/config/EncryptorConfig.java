package com.svj.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptorConfig {

    @Bean(name="jasyptStringEncryptor")
    public StringEncryptor getSensitiveInfoEncryptor(){
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
        return encryptor;
    }
}
