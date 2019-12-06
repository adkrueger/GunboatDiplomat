function processDeletePlaylistResponse(result) {
	console.log("deleted: " + result);
	
	refreshPlaylistList();
}

function requestDeletePlaylist(playlist) {
	if(confirm("Delete playlist \"" + playlist + "\"?")) {
		processPlaylistDelete(playlist);
	}
}

function processPlaylistDelete(playlist) {
	
	let pdData = {};
	pdData["id"] = playlist;
	
	let js = JSON.stringify(pdData);
	console.log("Delete Playlist JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", deletePlaylist_url, true); 
	xhr.send(js);
	
	xhr.onloadend = function() {
		console.log(xhr);
		
		if(xhr.readyState === XMLHttpRequest.DONE) {
			if(xhr.status === 200) {
				console.log("XHR: " + xhr.responseText);
				processDeletePlaylistResponse(xhr.responseText);
			}
			else {
				console.log("actual: " + xhr.responseText);
				let newJS = JSON.parse(xhr.responseText);
				let err = newJS["error"];
				alert(err);
			}
		}
		else {
			processDeleteResponse("N/A");
		}
	}
	
}