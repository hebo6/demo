package com.example.demo.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/security/**").authenticated()
                .and().formLogin()
        ;
        configForH2console(http);
        configForActuator(http);
        //fixme find the way to be able to accept post request while csrf is enabled.
        http.csrf().ignoringAntMatchers("!/security/**");
    }

    /**
     * @see <a href="https://blog.csdn.net/tyyytcj/article/details/103399421">csdn blog</a>
     */
    private void configForH2console(HttpSecurity http) throws Exception {
        // 授权h2控制台url
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        // 禁用 H2 控制台的 CSRF 防护, 否则无法登录h2控制台
        http.csrf().ignoringAntMatchers("/h2-console/**");
        // 允许来自同一来源的H2 控制台的请求, 否则图片打不开
        http.headers().frameOptions().sameOrigin();
    }

    private void configForActuator(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/actuator/**");
    }
}
