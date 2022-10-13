package com.svj.common;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // SecurityContextHolder
        // get the principal
        // get the user
        return Optional.of("Saroj");
    }
}
