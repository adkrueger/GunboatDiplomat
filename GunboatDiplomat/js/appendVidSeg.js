function processAppendResponse(result) {
	console.log("append response: " + result);

	refreshPlaylistList();
}

function requestAppendVidSeg(vsURL) {

	let pName = prompt("Please enter the name of the playlist you would like to append this video segment to.", "Playlist Name");

	if(pName !== null && pName !== "" && pName !== "Playlist Name") {	// if the user didn't cancel the prompt or just hit enter
		handleAppendRequest(pName, vsURL);
	}

}

function handleAppendRequest(playlist, vsURL) {

	let appendData = {};
	appendData["playlistName"] = playlist;
	appendData["videoID"] = vsURL;
	
	let js = JSON.stringify(appendData);
	console.log("Append JS: " + js);
	let appendXHR = new XMLHttpRequest();
	appendXHR.open("POST", appendVidSeg_url, true);
	appendXHR.send(js);
	console.log("sent append");

	appendXHR.onloadend = function() {
		if(appendXHR.readyState === XMLHttpRequest.DONE) {
			if(appendXHR.status === 200) {
				console.log("Remote XHR: " + appendXHR.responseText);
				processAppendResponse(appendXHR.responseText);
			}
			else {
				console.log("remote actual: " + appendXHR.responseText);
				let newJS = JSON.parse(appendXHR.responseText);
				let err = newJS["error"];
				alert(err);
			}
		}
		else {
			processAppendResponse("N/A");
		}
	}

}