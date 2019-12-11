function processAppendResponse(result) {
	console.log("append response: " + result);

	refreshPlaylistList();
}

function processAppendRemoteResponse(result, playlist, vsID) {
	console.log(result);

	let appendID = "";
	let remoteJS = JSON.parse(result);
	for(let m = 0; m < remoteJS.remotes.length; m++) {		// go through each remote site
		let url = remoteJS.remotes[m].url;
		let qIndex = url.indexOf("?apikey=");
		let search_remote_url = url.substring(0, qIndex);
		let apikey = url.substring(qIndex+8);

		let remoteVidSegs = [];

		let remoteXHR = new XMLHttpRequest();
		remoteXHR.open("GET", search_remote_url, true);		// get the publicly available segments from the remote
		remoteXHR.setRequestHeader("x-api-key", apikey);
		remoteXHR.send();

		remoteXHR.onloadend = function() {
			if(remoteXHR.readyState === XMLHttpRequest.DONE) {
				if(remoteXHR.status === 200) {
					console.log("Pulled Remote XHR: " + remoteXHR.responseText);
					let pulledJS = JSON.parse(remoteXHR.responseText);
					console.log(pulledJS);
					for(let n = 0; n < pulledJS.segments.length; n++) {		// go through each segment and add it to our list
						let pulledRemotes = pulledJS.segments;
						remoteVidSegs.push(pulledRemotes[n]);
					}

					if(remoteVidSegs.length != 0) {		// usually if there are no remotes registered
						for(let x = 0; x < remoteVidSegs.length; x++) {		// go through each returned remote
							let pulledURL = remoteVidSegs[x]["url"];
							let videoID = pulledURL.substring(pulledURL.lastIndexOf("/")+1);
							console.log("found " + videoID);

							console.log("comparing " + videoID + " and " + vsID);
							if(videoID === vsID) {		// searching through both character and quote
								appendID = videoID;
								console.log("match!");
								break;
							}

						}
					}
				}
				else {
					console.log("remote actual: " + remoteXHR.responseText);
					let newJS = JSON.parse(remoteXHR.responseText);
					let err = newJS["error"];
					alert(err);
				}
			}
			else {
				console.log("N/A");
			}

			if(appendID != "" || m == remoteJS.remotes.length-1) {		// if we've found the remote segments, or if it turns out to be local
				console.log("done checking remotes, found " + appendID);
				
				let pData = {};
				pData["playlistName"] = playlist;
				if(appendID != "") {
					pData["videoID"] = appendID;
				}
				else {	// local video segment, need to enter url manually
					pData["videoID"] = vs_url + vsID;
				}


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
				};
				
				return;
			}
		};
	}

}

function requestAppendVidSeg(playlist) {

	let vsID = prompt("Please enter the ID of the video segment you would like to append.", "Video ID");

	if(vsID !== null && vsID !== "" && vsID !== "Video ID") {	// if the user didn't cancel the prompt or just hit enter
		handleAppendRequest(playlist, vsID);
	}

}

function handleAppendRequest(playlist, vsID) {

	let remoteXHR = new XMLHttpRequest();
	remoteXHR.open("GET", listRemotes_url, true);
	remoteXHR.send();
	console.log("sent remotes get");

	remoteXHR.onloadend = function() {
		if(remoteXHR.readyState === XMLHttpRequest.DONE) {
			if(remoteXHR.status === 200) {
				console.log("Remote XHR: " + remoteXHR.responseText);
				processAppendRemoteResponse(remoteXHR.responseText, playlist, vsID);
			}
			else {
				console.log("remote actual: " + remoteXHR.responseText);
				let newJS = JSON.parse(remoteXHR.responseText);
				let err = newJS["error"];
				alert(err);
			}
		}
		else {
			processAppendRemoteResponse("N/A", "", "");
		}
	}

}