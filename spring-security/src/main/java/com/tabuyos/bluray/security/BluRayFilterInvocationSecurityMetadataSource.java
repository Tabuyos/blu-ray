/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.security
 *   <b>class: </b>BluRayFilterInvocationSecurityMetadataSource
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
 * @since 0.1.0 - 3/11/21 5:19 PM
 */
public class BluRayFilterInvocationSecurityMetadataSource
    implements FilterInvocationSecurityMetadataSource {

  private final BluRayResourceDefine bluRayResourceDefine;
  private Map<String, Collection<ConfigAttribute>> configAttributeMap = null;

  public BluRayFilterInvocationSecurityMetadataSource(BluRayResourceDefine bluRayResourceDefine) {
    this.bluRayResourceDefine = bluRayResourceDefine;
  }

  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    AntPathMatcher matcher = new AntPathMatcher();
    String requestUri = ((FilterInvocation) object).getRequest().getRequestURI();
    // 1.加载权限资源数据
    if (configAttributeMap == null) {
      // 加载数据库的所有权限
      configAttributeMap = bluRayResourceDefine.loadResourceDefine();
    }
    System.out.println(configAttributeMap);
    for (String resourceUri : configAttributeMap.keySet()) {
      if (matcher.match(resourceUri, requestUri)) {
        System.out.println("resourceUri: " + resourceUri);
        System.out.println("requestUri: " + requestUri);
        return configAttributeMap.get(resourceUri);
      }
    }
    // 登录即可访问
    return SecurityConfig.createList("ROLE_SIGN_IN");
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    // 1.加载权限资源数据
    if (configAttributeMap == null) {
      // 加载数据库的所有权限
      configAttributeMap = bluRayResourceDefine.loadResourceDefine();
    }
    return configAttributeMap.values().parallelStream()
        .flatMap(Collection::parallelStream)
        .collect(Collectors.toList());
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return FilterInvocation.class.isAssignableFrom(clazz);
  }
}
