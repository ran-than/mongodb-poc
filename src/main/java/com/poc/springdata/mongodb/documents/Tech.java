package com.poc.springdata.mongodb.documents;

import org.springframework.util.Assert;

/**
 * A technology. E.g: {name:"java",exp:"expert"}
 * 
 * @author Ran.ga.na.than
 * 
 */
public class Tech {
	private final String name;
	private final String exp;

	/**
	 * Creates a new {@link Tech} from the given name, and experience.
	 * 
	 * @param name
	 *            must not be {@literal null} or empty.
	 * @param exp
	 *            must not be {@literal null} or empty.
	 */
	public Tech(String name, String exp) {

		Assert.hasText(name, "Street must not be null or empty!");
		Assert.hasText(exp, "Street must not be null or empty!");

		this.name = name;
		this.exp = exp;
	}

	/**
	 * Returns a copy of the current {@link Tech} instance which is a new entity
	 * in terms of persistence.
	 * 
	 * @return a copy of {@link Tech}
	 */
	public Tech getCopy() {
		return new Tech(this.name, this.exp);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the exp
	 */
	public String getExp() {
		return exp;
	}
	
}
