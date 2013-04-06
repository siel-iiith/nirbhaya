
function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function sendQueryToBackend(qresult) {
		//var strURL = "http://localhost:8080/RestSimpleAppMaven-0.0.1-SNAPSHOT/rest/BingSearch?q="+qresult+"&callback=?";
		//var strURL = "http://10.2.4.234:8080/RestSimpleAppMaven/rest/BingSearch?q="+qresult+"&callback=?";
		var strURL = "http://localhost:8080/VerticalSearch/BingSearch?q="+qresult+"&callback=?";
        
            
		$.ajax({
			url:strURL,
			timeout:20000,
			async: true,
			type: 'GET',
			dataType: 'jsonp',
			crossDomain:true,
			success: function(result){
				// Display Results on the Screen
		    	var i = 0;
    
		    	
		    	for(i = 0; i<result.searchResults.length; i++){
					var tempTitle = result.searchResults[i].jBingTitle;
					var tempURL = result.searchResults[i].jBingURL;
					var tempDesc = result.searchResults[i].jBingDesc;

					var innerHTML = "";
					
					innerHTML = "<span class = \"bingTitle\"> <h3 class=\"vscaption\">"+tempTitle+"</h3> </span>";
					innerHTML = innerHTML + "<a href = "+tempURL+" class = \"vsurl\"> "+tempURL+" </a>";
					innerHTML = innerHTML + "<span class = \"vsdescription\"> "+tempDesc+" </span>";
                                        innerHTML = innerHTML + "<hr style='width:99%'/>";

					$("#vresults").append(innerHTML);
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
    sendQueryToBackend(searchquery);
});
