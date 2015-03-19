	package io.pt.springboot.bookmark.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import io.pt.springboot.bookmark.Application;
import io.pt.springboot.bookmark.model.Bookmark;
import io.pt.springboot.bookmark.repo.BookmarkRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BookmarkRestControllerTest {
	
	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		bookmarkRepository.deleteAllInBatch();
	}
	
	@Test
	public void shouldReturnNotFoundStatusForNonExistingBookmark() throws Exception {
		// when
		MockHttpServletResponse response = mockMvc.perform(get("/bookmark/non_existing_name"))
												  .andReturn()
												  .getResponse();
		// then
		assertThat(response.getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
	}
	
	@Test
	public void shouldReturnFoundBookmark() throws Exception {
		// given
		final String bookmarkName = "bookmark1";
		final String bookmarkUrl = "http://something.com";
		
		Bookmark bookmark = new Bookmark();
		bookmark.setName(bookmarkName);
		bookmark.setUrl(bookmarkUrl);
		bookmarkRepository.save(bookmark);
		
		// when
		// using spring matchers
		mockMvc.perform(get("/bookmark/" + bookmarkName))
				.andExpect(status().isOk())
				.andExpect(jsonPath(".name").value(bookmarkName))
				.andExpect(jsonPath(".url").value(bookmarkUrl));
	}
	
}
