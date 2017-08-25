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
    var before = document.getElementById(data);
    if(ev.target == before.children[0]){
    		return;
    }
    if(ev.target.tagName == 'A'){
	    	ev.target.parentNode.appendChild(before.children[0]);
	    	ev.target.remove();
    }else{
	    	before.appendChild(ev.target.children[0]);
	    	ev.target.appendChild(before.children[0]);
    }
    ev.preventDefault();
}