/*function tabSwitch(new_tab , new_content){
    document.getElementById('content1').style.display = "none";
    document.getElementById('content2').style.display = "none";
    document.getElementById('content3').style.display = "none";
    document.getElementById(new_content).style.display = "block";
    document.getElementById('tab1').className = '';
    document.getElementById('tab2').className = '';
    document.getElementById('tab3').className = '';
    document.getElementById(new_tab).className = 'active';
}*/
 
$(document).ready(function(){
   $("a.tab").click(function(e){
    $(".active").removeClass("active");
    $(this).addClass("active");
    $(".content").slideUp("slow");
    var content_show = $(this).attr("title");  
    $("#"+content_show).slideDown("slow");
    e.preventDefault();
   });
});