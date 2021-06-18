package com.yang.jupiter.api.service;

import com.yang.jupiter.api.domain.Posts;
import com.yang.jupiter.api.dto.PostsDto;
import com.yang.jupiter.api.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postsRepository;

    /**
     * 게시글 조회
     * @param id
     * @return
     */
    public PostsDto getPost(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostsDto(entity);
    }

    /**
     * 게시글 전체 조회
     * @return
     */
    public List<Posts> getAllPost(){
        return postsRepository.findAll();
    }

    /**
     * Posts 저장
     * @param dto
     * @return
     */
    @Transactional
    public Long savePosts(PostsDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    /**
     * 게시글 수정
     * @param id
     * @param dto
     * @return
     */
    @Transactional
    public Long updatePosts(Long id,PostsDto dto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 파일이 없음.id : " + id));

        posts.update(dto.getTitle(),dto.getContent());
        return id;
    }

}
