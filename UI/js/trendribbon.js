
function addRibbon() {
		
    //var strURL = "http://10.2.4.234:8080/VerticalSearch-0.0.1-SNAPSHOT/BingSearch?q="+qresult+"&callback=?";
    var strURL1 = "http://10.2.4.216:8080/Trending/category-water?callback=?";
    var strURL2 = "http://10.2.4.216:8080/Trending/category-sewage?callback=?";
    var strURL3 = "http://10.2.4.216:8080/Trending/category-electricity?callback=?";
    var strURL4 = "http://10.2.4.216:8080/Trending/category-road?callback=?";
    var strURL5 = "http://10.2.4.216:8080/Trending/category-food?callback=?";
        
            
    $.ajax({
	url:strURL1,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(ribbonresult1){
		// Display Results on the Screen
		    var i = 0;
	    //alert(ribbonresult1.catContent.length);
		    for(i = 0; i<4; i++){
		    //alert(ribbonresult1.catContent[i].trendName);
			var trendname = ribbonresult1.catContent[i].trendName;
			var trendimageurl = ribbonresult1.catContent[i].imageURL;
	    		var innerHTML = "";
			$("#trendbarholder").append("<table id=\"trendset\"></table>");
			$("#trendset").append("<tr><td><a href=\""+trendimageurl+"\"><img src=\"" +trendimageurl+"\" class=\"trendimage\"/></a></td><td><a><span class=\"trendtext\" id=\"water\">" +trendname +"</span></a></td></tr>");
		
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});
    
    
    $.ajax({
	url:strURL2,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(ribbonresult2){
		// Display Results on the Screen
		    var i = 0;
	    //alert(ribbonresult2.catContent.length);
		    for(i = 0; i<4; i++){
		    //alert(ribbonresult2.catContent[i].trendName);
			var trendname = ribbonresult2.catContent[i].trendName;
			var trendimageurl = ribbonresult2.catContent[i].imageURL;
	    		var innerHTML = "";
			//$("#trendbarholder").append("<table id=\"trendset\"></table>");
			$("#trendset").append("<tr><td><a href=\""+trendimageurl+"\"><img src=\"" +trendimageurl+"\" class=\"trendimage\"/></a></td><td><a><span class=\"trendtext\" id=\"sewage\">" +trendname +"</span></a></td></tr>");
		
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});
    
    
    $.ajax({
	url:strURL3,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(ribbonresult3){
		// Display Results on the Screen
		    var i = 0;
	    //alert(ribbonresult3.catContent.length);
		    for(i = 0; i<4; i++){
		    //alert(ribbonresult3.catContent[i].trendName);
			var trendname = ribbonresult3.catContent[i].trendName;
			var trendimageurl = ribbonresult3.catContent[i].imageURL;
	    		var innerHTML = "";
			//$("#trendbarholder").append("<table id=\"trendset\"></table>");
			$("#trendset").append("<tr><td><a href=\""+trendimageurl+"\"><img src=\"" +trendimageurl+"\" class=\"trendimage\"/></a></td><td><a><span class=\"trendtext\" id=\"electricity\">" +trendname +"</span></a></td></tr>");
		
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});
    
    
    $.ajax({
	url:strURL4,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(ribbonresult4){
		// Display Results on the Screen
		    var i = 0;
	    //alert(ribbonresult4.catContent.length);
		    for(i = 0; i<4; i++){
		    //alert(ribbonresult4.catContent[i].trendName);
			var trendname = ribbonresult4.catContent[i].trendName;
			var trendimageurl = ribbonresult4.catContent[i].imageURL;
	    		var innerHTML = "";
			//$("#trendbarholder").append("<table id=\"trendset\"></table>");
			$("#trendset").append("<tr><td><a href=\""+trendimageurl+"\"><img src=\"" +trendimageurl+"\" class=\"trendimage\"/></a></td><td><a><span class=\"trendtext\" id=\"road\">" +trendname +"</span></a></td></tr>");
		
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});
    
    
    $.ajax({
	url:strURL5,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(ribbonresult5){
		// Display Results on the Screen
		    var i = 0;
	    //alert(ribbonresult5.catContent.length);
		    for(i = 0; i<4; i++){
		    //alert(ribbonresult5.catContent[i].trendName);
			var trendname = ribbonresult5.catContent[i].trendName;
			var trendimageurl = ribbonresult5.catContent[i].imageURL;
	    		var innerHTML = "";
			$("#trendbarholder").append("<table id=\"trendset\"></table>");
			$("#trendset").append("<tr><td><a href=\""+trendimageurl+"\"><img src=\"" +trendimageurl+"\" class=\"trendimage\"/></a></td><td><a><span class=\"trendtext\" id=\"food\">" +trendname +"</span></a></td></tr>");
		
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});

}		



function addTrends() {
		
    //var strURL = "http://10.2.4.234:8080/VerticalSearch-0.0.1-SNAPSHOT/BingSearch?q="+qresult+"&callback=?";
    var strURL1 = "http://10.2.4.216:8080/Trending/category-water?callback=?";
    var strURL2 = "http://10.2.4.216:8080/Trending/category-sewage?callback=?";
    var strURL3 = "http://10.2.4.216:8080/Trending/category-electricity?callback=?";
    var strURL4 = "http://10.2.4.216:8080/Trending/category-road?callback=?";
    var strURL5 = "http://10.2.4.216:8080/Trending/category-food?callback=?";
    
    $("#fulltrendstable").empty();
    $("#fulltrendsheading").empty();
    $("#fulltrendsheading").append("Today's Common Trending Topics");
          
    $.ajax({
	url:strURL1,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(trendresult1){
		// Display Results on the Screen
		    var i = 0;
		    var j=0;
		    for(i = 0; i<5; i++){
			for(j = 0; j<1; j++){			    
			
			    //alert(trendresult1.catContent[i].trendArray.length);
			    var fulltrendstitle = trendresult1.catContent[i].trendArray[j].contentTitle;
			    var fulltrendsurl = trendresult1.catContent[i].trendArray[j].sourceURL;
			    var fulltrendsnippet = trendresult1.catContent[i].trendArray[j].snippet;
			    var fulltrendsimageurl = trendresult1.catContent[i].trendArray[j].imageURL;
			    
			    var innerHTML = "";
			    $("#fulltrendstable").append("<tr id=\"fulltrendsrow\"></tr>");
			    $("#fulltrendsrow").append("<img class=\"trendsinliner\" id=\"fulltrendsimage\" src=\""+fulltrendsimageurl+"\"/><div class=\"trendsinliner\" id=\"fulltrendstextblock\"><a href=\""+fulltrendsurl+"\"><span class=\"fulltrendstitle\">" +fulltrendstitle+"</span></a><span class=\"fulltrendssnippet\">"+fulltrendsnippet+"</span></div>");
		
			    }
			}	

		    },
		    error: function(req, error) { 
			console.log(req);
			console.log(error);
			//alert("AJAX-JSON Error : "+req.error);
		    }
		});
    
    $.ajax({
	url:strURL2,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(trendresult2){
		// Display Results on the Screen
		    var i = 0;
		    var j=0;
		    for(i = 0; i<5; i++){
			for(j = 0; j<1; j++){			    
			
			    //alert(trendresult2.catContent[i].trendArray.length);
			    var fulltrendstitle = trendresult2.catContent[i].trendArray[j].contentTitle;
			    var fulltrendsurl = trendresult2.catContent[i].trendArray[j].sourceURL;
			    var fulltrendsnippet = trendresult2.catContent[i].trendArray[j].snippet;
			    var fulltrendsimageurl = trendresult2.catContent[i].trendArray[j].imageURL;
			    
			    var innerHTML = "";
			    //$("#fulltrendstable").append("<tr id=\"fulltrendsrow\"></tr>");
			    $("#fulltrendsrow").append("<img class=\"trendsinliner\" id=\"fulltrendsimage\" src=\""+fulltrendsimageurl+"\"/><div class=\"trendsinliner\" id=\"fulltrendstextblock\"><a href=\""+fulltrendsurl+"\"><span class=\"fulltrendstitle\">" +fulltrendstitle+"</span></a><span class=\"fulltrendssnippet\">"+fulltrendsnippet+"</span></div>");
		
			    }
			}	

		    },
		    error: function(req, error) { 
			console.log(req);
			console.log(error);
			//alert("AJAX-JSON Error : "+req.error);
		    }
		});
    
    $.ajax({
	url:strURL3,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(trendresult3){
		// Display Results on the Screen
		    var i = 0;
		    var j=0;
		    for(i = 0; i<5; i++){
			for(j = 0; j<1; j++){			    
			
			    //alert(trendresult3.catContent[i].trendArray.length);
			    var fulltrendstitle = trendresult3.catContent[i].trendArray[j].contentTitle;
			    var fulltrendsurl = trendresult3.catContent[i].trendArray[j].sourceURL;
			    var fulltrendsnippet = trendresult3.catContent[i].trendArray[j].snippet;
			    var fulltrendsimageurl = trendresult3.catContent[i].trendArray[j].imageURL;
			    
			    var innerHTML = "";
			    //$("#fulltrendstable").append("<tr id=\"fulltrendsrow\"></tr>");
			    $("#fulltrendsrow").append("<img class=\"trendsinliner\" id=\"fulltrendsimage\" src=\""+fulltrendsimageurl+"\"/><div class=\"trendsinliner\" id=\"fulltrendstextblock\"><a href=\""+fulltrendsurl+"\"><span class=\"fulltrendstitle\">" +fulltrendstitle+"</span></a><span class=\"fulltrendssnippet\">"+fulltrendsnippet+"</span></div>");
		
			    }
			}	

		    },
		    error: function(req, error) { 
			console.log(req);
			console.log(error);
			//alert("AJAX-JSON Error : "+req.error);
		    }
		});
    
    $.ajax({
	url:strURL4,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(trendresult4){
		// Display Results on the Screen
		    var i = 0;
		    var j=0;
		    for(i = 0; i<5; i++){
			for(j = 0; j<1; j++){			    
			
			    //alert(trendresult4.catContent[i].trendArray.length);
			    var fulltrendstitle = trendresult4.catContent[i].trendArray[j].contentTitle;
			    var fulltrendsurl = trendresult4.catContent[i].trendArray[j].sourceURL;
			    var fulltrendsnippet = trendresult4.catContent[i].trendArray[j].snippet;
			    var fulltrendsimageurl = trendresult4.catContent[i].trendArray[j].imageURL;
			    
			    var innerHTML = "";
			    //$("#fulltrendstable").append("<tr id=\"fulltrendsrow\"></tr>");
			    $("#fulltrendsrow").append("<img class=\"trendsinliner\" id=\"fulltrendsimage\" src=\""+fulltrendsimageurl+"\"/><div class=\"trendsinliner\" id=\"fulltrendstextblock\"><a href=\""+fulltrendsurl+"\"><span class=\"fulltrendstitle\">" +fulltrendstitle+"</span></a><span class=\"fulltrendssnippet\">"+fulltrendsnippet+"</span></div>");
		
			    }
			}	

		    },
		    error: function(req, error) { 
			console.log(req);
			console.log(error);
			//alert("AJAX-JSON Error : "+req.error);
		    }
		});
    
    $.ajax({
	url:strURL5,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(trendresult5){
		// Display Results on the Screen
		    var i = 0;
		    var j=0;
		    for(i = 0; i<5; i++){
			for(j = 0; j<1; j++){			    
			
			    //alert(trendresult5.catContent[i].trendArray.length);
			    var fulltrendstitle = trendresult5.catContent[i].trendArray[j].contentTitle;
			    var fulltrendsurl = trendresult5.catContent[i].trendArray[j].sourceURL;
			    var fulltrendsnippet = trendresult5.catContent[i].trendArray[j].snippet;
			    var fulltrendsimageurl = trendresult5.catContent[i].trendArray[j].imageURL;
			    
			    var innerHTML = "";
			    //$("#fulltrendstable").append("<tr id=\"fulltrendsrow\"></tr>");
			    $("#fulltrendsrow").append("<img class=\"trendsinliner\" id=\"fulltrendsimage\" src=\""+fulltrendsimageurl+"\"/><div class=\"trendsinliner\" id=\"fulltrendstextblock\"><a href=\""+fulltrendsurl+"\"><span class=\"fulltrendstitle\">" +fulltrendstitle+"</span></a><span class=\"fulltrendssnippet\">"+fulltrendsnippet+"</span></div>");
		
			    }
			}	

		    },
		    error: function(req, error) { 
			console.log(req);
			console.log(error);
			//alert("AJAX-JSON Error : "+req.error);
		    }
		});

}		


function expandTrends(trendid,trendname){
    var strURL = "http://10.2.4.216:8080/Trending/category-"+trendid+"?callback=?";

    $("#fulltrendstable").empty();
    
    $.ajax({
	url:strURL,
	timeout:10000,
	async: true,
	type: 'GET',
	dataType: 'jsonp',
	crossDomain:true,
	success: function(expandresult){
		    // Display Results on the Screen
		    var trend_name = trendname;
		    var j=-1;
		    for(i in expandresult.catContent){
			if(expandresult.catContent[i].trendName == trend_name){
			    j = i;
			    break;
			}
		    }
		    $("#fulltrendsheading").empty();
		    $("#fulltrendsheading").append("Refined Trends on :"+trend_name);
		    
		    for(i=0; i<expandresult.catContent[j].trendArray.length; i++){
			var fulltrendstitle = expandresult.catContent[j].trendArray[i].contentTitle;
			var fulltrendsurl = expandresult.catContent[j].trendArray[i].sourceURL;
			var fulltrendsnippet = expandresult.catContent[j].trendArray[i].snippet;
			var fulltrendsimageurl = expandresult.catContent[j].trendArray[i].imageURL;
		    
			var innerHTML = "";
			$("#fulltrendstable").append("<tr id=\"fulltrendsrow\"></tr>");
			$("#fulltrendsrow").append("<img class=\"trendsinliner\" id=\"fulltrendsimage\" src=\""+fulltrendsimageurl+"\"/><div class=\"trendsinliner\" id=\"fulltrendstextblock\"><a href=\""+fulltrendsurl+"\"><span class=\"fulltrendstitle\">" +fulltrendstitle+"</span></a><span class=\"fulltrendssnippet\">"+fulltrendsnippet+"</span></div>");
		    }
		},
		    error: function(req, error) { 
			console.log(req);
			console.log(error);
			//alert("AJAX-JSON Error : "+req.error);
		}
	    }); 
}




$(document).ready(function(){
    addRibbon();
})

function trender(){
    document.getElementById('vsearcher').style.backgroundImage="url('images/vsearch.png')";
    document.getElementById('solver').style.backgroundImage="url('images/solutions.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trends.png')";
    document.getElementById('reporter').style.backgroundImage="url('images/report.png')";
    document.getElementById('buzzer').style.backgroundImage="url('images/buzz.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trendscolor.png')";
    addTrends();
}

$(document).on('click', '.trendtext', function(){ 
    var trendid = $(this).attr('id');
    var trendname = $(this).text();
    document.getElementById('vsearcher').style.backgroundImage="url('images/vsearch.png')";
    document.getElementById('solver').style.backgroundImage="url('images/solutions.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trends.png')";
    document.getElementById('reporter').style.backgroundImage="url('images/report.png')";
    document.getElementById('buzzer').style.backgroundImage="url('images/buzz.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trendscolor.png')";
    $("#vsearchcolumn").hide("slow");
    $("#trendscolumn").show("slow");
    $("#buzzcolumn").hide("slow");
    $("#solutioncolumn").hide("slow");
    $("#trendscolumn").show("slow");
    expandTrends(trendid,trendname);
});
    



