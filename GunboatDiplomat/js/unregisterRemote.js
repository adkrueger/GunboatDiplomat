function processUnregisterRemoteResponse(result) {
	console.log("unregistered: " + result);
	
	refreshRemoteList();
}

function requestUnregisterRemote(remoteURL) {
	if(confirm("Unregister remote \"" + remoteURL + "\"?")) {
		processRemoteUnregister(remoteURL);
	}
}

function processRemoteUnregister(remoteURL) {
	
	let rData = {};
	rData["url"] = remoteURL;
	
	let js = JSON.stringify(rData);
	console.log("Delete remote JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", unregisterRemote_url, true);
	xhr.send(js);
	
	xhr.onloadend = function() {
		console.log(xhr);
		
		if(xhr.readyState === XMLHttpRequest.DONE) {
			if(xhr.status === 200) {
				console.log("XHR: " + xhr.responseText);
				processUnregisterRemoteResponse(xhr.responseText);
			}
			else {
				console.log("actual: " + xhr.responseText);
				let newJS = JSON.parse(xhr.responseText);
				let err = newJS["error"];
				alert(err);
			}
		}
		else {
			processUnregisterRemoteResponse("N/A");
		}
	}
	
}