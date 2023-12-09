package com.todaytrend.postservice.post.controller;

import com.todaytrend.postservice.post.dto.CRUD.RequestUpdatePostDto;
import com.todaytrend.postservice.post.dto.CRUD.ResponseMakePostDto;
import com.todaytrend.postservice.post.dto.RequestCheckLikedDto;
import com.todaytrend.postservice.post.dto.main.RequestTabDto;
import com.todaytrend.postservice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
//@CrossOrigin("http://127.0.0.1:5500")
public class PostController {

    private final PostService postService;

    //게시물 생성
    @PostMapping("")
    public ResponseEntity<?> makePost(@RequestBody ResponseMakePostDto responseMakePostDto){
        return new ResponseEntity(postService.makePost(responseMakePostDto), HttpStatus.OK);
    }

    //게시물 삭제
    @DeleteMapping("")
    public ResponseEntity<?> deletePost(@RequestParam("postId")Long postId){
        return new ResponseEntity(postService.removePost(postId),HttpStatus.OK);
    }

    //게시물 상세보기
    @GetMapping("")
    public ResponseEntity<?> findPost(@RequestParam("postId")Long postId){
        return new ResponseEntity(postService.findPost(postId),HttpStatus.OK);
    }

    //해당 포스트에서 선택한 카테고리 목록
    @GetMapping("category")
    public ResponseEntity<?> findPostCategory(@RequestParam("postId")Long postId){
        return new ResponseEntity(postService.findPostCategoryList(postId),HttpStatus.OK);
    }

    //좋아요 클릭(insert/delete)
    @PutMapping("/like")
    public ResponseEntity<?> clickLike(@RequestBody RequestCheckLikedDto requestCheckLikedDto){
        return new ResponseEntity(postService.clickLike(requestCheckLikedDto), HttpStatus.OK);
    }

    //좋아요 클릭 여부 (true false)
    @GetMapping("/liked")
    public ResponseEntity<?> checkLiked(@RequestHeader RequestCheckLikedDto requestCheckLikedDto){
        return new ResponseEntity<>(postService.checkLiked(requestCheckLikedDto),HttpStatus.OK);
    }
    
    //좋아요 개수
    @GetMapping("/likecnt")
    public ResponseEntity<?> checkLikedCnt(@RequestParam("postId")Long postId){
        return new ResponseEntity<>(postService.checkLikeCnt(postId),HttpStatus.OK);
    }

    //좋아요 누른 유저 리스트
    @GetMapping("/likelist")
    public ResponseEntity<?> postLikeList(@RequestHeader String postId){
        return new ResponseEntity<>(postService.postLikeList(Long.getLong(postId)),HttpStatus.OK);
    }

    //해당 유저가 좋아요 누른 리스트
    @GetMapping("/likeposts")
    public ResponseEntity<?> userLikePost(@RequestHeader String uuid){
        return new ResponseEntity<>(postService.userLikePost(uuid), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updatePost(@RequestParam("postId")Long postId, @RequestBody RequestUpdatePostDto requestPostDto){
        return new ResponseEntity(postService.updatePost(postId, requestPostDto), HttpStatus.OK);
    }

    // 게시물 상세 보기 하단 게시글 리스트
    @GetMapping("posts/detaillist")
    public ResponseEntity<?> recommendPostWithDetail(@RequestHeader RequestCheckLikedDto requestDto){
        return new ResponseEntity(postService.detailPostsList(requestDto), HttpStatus.OK);
    }

//    AdminCategory 리스트 제공
    @GetMapping("admincategorylist")
    public ResponseEntity<?> adminCategoryListForMain(){
        return new ResponseEntity<>(postService.findAdminCategoryList(),HttpStatus.OK);
    }

//     최신, 좋아요, 팔로잉 순
    @GetMapping("main")
    public ResponseEntity<?> chooseTab(@RequestHeader RequestTabDto requestTabDto,
                                       @RequestParam(name = "page",defaultValue = "0")Integer page,
                                       @RequestParam(name = "size",defaultValue = "24")Integer size){
        return new ResponseEntity<>(postService.postListTab(requestTabDto,page,size),HttpStatus.OK);
    }

//    main 최신 + 카테고리
    @GetMapping("main/category")
    public ResponseEntity<?> chooseCategory(@RequestHeader List<Long> categoryIds){
        return new ResponseEntity<>(postService.postListCategory(categoryIds),HttpStatus.OK);
    }

//    hashtag 검색
    @GetMapping("/hashtag/{hashtag}")
    public ResponseEntity<?> hashTagList(@PathVariable("hashtag")String hashtag){
        return new ResponseEntity<>(postService.findhashTag(hashtag), HttpStatus.OK);
    }

}