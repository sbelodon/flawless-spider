package com.sbelodon.spider.parser;

public class SiteParserFactory {
	public static SiteParser getSiteParser() {
		return new HtmlUnitSiteParser();
	}
}
