package com.sbelodon.spider.parser;

import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sbelodon.spider.SiteRequestInfo;
import com.sbelodon.spider.dataobject.SiteDO;

public class HtmlUnitSiteParser implements SiteParser {
	final static Logger logger = Logger.getLogger(HtmlUnitSiteParser.class);

	public SiteDO parse(SiteRequestInfo info) throws Exception {
		logger.debug("start parsing");
		WebClient webClient = null;
		SiteDO siteDO = new SiteDO();
		HtmlPage page = null;
		TimingWebConnectionWrapper timingWebConnectionWrapper = null;
		try {
			webClient = new WebClient(BrowserVersion.CHROME);
			timingWebConnectionWrapper = new TimingWebConnectionWrapper(webClient.getWebConnection());
			webClient.setWebConnection(timingWebConnectionWrapper);
			webClient.getOptions().setJavaScriptEnabled(false);
			page = webClient.getPage(info.getUrl());
			if (page != null) {
				siteDO.setTitle(page.getTitleText());
				siteDO.setDescription(HtmlUnitUtils.getAttributeByXPath(page, "//meta[@name='description']", "content"));
				siteDO.setH1Headers(HtmlUnitUtils.getTectContentByXPath(page, "//h1"));
				siteDO.setImages(HtmlUnitUtils.getAttributesByXPath(page, "//img", "src"));
				siteDO.setAhrefLinks(HtmlUnitUtils.getAttributesByXPath(page, "//a[@href]", "href"));
			}
		} catch (FailingHttpStatusCodeException e) {
			// its ok
		} finally {
			if (timingWebConnectionWrapper != null && !timingWebConnectionWrapper.getTimings().isEmpty()) {
				Entry<Pair<WebRequest, WebResponse>, Long> firstRequestEntry = timingWebConnectionWrapper.getTimings()
						.entrySet().iterator().next();
				siteDO.setServerResponseTime(firstRequestEntry.getValue());
				siteDO.setServerResponseCode(firstRequestEntry.getKey().getRight().getStatusCode());
			}
			if (webClient != null) {
				webClient.closeAllWindows();
			}
		}
		logger.debug("end parsing");
		return siteDO;
	}
}
