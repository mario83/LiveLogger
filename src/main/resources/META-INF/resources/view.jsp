<%@ include file="./init.jsp" %>


<div class="panel">
	<div class="panel-heading">
		<aui:input
			name="filter"
			label="logger-filter"
			value=""
			required="false"
			onkeyup="websocketModule.websocketSend(this.value)"
		/>
		<aui:button-row>
			<aui:button value="logger-clear" onClick="$('#log-container').html('')"/>
			<aui:button value="logger-copy" onClick="copyToClipboard()"/>		
		</aui:button-row>
	</div>
	
	<div class="panel-body">
		<div id="log-container"></div>
	</div>
</div>

<aui:script>
;(function (A) {
	var handle = Liferay.on('destroyPortlet', function (event) {
		if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
			websocketModule.websocketClose();
			handle.detach();
		}
	});

}(AUI()));
</aui:script>
