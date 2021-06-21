/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.security
 *   <b>class: </b>BluRayPasswordEncoder
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
 * @since 0.1.0 - 3/12/21 10:17 AM
 */
public class BluRayPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence charSequence) {
    return (String) charSequence;
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    System.out.println("============================================");
    System.out.println(charSequence);
    System.out.println(s);
    System.out.println("============================================");
    return charSequence.toString()
                       .equalsIgnoreCase(s);
  }
}
