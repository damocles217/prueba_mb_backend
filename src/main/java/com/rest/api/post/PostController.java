package com.rest.api.post;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

  @Autowired
  private PostRepository postRepository;

  public PostController(PostRepository postRepository) {
    PostEntity[] posts = new PostEntity[] {
      new PostEntity("Post 1", "Description 1"),
      new PostEntity("Post 2", "Description 2"),
      new PostEntity("Post 3", "Description 3"),
      new PostEntity("Post 4", "Description 4"),
    };

    postRepository.saveAll(Arrays.asList(posts));
  }

  @PostMapping
  public List<PostEntity> getFilteredPostByName(@RequestBody PostEntity post) {
    Field[] postFields = post.getClass().getDeclaredFields();
    Boolean isNotEmptyBody = Arrays
      .stream(postFields)
      .anyMatch(field -> {
        field.setAccessible(true);
        try {
          return field.get(post) != null;
        } catch (IllegalAccessException e) {
          return false;
        }
      });

    if (!isNotEmptyBody) {
      return postRepository.findAll();
    }

    List<PostEntity> posts = postRepository.findByNameLikeOrDescriptionLike(
      post.getName(),
      post.getDescription()
    );

    return posts;
  }
}
