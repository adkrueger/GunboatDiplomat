function processSearchResponse(response) {

	let js = JSON.parse(response);
	let searchResults = document.getElementById("searchResults");

	let output = "<h3>Search Results</h3><ul class=\"itemList\">";

	for(let i = 0; i < js.vidSegs.length; i++) {
		let resultJson = js.vidSegs[i];
		console.log(resultJson);

		let id = resultJson["id"];
		let charSpeaking = resultJson["character"];
		let quote = resultJson["quote"];
		
		output = output + "<li><a href=\"#vidSeg-" + id + "\"><b>" + id + "</b></a><br/><p>"
		+ charSpeaking + ": \"" + quote + "\"</p></li>";

		if(i == js.vidSegs.length-1) {
			output = output + "</ul>";
		}

		searchResults.innerHTML = output;
	}
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
		console.log(xhr);

		if(xhr.readyState == XMLHttpRequest.DONE) {
			if(xhr.status == 200) {
				console.log("XHR: " + xhr.responseText);
				processSearchResponse(xhr.responseText);
			}
			else {
				console.log("actual: " + xhr.responseText);
				let newJS = JSON.parse(xhr.responseText);
				let err = newJS["response"];
				alert(err);
			}
		}
		else {
			processSearchResponse("N/A");
		}
	};
}