package com.sbelodon.spider.model;

import java.util.LinkedList;
import java.util.List;

import com.sbelodon.spider.dataobject.SiteDO;
import com.sbelodon.spider.react.Observable;
import com.sbelodon.spider.react.Observer;

public class MainModel implements Observable<SiteDO>{
	List<Observer<SiteDO>> observers = new LinkedList<Observer<SiteDO>>();
	private SiteDO siteDO = new SiteDO();

	public SiteDO getSiteDO() {
		return siteDO;
	}

	public void setSiteDO(SiteDO siteDO) {
		this.siteDO = siteDO;
		notifyObservers();
	}

	public void notifyObservers() {
		for(Observer<SiteDO> observer:observers) {
			observer.onValueChange(siteDO);
		}
	}

	public void subscribe(Observer<SiteDO> obs) {
		if(obs != null) {
			observers.add(obs);
			obs.onValueChange(siteDO);
		}
	}
	
}
