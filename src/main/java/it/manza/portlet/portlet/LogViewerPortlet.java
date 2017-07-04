package it.manza.portlet.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author mario anzà
 *
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.css-class-wrapper=websocket-logger-portlet",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"javax.portlet.display-name=Log Viewer Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.name=it_manza_portlet_portlet_LogViewerPortlet",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class LogViewerPortlet extends MVCPortlet {
}