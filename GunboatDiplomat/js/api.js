// api gateway url
let api_url = "https://quf3be50n8.execute-api.us-east-2.amazonaws.com/echo/";

let appendVidSeg_url = api_url + "appendVidSeg";			// POST
let createPlaylist_url = api_url + "createPlaylist";		// POST
let deletePlaylist_url = api_url + "deletePlaylist";		// POST
let deleteVidSeg_url = api_url + "deleteVidSeg";			// POST
let listPlaylists_url = api_url + "listPlaylists";			// GET
let listRemotes_url = api_url + "listRemotes";				// GET
let listVidSegs_url = api_url + "listVidSegs";				// GET
let registerRemote_url = api_url + "registerRemote";		// POST
let removeVidSeg_url = api_url + "removeVidSeg";			// POST
let searchVidSegs_url = api_url + "searchVidSegs";			// POST
let showPlaylists_url = api_url + "showPlaylists";			// GET
let unregisterRemote_url = api_url + "unregisterRemote";	// POST
let uploadVidSeg_url = api_url + "uploadVidSeg";			// POST

let vs_url = "https://gd3733.s3.us-east-2.amazonaws.com/videoSegments/";

let remote_url =  "https://5uoy8u1hhh.execute-api.us-east-2.amazonaws.com/RemoteSite/";
	
let search_remote_url = remote_url + "publicSegments";
let apikey = "GgpE67ljkia9Pma3PlgJo8MKOJnxobVZ9mCCLxtq";
let url_with_key = "https://5uoy8u1hhh.execute-api.us-east-2.amazonaws.com/RemoteSite/publicSegments?apikey=GgpE67ljkia9Pma3PlgJo8MKOJnxobVZ9mCCLxtq";