function processRegisterResponse(result) {
	console.log("response: " + result);
	
	refreshRemoteList();
}

function handleRegisterClick() {
	let form = document.remoteForm;
	
	let rData = {}
	rData["url"] = form.remoteURL.value;
	
	if(rData["url"] === "") {
		alert("Please enter a URL.");
	}
	else {
		let js = JSON.stringify(rData);
		console.log("Register JS: " + js);
		let xhr = new XMLHttpRequest();
		xhr.open("POST", registerRemote_url, true);
		xhr.send(js);
		console.log("sent register");
		
		xhr.onloadend = function() {
			console.log(xhr);

			if(xhr.readyState === XMLHttpRequest.DONE) {
				if(xhr.status === 200) {
					console.log("XHR: " + xhr.responseText);
					processRegisterResponse(xhr.responseText);
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