package com.tts.techtalentwitter.repository;

import com.tts.techtalentwitter.model.Tag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
    
    Tag findByPhrase( String phrase);
}
