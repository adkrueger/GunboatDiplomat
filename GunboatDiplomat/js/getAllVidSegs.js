function refreshVidSegList() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", listVidSegs_url, true);
	xhr.send();
	console.log("sent");

	xhr.onloadend = function() {
		if(xhr.readyState == XMLHttpRequest.DONE) {
			processVidSegListResponse(xhr.responseText);
		}
		else {
			processVidSegListResponse("N/A");
		}
	};
}

function processVidSegListResponse(response) {

	let js = JSON.parse(response);
	let vidsegList = document.getElementById("vidSegList");

	let output = "<h3>Videos</h3><ul class=\"itemList\">";
	for(let i = 0; i < js.vidSegs.length; i++) {
		let vidSegJson = js.vidSegs[i];
		console.log(vidSegJson);

		let id = vidSegJson["id"];
		let charSpeaking = vidSegJson["character"];
		let quote = vidSegJson["quote"];
		let isLocal = vidSegJson["isLocal"];
		let isMarked = vidSegJson["isMarked"];
		let url = vidSegJson["url"];

		output = output + "<li><div id=\"vidSeg-" + id + "\"><b>" + id + "</b><br/><span>" + charSpeaking + ": \"" + quote + 
		"\"</span><br/><video width=\"350\" height=\"260\" controls><source src=\"" + url + "\" type=\"video/ogg\"></video>" +
		"<br/><input class=\"button\" type=\"button\" value=\"Delete\" onclick=\"requestDelete(\'" + id + "\', 0)\"/></div></li><br/><br/>";
		/*
		infoOutput = infoOutput + "<li><div id=\"vidSeg-" + id + "-info\"><b>" + id + "</b><br/><span>" + charSpeaking + ": \"" + quote + 
		"\"</span><br/><span>Season Number: " + seasonNum + "<br/>Episode Number: " + episodeNum + "</span>" +
		"<br/></div></li><br/>";
		 */
		if(i == js.vidSegs.length-1) {
			output = output + "</ul>";
		}

		vidSegList.innerHTML = output;
	}
}