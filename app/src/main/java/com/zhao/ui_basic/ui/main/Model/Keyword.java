package com.zhao.ui_basic.ui.main.Model;

import java.io.Serializable;

public class Keyword implements Serializable {
    private Long id;
    private String keyword;
    private String type;
    public Keyword(String keyword){
        this.keyword = keyword;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getKeyword(){return keyword;}
    public void setKeyword(String keyword){this.keyword=keyword;}
    public String getType(){return type;}
    public void setType(String type){this.type=type;}
}
