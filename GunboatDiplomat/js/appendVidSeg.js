function processAppendResponse(result) {
	console.log("append response: " + result);

	refreshPlaylistList();
}

function requestAppendVidSeg(playlist) {

	let vsID = prompt("Please enter the ID of the video segment you would like to append.", "Video ID");

	if(vsID !== null && vsID !== "" && vsID !== "Video ID") {	// if the user didn't cancel the prompt or just hit enter
		handleAppendRequest(playlist, vsID);
	}

}

function handleAppendRequest(playlist, vsID) {

	let pData = {};
	pData["playlistName"] = playlist;
	pData["videoID"] = vsID;

	if(pData["videoID"] === "") {
		alert("Please enter a video ID.");
	}
	else {
		let js = JSON.stringify(pData);
		console.log("Append JS: " + js);
		let xhr = new XMLHttpRequest();
		xhr.open("POST", appendVidSeg_url, true);
		xhr.send(js);
		console.log("sent append");

		xhr.onloadend = function() {
			console.log(xhr);

			if(xhr.readyState === XMLHttpRequest.DONE) {
				if(xhr.status === 200) {
					console.log("XHR: " + xhr.responseText);
					processAppendResponse(xhr.responseText);
				}
				else if(xhr.status === 404) {
					console.log("not found feller!");
				}
				else {
					console.log("actual: " + xhr.responseText);
					let newJS = JSON.parse(xhr.responseText);
					let err = newJS["message"];
					alert(err);
				}
			}
			else {
				processAppendResponse("N/A");
			}
		}
	}

}