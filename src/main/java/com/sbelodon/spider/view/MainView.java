package com.sbelodon.spider.view;

import java.io.File;

import org.apache.log4j.Logger;

import com.sbelodon.spider.SiteRequestInfo;
import com.sbelodon.spider.controller.MainController;
import com.sbelodon.spider.dataobject.SiteDO;
import com.sbelodon.spider.model.MainModel;
import com.sbelodon.spider.react.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.*;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class MainView implements StageAwareRenderer, Observer<SiteDO> {
	private static final String title = "Site Info";
	private static final String defaultUrl = "https://netpeaksoftware.com/";
	final static Logger logger = Logger.getLogger(MainView.class);
	private MainModel mainModel;
	private MainController mainController;

	private TextField urlField = new TextField(defaultUrl);
	private Text titleControl = new Text();
	private Text descriptionControl = new Text();
	private Text serverResponseControl = new Text();
	private Text responseTimeControl = new Text();
	private ListView<String> h1HeadersControl = new ListView<String>();
	private ListView<String> imagesControl = new ListView<String>();
	private ListView<String> ahrefsControl = new ListView<String>();
	private Button updateButton = new Button("Update");
	private Button saveButton = new Button("Save to file");
	private Button loadButton = new Button("Load from file");
	final FileChooser fileChooser = new FileChooser();

	public MainView(MainModel mainModel, MainController mainController) {
		super();
		this.mainModel = mainModel;
		this.mainController = mainController;
		this.mainModel.subscribe(this);
	}

	public void render(final Stage primaryStage) throws Exception {
		GridPane gridPane = new GridPane();
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String url = urlField.getText();
				SiteRequestInfo info = new SiteRequestInfo(url);
				try {
					mainController.updateSiteInfo(info);
					primaryStage.setTitle(title + " " + url);
					showUpdatedAlert();
				} catch (Exception e) {
					onError(e);
				}
			}
		});
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				File file = fileChooser.showSaveDialog(primaryStage);
				if (file != null) {
					SiteDO siteDO = new SiteDO();
					siteDO.setUrl(urlField.getText());
					siteDO.setTitle(titleControl.getText());
					siteDO.setDescription(descriptionControl.getText());
					siteDO.setServerResponseCode(Integer.valueOf(serverResponseControl.getText()));
					siteDO.setServerResponseTime(Long.valueOf(responseTimeControl.getText()));
					siteDO.setH1Headers(h1HeadersControl.getItems());
					siteDO.setImages(imagesControl.getItems());
					siteDO.setAhrefLinks(ahrefsControl.getItems());
					String path = file.getPath();
					logger.debug("path to save: " + path);
					try {
						mainController.saveToDataSource(siteDO, path);
						showSavedAlert();
					} catch (Exception e) {
						onError(e);
					}

				}

			}
		});
		loadButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					String path = file.getPath();
					logger.debug("path to load: " + path);
					SiteDO siteDO;
					try {
						siteDO = mainController.loadFromDataSource(path);
						onValueChange(siteDO);
						primaryStage.setTitle(title + " " + path);
					} catch (Exception e) {
						onError(e);
					}

				}
			}
		});
		gridPane.add(new Text("Url: "), 0, 0);
		gridPane.add(urlField, 1, 0);
		gridPane.add(new Text("Title: "), 0, 1);
		gridPane.add(titleControl, 1, 1);
		gridPane.add(new Text("Description: "), 0, 2);
		gridPane.add(descriptionControl, 1, 2);
		gridPane.add(new Text("Server Response: "), 0, 3);
		gridPane.add(serverResponseControl, 1, 3);
		gridPane.add(new Text("Response Time (milliseconds): "), 0, 4);
		gridPane.add(responseTimeControl, 1, 4);
		gridPane.add(new Text("h1 headers: "), 0, 5);
		gridPane.add(h1HeadersControl, 1, 5);
		gridPane.add(new Text("Images: "), 0, 6);
		gridPane.add(imagesControl, 1, 6);
		gridPane.add(new Text("AHREF Links: "), 0, 7);
		gridPane.add(ahrefsControl, 1, 7);
		GridPane buttonPane = new GridPane();
		buttonPane.add(updateButton, 0, 0);
		buttonPane.add(saveButton, 1, 0);
		buttonPane.add(loadButton, 2, 0);
		initGaps(buttonPane);
		gridPane.add(buttonPane, 1, 8);
		initGaps(gridPane);

		Scene scene = new Scene(gridPane, 1000, 500);
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initGaps(GridPane buttonPane) {
		buttonPane.setHgap(10);
		buttonPane.setVgap(10);
		buttonPane.setPadding(new Insets(10, 10, 10, 10));
	}

	public void onValueChange(SiteDO siteDO) {
		logger.debug("onValueChange");
		titleControl.setText(siteDO.getTitle());
		descriptionControl.setText(siteDO.getDescription());
		serverResponseControl.setText(ViewUtils.nullSafe(siteDO.getServerResponseCode()));
		responseTimeControl.setText(ViewUtils.nullSafe(siteDO.getServerResponseTime()));
		h1HeadersControl.setItems(ViewUtils.nullSafe(siteDO.getH1Headers()));
		imagesControl.setItems(ViewUtils.nullSafe(siteDO.getImages()));
		ahrefsControl.setItems(ViewUtils.nullSafe(siteDO.getAhrefLinks()));

	}

	private void showUpdatedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION, "Info updated");
		alert.showAndWait();
	}

	private void showSavedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION, "Info saved");
		alert.showAndWait();
	}

	private void onError(Exception e) {
		e.printStackTrace();
		Alert alert = new Alert(AlertType.ERROR, "Error! " + e.getMessage());
		alert.showAndWait();
	}

}
