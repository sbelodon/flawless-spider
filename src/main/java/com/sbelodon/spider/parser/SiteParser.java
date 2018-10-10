package com.sbelodon.spider.parser;

import com.sbelodon.spider.SiteRequestInfo;
import com.sbelodon.spider.dataobject.SiteDO;

public interface SiteParser {
	public SiteDO parse(SiteRequestInfo info) throws Exception;
}
