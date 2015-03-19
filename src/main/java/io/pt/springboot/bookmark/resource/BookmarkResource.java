package io.pt.springboot.bookmark.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import io.pt.springboot.bookmark.controller.BookmarkRestController;
import io.pt.springboot.bookmark.model.Bookmark;

import org.springframework.hateoas.ResourceSupport;

public class BookmarkResource extends ResourceSupport {
	private final Bookmark bookmark;

	public BookmarkResource(Bookmark bookmark) {
		this.bookmark = bookmark;
		initLinks();
	}

	private void initLinks() {
		this.add(linkTo(BookmarkRestController.class).withRel("bookmarks"));
		this.add(linkTo(methodOn(BookmarkRestController.class).getBookmark(bookmark.getName())).withSelfRel());
	}
}
