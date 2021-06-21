/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.model;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.model
 *   <b>class: </b>TestResourceDefine
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
 * @since 0.1.0 - 2021/3/14 20:41
 */
@Service
public class TestResourceDefine {
  private final TestUserPrivilegeRepository testUserPrivilegeRepository;

  public TestResourceDefine(TestUserPrivilegeRepository testUserPrivilegeRepository) {
    this.testUserPrivilegeRepository = testUserPrivilegeRepository;
  }

  public Map<String, Collection<ConfigAttribute>> loadResourceDefine() {
    System.out.println("=============================================");
    System.out.println("loadResourceDefine");
    System.out.println("=============================================");
    Map<String, Collection<ConfigAttribute>> map = new ConcurrentHashMap<>();
    List<TestUserPrivilege> all = testUserPrivilegeRepository.findAll();
    for (TestUserPrivilege testUserPrivilege : all) {
      Collection<ConfigAttribute> collection = new ArrayList<>();
      collection.add(new SecurityConfig(testUserPrivilege.getName()));
      map.put(testUserPrivilege.getUrl(), collection);
    }
    return map;
  }
}