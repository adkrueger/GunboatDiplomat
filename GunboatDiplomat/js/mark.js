function processMarkResponse(result, isAdmin) {
	console.log("mark result is: " + result);

	if(isAdmin) {
		refreshVidSegListAdmin();
	}
	else {
		refreshVidSegList();
	}
}

function requestMark(vs, isAdmin) {

	let data = {};
	data["videoID"] = vs;		// this is the same variable as in the markRequest class

	let js = JSON.stringify(data);
	console.log("Mark VidSeg JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", markVidSeg_url, true);
	xhr.send(js);

	xhr.onloadend = function() {
		console.log(xhr);

		if(xhr.readyState == XMLHttpRequest.DONE) {
			if(xhr.status === 200) {
				console.log("XHR: " + xhr.responseText);
				processMarkResponse(xhr.responseText, isAdmin);
			}
			else {
				console.log("actual: " + xhr.responseText);
				let newJS = JSON.parse(xhr.responseText);
				let err = newJS["message"];
				alert(err);
			}
		}
		else {
			processMarkResponse("N/A", isAdmin);
		}
	}

}