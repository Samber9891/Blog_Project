package com.tts.techtalentblog.BlogPost;

import java.util.List;

public class BlogPostDisplay {
    
    private String title; 
    private String author; 
    private String blogEntry; 
    private String date; 
    private List<Tag> tag;

    public BlogPostDisplay() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlogEntry() {
        return blogEntry;
    }

    public void setBlogEntry(String blogEntry) {
        this.blogEntry = blogEntry;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "BlogPostDisplay [author=" + author + ", blogEntry=" + blogEntry + ", date=" + date + ", tag=" + tag
                + ", title=" + title + "]";
    }

    

}
