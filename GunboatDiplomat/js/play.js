function playPlaylist(playlist, pLength) {
	if(pLength > 0) {
		document.getElementById("playlist-" + playlist + "-VidSeg-1").play();		// need to play initial playlist so we "get the callback ball rolling"
		
		for (i = 1; i < pLength; i++) {
			let priorVid = document.getElementById("playlist-" + playlist + "-VidSeg-" + i);
			callBackFunction = playVidSeg("playlist-" + playlist + "-VidSeg-" + (i+1));
			priorVid.addEventListener("ended", callBackFunction);
		}
	}
}

function playVidSeg(id) {
	return function(e) { 
		document.getElementById(id).play();
	};
}