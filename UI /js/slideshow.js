$(document).ready(function(){
   var json=$.getJSON("json-data-model.json", function(json) {
      var i=0;
      var children = $('ul[class="nav"]').children();
      for(var i=0; i<children.length; i++){
         children[i].innerHTML = json.grievance.categories[i].name;
         
      }
      //$("li").children().each(function(){
         //$(this).text(json.user.options.locations[i++]);
        // alert(i++);
      //});
   });
   $('ul[class="nav"] li').click(function(e){
      var collected = $('ul[class="nav"] li').text();
      var categories = $('ul[class="categories"]').children();
      $('ul[class="categories"]').empty();
        for(var j=0; j<categories.length; j++){
          $('ul[class="categories"]').append('<li>json.grievance.categories[i].name</li>');
          }
      e.preventDefault();
   });
});