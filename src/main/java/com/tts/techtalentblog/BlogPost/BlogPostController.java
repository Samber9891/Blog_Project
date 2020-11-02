package com.tts.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BlogPostController {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private BlogService blogService;

    private List<BlogPost> posts = new ArrayList<>();

// localhost 8080
// handle get request to foward slash
    
    @GetMapping(value = "/") 
    public String index(BlogPost blogPost, Model model) {
        // remove all current posts inside the arraylist called posts, from line 22
    posts.removeAll(posts); 

    // This for each loop goes over the entire repository(all blog posts), and for each one it adds them
    // to the posts array list
    for(BlogPost postFromDB : blogPostRepository.findAll()) {
    posts.add(postFromDB);

}

    model.addAttribute("posts", posts);
    return "blogpost/index";
    }

    @GetMapping(value = "/blogpost/new")
    public String newBlog(BlogPost blogPost) {
        return "blogpost/new";
    }
 
@GetMapping (value= "/blogpost/{tag}") 
public String getTweetsByTag(@PathVariable(value="tag") String tag, Model model) {
    List<BlogPostDisplay> blogPosts = blogService.findAllWithTag(tag);
    model.addAttribute("blogList", blogPosts);
    model.addAttribute("tag", tag);
    return "blogpost/taggedblogpost";
}


    @PostMapping(value = "/blogpost")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        // We do not want to create a new instance everytime, 
        // instead we can pass in the blogpost as is. 
        blogPostRepository.save(blogPost);
      
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
        }

        @PostMapping( value= "/blogpost/update/{id}")
        public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {

    Optional<BlogPost> post = blogPostRepository.findById(id);

    if(post.isPresent()){

        BlogPost actualPost = post.get(); 

        actualPost.setTitle(blogPost.getTitle());
        actualPost.setAuthor(blogPost.getAuthor());
        actualPost.setBlogEntry(blogPost.getBlogEntry());

        blogPostRepository.save(actualPost); 

        model.addAttribute("blogPost", actualPost); 

    } else {
        // Handle the error here
    }

    return "blogpost/result"; 
    }
   
    
    

    @RequestMapping(value = "/blogpost/delete/{id}")
    // Takes id from the URL path, passes it into deleteById from the CRUD repository
    public String deletePostWithID(@PathVariable Long id, BlogPost blogPost){
        blogPostRepository.deleteById(id);

        return "blogpost/delete";
    }

    // This is the mapping to edit a specific post 

    @RequestMapping(value= "/blogpost/edit/{id}")

    public String editWithId(@PathVariable Long id, Model model) {

        // Use blogPostRepo to find post by id 
        // It returns an Optional<T> 
        // Use a variable to store the blogPost if its there
        Optional<BlogPost> editPost= blogPostRepository.findById(id); 

        // Initalize a varible to be filled by the post if it exists 
        BlogPost result = null;  

        // Us optional methiod to check it the post came through
        if(editPost.isPresent()) {

            // if the posts came through, store it in result
            result = editPost.get(); 
            // add attriibute to page, accessible throuugh model 
            model.addAttribute("blogPost", result); 

        } else {
            // need to handle error here, you could use a html error page
            return "Error"; 
        }
// show broswer 
        return "blogpost/edit"; 
        }
    }
