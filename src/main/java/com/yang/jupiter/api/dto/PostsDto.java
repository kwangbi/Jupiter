package com.yang.jupiter.api.dto;

import com.yang.jupiter.api.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsDto(Long id, String title, String content, String author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .tile(title)
                .content(content)
                .author(author)
                .build();
    }

    public PostsDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
