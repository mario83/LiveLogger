package it.manza.websocket.endpoint;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import it.manza.websocket.bean.LoggerConfig;

/**
 * @author mario anzà
 *
 */
@Component(
    immediate = true,
    property = {
    	"org.osgi.http.websocket.endpoint.path=/o/websocket/logger"
    },
    service = Endpoint.class
)
public class LoggerWebSocketEndpoint extends Endpoint {
    @Override
    public void onOpen(final Session session, EndpointConfig endpointConfig) {
    	
    	_log.info("onOpen "+session.getId());
		remoteEndpoints.put(
			session.getId(), new LoggerConfig(session.getBasicRemote()));
		
		session.addMessageHandler(
            new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String text) {

                	_log.debug("onMessage " +text);
					LoggerConfig loggerConfig =
						remoteEndpoints.get(session.getId());
					loggerConfig.setFilter(text);
					remoteEndpoints.put(session.getId(), loggerConfig);
                }
            });
    }
    
    @Override
    public void onClose(Session session, CloseReason closeReason) {
    	
    	_log.info("onClose "+session.getId());
    	remoteEndpoints.remove(session.getId());
    }
    
    public static void sendBroadcast(String text){
    	Set<String> keySet = remoteEndpoints.keySet();
		
		for (String string : keySet) {
			try {
				LoggerConfig loggerConfig = remoteEndpoints.get(string);
				if(Validator.isNotNull(loggerConfig.getFilter())){
					if(text.contains(loggerConfig.getFilter())){
						loggerConfig.getRemoteEndpoint().sendText(text);						
					}
				} else {
					loggerConfig.getRemoteEndpoint().sendText(text);
				}
			}
			catch (IOException e) {
				_log.error(e);
			}
		}
    }
    
	private static ConcurrentMap<String, LoggerConfig> remoteEndpoints =
		new ConcurrentHashMap<String, LoggerConfig>();
    
	private static final Log _log =
		LogFactoryUtil.getLog(LoggerWebSocketEndpoint.class);
}
