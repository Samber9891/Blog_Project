package com.tts.techtalentblog.BlogPost;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;



@Entity
public class BlogPost {
   

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "blogpost_tag", joinColumns = @JoinColumn(name = "blogpost_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;

    // fields or properties

        private String title; 
        private String author; 
        private String blogEntry; 
    
        @CreationTimestamp
        private Date createdAt;
        
        private String date; 
        

        // default constructor
        public BlogPost(){}
    
    
    // source action - constructor (all but id)
    public BlogPost(String title, String author, String blogEntry, Date createdAt, String date, List<Tag> tags) {
        this.title = title;
        this.author = author;
        this.blogEntry = blogEntry;
        this.createdAt = createdAt;
        this.date = date;
        this.tags = tags;
    }


      // source action - getters and setters (all but id)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }



    
         // source action - toString (all)
    @Override
    public String toString() {
        return "BlogPost [author=" + author + ", blogEntry=" + blogEntry + ", createdAt=" + createdAt + ", date=" + date
                + ", id=" + id + ", tags=" + tags + ", title=" + title + "]";
    }

       
  

     


      




                  
    
      
       

 
       

	


		
        
    
    }
    
      

