/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.config;

import com.tabuyos.bluray.security.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.config
 *   <b>class: </b>BluRayAutoConfiguration
 *   comment here.
 * </pre>
 *
 * @author <pre><b>username: </b><a href="http://www.tabuyos.com">Tabuyos</a></pre>
 * <pre><b>site: </b><a href="http://www.tabuyos.com">http://www.tabuyos.com</a></pre>
 * <pre><b>email: </b>tabuyos@outlook.com</pre>
 * <pre><b>description: </b>
 *       <pre>
 *         Talk is cheap, show me the code.
 *       </pre>
 *     </pre>
 * @version 0.1.0
 * @since 0.1.0 - 2021/3/14 18:35
 */
@Configuration
public class BluRayAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(AccessDecisionManager.class)
  public AccessDecisionManager accessDecisionManager() {
    return new BluRayAccessDecisionManager();
  }

  @Bean
  @ConditionalOnMissingBean(AccessDeniedHandler.class)
  public AccessDeniedHandler accessDeniedHandler() {
    return new BluRayAccessDeniedHandler();
  }

  @Bean
  @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new BluRayAuthenticationEntryPoint();
  }

  @Bean
  @ConditionalOnMissingBean(FilterInvocationSecurityMetadataSource.class)
  public BluRayFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(BluRayResourceDefine bluRayResourceDefine) {
    return new BluRayFilterInvocationSecurityMetadataSource(bluRayResourceDefine);
  }

  @Bean
  @ConditionalOnMissingBean(BluRayFilterSecurityInterceptor.class)
  public BluRayFilterSecurityInterceptor filter() {
    return new BluRayFilterSecurityInterceptor();
  }

  @Bean
  @ConditionalOnMissingBean(PasswordEncoder.class)
  public PasswordEncoder passwordEncoder() {
    return new BluRayPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean(BluRayResourceDefine.class)
  public BluRayResourceDefine bluRayResourceDefine() {
    return new DefaultBluRayResourceDefineImpl();
  }

  @Bean
  @ConditionalOnMissingBean(UserDetailsService.class)
  public UserDetailsService userDetailsService() {
    return new DefaultBluRayUserDetailsServiceImpl();
  }
}