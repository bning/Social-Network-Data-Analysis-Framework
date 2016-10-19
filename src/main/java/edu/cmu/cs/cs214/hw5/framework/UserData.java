package edu.cmu.cs.cs214.hw5.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserData.
 */
public class UserData {

	/** The user name. */
	private String userName;

	/** The user ID. */
	private String userID;

	/** The platform. */
	private String platform;

	/** The location. */
	private String location;

	/** The posts. */
	private ArrayList<Post> posts;

	/** The followers. */
	private int followers;

	/** The followees. */
	private int followees;

	/** The error msg string. */
	private String erroMsg;

	/**
	 * Gets the error msg string.
	 *
	 * @return the error msg string
	 */
	public String getErroMsg() {
		return erroMsg;
	}

	/**
	 * Sets the erro msg string.
	 *
	 * @param erroMsgString1
	 *          the new erro msg string
	 */
	public void setErroMsg(String erroMsgString1) {
		this.erroMsg = erroMsgString1;
	}

	/**
	 * Instantiates a new user data.
	 *
	 * @param userName1
	 *          the user name
	 * @param platform1
	 *          the platform
	 */
	public UserData(String userName1, String platform1) {
		this.userName = userName1;
		this.platform = platform1;
		location = null;
		posts = new ArrayList<Post>();
		followers = -1;
		followees = -1;
	}

	/**
	 * Gets the platform.
	 *
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * Gets the posts.
	 *
	 * @return the posts
	 */
	public ArrayList<Post> getPosts() {
		return posts;
	}

	/**
	 * Gets the followees.
	 *
	 * @return the followees
	 */
	public int getFollowees() {
		return followees;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {

		return location;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return userName;
	}

	/**
	 * Gets the followers.
	 *
	 * @return the followers
	 */
	public int getFollowers() {
		return followers;
	}

	/**
	 * Sets the location.
	 *
	 * @param userLocation
	 *          the new location
	 */
	public void setLocation(String userLocation) {
		if (userLocation == null) {
			System.out.println("The input location is null!");
			return;
		}
		this.location = userLocation;

	}

	/**
	 * Sets the followers.
	 *
	 * @param followers2
	 *          the new followers
	 */
	public void setFollowers(int followers2) {
		this.followers = followers2;

	}

	/**
	 * Sets the followees.
	 *
	 * @param followees2
	 *          the new followees
	 */
	public void setFollowees(int followees2) {
		this.followees = followees2;

	}

	/**
	 * Adds the post.
	 *
	 * @param post
	 *          the post
	 */
	public void addPost(Post post) {
		if (post == null) {
			System.out.println("The input post is null!");
			return;
		}
		posts.add(post);

	}

	/**
	 * Sets the post list.
	 *
	 * @param postList
	 *          the new post list
	 */
	public void setPostList(List<Post> postList) {
		for (Post p : postList) {
			addPost(p);
		}
	}

	/**
	 * Sets the id.
	 *
	 * @param userID1
	 *          the new id
	 */
	public void setId(String userID1) {
		this.userID = userID1;

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return userID;
	}

}
