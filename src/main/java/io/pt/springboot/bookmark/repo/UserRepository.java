package io.pt.springboot.bookmark.repo;

import java.util.Collection;
import java.util.Optional;

import io.pt.springboot.bookmark.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="user", path="users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	Optional<User> findByUserName(String user);

	@Query("select u from User u where u.groupName like CONCAT('%',:group,'%')")
	Collection<User> findByPartialGroupName(@Param("group") String group);
}
