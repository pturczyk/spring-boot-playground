package io.pt.springboot.bookmark.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import io.pt.springboot.bookmark.exception.BookmarkNotFoundException;
import io.pt.springboot.bookmark.model.Bookmark;
import io.pt.springboot.bookmark.repo.BookmarkRepository;
import io.pt.springboot.bookmark.resource.BookmarkResource;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkRestController {

	private final BookmarkRepository bookmarkRepository;

	@Autowired
	public BookmarkRestController(BookmarkRepository bookmarkRepository) {
		this.bookmarkRepository = bookmarkRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> addBookmark(@RequestBody Bookmark bookmark) {
		bookmarkRepository.save(bookmark);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(linkTo(methodOn(getClass()).getBookmark(bookmark.getName())).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	public void removeBookmark(@PathVariable String name) {
		bookmarkRepository.delete(findBookmark(name));
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET) 
	public BookmarkResource getBookmark(@PathVariable String name) {
		return new BookmarkResource(findBookmark(name));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Resources<BookmarkResource> getBookmarks() {
		return new Resources<BookmarkResource>(
				bookmarkRepository.findAll()
				.stream()
				.map(BookmarkResource::new)
				.collect(Collectors.toList()));
	}

	private Bookmark findBookmark(String name) {
		return bookmarkRepository.findByName(name)
				.orElseThrow(() -> new BookmarkNotFoundException(name));
	}
	
}
