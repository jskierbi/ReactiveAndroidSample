package com.jskierbi.reactiveandroidsample.Network;

import com.google.gson.annotations.Expose;

/**
 * Created by jakub on 15/05/15.
 */
public class Repository {

  @Expose
  long    id;
  @Expose
  String  name;
  @Expose
  String  full_name;
  @Expose
  String  description;
  @Expose
  boolean fork;
  @Expose
  String  url;
  @Expose
  String  html_url;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFull_name() {
    return full_name;
  }

  public void setFull_name(String full_name) {
    this.full_name = full_name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isFork() {
    return fork;
  }

  public void setFork(boolean fork) {
    this.fork = fork;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHtml_url() {
    return html_url;
  }

  public void setHtml_url(String html_url) {
    this.html_url = html_url;
  }

  @Override
  public String toString() {
    return "Repository{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", full_name='" + full_name + '\'' +
        ", description='" + description + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
