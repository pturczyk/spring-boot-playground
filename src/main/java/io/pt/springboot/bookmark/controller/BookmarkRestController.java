package io.pt.springboot.bookmark.controller;

import java.util.Collection;
import java.util.Collections;

import io.pt.springboot.bookmark.model.Bookmark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmark")
public class BookmarkRestController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addBookmark(@RequestBody Bookmark bookmark) {
		// TODO: call service
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{bookmarkId}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> removeBookmark(@PathVariable int bookmarkId) {
		// TODO: call service
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET) 
	public Bookmark getBookmark(@PathVariable int bookmarkId) {
		// TODO: call service
		return null;
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public Collection<Bookmark> getBookmarks() {
		// TODO: call service
		return Collections.emptyList();
	}
}
