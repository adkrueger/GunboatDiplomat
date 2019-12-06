function processCreateResponse(result) {
	console.log("response: " + result);
	
	refreshPlaylistList();
}

function handleCreateClick() {
	let form = document.createPlaylist;
	
	let pData = {}
	pData["name"] = form.name.value;
	
	if(pData["name"] == "") {
		alert("Please enter a playlist name.");
	}
	else if(pData["name"].indexOf("\'") || pData["name"].indexOf("\"")) {
		alert("Playlist names cannot contain double quote (\") or apostrophe (\') characters.");
	}
	else {
		let js = JSON.stringify(pData);
		console.log("Create JS: " + js);
		let xhr = new XMLHttpRequest();
		xhr.open("POST", createPlaylist_url, true);
		xhr.send(js);
		console.log("sent create");
		
		xhr.onloadend = function() {
			console.log(xhr);

			if(xhr.readyState == XMLHttpRequest.DONE) {
				if(xhr.status == 200) {
					console.log("XHR: " + xhr.responseText);
					processCreateResponse(xhr.responseText);
				}
				else {
					console.log("actual: " + xhr.responseText);
					let newJS = JSON.parse(xhr.responseText);
					let err = newJS["response"];
					alert(err);
				}
			}
			else {
				processRegisterResponse("N/A");
			}
		};
	}
}