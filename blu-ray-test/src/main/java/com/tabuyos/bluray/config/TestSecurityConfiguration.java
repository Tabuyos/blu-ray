/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.config;

import com.tabuyos.bluray.model.TestResourceDefine;
import com.tabuyos.bluray.model.TestUserDetailsServiceImpl;
import com.tabuyos.bluray.security.BluRayFilterSecurityInterceptor;
import com.tabuyos.bluray.security.BluRayResourceDefine;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.config
 *   <b>class: </b>TestSecurityConfiguration
 *   comment here.
 * </pre>
 *
 * @author
 *     <pre><b>username: </b><a href="http://www.tabuyos.com">Tabuyos</a></pre>
 *     <pre><b>site: </b><a href="http://www.tabuyos.com">http://www.tabuyos.com</a></pre>
 *     <pre><b>email: </b>tabuyos@outlook.com</pre>
 *     <pre><b>description: </b>
 *       <pre>
 *         Talk is cheap, show me the code.
 *       </pre>
 *     </pre>
 *
 * @version 0.1.0
 * @since 0.1.0 - 2021/3/14 18:33
 */
@EnableWebSecurity
public class TestSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AccessDeniedHandler accessDeniedHandler;
  private final AuthenticationEntryPoint authenticationEntryPoint;
  private final BluRayFilterSecurityInterceptor filterSecurityInterceptor;
  private final TestUserDetailsServiceImpl testUserDetailsService;
  private final TestResourceDefine testResourceDefine;

  public TestSecurityConfiguration(
      AccessDeniedHandler accessDeniedHandler,
      AuthenticationEntryPoint authenticationEntryPoint,
      BluRayFilterSecurityInterceptor filterSecurityInterceptor, TestUserDetailsServiceImpl testUserDetailsService,
      TestResourceDefine testResourceDefine
  ) {
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.filterSecurityInterceptor = filterSecurityInterceptor;
    this.testUserDetailsService = testUserDetailsService;
    this.testResourceDefine = testResourceDefine;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers()
        .frameOptions()
        .disable()
        .and()
        // 放过以下请求
        .authorizeRequests()
        .antMatchers("/login", "/static/**", "/valid/image/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        // 表单登录
//        .httpBasic()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/index")
        .loginProcessingUrl("/auth")
        .permitAll()
        .and()
        .csrf()
        .disable()
//        // 自定义权限拒绝处理类
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(authenticationEntryPoint)
        .and()
//        // 增加自定义权限授权拦截器
        .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
    ;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  /**
   * 获取登录用户信息
   *
   * @return function
   */
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return testUserDetailsService::loadUserByUsername;
  }

  @Bean
  public BluRayResourceDefine bluRayResourceDefine() {
    return testResourceDefine::loadResourceDefine;
  }
}
