package com.yang.jupiter.api.controller;


import com.yang.jupiter.api.domain.Posts;
import com.yang.jupiter.api.dto.PostsDto;
import com.yang.jupiter.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostService postService;

    /**
     * 게시글 등록
     *
     * @param dto
     * @return
     */
    @PostMapping("/api/posts")
    public Long save(@RequestBody PostsDto dto) {
        return postService.savePosts(dto);
    }

    /**
     * 게시글 수정
     *
     * @param id
     * @param dto
     * @return
     */
    @PostMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostsDto dto) {
        return postService.updatePosts(id, dto);
    }

    /**
     * 게시글 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/api/posts/{id}")
    public PostsDto getPosts(@PathVariable Long id) {
        return postService.getPost(id);
    }

    /**
     * 게시글 전체 조회
     *
     * @return
     */
    @GetMapping("/api/posts")
    public List<Posts> getAllPosts() {
        return postService.getAllPost();
    }

    /**
     * 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/posts/{id}")
    public int deletePosts(@PathVariable Long id) {
        return postService.deletePosts(id);
    }

}
