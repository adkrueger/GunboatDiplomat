function refreshVidSegList() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", listVidSegs_url, true);
	xhr.send();
	console.log("request sent");
	
	xhr.onloadend = function() {
		if(xhr.readyState == XMLHttpRequest.DONE) {
			console.log("XHR: " + xhr.responseText);
			processListResponse(xhr.responseText);
		}
		else {
			processListResponse("N/A");
		}
	};
}

function processListResponse(response) {
	console.log("response: " + response);
	
	let js = JSON.parse(result);
	let vidsegList = document.getElementById("vidSegList");
	
	let output = "";
	for(let i = 0; i < js.list.length; i++) {
		let vidSegJson = js.list[i];
		console.log(vidSegJson);
		
		let id = vidSegJson[""];
		let charSpeaking = vidSegJson[""];
		let quote = vidSegJson[""];
		let seasonNum = vidSegJson[""];
		let episodeNum = vidSegJson[""];
		let isLocal = vidSegJson[""];
		let isMarked = vidSegJson[""];
		let base64Contents = vidSegJson[""];
		
		output = output + "<div id=\"vidSeg-" + id + "\"><b>" + id + "</b><br/><p>" + charSpeaking + "</p><br/><p>" + quote + 
		"</p><br/><video width=\"350\" height=\"350\" controls><source src=\"" + base64Contents + "\" type=\"video/ogg\"></video></div>";
		
		vidSegList.innerHTML = output;
	}
}