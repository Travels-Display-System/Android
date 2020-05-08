package com.zhao.ui_basic.ui.main.Model;

public class Like {
    private Long id;
    private String userid;
    private String travelid;
    private String type;

    public Like(String id, String travel){
        this.userid = id;
        this.travelid = travel;
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
