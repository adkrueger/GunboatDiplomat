function processUnmarkResponse(result, isAdmin){
	console.log("mark result is: " + result);
	
	if(isAdmin){
		refreshVidSegListAdmin();
	}
	else{
		refreshVidSegList();
	}
}

function requestUnmark(vs, isAdmin){ //isAdmin is boolean, and result a string/JSON. 
	console.log("Unmark result is" + result);

		let data = {};
		data["videoID"] = vs
		
		let js = JSON.stringify(data);
		console.log("Mark VidSeg JSL: " + js);
		
		let xhr = new XMLHttpRequest();
		xhr.open("POST", unmarkVidSeg_url, true);
		xhr.send(js);
		
		xhr.onloadend = function() {
			
			console.log(xhr);
			
			if(xhr.readyState === XMLHttpRequest.DONE){
				if(xhr.status === 200){
					console.log("XHR: " + xhr.responseText);
					processUnmarkResponse(xhr.responseText, isAdmin);
				}
				
				else{
					console.log("actual: " + xhr.responseText);
					let newJS = JSON.parse(xhr.responseText);
					let err = newJS["message"];
					alert(err)
				}
			}
			else{
				processUnmarkResponse("N/A", isAdmin);
			}
			
		}
	}
	
	
} 
