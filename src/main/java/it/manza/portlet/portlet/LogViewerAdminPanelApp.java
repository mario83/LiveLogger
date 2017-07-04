package it.manza.portlet.portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

/**
 * @author mario anzà
 *
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=300",
		"panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_CONFIGURATION
	},
	service = PanelApp.class
)
public class LogViewerAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return PortletKeys.LOGVIEWER_ADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + PortletKeys.LOGVIEWER_ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}
