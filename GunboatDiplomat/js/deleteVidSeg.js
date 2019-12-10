function processDeleteResponse(result, isAdmin) {
	// Can grab any DIV or SPAN HTML element and can then manipulate its
	// contents dynamically via javascript
	console.log("deleted: " + result);

	if(isAdmin) {
		console.log('admin request');
		refreshVidSegListAdmin();
	}
	else {
		console.log('client request');
		refreshVidSegList();
	}
}

function requestDelete(vs, charSpeaking, quote, isAdmin) {
	if(confirm("Delete " + vs + ", where " + charSpeaking + " says \"" + quote + "\"?")) {
		processDelete(vs, isAdmin);
	}
}

function processDelete(vs, isAdmin) {

	let data = {};
	data["id"] = vs;

	let js = JSON.stringify(data);
	console.log("Delete JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", deleteVidSeg_url, true);

	xhr.send(js);

	xhr.onloadend = function() {
		console.log(xhr);

		if(xhr.readyState == XMLHttpRequest.DONE) {
			if(xhr.status == 200) {
				console.log("XHR: " + xhr.responseText);
				processDeleteResponse(xhr.responseText, isAdmin);
			}
			else {
				console.log("actual: " + xhr.responseText);
				let newJS = JSON.parse(xhr.responseText);
				let err = newJS["error"];
				alert(err);
			}
		}
		else {
			processDeleteResponse("N/A", isAdmin);
		}
	}

}