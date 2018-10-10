package com.sbelodon.spider.dataobject;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SiteDO {
	private String url;
	private String title;
	private String description;
	private Integer serverResponseCode;
	private Long serverResponseTime;
	private List<String> h1Headers;
	private List<String> images;
	private List<String> ahrefLinks;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getServerResponseCode() {
		return serverResponseCode;
	}

	public void setServerResponseCode(Integer serverResponseCode) {
		this.serverResponseCode = serverResponseCode;
	}

	public Long getServerResponseTime() {
		return serverResponseTime;
	}

	public void setServerResponseTime(Long serverResponseTime) {
		this.serverResponseTime = serverResponseTime;
	}

	public List<String> getH1Headers() {
		return h1Headers;
	}

	public void setH1Headers(List<String> h1Headers) {
		this.h1Headers = h1Headers;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<String> getAhrefLinks() {
		return ahrefLinks;
	}

	public void setAhrefLinks(List<String> ahrefLinks) {
		this.ahrefLinks = ahrefLinks;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
