package io.pt.springboot.bookmark.repo;

import java.util.Optional;

import io.pt.springboot.bookmark.model.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	Optional<Bookmark> findByName(String name);
}
