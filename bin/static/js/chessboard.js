$(function(){
	var index = 0;
	var source;

    $('#boards tr td').click(function() {
      	var position = $(this).attr('id');
      	var value = index % 2;
      	
      	if ( value == 0 ) {
      		source = position;
      	} else {
	    	var moveUrl = "/move?source=" + source + "&target=" + position;
	    	console.log(moveUrl);
	    	$(location).attr('href',moveUrl);
      	}
      	
      	index++;
      	return false;
    });
});
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("Text", ev.target.parentNode.id);
}

function drop(ev) {
    var data = ev.dataTransfer.getData("Text");
    ev.target.append(document.getElementById(data).children[0]);
    ev.preventDefault();
}