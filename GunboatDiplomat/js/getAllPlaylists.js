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

	let output = "<h3>Playlists</h3><ul class=\"itemList\">";
	console.log(Object.keys(js.playlists).length + " is the length");
	for(let i = 0; i < Object.keys(js.playlists).length; i++) {
		let name = Object.keys(js.playlists)[i];
		console.log("name: " + name);

		output = output + "<br/><li><div id=\"playlist-" + name + "\"><b>" + name + "</b><br/>";

		let vidSegs = js.playlists[name];
		console.log("its vidSegs: " + JSON.stringify(vidSegs));

		console.log("vidSegs length is " + vidSegs.length);
		for(let j = 0; j < vidSegs.length; j++) {
			console.log("vid seg " + j + " is: " + JSON.stringify(js.playlists[name][j]));
			console.log("vid seg id " + j + " is: " + vidSegs[j]["id"]);

			let vsID = vidSegs[j]["id"];
			if(vsID != "") {
				let vsURL = vs_url + vsID;

				output = output + "<video width=\"350\" height=\"260\" controls><source src=\"" + vsURL + "\" type=\"video/ogg\"></video>";
			}

			if(j == vidSegs.length-1) {
				output = output + "<br/>";
			}

		}

		output = output + "<input class=\"button\" type=\"button\" value=\"Play\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Append VS\" onclick=\"requestAppendVidSeg(\'" + name + "\')\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Remove VS\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Delete\" onclick=\"requestDeletePlaylist(\'" + name + "\')\"/></div></li>";

		if(i == Object.keys(js.playlists).length-1) {
			output = output + "</ul>";
		}

		playlistList.innerHTML = output;
	}
}