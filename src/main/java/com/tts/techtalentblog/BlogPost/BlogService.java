package com.tts.techtalentblog.BlogPost;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class BlogService {

    @Autowired
    private static BlogPostRepository blogPostRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<BlogPostDisplay> findAll() {
        List<BlogPost> blogPosts = blogPostRepository.findAllByOrderByCreatedAtDesc();
        return formatBlogPosts(blogPosts);
    }

    public List<BlogPostDisplay> findAllWithTag(String tag) {
        List<BlogPost> blogPosts = blogPostRepository.findByTags_PhraseOrderByCreatedAtDesc(tag);
        return formatBlogPosts(blogPosts);
    }

 

    public void save(BlogPost blogPost) {
        handleTags(blogPost);
        blogPostRepository.save(blogPost);
    }

    private void handleTags(BlogPost blogPost) {
        List<Tag> tags = new ArrayList<Tag>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(blogPost.getBlogEntry());
     
        while (matcher.find()) {
            String phrase = matcher.group().substring(1).toLowerCase();
            Tag tag = tagRepository.findByPhrase(phrase);
            if (tag == null) {
                tag = new Tag();
                tag.setPhrase(phrase);
                tagRepository.save(tag);
            }
            tags.add(tag);
        }
        blogPost.setTags(tags);
    }

    private List<BlogPostDisplay> formatBlogPosts(List<BlogPost> blogPosts) {
        addTagLinks(blogPosts);
        List<BlogPostDisplay> displayBlogPosts = formatTimeStamps(blogPosts); 
        return  displayBlogPosts;
    }

    private List<BlogPostDisplay> formatTimeStamps(List<BlogPost> blogPosts) {
        List<BlogPostDisplay> response = new ArrayList<>();
        PrettyTime prettyTime = new PrettyTime();
        SimpleDateFormat simpleDate = new SimpleDateFormat("M/d/yy");
        Date now = new Date();
        for (BlogPost blogPost : blogPosts) {
            BlogPostDisplay blogPostDisplay = new BlogPostDisplay();
            blogPostDisplay.setBlogEntry(blogPost.getBlogEntry());
            blogPostDisplay.setTag(blogPost.getTags());
            long diffInMillies = Math.abs(now.getTime() - blogPost.getCreatedAt().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff > 3) {
                blogPostDisplay.setDate(simpleDate.format(blogPost.getCreatedAt()));
            } else {
                blogPostDisplay.setDate(prettyTime.format(blogPost.getCreatedAt()));
            }
            response.add(blogPostDisplay);
        }
        return response;
    }
    

    private void addTagLinks(List<BlogPost> blogPosts) {
        Pattern pattern = Pattern.compile("#\\w+");
        for (BlogPost blogPost : blogPosts) {
            String blogEntry = blogPost.getBlogEntry();
            Matcher matcher = pattern.matcher(blogEntry);
            Set<String> tags = new HashSet<String>();
            while (matcher.find()) {
                tags.add(matcher.group());
            }
            for (String tag : tags) {
                blogEntry = blogEntry.replaceAll(tag,
                "<a class=\"tag\" href=\"/blogpost/taggedblogpost" + tag.substring(1).toLowerCase() + "\">" + tag + "</a>");
            }
            blogPost.setBlogEntry(blogEntry);
        }
    }
   


    }

    




