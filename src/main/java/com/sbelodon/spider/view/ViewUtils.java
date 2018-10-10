package com.sbelodon.spider.view;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@SuppressWarnings("restriction")
public class ViewUtils {
	
	public static <T> ObservableList<T> nullSafe(List<T> obj) {
		if (obj == null) {
			return FXCollections.observableArrayList(new ArrayList<T>());
		}
		return FXCollections.observableArrayList(obj);
	}

	public static String nullSafe(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

}
