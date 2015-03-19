package io.pt.springboot.bookmark.repo;

import java.util.Optional;

import io.pt.springboot.bookmark.model.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	Optional<Bookmark> findByName(String name);
}
