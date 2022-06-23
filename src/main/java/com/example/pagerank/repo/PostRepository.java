package com.example.pagerank.repo;

import com.example.pagerank.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
}
