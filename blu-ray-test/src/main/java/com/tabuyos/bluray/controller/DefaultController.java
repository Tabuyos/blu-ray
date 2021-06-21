/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.controller
 *   <b>class: </b>DefaultController
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
 * @since 0.1.0 - 2021/3/14 20:24
 */
@Controller
public class DefaultController {

  @GetMapping("/")
  public String root() {
    System.out.println("root");
    return "forward:/index";
  }

  @GetMapping("index")
  public String index() {
    System.out.println("index");
    return "index";
  }

  @GetMapping("login")
  public String login() {
    System.out.println("login");
    return "login";
  }

  @GetMapping("a")
  @ResponseBody
  public String a() {
    return "a";
  }

  @GetMapping("b")
  @ResponseBody
  public String b() {
    return "b";
  }

  @GetMapping("c")
  @ResponseBody
  public String c() {
    return "c";
  }

  @GetMapping("d")
  @ResponseBody
  public String d() {
    return "d";
  }
}