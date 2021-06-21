/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.security
 *   <b>class: </b>DefaultBluRayResourceDefine
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
 * @since 0.1.0 - 2021/3/14 18:54
 */
public class DefaultBluRayResourceDefineImpl implements BluRayResourceDefine {
  @Override
  public Map<String, Collection<ConfigAttribute>> loadResourceDefine() {
    Map<String, Collection<ConfigAttribute>> map = new ConcurrentHashMap<>(1);
    Collection<ConfigAttribute> collection = new ArrayList<>();
    collection.add(new SecurityConfig("ROLE_SIGN_IN"));
    map.put("/**", collection);
    return map;
  }
}