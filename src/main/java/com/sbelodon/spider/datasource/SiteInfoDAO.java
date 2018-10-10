package com.sbelodon.spider.datasource;

public interface SiteInfoDAO <DO>{
	public void setDataSource(Object dataSource)throws Exception;
	public void saveInfo(DO siteDO) throws Exception;
	public DO readInfo() throws Exception;
	
}
