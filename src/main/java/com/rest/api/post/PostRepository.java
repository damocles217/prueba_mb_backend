package com.rest.api.post;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, String> {
  // List<PostEntity> findByName(String name);
  List<PostEntity> findByNameLike(String name);
}
