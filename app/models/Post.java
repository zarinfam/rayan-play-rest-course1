package models;

import java.util.Date;

/**
 * Created by saeed on 12/17/15.
 */
public class Post {
    private long id;
    private String title;
    private String content;
    private Date publishDate;

    public Post() {
    }

    public Post(long id, String title, String content, Date publishDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
