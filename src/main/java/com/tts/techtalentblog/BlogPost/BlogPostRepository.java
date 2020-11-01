package com.tts.techtalentblog.BlogPost;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

	List<BlogPost> findByTags_PhraseOrderByCreatedAtDesc(String tag);

	List<BlogPost> findAllByOrderByCreatedAtDesc();
    
}