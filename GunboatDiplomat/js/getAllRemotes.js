function refreshRemoteList() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", listRemotes_url, true);
	xhr.send();
	console.log("sent");
	
	xhr.onloadend = function() {
		if(xhr.readyState == XMLHttpRequest.DONE) {
			processRemoteListResponse(xhr.responseText);
		}
		else {
			processRemoteListResponse("N/A");
		}
	};
}

function processRemoteListResponse(response) {
	
	let js = JSON.parse(response);
	console.log(js);
	let remoteList = document.getElementById("remoteList");
	
	let output = "<h3>Remote List</h3><ul class=\"itemList\">";
	
	for(let i = 0; i < js.remotes.length; i++) {
		let remoteJson = js.remotes[i];
		console.log(remoteJson);
		
		let url = remoteJson["url"];
		
		output = output + "<li><div id=\"remote-" + url + "\"><span>" + url + "</span><div class=\"divider\"></div>" +
				"<input class=\"button\" type=\"button\" value=\"Unregister\" onclick=\"requestUnregisterRemote(\'" + url + "\')\"/>" +
				"</div></li><div class=\"vertSpace\"></div>";
				
		
		if(i == js.remotes.length-1) {
			output = output + "</ul>";
		}
		
		remoteList.innerHTML = output;
	}
}
