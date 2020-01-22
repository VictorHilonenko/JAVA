function showStatus(status, code, message) {
	if ($("#message_block" ).length) {
	    $("#message_block").css(status);
	    $("#message_block").text(message);
	    $("#message_block").show();
	    //(!!!) then turn fading
	} else {
		if(status == "success") {
			alert(status+" : " + message);
		} else {
			alert(status+" ("+code+") : " + message);
		}
	}
}
