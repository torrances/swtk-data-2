package org.swtk.data.imdb.json.dto;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.CastMemberAdapter;

import com.google.gson.annotations.Expose;

public class CastMember {

	@Expose
	private String	character;
	
	@Expose
	private boolean	credited;
	
	@Expose
	private String	name;
	
	@Expose
	private String	role;

	public String getCharacter() {
		return character;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public boolean isCredited() {
		return credited;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public void setCredited(boolean credited) {
		this.credited = credited;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	@Ignore
	public String toString() {
		return CastMemberAdapter.toTsv(this);
	}
}
