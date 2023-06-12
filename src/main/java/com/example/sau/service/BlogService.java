package com.example.sau.service;

import com.example.sau.model.Blog;
import com.example.sau.repository.BlogRepo;
import com.example.sau.service.impl.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    BlogRepo blogRepo;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    @Override
    public void addBlog(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void removeBlogById(long id) {
        blogRepo.deleteById(id);

    }

    @Override
    public Optional<Blog> getBlogById(long id) {
        return blogRepo.findById(id);
    }
}