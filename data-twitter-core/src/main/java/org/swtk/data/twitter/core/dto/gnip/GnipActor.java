package org.swtk.data.twitter.core.dto.gnip;

import com.google.gson.annotations.Expose;

public class GnipActor {

	@Expose
	private String objectType;

	@Expose
	private String id;

	@Expose
	private String link;

	@Expose
	private String postedTime;

	@Expose
	private String image;

	@Expose
	private String summary;

	@Expose
	private Integer friendsCount;

	@Expose
	private Integer followersCount;

	@Expose
	private Integer listedCount;

	@Expose
	private Integer statusesCount;

	@Expose
	private String twitterTimeZone;

	@Expose
	private boolean verified;

	@Expose
	private String[] languagues;

	@Expose
	private String utfOffset;

	@Expose
	private String preferredUsername;

	@Expose
	private Integer favoritesCount;

	public Integer getFavoritesCount() {
		return favoritesCount;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public Integer getFriendsCount() {
		return friendsCount;
	}

	public String getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String[] getLanguagues() {
		return languagues;
	}

	public String getLink() {
		return link;
	}

	public Integer getListedCount() {
		return listedCount;
	}

	public String getObjectType() {
		return objectType;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public String getPreferredUsername() {
		return preferredUsername;
	}

	public Integer getStatusesCount() {
		return statusesCount;
	}

	public String getSummary() {
		return summary;
	}

	public String getTwitterTimeZone() {
		return twitterTimeZone;
	}

	public String getUtfOffset() {
		return utfOffset;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setFavoritesCount(Integer favoritesCount) {
		this.favoritesCount = favoritesCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public void setFriendsCount(Integer friendsCount) {
		this.friendsCount = friendsCount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setLanguagues(String[] languagues) {
		this.languagues = languagues;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setListedCount(Integer listedCount) {
		this.listedCount = listedCount;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}

	public void setPreferredUsername(String preferredUsername) {
		this.preferredUsername = preferredUsername;
	}

	public void setStatusesCount(Integer statusesCount) {
		this.statusesCount = statusesCount;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setTwitterTimeZone(String twitterTimeZone) {
		this.twitterTimeZone = twitterTimeZone;
	}

	public void setUtfOffset(String utfOffset) {
		this.utfOffset = utfOffset;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
