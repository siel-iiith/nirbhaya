$(document).ready(function(){
    $("#vsearcher").click(function(e){
        $("#solutioncolumn").hide("slow");
        $("#trendscolumn").hide("slow");
        $("#vsearchcolumn").show("slow");        
    });
    $("#solver").click(function(e){
        $("#vsearchcolumn").hide("slow");
        $("#trendscolumn").hide("slow");
        $("#solutioncolumn").show("slow");        
    });
    $("#trender").click(function(e){
        $("#vsearchcolumn").hide("slow");
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

$(document).ready(function(){
    
});