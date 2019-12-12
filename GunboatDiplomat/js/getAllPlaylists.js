function refreshPlaylistList() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", listPlaylists_url, true);
	xhr.send();
	console.log("sent");

	xhr.onloadend = function() {
		if(xhr.readyState === XMLHttpRequest.DONE) {
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

	for(let i = 0; i < Object.keys(js.playlists).length; i++) {
		let name = Object.keys(js.playlists)[i];

		output = output + "<br/><li><div id=\"playlist-" + name + "\"><b>" + name + "</b><br/>";

		let vidSegs = js.playlists[name];
		let actualLength = 0;
		//console.log("its vidSegs: " + JSON.stringify(vidSegs));

		for(let j = 0; j < vidSegs.length; j++) {
			let vsURL = vidSegs[j]["id"];
			if(vsURL != null && vsURL !== "") {
				output = output + "<video id=\"playlist-" + name + "-VidSeg-" + (++actualLength) + "\" width=\"350\" height=\"260\" controls><source src=\"" + vsURL + "\" type=\"video/ogg\"></video>";

				if(j === vidSegs.length-1) {
					output = output + "<br/>";
				}
			}
		}

		output = output + "<input class=\"button\" type=\"button\" value=\"Play\" onclick=\"playPlaylist(\'" + name + "\', \'" + actualLength + "\')\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Remove VS\" onclick=\"requestRemoveVidSeg(\'" + name + "\', \'" + (vidSegs.length-1) + "\')\"/>"
		+ "<div class=\"divider\"></div><input class=\"button\" type=\"button\" value=\"Delete\" onclick=\"requestDeletePlaylist(\'" + name + "\')\"/></div></li>";

		if(i === Object.keys(js.playlists).length-1) {
			output = output + "</ul>";
		}

		playlistList.innerHTML = output;
	}
}