
function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function getUsertalk(qresult) {
		//var strURL = "http://localhost:8080/RestSimpleAppMaven-0.0.1-SNAPSHOT/rest/BingSearch?q="+qresult+"&callback=?";
		//var strURL = "http://10.2.4.234:8080/RestSimpleAppMaven/rest/BingSearch?q="+qresult+"&callback=?";
		var strURL = "http://10.2.4.239:8080/VerticalSearch/TwitterSearch?q="+qresult+"&callback=?";
        
            
		$.ajax({
			url:strURL,
			timeout:10000,
			async: true,
			type: 'GET',
			dataType: 'jsonp',
			crossDomain:true,
			success: function(talkresult){
				// Display Results on the Screen
		    	var i = 0;
    
		    	
		    	for(i = 0; i<talkresult.searchResults.length; i++){
					var jTweettext = talkresult.searchResults[i].jTweetText;
					var jTweetloc = talkresult.searchResults[i].jTweetLocation;
                                        var jTweetid = talkresult.searchResults[i].jTweetId;
					var innerHTML = "";
					
					innerHTML = "<div class = \"vsdescription\"> Tweet: &nbsp; &nbsp;"+jTweettext+" </div>";                                        				
                                        innerHTML = innerHTML + "<div class = \"vsurl\" style='color:blue'> Location: &nbsp; &nbsp;"+jTweetloc+" </div>";
                                        innerHTML = innerHTML + "<div style='color:green'> TweetID: &nbsp; &nbsp;"+jTweetid+"</div>";
                                        innerHTML = innerHTML + "<hr style='width:99%'/>";

					$("#talkspace").append(innerHTML);
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});

}		


$(document).ready(function() {
    var searchquery = getURLParameter("query");
    getUsertalk(searchquery);
});
