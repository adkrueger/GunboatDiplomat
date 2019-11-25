function refreshVidSegListAdmin() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", listVidSegs_url, true);
	xhr.send();
	console.log("sent");

	xhr.onloadend = function() {
		if(xhr.readyState == XMLHttpRequest.DONE) {
			processVidSegListResponseAdmin(xhr.responseText);
		}
		else {
			processVidSegListResponseAdmin("N/A");
		}
	};
}

function processVidSegListResponseAdmin(response) {

	let js = JSON.parse(response);
	let vidsegList = document.getElementById("vidSegList");
	let vidsegInfoList = document.getElementById("vidSegInfoList");
	
	let output = "<h3>Videos</h3><ul class=\"itemList\">";
	let infoOutput = "<h3>Video Segment Info</h3><ul class=\"itemList\">";
	for(let i = 0; i < js.vidSegs.length; i++) {
		let vidSegJson = js.vidSegs[i];
		console.log(vidSegJson);

		let id = vidSegJson["id"];
		let charSpeaking = vidSegJson["character"];
		let quote = vidSegJson["quote"];
		let seasonNum = vidSegJson["seasonNum"];
		let episodeNum = vidSegJson["episodeNum"];
		let isLocal = vidSegJson["isLocal"];
		let isMarked = vidSegJson["isMarked"];
		let url = vidSegJson["url"];
		
		output = output + "<li><div id=\"vidSeg-" + id + "\"><b>" + id + "</b><br/><span>" + charSpeaking + ": \"" + quote + 
		"\"</span><br/><video width=\"350\" height=\"260\" controls><source src=\"" + url + "\" type=\"video/ogg\"></video>" +
		"<br/><input class=\"button\" type=\"button\" value=\"Delete\"/><div class=\"divider\"></div>" +
		"<input class=\"button\" type=\"button\" value=\"Mark\"><div class=\"divider\"></div>" +
		"<input class=\"button\" type=\"button\" value=\"Unmark\"></div></li><br/><br/>";
		
		let localString = isLocal ? "True" : "False";
		let markedString = isMarked ? "True" : "False";
		
		infoOutput = infoOutput + "<li><div id=\"vidSeg-" + id + "-info\"><b>" + id + "</b><br/><span>" + charSpeaking + ": \"" + quote + 
		"\"</span><br/><span>Season Number: " + seasonNum + "<br/>Episode Number: " + episodeNum + "</span>" +
		"<br/><span>Local: " + localString + "</span>" +
		"<br/><span>Marked: " + markedString + "</span>" +
		"<br/></div></li><br/>";
		
		if(i == js.vidSegs.length-1) {
			output = output + "</ul>";
			infoOutput = infoOutput + "</ul>";
		}

		vidSegList.innerHTML = output;
		vidSegInfoList.innerHTML = infoOutput;
	}
}