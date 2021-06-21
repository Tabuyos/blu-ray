/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.model
 *   <b>class: </b>TestUserDetailsServiceImpl
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
 * @since 0.1.0 - 2021/3/14 19:27
 */
@Service
public class TestUserDetailsServiceImpl {

  private final TestUserDetailsRepository testUserDetailsRepository;
  private final TestUserPrivilegeRepository testUserPrivilegeRepository;

  public TestUserDetailsServiceImpl(TestUserDetailsRepository testUserDetailsRepository, TestUserPrivilegeRepository testUserPrivilegeRepository) {
    this.testUserDetailsRepository = testUserDetailsRepository;
    this.testUserPrivilegeRepository = testUserPrivilegeRepository;
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<TestUserDetails> testUserDetails = testUserDetailsRepository.selectByUserName(username);
    if (testUserDetails.size() < 1) {
      throw new UsernameNotFoundException("未找到用户");
    }
    TestUserDetails details = testUserDetails.get(0);
    if (details != null) {
      List<String> strings = testUserPrivilegeRepository.selectByUid(details.getId());
      return new DefaultUserDetails(details, strings);
    }
    throw new UsernameNotFoundException("未找到用户");
  }
}