package it.manza.websocket.bean;

import javax.websocket.RemoteEndpoint;
import javax.websocket.RemoteEndpoint.Basic;

/**
 * @author mario anzà
 *
 */
public class LoggerConfig {

	private RemoteEndpoint.Basic remoteEndpoint;
	private String filter;
	
	public LoggerConfig(Basic remoteEndpoint) {
		this.remoteEndpoint = remoteEndpoint;
	}

	public RemoteEndpoint.Basic getRemoteEndpoint() {

		return remoteEndpoint;
	}
	
	public void setRemoteEndpoint(RemoteEndpoint.Basic remoteEndpoint) {

		this.remoteEndpoint = remoteEndpoint;
	}
	
	public String getFilter() {

		return filter;
	}
	
	public void setFilter(String filter) {

		this.filter = filter;
	}
}
