/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.security
 *   <b>class: </b>BluRayAccessDecisionManager
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
 * @since 0.1.0 - 3/11/21 5:18 PM
 */
public class BluRayAccessDecisionManager implements AccessDecisionManager {

  @Override
  public void decide(
      Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
      throws AccessDeniedException, InsufficientAuthenticationException {
    System.out.println("321321321");
    String needAuthority;
    if (null == configAttributes || configAttributes.size() <= 0) {
      System.out.println("0+0");
      return;
    }
    // 1.获取已定义的好资源权限配置
    for (ConfigAttribute configAttribute : configAttributes) {
      needAuthority = configAttribute.getAttribute();
      if ("ROLE_SIGN_IN".equals(needAuthority)) {
        if (authentication instanceof AnonymousAuthenticationToken) {
          System.out.println("1+1");
          throw new BadCredentialsException("未登录");
        } else {
          System.out.println("2+2");
          return;
        }
      }
      System.out.println("-----------------------------------");
      System.out.println("details: " + authentication.getDetails());
      System.out.println("needAuthority: " + needAuthority);
      System.out.println("-----------------------------------");
      // 2.依次比对用户角色对应的资源权限
      for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
        System.out.println(needAuthority + " <--> " + grantedAuthority.getAuthority());
        if (needAuthority.trim()
            .equals(grantedAuthority.getAuthority())) {
          return;
        }
      }
    }
    System.out.println("权限不足");
    throw new AccessDeniedException("权限不足!");
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}
