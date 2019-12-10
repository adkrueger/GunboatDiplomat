function processSearchResponse(response, remoteResponse) {

	console.log(response);
	console.log(remoteResponse);

	let js = JSON.parse(response);
	let searchResults = document.getElementById("searchResults");

	let output = "<h3>Search Results</h3><ul class=\"itemList\">";

	if(js.vidSegs.length === 0) {
		output = output + "<p>No results found.</p>"
	}
	else {
		for(let i = 0; i < js.vidSegs.length; i++) {
			let resultJson = js.vidSegs[i];
			console.log(resultJson);

			let id = resultJson["id"];
			let charSpeaking = resultJson["character"];
			let quote = resultJson["text"];
			let isLocal = resultJson["isLocal"];

			if(isLocal) {
				output = output + "<li><a href=\"#vidSeg-" + id + "\"><b>" + id + "</b></a><div class=\"vertSpace\"></div>"
				+ "<span>" + charSpeaking + ": \"" + quote + "\"</span></li><br/>";
			}
			else {
				output = output + "<li><b>" + id + "</b><div class=\"vertSpace\"></div>"
				+ "<span>" + charSpeaking + ": \"" + quote + "\"</span></li><br/>";
			}
			if(i === js.vidSegs.length-1) {
				output = output + "</ul>";
			}

		}
	}
	// vidSegs is in response https class of search

	searchResults.innerHTML = output;

}

function handleSearchClick() {
	let form = document.searchVidSegs;

	let searchData = {}
	// note: fields for search data match names of variables in search https request class
	// otherwise, this won't work!
	searchData["character_speaking"] = form.character.value;
	searchData["quote"] = form.quote.value;

	let js = JSON.stringify(searchData);
	console.log("Search JS: " + js);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", searchVidSegs_url, true);
	xhr.send(js);
	console.log("sent search input");



	xhr.onloadend = function() {

		let remoteXHR = new XMLHttpRequest();
		remoteXHR.open("GET", listRemotes_url, true);
		remoteXHR.send();
		console.log("sent remotes get");

		console.log(xhr);
		console.log(remoteXHR);

		remoteXHR.onloadend = function() {
			if(xhr.readyState === XMLHttpRequest.DONE && remoteXHR.readyState === XMLHttpRequest.DONE) {
				if(xhr.status === 200 && remoteXHR.status === 200) {
					console.log("XHR: " + xhr.responseText);
					console.log("Remote XHR: " + remoteXHR.responseText);
					processSearchResponse(xhr.responseText, remoteXHR.responseText);
				}
				else if(xhr.status !== 200) {
					console.log("actual: " + xhr.responseText);
					let newJS = JSON.parse(xhr.responseText);
					let err = newJS["error"];
					alert(err);
				}
				else {
					console.log("remote actual: " + remoteXHR.responseText);
					let newJS = JSON.parse(remoteXHR.responseText);
					let err = newJS["error"];
					alert(err);
				}
			}
			else {
				processSearchResponse("N/A", "N/A");
			}
		};
	};
}