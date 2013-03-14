function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function getContacts(qresult) {
		//var strURL = "http://localhost:8080/RestSimpleAppMaven-0.0.1-SNAPSHOT/rest/BingSearch?q="+qresult+"&callback=?";
		
		//var strURL = "http://10.2.4.134:8080/JSONBuilder/solutions/Solution?q="+qresult+"&callback=?";
		var strURL = "http://0.0.0.0:8080/JSONBuilder/solutions/Solution?q="+qresult+"&callback=?";
        
            
		$.ajax({
			url:strURL,
			timeout:10000,
			async: true,
			type: 'GET',
			dataType: 'jsonp',
			crossDomain:true,
			success: function(contacts){
				// Display Results on the Screen
			    for(i = 0; i<contacts.contactList.length; i++){
				var deptname = contacts.contactList[i].dept_name;
				var id = contacts.contactList[i].departmentid;
				var address = contacts.contactList[i].address;
				var location = contacts.contactList[i].location;
				var depttype = contacts.contactList[i].dept_type;
				var number = contacts.contactList[i].phone;
				var email = contacts.contactList[i].emailId;

				
				
				$("#solutionspace").append("<table id=\"my_table1\" class=\"tableclass\"></table>");
				$("#my_table1").append("<tr><td class=\"deptcontact\">" +deptname+ "</td>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">" +depttype+ "</td></tr>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">" +id+ "</td></tr>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">" +address+ "</td></tr>");				
				$("#my_table1").append("<tr><td class=\"columnsizer\">" +location+ "</td></tr>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">" +number+ "</td></tr>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">" +email+ "</td></tr>");
			    }
			    
				/*var innerHTML = "";
				innerHTML = "<span>"+contactname+"</span>";
				innerHTML = innerHTML + "<span>"+address+"</span>";
				innerHTML = innerHTML + "<span>"+location+"</span>";
				innerHTML = innerHTML + "<hr style='width:99%'/>";
				$("#solutionspace").append(innerHTML);*/

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
    getContacts(searchquery);
});