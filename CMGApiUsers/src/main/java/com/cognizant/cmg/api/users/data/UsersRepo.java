package com.cognizant.cmg.api.users.data;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<UserEntity, Long> {

	UserEntity findByUserName(String userName);
}
