package com.dev.service.impl;

import com.dev.configurations.security.SpringSecurityAuditorAware;
import com.dev.domain.BlogPost;
import com.dev.domain.User;
import com.dev.dto.projection.BlogPostProjection;
import com.dev.dto.requestdto.BlogPostRequestDto;
import com.dev.exceptions.DBOperationExceptionHandler;
import com.dev.repo.BlogPostRepo;
import com.dev.service.BlogPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepo blogPostRepo;
    @Autowired
    private SpringSecurityAuditorAware springSecurityAuditorAware;

    public List<BlogPostProjection> getAllBlogPost() {
        return blogPostRepo.getAllBlogPost();
    }

    @Override
    @Transactional
    public UUID deleteBlogPost(UUID postId) {
        Integer countRow = blogPostRepo.deleteBlogPost(postId);
        if (countRow == null || countRow == 0) {
            throw new DBOperationExceptionHandler("Blog post can't be deleted!!",
                    "Blog post can't be deleted!!");
        }
        return postId;
    }

    @Override
    @Transactional
    public UUID approveBlogPost(UUID postId) {

        Integer countRow = blogPostRepo.approveBlogPost(postId);
        if (countRow == null || countRow == 0) {
            throw new DBOperationExceptionHandler("Blog post can't be approved!!",
                    "Blog post can't be approved!!");
        }
        return postId;
    }

    @Override
    public BlogPost requestForBlogPostApproval(BlogPostRequestDto blogPostRequestDto) {
        BlogPost blogPost = copyToEntity(blogPostRequestDto);
        blogPost.setStatus(false);
        blogPost.setUser((User) new User().setId(springSecurityAuditorAware.getCurrentUserId()));
        return blogPostRepo.save(blogPost);
    }

    public BlogPost copyToEntity(BlogPostRequestDto blogPostRequestDto) {
        BlogPost blogPost = new BlogPost();
        BeanUtils.copyProperties(blogPostRequestDto, blogPost);
        return blogPost;
    }
}
