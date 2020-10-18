package com.xlhj.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName SecurityPasswordEncoder
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/17 21:37
 * @Version 1.0
 */
public class SecurityPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
