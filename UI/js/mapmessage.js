function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function displayMessage(entry, searchquery){
    var mapentry = entry;
    var strURL = "http://10.2.4.239:8080/HeatMap/mapmessage?location=" +mapentry+"&problem="+searchquery+"&callback=?";
    $.ajax({
	    url:strURL,
	    timeout:10000,
	    async: true,
            type: 'GET',
            dataType: 'jsonp',
            crossDomain:true,            
            success: function(result){
		// Display Results on the Screen
                    var data = result.results;
                    $("#mapmsg").empty();
                    $("#mapmsg").append(data);
                },
		    error: function(req, error) {
                        console.log(req);
                    	console.log(error);
			alert("AJAX-JSON Error : "+req.error);
		    }
		});
}



$(document).ready(function(){
    $('button').click(function(){
        var entry = $('#mapquery').val();
	var searchquery = getURLParameter("query");
        displayMessage(entry , searchquery);
    });
});