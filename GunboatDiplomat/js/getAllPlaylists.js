function refreshPlaylistList() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", listPlaylists_url, true);
	xhr.send();
	console.log("sent");

	xhr.onloadend = function() {
		if(xhr.readyState == XMLHttpRequest.DONE) {
			processPlaylistListResponse(xhr.responseText);
		}
		else {
			processPlaylistListResponse("N/A");
		}
	};
}

function processPlaylistListResponse(response) {

	let js = JSON.parse(response);
	console.log(js);
	let playlistList = document.getElementById("playlistList");

	let output = "<ul class=\"itemList\">";
	for(let i = 0; i < js.playlists.length; i++) {
		let playlistJson = js.playlists[i];
		console.log(playlistJson);

		let name = playlistJson["name"];

//		console.log("ENCODED: " + base64Contents);
//		console.log("DECODED: " + atob(base64Contents););
		/*		output = output + "<li><div id=\"vidSeg-" + id + "\"><b>" + id + "</b><br/><span>" + charSpeaking + ": \"" + quote + 
		"\"</span><br/><video width=\"350\" height=\"350\" controls><source src=\"" + atob(base64Contents); + "\" type=\"video/ogg\"></video>" +
				"<br/><input class=\"button\" type=\"button\" value=\"Delete\"/></div></br></li>";
		 */
		output = output + "<li><div id=\"playlist-" + name + "\"><b>" + name + "</b><br/><input class=\"button\" type=\"button\" value=\"Play\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Append VS\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Remove VS\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Delete\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"List VS in Playlist\"/></div></li>";

		if(i == js.playlists.length-1) {
			output = output + "</ul>";
		}

		playlistList.innerHTML = output;
	}
}