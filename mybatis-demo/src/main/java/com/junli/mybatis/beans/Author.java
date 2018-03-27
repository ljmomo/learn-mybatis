package com.junli.mybatis.beans;

/**
 * @author lenovo
 */
public class Author {
    private Integer aid;

    private String authorName;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    @Override
    public String toString() {
        return "Author{" +
                "                            aid:" + aid +
                ",                             authorName:'" + authorName + '\'' +
                '}';
    }
}