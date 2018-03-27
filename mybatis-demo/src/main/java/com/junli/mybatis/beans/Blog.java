package com.junli.mybatis.beans;

import java.util.List;

public class Blog {
    private Integer bid;

    private String name;

    private Integer authorId;

    private Author author;

    private List<Posts> posts;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "                            bid:" + bid +
                ",                             name:'" + name + '\'' +
                ",                             authorId:" + authorId +
                ",                             author:" + author +
                ",                             posts:" + posts +
                '}';
    }
}