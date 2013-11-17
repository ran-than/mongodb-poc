package com.poc.springdata.mongodb.repositories;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.poc.springdata.mongodb.documents.EmailAddress;
import com.poc.springdata.mongodb.documents.User;

/**
 * {@link UserRepository} implementation using the Spring Data
 * {@link MongoOperations} API.
 * 
 * @author Ran.ga.na.than
 * 
 */
@Repository
@Profile("mongodb")
public class MongoDbUserRepository implements UserRepository {

	private final MongoOperations operations;

	/**
	 * Creates a new {@link MongoDbUserRepository} using the given
	 * {@link MongoOperations}.
	 * 
	 * @param operations
	 *            must not be {@literal null}.
	 */
	@Autowired
	public MongoDbUserRepository(MongoOperations operations) {

		Assert.notNull(operations);
		this.operations = operations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.poc.springdata.mongodb.repositories.UserRepository#findOne(java.lang
	 * .Long)
	 */
	public User findOne(Long id) {
		Query query = query(where("id").is(id));
		return operations.findOne(query, User.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.poc.springdata.mongodb.repositories.UserRepository#save(com.poc.
	 * springdata.mongodb.documents.User)
	 */
	public User save(User user) {
		operations.save(user);
		return user;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.poc.springdata.mongodb.repositories.UserRepository#remove()
	 */
	public void remove(User user) {
		operations.remove(user);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.poc.springdata.mongodb.repositories.UserRepository#update(com.poc
	 * .springdata.mongodb.documents.User)
	 */
	public User update(User user) {
		Query query = query(where("id").is(user.getId()));
		Update update = new Update();
		update.set("name", user.getName());
		update.set("email", user.getEmail());
		operations.updateFirst(query, update, User.class);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.poc.springdata.mongodb.repositories.UserRepository#findByEmailAddress
	 * (com.poc.springdata.mongodb.documents.EmailAddress)
	 */
	public User findByEmailAddress(EmailAddress emailAddress) {
		Query query = query(where("emailAddress").is(emailAddress));
		return operations.findOne(query, User.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.poc.springdata.mongodb.repositories.UserRepository#findUsers()
	 */
	public List<User> findUsers() {
		return operations.findAll(User.class);
	}
}
