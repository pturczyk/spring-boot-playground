package io.pt.springboot.bookmark.controller;

import io.pt.springboot.bookmark.model.Bookmark;
import io.pt.springboot.bookmark.repo.BookmarkRepository;
import io.pt.springboot.bookmark.exception.BookmarkNotFoundException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/bookmark")
public class BookmarkRestController {

	private final BookmarkRepository bookmarkRepository;

	@Autowired
	public BookmarkRestController(BookmarkRepository bookmarkRepository) {
		this.bookmarkRepository = bookmarkRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Bookmark> addBookmark(@RequestBody Bookmark bookmark) {
		bookmarkRepository.save(bookmark);
		
		return new ResponseEntity<Bookmark>(
				null, 
				buildLocationHeader("/{name}", bookmark.getName()), 
				HttpStatus.CREATED
			);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	public void removeBookmark(@PathVariable String name) {
		bookmarkRepository.delete(findBookmark(name));
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET) 
	public Bookmark getBookmark(@PathVariable String name) {
		return findBookmark(name);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Bookmark> getBookmarks() {
		return bookmarkRepository.findAll();
	}

	private MultiValueMap<String, String> buildLocationHeader(String path, String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
			ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(name).toUri()
		);
		return headers;
	}

	private Bookmark findBookmark(String name) {
		return bookmarkRepository.findByName(name)
				.orElseThrow(() -> new BookmarkNotFoundException(name));
	}
	
}
