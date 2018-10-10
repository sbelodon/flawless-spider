package com.sbelodon.spider.datasource;

import java.io.File;

public class DataSourceFactory {
	public Object getDataSource(String uri) {
		return new  File(uri);
	}
}
