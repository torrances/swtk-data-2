package org.swtk.data.imdb.json.dto;

public class RunnerContract {

	private String baseDirectory;
	
	private Integer max;
	
	private Integer min;

	public String getBaseDirectory() {
		return baseDirectory;
	}

	public Integer getMax() {
		return max;
	}

	public Integer getMin() {
		return min;
	}

	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	public void setMax(Integer max) {
		
		this.max = max;
	}

	public void setMin(Integer min) {
		this.min = min;
	}
}
