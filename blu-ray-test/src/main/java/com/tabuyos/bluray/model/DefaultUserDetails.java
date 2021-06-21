/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.model
 *   <b>class: </b>DefaultUserDetails
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
 * @since 0.1.0 - 2021/3/14 19:37
 */
public class DefaultUserDetails implements UserDetails {

  private final TestUserDetails  userDetails;
  private final List<String> resourceList;

  public DefaultUserDetails(TestUserDetails userDetails, List<String> resourceList) {
    this.userDetails = userDetails;
    this.resourceList = resourceList;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // 返回当前用户的角色
    return resourceList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return userDetails.getPassword();
  }

  @Override
  public String getUsername() {
    return userDetails.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}