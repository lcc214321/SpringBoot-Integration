package com.xlhj.securityjwt.config;

import com.xlhj.securityjwt.filter.JwtAuthTokenFilter;
import com.xlhj.securityjwt.handler.JwtAuthenticationFailureHandler;
import com.xlhj.securityjwt.handler.JwtAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Author: lcj
 * @Date: 2020/10/29 15:40
 * @Description: Spring Security配置类
 * @Version: 0.0.1
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthTokenFilter jwtAuthTokenFilter;
    /*@Resource
    private JwtAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private JwtAuthenticationFailureHandler authenticationFailureHandler;*/
    @Resource
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //允许匿名访问
                .antMatchers("/login").anonymous()
                .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .antMatchers("/test")
                .hasAnyAuthority("ROLE_user", "ROLE_admin")
                .antMatchers("/system/user", "/system/role")
                .hasRole("admin")
                .anyRequest().authenticated().and()
                .csrf().disable();
        http.logout().logoutUrl("/logout");
        http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 强散列哈希加密实现
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
