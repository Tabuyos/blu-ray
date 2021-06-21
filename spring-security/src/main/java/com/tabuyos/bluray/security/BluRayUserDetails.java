/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.security
 *   <b>class: </b>BluRayUserDetails
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
 * @since 0.1.0 - 3/12/21 10:21 AM
 */
public class BluRayUserDetails implements UserDetails {

  @Serial
  private static final long serialVersionUID = -6406258347270402933L;
  private final UserDetails userDetails;
  private final List<String> resourceList;

  public BluRayUserDetails(UserDetails userDetails, List<String> resourceList) {
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
    return userDetails.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return userDetails.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return userDetails.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return userDetails.isEnabled();
  }

  @Override
  public String toString() {
    return "BluRayUserDetails{"
        + "userDetails="
        + userDetails
        + ", resourceList="
        + resourceList
        + '}';
  }
}
