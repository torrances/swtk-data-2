package org.swtk.data.imdb.mda.dto;

public class InstanceTemplateContract {

	private String content;
	
	private String filename;

	private String className;

	private String pkgSuffix;
	
	private String targetFilePathSuffix;

	public String getClassName() {
		return className;
	}

	public String getContent() {
		return content;
	}

	public String getFilename() {
		return filename;
	}

	public String getPkgSuffix() {
		return pkgSuffix;
	}

	public String getTargetFilePathSuffix() {
		return targetFilePathSuffix;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void setPkgSuffix(String pkgSuffix) {
		this.pkgSuffix = pkgSuffix;
	}
	
	public void setTargetFilePathSuffix(String targetFilePathSuffix) {
		this.targetFilePathSuffix = targetFilePathSuffix;
	}	
}
