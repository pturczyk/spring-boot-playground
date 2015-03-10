package io.pt.springboot.bookmark.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CountingExceptionHandler {
	public static final String COUNTER_INVALID_RESOURCE = "bookmark.invalidresource.counter";

	private final CounterService counterService;

	@Autowired
	public CountingExceptionHandler(CounterService counterService) {
		this.counterService = counterService;
	}

	@ExceptionHandler(BookmarkNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)  
	@ResponseBody
	public void countError(BookmarkNotFoundException e) {
		counterService.increment(COUNTER_INVALID_RESOURCE);
	}
}
