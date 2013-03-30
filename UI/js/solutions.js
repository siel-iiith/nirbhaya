function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function getContacts(qresult) {
		//var strURL = "http://localhost:8080/RestSimpleAppMaven-0.0.1-SNAPSHOT/rest/BingSearch?q="+qresult+"&callback=?";
		
		var strURL = "http://localhost:8080/SolutionsModule/solutions/Solution?q="+qresult+"&callback=?";
        
            
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
				var contact_name = contacts.contactList[i].name;
				var departmentid = contacts.contactList[i].departmentid;
				var designation = contacts.contactList[i].designation;
				var perdeptname = contacts.contactList[i].perdeptname;
				var phone = contacts.contactList[i].phone;
				var emailid = contacts.contactList[i].emailid;


				$("#solutionspace").append("<table id=\"my_table1\" class=\"tableclass\"></table>");
				$("#my_table1").append("<tr><td class=\"deptcontact\">Contact Name: &nbsp;&nbsp;&nbsp;" +contact_name+ "</td>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">Department ID: &nbsp;&nbsp;&nbsp;" +departmentid+ "</td></tr>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">Designation: &nbsp;&nbsp;&nbsp;" +designation+ "</td></tr>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">Department Board: &nbsp;&nbsp;&nbsp;" +perdeptname+ "</td></tr>");				
				$("#my_table1").append("<tr><td class=\"columnsizer\">Contact Number: &nbsp;&nbsp;&nbsp;" +phone+ "</td></tr>");
				$("#my_table1").append("<tr><td class=\"columnsizer\">Email Address: &nbsp;&nbsp;&nbsp;" +emailid+ "</td></tr>");
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