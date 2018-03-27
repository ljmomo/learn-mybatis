package com.junli.mybatis.beans;

public class Posts {
    private Integer pid;

    private String postName;

    private Integer blogId;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "                            pid:" + pid +
                ",                             postName:'" + postName + '\'' +
                ",                             blogId:" + blogId +
                '}';
    }
}