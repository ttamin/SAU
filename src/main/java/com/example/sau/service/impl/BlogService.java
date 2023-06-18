package com.example.sau.service.impl;

import com.example.sau.model.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
     List<Blog> getAllBlogs();
     void addBlog(Blog blog);
     void removeBlogById(long id);
     Optional<Blog> getBlogById(long id);
}
