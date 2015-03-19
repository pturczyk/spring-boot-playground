package io.pt.springboot.bookmark.resource;

import io.pt.springboot.bookmark.controller.BookmarkRestController;
import io.pt.springboot.bookmark.model.Bookmark;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class BookmarkResourceAssembler extends
		ResourceAssemblerSupport<Bookmark, BookmarkResource> {

	public BookmarkResourceAssembler() {
		super(BookmarkRestController.class, BookmarkResource.class);
	}

	@Override
	public BookmarkResource toResource(Bookmark entity) {
		BookmarkResource resource = createResourceWithId(entity.getName(), entity);
		resource.add(linkTo(BookmarkRestController.class).withRel(BookmarkResource.LINK_NAME_BOOKMARKS));
		return resource;
	}
	
	@Override
	protected BookmarkResource instantiateResource(Bookmark entity) {
		return new BookmarkResource(entity);
	}

}
