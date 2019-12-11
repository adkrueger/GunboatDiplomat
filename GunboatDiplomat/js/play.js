function playPlaylist(vidSegs, pLength) {
	
	if(pLength > 0) {
		let idList = [];
		for(let i = 0; i < pLength; i++) {
			idList.push(vidSegs[0]["id"]);
		}
		console.log(idList);
	}
	
}