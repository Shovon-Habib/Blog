package com.dev.web;

import com.dev.dto.requestdto.BlogPostRequestDto;
import com.dev.dto.responsedto.SuccessResponse;
import com.dev.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/blog-post/all")
    public ResponseEntity<?> getAllBloggers() {
        return ResponseEntity.ok().body(new SuccessResponse(blogPostService.getAllBlogPost()));
    }

    @PutMapping("/blog-post/approve")
    public ResponseEntity<?> approveBlogPost(@RequestParam("postId") UUID postId) {
        return ResponseEntity.ok().body(new SuccessResponse(blogPostService.approveBlogPost(postId)));
    }

    @DeleteMapping("/blog-post/delete")
    public ResponseEntity<?> deleteBlogPost(@RequestParam("postId") UUID postId) {
        return ResponseEntity.ok().body(new SuccessResponse(blogPostService.deleteBlogPost(postId)));
    }

    @PostMapping("/blog-post/request")
    public ResponseEntity<?> requestForBlogPostApproval(@Valid @RequestBody BlogPostRequestDto blogPostRequestDto) {
        return ResponseEntity.ok().body(new SuccessResponse(blogPostService.requestForBlogPostApproval(blogPostRequestDto).getId()));
    }
}
