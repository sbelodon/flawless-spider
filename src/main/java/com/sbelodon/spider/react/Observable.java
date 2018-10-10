package com.sbelodon.spider.react;

public interface Observable<T> {
	public void notifyObservers();
	public void subscribe(Observer<T> obs);
}
