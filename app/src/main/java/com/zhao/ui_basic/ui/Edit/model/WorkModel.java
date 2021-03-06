package com.zhao.ui_basic.ui.Edit.model;
import   java.text.SimpleDateFormat;

import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.ui.main.Model.Keyword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkModel {
    private String id;

    private String title;

    private String content;

    //审核状态：0为未审核(未提交），1为审核中，2为审核通过，3为审核未通过
    private Integer state;

    private String timestamp;

    private String advice;

    private String  username;

    private  String type;

    public List<Keyword> keywordList;

    public WorkModel(String title,String content,String username,String time,Integer n,String keywords){
        this.title = title;
        this.content = content;
        this.timestamp = time;
        this.username = username;
        this.state = n;
        String regex = "[;]";
        String[] result1 = new String[]{};
        if(!Utils.isEmpty(keywords))
            result1 = keywords.split(";");
        else
            keywordList.add(new Keyword("mother fucker"));


        keywordList = new ArrayList<>();
        for(String a : result1){
            keywordList.add(new Keyword(a));
        }
    }
    public WorkModel(String title,String content,String username,String time,Integer n,String keywords,String Id){
        this.title = title;
        this.content = content;
        this.timestamp = time;
        this.username = username;
        this.state = n;
        this.id = Id;
        String regex = "[;]";
        String[] result1 = new String[]{};
        if(!Utils.isEmpty(keywords))
            result1 = keywords.split(";");
        else
            keywordList.add(new Keyword("mother fucker"));


        keywordList = new ArrayList<>();
        for(String a : result1){
            keywordList.add(new Keyword(a));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public String getType(){return type;}
    public void setType(String type){this.type=type;}

    public List<Keyword> getKeywordList(){return keywordList;}
    public void setKeywordList(List<Keyword> keywordList){this.keywordList=keywordList;}
}
