package org.myungkeun.spring_blog_2.repositories;

import org.myungkeun.spring_blog_2.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
