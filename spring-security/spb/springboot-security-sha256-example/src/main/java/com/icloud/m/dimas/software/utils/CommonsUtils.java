package com.icloud.m.dimas.software.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
@Slf4j
public class CommonsUtils {

    private String passwordHashed(String password, String salt) {
    	System.out.println("encrypt = " + new Sha256Hash(password));
        return new Sha256Hash(password, salt, 1024).toBase64();
    }

    public boolean isPasswordValid(String encryptPassword, String rawPassword, String salt) {
        String hashedPassword = passwordHashed(rawPassword, salt);
        return StringUtils.equals(hashedPassword, encryptPassword);
    }

    public String decode(String password) {
        byte[] bytes = Base64Utils.decodeFromString(password);
        return new String(bytes);
    }

    @Bean
    public ShaPasswordEncoder passwordSpringEncoder() {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        return encoder;
    }
}
