package org.swtk.data.twitter.core.dto.v2;

import com.google.gson.annotations.Expose;

public class TweetV2 {

	@Expose
	private String createdAt;

	@Expose
	private Long id;

	@Expose
	private String id_str;

	@Expose
	private UserV2 user;

	//	@Expose
	//	private List<Entity> entities;

	@Expose
	private String text;

	@Expose
	private String source;

	@Expose
	private boolean truncated;

	@Expose
	private long in_reply_to_status_id;

	@Expose
	private String in_reply_to_status_id_str;

	@Expose
	private Long in_reply_to_user_id;

	@Expose
	private String in_reply_to_user_id_str;

	@Expose
	private String in_reply_to_screen_name;

	public String getCreatedAt() {
		return createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getId_str() {
		return id_str;
	}

	//	public List<Entity> getEntities() {
	//		if (null == entities) setEntities(new ArrayList<Entity>());
	//		return entities;
	//	}

	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}

	public long getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}

	public String getIn_reply_to_status_id_str() {
		return in_reply_to_status_id_str;
	}

	public Long getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}

	public String getIn_reply_to_user_id_str() {
		return in_reply_to_user_id_str;
	}

	public String getSource() {
		return source;
	}

	public String getText() {
		return text;
	}

	public UserV2 getUser() {
		return user;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//	public void setEntities(List<Entity> entities) {
	//		this.entities = entities;
	//	}

	public void setId_str(String id_str) {
		this.id_str = id_str;
	}

	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}

	public void setIn_reply_to_status_id(long in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}

	public void setIn_reply_to_status_id_str(String in_reply_to_status_id_str) {
		this.in_reply_to_status_id_str = in_reply_to_status_id_str;
	}

	public void setIn_reply_to_user_id(Long in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}

	public void setIn_reply_to_user_id_str(String in_reply_to_user_id_str) {
		this.in_reply_to_user_id_str = in_reply_to_user_id_str;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public void setUser(UserV2 user) {
		this.user = user;
	}
}
