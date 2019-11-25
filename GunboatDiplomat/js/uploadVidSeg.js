function processUploadResponse(response) {
	console.log("response: " + response);
	
	refreshVidSegList();
}

function handleUploadClick(e) {
	let form = document.uploadVidSeg;

	let data = {}
	data["id"] = uuidv4();	// get a UUID for the file
	data["character"] = form.character.value;
	data["quote"] = form.quote.value;
	data["seasonNum"] = form.seasonNum.value;
	data["episodeNum"] = form.episodeNum.value;
	data["isLocal"] = 1;
	data["isMarked"] = 0;
	
	let segments = form.base64Encoding.value.split(',');
	data["base64EncodedValue"] = segments[1];

	// make a JSON object out of the input
	let js = JSON.stringify(data);
	console.log("Upload JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", uploadVidSeg_url, true);
	
	xhr.send(js);
	console.log("sent upload input");
	
	xhr.onloadend = function() {
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE) {
			if(xhr.status == 200) {
				console.log("XHR: " + xhr.responseText);
				processUploadResponse(xhr.responseText);
			}
			else {
				console.log("actual: " + xhr.responseText);
				let newJS = JSON.parse(xhr.responseText);
				let err = newJS["response"];
				alert(err);
			}
		}
		else {
			processUploadResponse("N/A");
		}
	};
}

function uuidv4() {
	  return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
	    (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
	  );
}