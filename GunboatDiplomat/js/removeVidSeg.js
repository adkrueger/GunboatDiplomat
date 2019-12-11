function processRemoveResponse(result) {
	console.log("remove response: " + result);

	refreshPlaylistList();
}

function requestRemoveVidSeg(playlist, pLength) {

	if(pLength == 0) {	// leave this as double equals so that it doesn't fail the type check
		alert("No video segments to remove!");
		return;
	}

	console.log("requesting remove for " + playlist + " of length " + pLength)
	let vIndex = prompt("Please insert the vIndex of the video segment you would like to remove (first video segment is vIndex 0)", "Index");
	console.log(vIndex);
	if(!isNaN(vIndex) && vIndex < pLength && vIndex >= 0 && vIndex != null) {		// check if the user entered a number
		processRemove(playlist, vIndex);
	}
	else if(vIndex != null) {
		alert("Please enter an integer within range of the playlist.");
	}
}

function processRemove(playlist, vIndex) {

	let data = {};
	data["playlistName"] = playlist;
	data["videoIndex"] = Math.floor(vIndex);

	let js = JSON.stringify(data);
	console.log("Remove JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", removeVidSeg_url, true);
	console.log("going to remove index " + data["videoIndex"]);
	xhr.send(js);

	xhr.onloadend = function() {
		console.log(xhr);

		if(xhr.readyState === XMLHttpRequest.DONE) {
			if(xhr.status === 200) {
				console.log("XHR: " + xhr.responseText);
				processRemoveResponse(xhr.responseText);
			}
			else {
				console.log("actual: " + xhr.responseText);
				let newJS = JSON.parse(xhr.responseText);
				let err = newJS["message"];		// TODO: check that this is the correct field
				alert(err);
			}
		}
		else {
			processRemoveResponse("N/A");
		}
	};
}