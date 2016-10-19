package edu.cmu.cs.cs214.hw5.framework;

import java.util.Date;

/**
 * The Class Post.
 */
public class Post {

	/** The content. */
	private String content;

	/** The date. */
	private Date date;

	/**
	 *
	 * @param content1
	 *            the content
	 * @param date1
	 *            the date
	 */
	public Post(String content1, Date date1) {
		this.content = content1;
		this.date = date1;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

}
