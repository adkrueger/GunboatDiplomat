function processRemoveResponse(result) {
	console.log("remove response: " + result);

	refreshPlaylistList();
}

function requestRemoveVidSeg(playlist, pLength) {

	if(pLength == 0) {	// leave this as double equals so that it doesn't fail the type check
		alert("No video segments to remove!");
	}

	console.log("requesting remove for " + playlist + " of length " + pLength)
	let index = prompt("Please insert the index of the video segment you would like to remove (first video segment is index 0)", "Index");

	if(!isNaN(index) && index < pLength && index >= 0) {		// check if the user entered a number
		processRemove(playlist, index);
	}
	else {
		alert("Please enter an integer within range of the playlist.");
	}
}

function processRemove(playlist, index) {

	let data = {};
	data["playlistTitle"] = playlist;		// TODO: Change these names to match request class!
	data["index"] = index;

	let js = JSON.stringify(data);
	console.log("Remove JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", removeVidSeg_url, true);

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
				let err = newJS["error"];		// TODO: check that this is the correct field
				alert(err);
			}
		}
		else {
			processRemoveResponse("N/A");
		}
	};
}