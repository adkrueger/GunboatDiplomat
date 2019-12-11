function processSearchResponse(response, remoteResponse, characterKeyword, quoteKeyword) {

	console.log(response);
	console.log(remoteResponse);

	let searchResults = document.getElementById("searchResults");

	let output = "<h3>Search Results</h3><ul class=\"itemList\">";

	let remoteJS = JSON.parse(remoteResponse);
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

					if(remoteVidSegs.length === 0) {		// usually if there are no remotes registered
						ouput = output + "<p>No remote results found.</p>";

						searchResults.innerHTML = output;
					}
					else {
						for(let x = 0; x < remoteVidSegs.length; x++) {		// go through each returned remote
							let pulledURL = remoteVidSegs[x]["url"];
							let pulledCharacter = remoteVidSegs[x]["character"];
							let pulledText = remoteVidSegs[x]["text"];

							if(characterKeyword !== "" && quoteKeyword !== "") {		// searching through both character and quote
								if(pulledCharacter.toLowerCase().indexOf(characterKeyword.toLowerCase()) !== -1 && pulledText.toLowerCase().indexOf(quoteKeyword.toLowerCase()) !== -1) {		// check if our keyword is in the string
									output = output + "<li><b>" + pulledURL + "</b><div class=\"vertSpace\"></div>"
									+ "<span>" + pulledCharacter + ": \"" + pulledText + "\"</span></li><br/>";
								}
							}
							else if(characterKeyword !== "") {		// searching through character, not quote
								if(pulledCharacter.toLowerCase().indexOf(characterKeyword.toLowerCase()) !== -1) {		// check if our keyword is in the string
									output = output + "<li><b>" + pulledURL + "</b><div class=\"vertSpace\"></div>"
									+ "<span>" + pulledCharacter + ": \"" + pulledText + "\"</span></li><br/>";
								}
							}
							else if(quoteKeyword !== "") {		// searching through quote, not character (need if statement here because otherwise it'll add everything by default)
								if(pulledText.toLowerCase().indexOf(quoteKeyword.toLowerCase()) !== -1) {		// check if our keyword is in the string
									output = output + "<li><b>" + pulledURL + "</b><div class=\"vertSpace\"></div>"
									+ "<span>" + pulledCharacter + ": \"" + pulledText + "\"</span></li><br/>";
								}
							}

							searchResults.innerHTML = output;
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
		};
	}

	let js = JSON.parse(response);

	if(js.vidSegs.length === 0) {
		output = output + "<p>No results found.</p>";
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
		}
	}
	// vidSegs is in response https class of search

	output = output + "</ul>";
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
					processSearchResponse(xhr.responseText, remoteXHR.responseText, form.character.value, form.quote.value);
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
				processSearchResponse("N/A", "N/A", "", "");
			}
		};
	};
}