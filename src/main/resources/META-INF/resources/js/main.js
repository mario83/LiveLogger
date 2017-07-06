var websocketModule = (function () {
	
	var wsUri = 'ws://' +window.location.host+ '/o/websocket/logger';
	if(window.location.protocol == "https:"){
		wsUri = 'wss://' +window.location.host+ '/o/websocket/logger';		
	}
	
	var websocket = new WebSocket(wsUri);
	
	websocket.onmessage = function(event) {
	  $('#log-container').append('<div>' + event.data + '</div>');
	  $("#log-container").scrollTop($("#log-container").prop('scrollHeight'));
	};
	
	return {
		websocketSend: function(text) {
			websocket.send(text);	        
		},
		websocketClose: function() {
			websocket.close();	        
		}
	}
})();

function copyToClipboard() {
  var $temp = $("<textarea>");
  $("body").append($temp);
  $temp.val($("#log-container").text()).select();
  document.execCommand("copy");
  $temp.remove();
}