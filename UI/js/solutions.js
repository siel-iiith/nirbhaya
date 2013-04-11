function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function getContacts(qresult) {
		//var strURL = "http://localhost:8080/RestSimpleAppMaven-0.0.1-SNAPSHOT/rest/BingSearch?q="+qresult+"&callback=?";
		
		var strURL = "http://10.2.4.226:8080/SolutionsModule/solutions/Solution?q="+qresult+"&callback=?";
        
            
		$.ajax({
			url:strURL,
			timeout:10000,
			async: true,
			type: 'GET',
			dataType: 'jsonp',
			crossDomain:true,
			success: function(contacts){
				// Display Results on the Screen
				$("#personspace").html("<p id=\"contactheading\"> Person Contacts for " +qresult+"</p>");
				if(contacts.contactList.person.length != 0)
				{
				for(i = 0; i<contacts.contactList.person.length; i++){
				    var per_name = contacts.contactList.person[i].name;
				    var per_designation = contacts.contactList.person[i].designation;
				    var per_emailid = contacts.contactList.person[i].emailid;
				    var per_deptname = contacts.contactList.person[i].perdeptname;
				    var per_phone = contacts.contactList.person[i].phone;
				    var per_location = contacts.contactList.person[i].location;


				    $("#personspace").append("<table id=\"my_table1\" class=\"tableclass\"></table>");
				    $("#my_table1").append("<tr><td class=\"deptcontact\">Contact Name: &nbsp;&nbsp;&nbsp;" +per_name+ "</td>");
				    $("#my_table1").append("<tr><td class=\"columnsizer\">Designation: &nbsp;&nbsp;&nbsp;" +per_designation+ "</td></tr>");
				    $("#my_table1").append("<tr><td class=\"columnsizer\">Department Board: &nbsp;&nbsp;&nbsp;" +per_deptname+ "</td></tr>");				
				    $("#my_table1").append("<tr><td class=\"columnsizer\">Contact Number: &nbsp;&nbsp;&nbsp;" +per_phone+ "</td></tr>");
				    $("#my_table1").append("<tr><td class=\"columnsizer\">Email Address: &nbsp;&nbsp;&nbsp;" +per_emailid+ "</td></tr>");
				    $("#my_table1").append("<tr><td class=\"columnsizer\">Location &nbsp;&nbsp;&nbsp;" +per_location+ "</td></tr>");
				    }
				}
				else{
				    $("#personspace").append("<table id=\"my_table1\" class=\"tableclass\"></table>");
				    $("#my_table1").append("<tr><td id=\"error\">Sorry! No Person specific Contacts found for your Grievance! To add a contact <a id=\"solclick\">Click Here</a></td><tr>");
				}
				
				$("#departmentspace").html("<p id=\"contactheading\"> Department Contacts for " +qresult+"</p>");
				if(contacts.contactList.department.length != 0){
				for(j = 0; j<contacts.contactList.department.length; j++){
				    var dept_name = contacts.contactList.department[j].dept_name;
				    var dept_address = contacts.contactList.department[j].Address;
				    var dept_url = contacts.contactList.department[j].url;
				    var dept_phone = contacts.contactList.department[j].phone;
				    var dept_location = contacts.contactList.department[j].location;
				    var dept_type = contacts.contactList.department[j].dept_type;


				    $("#departmentspace").append("<table id=\"my_table2\" class=\"tableclass\"></table>");
				    $("#my_table2").append("<tr><td class=\"deptcontact\">Department Name: &nbsp;&nbsp;&nbsp;" +dept_name+ "</td>");
				    $("#my_table2").append("<tr><td class=\"columnsizer\">Department Address: &nbsp;&nbsp;&nbsp;" +dept_address+ "</td></tr>");
				    $("#my_table2").append("<tr><td class=\"columnsizer\">Department Location: &nbsp;&nbsp;&nbsp;" +dept_location+ "</td></tr>");
				    $("#my_table2").append("<tr><td class=\"columnsizer\">Contact Number: &nbsp;&nbsp;&nbsp;" +dept_phone+ "</td></tr>");								
				    $("#my_table2").append("<tr><td class=\"columnsizer\">Department Type: &nbsp;&nbsp;&nbsp;" +dept_type+ "</td></tr>");
				    $("#my_table2").append("<tr><td> URL: &nbsp;<a class=\"columnsizer\" href=\""+dept_url+"\" target=\"_blank\">" +dept_url+"</a></td></tr>");
				    }
				}else{
				    $("#departmentspace").append("<table id=\"my_table2\" class=\"tableclass\"></table>");
				    $("#my_table2").append("<tr><td id=\"error\">Sorry! No Person specific Contacts found for your Grievance!To add a contact <a id=\"solclick\">Click Here</a></td><tr>");
				}

			},
			error: function(req, error) { 
				console.log(req);
				console.log(error);
				//alert("AJAX-JSON Error : "+req.error);
			}
		});

}		

function solver(){
    document.getElementById('vsearcher').style.backgroundImage="url('images/vsearch.png')";
    document.getElementById('solver').style.backgroundImage="url('images/solutions.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trends.png')";
    document.getElementById('reporter').style.backgroundImage="url('images/report.png')";
    document.getElementById('buzzer').style.backgroundImage="url('images/buzz.png')";
    document.getElementById('solver').style.backgroundImage="url('images/solutionscolor.png')";
    var searchquery = getURLParameter("query");
    getContacts(searchquery);    
}

/*code to send new contact*/
function sendSolution(){

  var strURL = "http://10.2.4.226:8080/SolutionsModule/solutions/addsolution/add?";
  var contName =  $("#solncontname").val();
  var contDesig = $("#solncontdesig").val();
  var contLoc = $("#solncontloc").val();
  var contEmail = $("#solncontemail").val();
  var contPhone = $("#solncontphone").val();  
  var contDept = $("#solncontdept").val();
  // name=grievance_name&comments=comment&location=locationa_name&type=type_name
  strURL = strURL+"name="+contName+"&designation="+contDesig+"&location="+contLoc+"&emailid="+contEmail+"&phone="+contPhone+"&perdeptname="+contDept+"&callback=?"


  //alert(grievName);
       $.ajax({
       url:strURL,
       timeout:10000,
       async: false,
       type: 'GET',
       dataType: 'jsonp',
       crossDomain: true,
       success: function(result){
         alert("Thank You! We registered your Solution");
	 $('#solutionreporter').slideUp('slow',function(){
            $('#overlay').fadeOut("fast");
        });
         
       },
       error: function(req, error) { 
         console.log(req);
         console.log(error);
         alert("Sorry we cannot collect your Contact Information");
       }
     });
}

    $(document).on('click', '#solclick' ,function(){
        $('#overlay').fadeIn('fast',function(e){
            $('#solutionreporter').slideDown("slow");  
        });        
    });
    
    $(document).on('click', '#solutionclose' ,function(){
        $('#solutionreporter').slideUp('slow',function(){
            $('#overlay').fadeOut("fast");
        });
    }); 


