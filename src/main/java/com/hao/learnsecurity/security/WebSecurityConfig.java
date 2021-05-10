package com.hao.learnsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author wang hao
 * @since 2021/5/10 14:12
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private UserLoginAuthHandler userLoginAuthHandler;

    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式等
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //创建一个service  继承security UserDetailsService
        return new UserLoginService();
    }

    /**
     * 可以在此处指定需要防御常见漏洞的任何端点，包括公共漏洞
     * successHandler 和 failureHandler 可以通过重写AuthenticationSuccessHandler 的方法 给spring进行托管
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //闭csrf避免POST请求时出现401异常
        http.csrf().disable();

        //构建  登入成功  登入失败  登出注销
        http.authorizeRequests()
                // 不需要任何认证就可以访问，其他的路径都必须通过身份验证
                    .antMatchers("/login")
                    // 允许其他
                    .permitAll()
                    // 任何尚未匹配的URL只需要验证用户即可访问
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .permitAll()
                    //登录成功
                    .successHandler(userLoginSuccessHandler)
                    //登录失败
                    .failureHandler(userLoginFailureHandler)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(userLogoutSuccessHandler)
                    .permitAll()
                .and()
                    .exceptionHandling()
                    //  验证用户是否登录
                    .authenticationEntryPoint(userLoginAuthHandler)
                .and()
                    //会话管理,//配置取消session管理,又Jwt来获取用户状态,否则即使token无效,也会有session信息,依旧判断用户为登录状态
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                // 开启验证
                http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
