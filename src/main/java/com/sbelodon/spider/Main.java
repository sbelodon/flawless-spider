package com.sbelodon.spider;

import com.sbelodon.spider.controller.MainController;
import com.sbelodon.spider.dataobject.SiteDO;
import com.sbelodon.spider.datasource.DataSourceFactory;
import com.sbelodon.spider.datasource.SiteInfoDAO;
import com.sbelodon.spider.datasource.SiteInfoXMLFileDAO;
import com.sbelodon.spider.model.MainModel;
import com.sbelodon.spider.parser.SiteParser;
import com.sbelodon.spider.parser.SiteParserFactory;
import com.sbelodon.spider.view.MainView;
import com.sbelodon.spider.view.StageAwareRenderer;

import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		SiteInfoDAO<SiteDO> siteInfoDAO = new SiteInfoXMLFileDAO();
		SiteParser siteParser = SiteParserFactory.getSiteParser();
		MainModel mainModel = new MainModel();
		DataSourceFactory dataSourceFactory = new DataSourceFactory();
		final MainController mainController = new MainController(mainModel, siteParser, siteInfoDAO, dataSourceFactory);
		StageAwareRenderer mainView = new MainView(mainModel, mainController);
		mainView.render(primaryStage);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
