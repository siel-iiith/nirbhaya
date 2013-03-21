$(document).ready(function(){ 
  $('#slideshow').cycle({
    fx:'fade',
    speed:  '2000',
    timeout: 1,
    pauseOnPagerHover: 'true',
    pager:  '#pager',
    pagerAnchorBuilder: function(idx, slide) { 
        return '<li><a href="#"><img src="' + slide.src + '" width="80" height="80" /></a></li>'; 
    }
  });
  $("#pager").simplyScroll();
});