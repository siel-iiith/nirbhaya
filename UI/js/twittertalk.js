
function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function getUsertalk(qresult) {
		
		var strURL = "http://search.iiit.ac.in/nirbhaya/VerticalSearch/TwitterSearch?q="+qresult+"&callback=?";
        
            
		$.ajax({
			url:strURL,
			timeout:10000,
			async: true,
			type: 'GET',
			dataType: 'jsonp',
			crossDomain:true,
			success: function(talkresult){
				// Display Results on the Screen
		    	
			if(talkresult.searchResults.length != 0)
				{
				var i = 0;
				$("#buzzheading").html("<p id=\"buzzheading\"> Social Buzz on " +qresult+"</p>");
				$("#buzzholder").empty();
				for(i = 0; i<talkresult.searchResults.length; i++){
					var jTweettext = talkresult.searchResults[i].jTweetText;
					var jTweetloc = talkresult.searchResults[i].jTweetLocation;
                                        var jTweetid = talkresult.searchResults[i].jTweetId;
					var innerHTML = "";
					
					innerHTML = "<div class = \"vsdescription\"> Tweet: &nbsp; &nbsp;"+jTweettext+" </div>";                                        				
                                        innerHTML = innerHTML + "<div class = \"vsurl\" style='color:blue'> Location: &nbsp; &nbsp;"+jTweetloc+" </div>";
                                        innerHTML = innerHTML + "<div style='color:green'> TweetID: &nbsp; &nbsp;"+jTweetid+"</div>";
                                        innerHTML = innerHTML + "<hr style='width:99%'/>";
					$("#buzzholder").append(innerHTML);
				}
				}else{
				    var innerHTML = "";
				    innerHTML = "<p id=\"buzzheading\"> Sorry! No results found for your Query!!</p>"
				    $("#buzzholder").append(innerHTML);				    
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});

}		

function Buzzer(){
    document.getElementById('vsearcher').style.backgroundImage="url('images/vsearch.png')";
    document.getElementById('buzzer').style.backgroundImage="url('images/buzz.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trends.png')";
    document.getElementById('reporter').style.backgroundImage="url('images/report.png')";
    document.getElementById('solver').style.backgroundImage="url('images/solutions.png')";
    document.getElementById('buzzer').style.backgroundImage="url('images/buzzcolor.png')";
    var searchquery = getURLParameter("query");
    getUsertalk(searchquery);
}
