package com.jskierbi.reactiveandroidsample.ui.bus;

/**
 * Created by jakub on 25/05/15.
 */
public class PublishableData {

  private String eventName;
  private String comments;

  public PublishableData() {
  }

  public PublishableData(String eventName, String comments) {
    this.eventName = eventName;
    this.comments = comments;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "PublishableData{" +
        "eventName='" + eventName + '\'' +
        ", comments='" + comments + '\'' +
        '}';
  }
}
