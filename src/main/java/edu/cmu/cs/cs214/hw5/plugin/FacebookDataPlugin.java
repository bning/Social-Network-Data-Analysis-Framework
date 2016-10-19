package edu.cmu.cs.cs214.hw5.plugin;

/**
 * the Facebook data plugin interface
 * 
 * @author Zhiqi Xu
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.cs.cs214.hw5.framework.Post;
import edu.cmu.cs.cs214.hw5.framework.UserData;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Friendlist;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.auth.AccessToken;

/**
 * The Class FacebookDataPlugin.
 */
public class FacebookDataPlugin implements DataPlugin {
	private static final String PLUGIN_NAME = "Facebook";

	@Override
	public String getName() {
		return PLUGIN_NAME;
	}

	/**
	 * Function that can get one UserData given userName.
	 * 
	 * @param username
	 *          The user name of the user
	 * 
	 * @return an UserData object with useful info stored in it
	 */
	@Override
	public UserData getUserData(String userName) {
		UserData userData;
		Facebook facebook = new FacebookFactory().getInstance();
		userData = new UserData(userName, PLUGIN_NAME);
		try {
			AccessToken accessToken = facebook.getOAuthAppAccessToken();
			facebook.setOAuthAccessToken(accessToken);
			User user = facebook.getUser(userName);

			String userID = user.getId();
			int followers, followees;
			String flName = "flname";

			try {
				Friendlist friendlist = facebook.getFriendlist(flName);
				ResponseList<Friend> friendlistMembers = facebook
				    .getFriendlistMembers(friendlist.getId());
				followers = friendlistMembers.size();
				followees = friendlistMembers.size();
			} catch (FacebookException e) {
				followers = 0;
				followees = 0;
			}

			String userLocation;
			try {
				userLocation = user.getLocation().toString();
			} catch (NullPointerException e) {
				userLocation = "";
			}

			try {
				if (getPosts(facebook, userID).size() == 0) {
					userData.setErroMsg("error");
				}
				userData.setPostList(getPosts(facebook, userID));
				userData.setLocation(userLocation);
				userData.setFollowees(followees);
				userData.setFollowers(followers);
				userData.setId(userID);
				return userData;
			} catch (NullPointerException e) {
				userData.setErroMsg("error");
			}

		} catch (FacebookException e) {
			userData.setErroMsg("error");
		}
		return null;
	}

	// helper function: get posts for subject user
	/**
	 * Gets the posts.
	 *
	 * @param facebook
	 *          the facebook
	 * @param userID
	 *          the user id
	 * @return the posts
	 */
	public List<Post> getPosts(Facebook facebook, String userID) {

		List<Post> postList = new ArrayList<Post>();

		try {
			List<facebook4j.Post> fbPosts = facebook.getPosts(userID);
			for (facebook4j.Post post : fbPosts) {
				String content = post.getMessage();
				Date time = post.getCreatedTime();
				Post newPost = new Post(content, time);
				postList.add(newPost);

			}
		} catch (FacebookException e) {
			e.printStackTrace();
		}

		return postList;
	}

}
