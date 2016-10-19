package edu.cmu.cs.cs214.hw5.plugin;

/**
 * the Twitter data plugin interface
 * 
 * @author Zhiqi Xu
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import edu.cmu.cs.cs214.hw5.framework.Post;
import edu.cmu.cs.cs214.hw5.framework.UserData;

/**
 * The Class TwitterDataPlugin.
 */
public class TwitterDataPlugin implements DataPlugin {

	private static final String PLUGIN_NAME = "Twitter";
	private static final int MAX_POST_NUM = 200;
	private static final String INFO_QUERY = "/users/lookup";
	private static final String TIMELINE_QUERY = "/statuses/user_timeline";

	/** The Constant SEARCH_QUERY. */
	private static final String SEARCH_QUERY = "/search/tweets";

	@Override
	public String getName() {

		return PLUGIN_NAME;
	}

	@Override
	public UserData getUserData(String userName) {
		Twitter twitter = TwitterFactory.getSingleton();
		int infoUserLimitStatus = getUserLimitStatus(twitter, INFO_QUERY);
		int searchUserLimitStatus = getUserLimitStatus(twitter, SEARCH_QUERY);
		int timeLineUserLimitStatus = getUserLimitStatus(twitter, TIMELINE_QUERY);
		UserData userData = new UserData(userName, PLUGIN_NAME);
		if (searchUserLimitStatus < 0 || timeLineUserLimitStatus < 0
		    || infoUserLimitStatus < 0) {
			userData.setErroMsg("Hit rate limit!");
			return userData;
		}
		try {

			if (searchUserLimitStatus > 0) {

				User user = twitter.showUser(userName);
				if (infoUserLimitStatus > 0) {
					String userID = Long.toString(user.getId());
					String userLocation = user.getLocation();
					int followers = user.getFollowersCount();
					int followees = user.getFriendsCount();

					userData.setLocation(userLocation);
					userData.setId(userID);
					userData.setFollowers(followers);
					userData.setFollowees(followees);

					if (timeLineUserLimitStatus > 0) {
						List<Post> postList = getPosts(twitter, user.getId());

						if (postList.size() == 0 || postList == null) {
							userData.setErroMsg("The user: "+userName+" has no posts!");
							return null;
						}
						for (Post post : postList) {
							userData.addPost(post);
						}
					}
					return userData;
				}
			}
		} catch (TwitterException e) {
			userData.setErroMsg("The user: "+userName+"is invalid!");
		}
		return null;
	}

	private int getUserLimitStatus(Twitter twitter, String queryName) {
		try {
			int remainTime = twitter.getRateLimitStatus().get(queryName)
			    .getRemaining();
			return remainTime;
		} catch (TwitterException e) {
			return -1;
		}
	}

	// helper function: get posts for subject user
	/**
	 * Gets the posts.
	 *
	 * @param twitter
	 *          the twitter
	 * @param userID
	 *          the user id
	 * @return the posts
	 */
	public List<Post> getPosts(Twitter twitter, long userID) {
		List<Status> statusList = new ArrayList<Status>();
		List<Post> postList = new ArrayList<Post>();
		Paging page = new Paging(1, MAX_POST_NUM);

		try {
			statusList.addAll(twitter.getUserTimeline(userID, page));

		} catch (TwitterException e) {
			return null;
		}
		for (Status status : statusList) {
			String statusContent = status.getText();
			Date statusTime = status.getCreatedAt();
			Post newPost = new Post(statusContent, statusTime);
			postList.add(newPost);

		}
		return postList;
	}

}
