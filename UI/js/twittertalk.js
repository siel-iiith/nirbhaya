
function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function getUsertalk(qresult) {
		
		//var strURL = "http://10.2.4.251:8080/Nirbhaya/TwitterSearch?q="+qresult+"&callback=?";
		var strURL = "http://0.0.0.0:8080/Nirbhaya/TwitterSearch?q="+qresult+"&callback=?";
        
            
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
					
					innerHTML = "<div class = \"vsdescription\"> "+jTweettext+" </div>";                                        				
                                        innerHTML = innerHTML + "<div class = \"vsurl\" style='color:blue'> "+jTweetloc+" </div>";
                                        innerHTML = innerHTML + "<div style='color:green'>"+jTweetid+"</div>";
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
