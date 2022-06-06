package com.keymamo.study.mytest.controller;

import com.keymamo.study.mytest.controller.dto.PostsResponseDto;
import com.keymamo.study.mytest.controller.dto.PostsSaveRequestDto;
import com.keymamo.study.mytest.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public long update(
            @PathVariable Long id,
            @RequestBody PostsSaveRequestDto requestDto
    ) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
