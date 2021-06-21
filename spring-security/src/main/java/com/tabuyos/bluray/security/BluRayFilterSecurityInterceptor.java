/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.security
 *   <b>class: </b>BluRayFilterSecurityInterceptor
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
 * @since 0.1.0 - 3/11/21 5:19 PM
 */
public class BluRayFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

  private FilterInvocationSecurityMetadataSource securityMetadataSource;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    FilterInvocation fi = new FilterInvocation(request, response, chain);
    //OPTIONS请求直接放行
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (httpServletRequest.getMethod()
                          .equals(HttpMethod.OPTIONS.toString())) {
      fi.getChain()
        .doFilter(fi.getRequest(), fi.getResponse());
      return;
    }
    System.out.println("----------------");
    System.out.println(httpServletRequest.getRequestURI());
    System.out.println("----------------");
    if (httpServletRequest.getRequestURI()
                          .startsWith("/login")) {
      fi.getChain()
        .doFilter(fi.getRequest(), fi.getResponse());
      return;
    }
    System.out.println("call invoke");
    invoke(fi);
  }

  private void invoke(FilterInvocation fi) throws IOException, ServletException {
    // fi里面有一个被拦截的url
    // 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
    // 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
    InterceptorStatusToken token = super.beforeInvocation(fi);
    System.out.println(token);
    try {
      // 执行下一个拦截器
      fi.getChain()
        .doFilter(fi.getRequest(), fi.getResponse());
    } finally {
      super.afterInvocation(token, null);
    }
  }

  @Override
  public void destroy() {
  }

  @Override
  public Class<?> getSecureObjectClass() {
    return FilterInvocation.class;
  }

  @Override
  public SecurityMetadataSource obtainSecurityMetadataSource() {
    return this.securityMetadataSource;
  }

  public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
    return this.securityMetadataSource;
  }

  /**
   * 设置自定义的FilterInvocationSecurityMetadataSource
   *
   * @param metadataSource securityMetadataSource for platform
   */
  @Autowired
  public void setSecurityMetadataSource(BluRayFilterInvocationSecurityMetadataSource metadataSource) {
    this.securityMetadataSource = metadataSource;
  }

  /**
   * 设置自定义的AccessDecisionManager
   *
   * @param accessDecisionManager accessDecisionManager for platform
   */
  @Override
  @Autowired
  public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
    super.setAccessDecisionManager(accessDecisionManager);
  }
}
