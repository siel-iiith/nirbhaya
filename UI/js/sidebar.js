/*functions to change colors of sidebardivs */


function gReporter(){
    document.getElementById('vsearcher').style.backgroundImage="url('images/vsearch.png')";
    document.getElementById('solver').style.backgroundImage="url('images/solutions.png')";
    document.getElementById('trender').style.backgroundImage="url('images/trends.png')";
    document.getElementById('reporter').style.backgroundImage="url('images/report.png')";
    document.getElementById('buzzer').style.backgroundImage="url('images/buzz.png')";
    document.getElementById('reporter').style.backgroundImage="url('images/reportcolor.png')";
}

/* code to change the main div based on selection */

$(document).ready(function(){
    $("#vsearcher").click(function(e){        
        $("#solutioncolumn").hide("slow");
        $("#trendscolumn").hide("slow");
        $("#buzzcolumn").hide("slow");        
        $("#vsearchcolumn").show("slow");
    });
    $("#buzzer").click(function(e){        
        $("#solutioncolumn").hide("slow");
        $("#trendscolumn").hide("slow");
        $("#vsearchcolumn").hide("slow");
        $("#buzzcolumn").show("slow");
    });
    $("#solver").click(function(e){
        $("#vsearchcolumn").hide("slow");
        $("#trendscolumn").hide("slow");
        $("#buzzcolumn").hide("slow");
        $("#solutioncolumn").show("slow");        
    });
    $("#trender").click(function(e){
        $("#vsearchcolumn").hide("slow");
        $("#buzzcolumn").hide("slow");
        $("#solutioncolumn").hide("slow");
        $("#trendscolumn").show("slow");        
    });
});

$(document).ready(function(){
    $('#reporter').click(function(e){
        $('#overlay').fadeIn('fast',function(e){
            $('#grievancereporter').slideDown("slow");  
        });
        
    });
    $('.greportclose').click(function(){
        $('#grievancereporter').slideUp('slow',function(){
            $('#overlay').fadeOut("fast");
        });
    }); 
});

/* function to submit grievance */
function sendGrievance(){

  var strURL = "http://10.2.4.238:8080/UserGrievance/grievance/add?";
  var grievName =  $("#nameinput").val();
  var grievLoc = $("#lxninput").val();
  var grievComment = $("#commentinput").val();
  var grievType = $("#typeinput").val();
  // name=grievance_name&comments=comment&location=locationa_name&type=type_name
  strURL = strURL+"name="+grievName+"&comments="+grievComment+"&location="+grievLoc+"&type="+grievType+"&callback=?"


  //alert(grievName);
       $.ajax({
       url:strURL,
       timeout:10000,
       async: false,
       type: 'GET',
       dataType: 'jsonp',
       crossDomain: true,
       success: function(result){
         alert("Thank You! We registered your grievance");
         $('#grievancereporter').slideUp('slow',function(){
            $('#overlay').fadeOut("fast");
        });
         
         
       },
       error: function(req, error) { 
         console.log(req);
         console.log(error);
         alert("Sorry we cannot collect your Grievance");
       }
     });
}






