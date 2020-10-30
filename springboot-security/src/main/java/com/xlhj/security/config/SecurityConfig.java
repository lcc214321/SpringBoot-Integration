package com.xlhj.security.config;

import com.xlhj.security.handler.AuthenticationEntryPointHandler;
import com.xlhj.security.handler.SecurityAccessDeniedHandler;
import com.xlhj.security.handler.SecurityAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName SecurityConfig
 * @Description Spring Security配置类
 * @Author liucaijing
 * @Date 2020/10/25 16:46
 * @Version 1.0
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Resource
    private SecurityAuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private AuthenticationEntryPointHandler authenticationEntryPoint;
    @Resource
    private SecurityAccessDeniedHandler accessDeniedHandler;

    /**
     * 配置密码解析
     * @return
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置用户名和密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf防护
        http.csrf().disable()
                //表单登录
                .formLogin()
                //登录页面
                .loginPage("/login.html")
                //登录访问路径，与页面表单提交路径一致
                .loginProcessingUrl("/login")
                //登录成功后访问路径
                .defaultSuccessUrl("/index.html").permitAll()
                .failureHandler(authenticationFailureHandler)
                .and()
                //认证配置
                .authorizeRequests()
                .antMatchers("/login.html", "/login").permitAll()
                //配置静态页面可以访问
                .antMatchers("/js/**", "/css/**", "/images/**", "/favicon.ico").permitAll()
                //任何请求
                .anyRequest()
                //都需要身份验证
                .authenticated();
        //配置无权限访问页面
        //http.exceptionHandling().accessDeniedPage("/uanuth.html");
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
        //配置记住我
        http.rememberMe()
                //持久层对象
                .tokenRepository(persistentTokenRepository())
                //失效时间(秒)
                .tokenValiditySeconds(60)
                //配置自定义登录逻辑
                .userDetailsService(userDetailsService);
        //配置退出
        http.logout()
                //退出路径
                .logoutUrl("/logout")
                //退出后跳转页面
                .logoutSuccessUrl("/login.html");
    }

    /**
     * 配置记住我
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);//请求时创建表
        return jdbcTokenRepository;
    }
}
