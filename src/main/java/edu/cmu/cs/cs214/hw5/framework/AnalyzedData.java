package edu.cmu.cs.cs214.hw5.framework;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class AnalyzedData.
 */
public class AnalyzedData {

	/** The posts. */
	private ArrayList<Post> posts = new ArrayList<Post>();

	/** The followers. */
	private int followers;

	/** The followees. */
	private int followees;

	/** The location. */
	private String location;

	/** The name. */
	private String userName;

	/** The user data. */
	private UserData userData;

	/** The platform. */
	private String platform;

	/** The user id. */
	private String userID;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the user id.
	 */
	public void setUserID() {
		this.userID = userData.getId();
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
	 * Sets the platform.
	 */
	public void setPlatform() {
		this.platform = userData.getPlatform();
	}

	/** The words count. */
	private HashMap<String, Integer> wordsCount = new HashMap<String, Integer>();

	/** The non words count. */
	private HashMap<String, Integer> nonWordsCount = new HashMap<String, Integer>();

	/**
	 * Gets the words count.
	 *
	 * @return the words count
	 */
	public HashMap<String, Integer> getWordsCount() {
		return wordsCount;
	}

	/**
	 * Gets the non words count.
	 *
	 * @return the non words count
	 */
	public HashMap<String, Integer> getNonWordsCount() {
		return nonWordsCount;
	}

	/**
	 * Instantiates a new analyzed data.
	 *
	 * @param userData1
	 *          the user data1
	 */
	public AnalyzedData(UserData userData1) {
		this.userData = userData1;
		setFollowees();
		setFollowers();
		setLocation();
		setName();
		setPosts();
		setPlatform();
		setWordsCount();
		setUserID();

	}

	/**
	 * Sets the words count.
	 */
	private void setWordsCount() {
		ArrayList<String> punctuations = new ArrayList<String>();
		punctuations.add(".");
		punctuations.add(",");
		punctuations.add("://");
		punctuations.add("@");
		punctuations.add("#");
		punctuations.add("-");
		punctuations.add(";");
		punctuations.add("$");
		punctuations.add("!");
		punctuations.add("_");
		for (String s : punctuations) {
			nonWordsCount.put(s, 0);
		}

		for (Post p : posts) {
			if (p.getContent() != null) {
				String[] texts = p.getContent().split(" ");
				for (String text : texts) {
					if (text.matches("[a-zA-Z]+")) {
						text = text.toLowerCase();
						if (wordsCount.keySet().contains(text)) {
							wordsCount.put(text, wordsCount.get(text) + 1);
						} else {
							wordsCount.put(text, 1);
						}
					} else {
						for (String s : punctuations) {
							if (text.contains(s)) {
								nonWordsCount.put(s, nonWordsCount.get(s) + 1);
								break;
							}
						}
					}
				}
			}
		}
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
	 * Sets the posts.
	 */
	public void setPosts() {
		this.posts = userData.getPosts();
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
	 * Sets the followers.
	 */
	public void setFollowers() {
		this.followers = userData.getFollowers();
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
	 * Sets the followees.
	 */
	public void setFollowees() {
		this.followees = userData.getFollowees();
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
	 * Sets the location.
	 */
	public void setLocation() {
		this.location = userData.getLocation();
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
	 * Sets the name.
	 */
	public void setName() {
		this.userName = userData.getName();
	}

}
