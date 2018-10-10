package com.sbelodon.spider.controller;

import com.sbelodon.spider.SiteRequestInfo;
import com.sbelodon.spider.dataobject.SiteDO;
import com.sbelodon.spider.datasource.DataSourceFactory;
import com.sbelodon.spider.datasource.SiteInfoDAO;
import com.sbelodon.spider.model.MainModel;
import com.sbelodon.spider.parser.SiteParser;

public class MainController {
	private MainModel mainModel;
	private SiteParser siteParser;
	private SiteInfoDAO<SiteDO> siteInfoDAO;
	private DataSourceFactory dataSourceFactory;

	public MainController(MainModel mainModel, SiteParser siteParser,SiteInfoDAO<SiteDO> siteInfoDAO,  DataSourceFactory dataSourceFactory) {
		super();
		this.mainModel = mainModel;
		this.siteParser = siteParser;
		this.siteInfoDAO = siteInfoDAO;
		this.dataSourceFactory = dataSourceFactory;
	}

	public void updateSiteInfo(SiteRequestInfo info) throws Exception {
		SiteDO dataObject = siteParser.parse(info);
		mainModel.setSiteDO(dataObject);
	}

	public void saveToDataSource(SiteDO siteDO, String uri) throws Exception {
		siteInfoDAO.setDataSource(dataSourceFactory.getDataSource(uri));
		siteInfoDAO.saveInfo(siteDO);
	}

	public SiteDO loadFromDataSource(String uri) throws Exception {
		siteInfoDAO.setDataSource(dataSourceFactory.getDataSource(uri));
		return siteInfoDAO.readInfo();
	}

}
