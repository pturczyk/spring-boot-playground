package io.pt.springboot.bookmark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BookmarkNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BookmarkNotFoundException(String bookmarkName) {
		super("Bookmark with name '" + bookmarkName + "' not found.");
	}
}