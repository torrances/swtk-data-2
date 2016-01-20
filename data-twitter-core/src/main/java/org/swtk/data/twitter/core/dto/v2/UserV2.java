package org.swtk.data.twitter.core.dto.v2;

import com.google.gson.annotations.Expose;

public class UserV2 {

	@Expose
	private Long id;

	@Expose
	private String idStr;

	@Expose
	private String name;

	@Expose
	private String screen_name;

	@Expose
	private String location;

	@Expose
	private String url;

	@Expose
	private String description;

	@Expose
	private Integer followers_count;

	@Expose
	private Integer friends_count;

	@Expose
	private Integer listed_count;

	@Expose
	private String created_at;

	@Expose
	private Integer favourites_count;

	@Expose
	private Integer utc_offset;

	@Expose
	private String time_zone;

	@Expose
	private boolean geo_enabled;

	@Expose
	private boolean verified;

	@Expose
	private Integer statuses_count;

	@Expose
	private String lang;

	@Expose
	private boolean contributors_enabled;

	@Expose
	private boolean is_translator;

	@Expose
	private String profile_background_color;

	@Expose
	private String profile_background_image_url;

	@Expose
	private String profile_background_image_url_https;

	@Expose
	private boolean profile_background_tile;

	@Expose
	private String profile_image_url;

	@Expose
	private String profile_image_url_https;

	@Expose
	private String profile_banner_url;

	@Expose
	private String profile_link_color;

	@Expose
	private String profile_sidebar_border_color;

	@Expose
	private String profile_sidebar_fill_color;

	@Expose
	private String profile_text_color;

	@Expose
	private boolean profile_use_background_image;

	@Expose
	private boolean default_profile;

	@Expose
	private boolean default_profile_image;

	@Expose
	private Integer following;

	@Expose
	private Integer follow_request_sent;

	@Expose
	private Integer notifications;

	public String getCreated_at() {
		return created_at;
	}

	public String getDescription() {
		return description;
	}

	public Integer getFavourites_count() {
		return favourites_count;
	}

	public Integer getFollow_request_sent() {
		return follow_request_sent;
	}

	public Integer getFollowers_count() {
		return followers_count;
	}

	public Integer getFollowing() {
		return following;
	}

	public Integer getFriends_count() {
		return friends_count;
	}

	public Long getId() {
		return id;
	}

	public String getIdStr() {
		return idStr;
	}

	public String getLang() {
		return lang;
	}

	public Integer getListed_count() {
		return listed_count;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public Integer getNotifications() {
		return notifications;
	}

	public String getProfile_background_color() {
		return profile_background_color;
	}

	public String getProfile_background_image_url() {
		return profile_background_image_url;
	}

	public String getProfile_background_image_url_https() {
		return profile_background_image_url_https;
	}

	public String getProfile_banner_url() {
		return profile_banner_url;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public String getProfile_image_url_https() {
		return profile_image_url_https;
	}

	public String getProfile_link_color() {
		return profile_link_color;
	}

	public String getProfile_sidebar_border_color() {
		return profile_sidebar_border_color;
	}

	public String getProfile_sidebar_fill_color() {
		return profile_sidebar_fill_color;
	}

	public String getProfile_text_color() {
		return profile_text_color;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public Integer getStatuses_count() {
		return statuses_count;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public String getUrl() {
		return url;
	}

	public Integer getUtc_offset() {
		return utc_offset;
	}

	public boolean isContributors_enabled() {
		return contributors_enabled;
	}

	public boolean isDefault_profile() {
		return default_profile;
	}

	public boolean isDefault_profile_image() {
		return default_profile_image;
	}

	public boolean isGeo_enabled() {
		return geo_enabled;
	}

	public boolean isIs_translator() {
		return is_translator;
	}

	public boolean isProfile_background_tile() {
		return profile_background_tile;
	}

	public boolean isProfile_use_background_image() {
		return profile_use_background_image;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setContributors_enabled(boolean contributors_enabled) {
		this.contributors_enabled = contributors_enabled;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setDefault_profile(boolean default_profile) {
		this.default_profile = default_profile;
	}

	public void setDefault_profile_image(boolean default_profile_image) {
		this.default_profile_image = default_profile_image;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFavourites_count(Integer favourites_count) {
		this.favourites_count = favourites_count;
	}

	public void setFollow_request_sent(Integer follow_request_sent) {
		this.follow_request_sent = follow_request_sent;
	}

	public void setFollowers_count(Integer followers_count) {
		this.followers_count = followers_count;
	}

	public void setFollowing(Integer following) {
		this.following = following;
	}

	public void setFriends_count(Integer friends_count) {
		this.friends_count = friends_count;
	}

	public void setGeo_enabled(boolean geo_enabled) {
		this.geo_enabled = geo_enabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public void setIs_translator(boolean is_translator) {
		this.is_translator = is_translator;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setListed_count(Integer listed_count) {
		this.listed_count = listed_count;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setNotifications(Integer notifications) {
		this.notifications = notifications;
	}

	public void setProfile_background_color(String profile_background_color) {
		this.profile_background_color = profile_background_color;
	}

	public void setProfile_background_image_url(String profile_background_image_url) {
		this.profile_background_image_url = profile_background_image_url;
	}

	public void setProfile_background_image_url_https(String profile_background_image_url_https) {
		this.profile_background_image_url_https = profile_background_image_url_https;
	}

	public void setProfile_background_tile(boolean profile_background_tile) {
		this.profile_background_tile = profile_background_tile;
	}

	public void setProfile_banner_url(String profile_banner_url) {
		this.profile_banner_url = profile_banner_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public void setProfile_image_url_https(String profile_image_url_https) {
		this.profile_image_url_https = profile_image_url_https;
	}

	public void setProfile_link_color(String profile_link_color) {
		this.profile_link_color = profile_link_color;
	}

	public void setProfile_sidebar_border_color(String profile_sidebar_border_color) {
		this.profile_sidebar_border_color = profile_sidebar_border_color;
	}

	public void setProfile_sidebar_fill_color(String profile_sidebar_fill_color) {
		this.profile_sidebar_fill_color = profile_sidebar_fill_color;
	}

	public void setProfile_text_color(String profile_text_color) {
		this.profile_text_color = profile_text_color;
	}

	public void setProfile_use_background_image(boolean profile_use_background_image) {
		this.profile_use_background_image = profile_use_background_image;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public void setStatuses_count(Integer statuses_count) {
		this.statuses_count = statuses_count;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUtc_offset(Integer utc_offset) {
		this.utc_offset = utc_offset;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
