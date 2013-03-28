$(document).ready(function(){
    $("#expander").click(function(e){
        $("#leftcolumn").animate({marginLeft:'0px'},"slow");
        $("#rightcolumn").slideDown("slow");
        $(this).hide("slow");
        $("#closer").show("slow");
        e.preventDefault();
    });
    $("#closer").click(function(e){
        $("#leftcolumn").animate({marginLeft:'320px'},"slow");
        $("#rightcolumn").slideUp("slow");
        $(this).hide("slow");
        $("#expander").show("slow");
        e.preventDefault();
    });
});