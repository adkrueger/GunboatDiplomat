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

function requestDelete(vs, isAdmin) {
	if(confirm("Delete " + vs + "?")) {
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
		
	}
	
}