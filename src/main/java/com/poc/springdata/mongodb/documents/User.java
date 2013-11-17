package com.poc.springdata.mongodb.documents;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;

/**
 * A User.
 {
    name: "John",
    email: "john@email.com",
    technologies: [
        {name:"java",exp:"expert"},
        {name:"javascript",exp:"intermediate"},
        {name:"mongodb",exp:"beginner"}
        ]
 }
 * @author Ran.ga.na.than
 *
 */
@Document
public class User extends AbstractDocument{
	private String name;
	
	@Field("email")
	@Indexed(unique = true)
	private EmailAddress email;
	
	private Set<Tech> technologies = new HashSet<Tech>();

	/**
	 * Creates a new {@link User} from the given name and {@link EmailAddress}.
	 * 
	 * @param name must not be {@literal null} or empty.
	 * @param email must not be {@literal null} or empty.
	 */
	public User(String name, EmailAddress email) {

		Assert.hasText(name);
        Assert.notNull(email);
        
		this.name = name;
		this.email = email;
	}
	
	protected User() {
	}
	
	 /**
     * Adds the given {@link Tech} to the {@link User}.
     * 
     * @param tech must not be {@literal null}.
     */
    public void addTechnology(Tech tech) {
            Assert.notNull(tech);
            this.technologies.add(tech);
    }
    
    /**
     * Return the {@link User}'s technologies.
     * 
     * @return {@link Set} of {@link Tech}s
     */
    public Set<Tech> getTechnologies() {
            return Collections.unmodifiableSet(technologies);
    }
    
    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	public EmailAddress getEmail() {
		return email;
	}
	
}
