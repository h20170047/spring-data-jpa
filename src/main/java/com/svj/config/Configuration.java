package com.svj.config;

import com.svj.common.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@org.springframework.context.annotation.Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Configuration {

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }
}
