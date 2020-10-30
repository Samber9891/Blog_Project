package com.tts.techtalentwitter.repository;

import java.util.List;

import com.tts.techtalentwitter.model.Tweet;
import com.tts.techtalentwitter.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {

  
    List<Tweet> findAllByOrderByCreatedAtDesc() ; 
    List<Tweet> findAllByUserOrderByCreatedAtDesc(User user) ; 
    List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users) ;
    List<Tweet> findByTags_PhraseOrderByCreatedAtDesc(String phrase);
    
}
