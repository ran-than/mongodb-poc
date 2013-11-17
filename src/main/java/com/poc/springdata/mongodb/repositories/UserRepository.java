package com.poc.springdata.mongodb.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.poc.springdata.mongodb.documents.EmailAddress;
import com.poc.springdata.mongodb.documents.User;

/**
 * @author Ran.ga.na.than
 * 
 */
public interface UserRepository extends Repository<User, Long> {

	/**
	 * Returns the {@link User} with the given {@link EmailAddress}.
	 * 
	 * @param string
	 * @return the {@link User}
	 */
	User findByEmailAddress(EmailAddress emailAddress);
	
	/**
	 * Returns the {@link List} of all the {@link User}s in DB.
	 * 
	 * @return
	 */
	public List<User> findUsers();
	
	/**
	 * Saves the {@link User} in DB.
	 * 
	 * @return
	 */
	public User save(User user);
	
	/**
	 * Updates the {@link User} in DB.
	 * 
	 * @return
	 */
	public User update(User user);
	
	/**
	 * Removes the {@link User} in DB.
	 */
	public void remove(User user);
}
