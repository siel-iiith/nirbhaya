
// USE URL 
var devServer= "http://search.iiit.ac.in/nirbhaya";
var deployServer = "http://10.2.4.238:8080/VerticalSearch";
var useServer = devServer;
// for Testing/Dev

function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function countWords(s){
	s = s.replace(/(^\s*)|(\s*$)/gi,"");
	s = s.replace(/[ ]{2,}/gi," ");
	s = s.replace(/\n /,"\n");
	s = s.replace(/|/,"\n");
	return s.split(' ').length;
}


function queryCleaner(queryStr){
	var cleanQuery = queryStr;
	cleanQuery = cleanQuery.replace(/%2B/g," ");
	cleanQuery = cleanQuery.replace(/%20/g," ");
	cleanQuery = cleanQuery.replace(/\+/g," ");
//	cleanQuery = cleanQuery.replace(/\|/g," in ");
	return cleanQuery;
}

function sendQueryToBackend(qresult) {
		//var strURL = "http://localhost:8080/RestSimpleAppMaven-0.0.1-SNAPSHOT/rest/BingSearch?q="+qresult+"&callback=?";
		//var strURL = "http://10.2.4.234:8080/RestSimpleAppMaven/rest/BingSearch?q="+qresult+"&callback=?";
		var newresult = qresult.replace('/\+/g'," ");
		var strURL = "http://search.iiit.ac.in/nirbhaya/VerticalSearch/BingSearch?q="+newresult+"&callback=?";
		document.getElementById("searchformquery").value = newresult;
        
            
		$.ajax({
			url:strURL,
			timeout:10000,
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

function getQuerySuggestion(qresult) {
		
		var strURL = useServer+"/VerticalSearch/QuerySuggestion?q="+qresult+"&callback=?";
        var finalQuery = qresult;
        var suggestionQuery = qresult;
		suggestionQuery = suggestionQuery.replace(" in ","|");
		$.ajax({
			url:strURL,
			timeout:20000,
			async: false,
			type: 'GET',
			dataType: 'jsonp',
			crossDomain:true,
			success: function(result){
				// Display Results on the Screen

				if(result.hasOwnProperty('expansionResults') == true){
						$('#query').each(function() {
						    if ( $(this).val() == 'X' ) {
						        $(this).remove();
						    }
						});
					var innerHTML = "";
					var i=0;
					var cleanQuery = queryCleaner(qresult);
					var newresult = qresult.replace('/\+/g'," ");
					var strURL = "http://search.iiit.ac.in/nirbhaya/VerticalSearch/BingSearch?q="+newresult+"&callback=?";
					document.getElementById("searchformquery").value = cleanQuery;
					for(i=0; i<result.expansionResults.length; i++){
						var newWords = result.finalQuery + "|" + result.expansionResults[i].placeName;
						// var showWords = result.finalQuery + "|" + result.expansionResults[i].placeName;
						var showWords = result.finalQuery + " in " + result.expansionResults[i].placeName;
						innerHTML = innerHTML + "<option value = \""+newWords+"\">" + showWords + "</option>";
					}
				}
				if(result.hasOwnProperty('finalQuery')){
					finalQuery = result.finalQuery;
				}
				$("#queryList").append(innerHTML);

//				var cleanQuery = queryCleaner(finalQuery);
				
				/*
				cleanQuery = cleanQuery.replace("%2B"," ");
				cleanQuery = cleanQuery.replace("%20"," ");
				cleanQuery = cleanQuery.replace(/\+/g," ");
				cleanQuery = cleanQuery.replace("|"," ");
				// send query to backend from here
*/
				sendQueryToBackend(finalQuery);
			},
			error: function(req, error) { 
				//alert("FAIL");
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});
}		


function vSearcher(){
    document.getElementById('vsearcher').style.backgroundImage="url('images/vsearch.png')";
    document.getElementById('solver').style.backgroundImage="url('images/solutions.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trends.png')";
    document.getElementById('reporter').style.backgroundImage="url('images/report.png')";
    document.getElementById('buzzer').style.backgroundImage="url('images/buzz.png')";
    document.getElementById('vsearcher').style.backgroundImage="url('images/vsearchcolor.png')";
    var searchquery = getURLParameter("query");
    getQuerySuggestion(searchquery);

    
}