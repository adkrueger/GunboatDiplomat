function refreshPlaylistList() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", listPlaylists_url, true);
	xhr.send();
	console.log("sent");
	
	xhr.onloadend = function() {
		if(xhr.readyState == XMLHttpRequest.DONE) {
			processListResponse(xhr.responseText);
		}
		else {
			processListResponse("N/A");
		}
	};
}

function processListResponse(response) {
	
	let js = JSON.parse(response);
	let playlistList = document.getElementById("vidSegList");
	
	let output = "<ul class=\"itemList\">";
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
		let base64Contents = vidSegJson["base64EncodedContents"];
		console.log("ENCODED: " + base64Contents);
		console.log("DECODED: " + atob(base64Contents););
		output = output + "<li><div id=\"vidSeg-" + id + "\"><b>" + id + "</b><br/><span>" + charSpeaking + ": \"" + quote + 
		"\"</span><br/><video width=\"350\" height=\"350\" controls><source src=\"" + atob(base64Contents); + "\" type=\"video/ogg\"></video>" +
				"<br/><input class=\"button\" type=\"button\" value=\"Delete\"/></div></br></li>";
		if(i == js.vidSegs.length-1) {
			output = output + "</ul>";
		}
		
		vidSegList.innerHTML = output;
	}
}