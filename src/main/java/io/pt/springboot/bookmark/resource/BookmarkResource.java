package io.pt.springboot.bookmark.resource;

import io.pt.springboot.bookmark.model.Bookmark;

import org.springframework.hateoas.Resource;

public class BookmarkResource extends Resource<Bookmark> {
	public static final String LINK_NAME_BOOKMARKS = "bookmarks";

	public BookmarkResource(Bookmark bookmark) {
		super(bookmark);
	}
}
