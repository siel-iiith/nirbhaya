$(document).ready(function(){
   trendShow();
});
   
function trendShow(){
   var strURL = "http://search.iiit.ac.in:8080/Trending/trending-types?callback=?";
   $.ajax({
      url:strURL,
      timeout:10000,
      async: true,
      type: 'GET',
      dataType: 'jsonp',
      crossDomain:true,
      success: function(result){
         /*function to load the sandbar list in main page*/
         var children = $('ul[class="nav"]').children();
         for(var i=0; i<result.categories.length; i++){
            children[i].innerHTML = result.categories[i].name;
         }
         /*function to load category list*/
         for(var j=0;j<result.categories[0].subcategories.length; j++){
            $('#categories').append('<a><li>' + result.categories[0].subcategories[j].subname + '</li></a> ');
         }
         
         /* loading categories based on clicks */
         $('ul[class="nav"] li').click(function(){
            var namelist = $(this).text();
               $("#categories").empty();
               var j = -1;
                  for(i in result.categories){
                     if ( result.categories[i].name == namelist ) {
                        j = i;
                        break;
                     }
                  }
               for(i=0;i<result.categories[j].subcategories.length;i++){
                  var innertext = result.categories[j].subcategories[i].subname;
                  $("#categories").append('<a><li>' + innertext + '</li></a>');
               }
         });
      },
      error: function(req, error) { 
	 console.log(req);
	 console.log(error);
      }
   });
}



/* code to select the element and send query to userpage from main page */
$(document).on('click', '#categories li', function(){ 
   var qcategory = ($(this).text());         
   var url = "userpage.html?query="+qcategory;
   $(location).attr('href',url);
});

$(document).ready(function(){
   $('ul[class="nav"] li').click(function(e){
      $(".navactive").removeClass("navactive");
      $(this).addClass("navactive");
      e.preventDefault();
   });
})
