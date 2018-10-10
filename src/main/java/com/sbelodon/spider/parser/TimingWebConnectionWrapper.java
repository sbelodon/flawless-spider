package com.sbelodon.spider.parser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.gargoylesoftware.htmlunit.WebConnection;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper;

public class TimingWebConnectionWrapper extends WebConnectionWrapper{
	private LinkedHashMap<Pair<WebRequest,WebResponse>,Long> timings = new LinkedHashMap<Pair<WebRequest,WebResponse>,Long>();
	public TimingWebConnectionWrapper(WebConnection webConnection) throws IllegalArgumentException {
		super(webConnection);
	}

	@Override
	public WebResponse getResponse(final WebRequest request) throws IOException {
		long start = System.currentTimeMillis();
		WebResponse response =super.getResponse(request);
		long end = System.currentTimeMillis();
		timings.put(new ImmutablePair<WebRequest,WebResponse>(request,response), end-start);
		return response;
    }

	public LinkedHashMap<Pair<WebRequest,WebResponse>, Long> getTimings() {
		return timings;
	}


}
