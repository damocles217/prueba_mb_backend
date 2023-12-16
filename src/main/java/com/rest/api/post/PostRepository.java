package com.rest.api.post;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, String> {
  @Query(
    "SELECT * FROM post WHERE name LIKE %:name% OR description LIKE %:description%"
  )
  List<PostEntity> findByNameLikeOrDescriptionLike(
    String name,
    String description
  );
}
