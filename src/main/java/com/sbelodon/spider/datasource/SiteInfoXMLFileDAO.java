package com.sbelodon.spider.datasource;

import java.io.File;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.sbelodon.spider.dataobject.SiteDO;

public class SiteInfoXMLFileDAO implements SiteInfoDAO<SiteDO> {
	private File file;

	public void saveInfo(SiteDO siteDO) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(SiteDO.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(siteDO, file);
	}

	public SiteDO readInfo() throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(SiteDO.class);
		return (SiteDO)jaxbContext.createUnmarshaller().unmarshal(new FileReader(file));
	}

	public void setDataSource(Object dataSource) throws Exception {
		if (dataSource instanceof File) {
			file = (File) dataSource;
		} else {
			throw new Exception("Wrong Data Source!");
		}

	}

}
