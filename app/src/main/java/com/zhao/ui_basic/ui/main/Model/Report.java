package com.zhao.ui_basic.ui.main.Model;

public class Report {
    private Long id;
    private String content;
    private String userid;
    private String travelid;
    private String type;

    public Report(String content,String userid,String travelid){
        this.content = content;
        this.userid = userid;
        this.travelid = travelid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTravelid() {
        return travelid;
    }

    public void setTravelid(String travelid) {
        this.travelid = travelid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
