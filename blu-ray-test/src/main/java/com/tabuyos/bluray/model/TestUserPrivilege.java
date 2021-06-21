/*
 * Copyright 2019-2021 the Tabuyos.
 */
package com.tabuyos.bluray.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.bluray.model
 *   <b>class: </b>TestUserPrivilege
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
 * @since 0.1.0 - 2021/3/14 18:22
 */
@Entity
@Table(name = "user_privilege")
public class TestUserPrivilege {

  @Id
  @Column(name = "id")
  private Integer id;
  @Column(name = "uid")
  private Integer uid;
  @Column(name = "url")
  private String url;
  @Column(name = "name")
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "TestUserPrivilege{" +
        "id=" + id +
        ", uid=" + uid +
        ", url='" + url + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}