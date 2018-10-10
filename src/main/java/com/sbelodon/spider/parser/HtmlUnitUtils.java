package com.sbelodon.spider.parser;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitUtils {
	public static String getAttributeByXPath(HtmlPage page,String xpathExpr, String attribute){
		HtmlElement element = (HtmlElement) page.getFirstByXPath(xpathExpr);
		if(element == null)  {
			return null;
		}
		return element.getAttribute(attribute);
	}
	@SuppressWarnings("unchecked")
	public static List<String> getAttributesByXPath(HtmlPage page,String xpathExpr, String attribute){
		List<HtmlElement> elements = (List<HtmlElement>) page.getByXPath(xpathExpr);
		if(elements == null)  {
			return null;
		}
		List<String> result = new ArrayList<String>(elements.size());
		for(HtmlElement element:elements) {
			result.add(element.getAttribute(attribute));
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static List<String> getTectContentByXPath(HtmlPage page,String xpathExpr){
		List<HtmlElement> elements = (List<HtmlElement>) page.getByXPath(xpathExpr);
		if(elements == null)  {
			return null;
		}
		List<String> result = new ArrayList<String>(elements.size());
		for(HtmlElement element:elements) {
			result.add(element.getTextContent());
		}
		return result;
	}

}
