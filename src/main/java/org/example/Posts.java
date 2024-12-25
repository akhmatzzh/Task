package org.example;

public class Posts {
    private int UserId;
    private int id;
    private String title;
    private String body;

    public String getBody() {
        return body;
    }

    public int getUserId() {
        return UserId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "UserId=" + UserId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
