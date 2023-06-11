package com.example.sau.service.impl;

import com.example.sau.model.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    public List<Blog> getAllBlogs();
    public void addBlog(Blog blog);
    public void removeBlogById(long id);
    public Optional<Blog> getBlogById(long id);
}
