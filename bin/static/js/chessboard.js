token = $("meta[name='_csrf']").attr("content");
header = $("meta[name='_csrf_header']").attr("content");

$(function() {
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(function() {
	var index = 0;
	var source;

	$('#boards tr td').click(function() {
		var position = $(this).attr('id');
		var value = index % 2;

		if (value == 0) {
			source = position;
		} else {
			var moveUrl = "/move?source=" + source + "&target=" + position;
			console.log(moveUrl);
			$(location).attr('href', moveUrl);
		}

		index++;
		return false;
	});
});

function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	
	
	var possibilityPosition;
	ev.dataTransfer.setData("Text", ev.target.parentNode.id);
	var dragId = ev.target.parentNode.id;

	console.log(dragId);
	$.ajax({
		url : '/chess/possibilityPosition/',
		type : 'POST',
		async: false,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		data : JSON.stringify({
			"position" : dragId
		}),
		success : function(data) {
			for (var i = 0; i < data.length; i++) { 
				console.log(i + ": " +data[i].position);
				document.getElementById(data[i].position).style.border = "4px dotted blue";
			}
			possibilityPosition = data;
		},
		error : function(data) {
			alert("error" + data);
		}
	});
	ev.dataTransfer.setData("possibilityPosition",JSON.stringify(possibilityPosition));
}

function drop(ev) {
	var beforePositionId = ev.dataTransfer.getData("Text");
	var before = document.getElementById(beforePositionId);
	
	var possibilityPosition = JSON.parse(ev.dataTransfer.getData("possibilityPosition"));
	var isPossiblePosition = false;
	var targetId;

	if(ev.target.tagName == 'A'){
		targetId = ev.target.parentNode.id;
	}else{
		targetId = ev.target.id;
	}
	
	for (var i in possibilityPosition) { 
		if(targetId === possibilityPosition[i].position){
			isPossiblePosition = true;
		}
		document.getElementById(possibilityPosition[i].position).style.border = "";
	}
	
	if (ev.target == before.children[0]) {
		return;
	}
	
	if(isPossiblePosition == false){
		alert("이동 할 수 없는 위치입니다.");
		return;
	}
	
	var blanknode = document.createElement("a");
	blanknode.setAttribute("draggable", "true");
	blanknode.setAttribute("ondragstart", "drag(event)");
	blanknode.setAttribute("class", "chess-item");
	
	if (ev.target.tagName == 'A') {
		
		ev.target.removeChild(ev.target.childNodes[0]);
		before.appendChild(blanknode);
		ev.target.parentNode.appendChild(before.children[0]);
		ev.target.remove();
	} else {
		ev.target.removeChild(ev.target.childNodes[0]);
		before.appendChild(blanknode);
		ev.target.appendChild(before.children[0]);
	}
	 
	ev.preventDefault();
	$.ajax({
		url : '/chess/move/',
		type : 'PUT',
		async: false,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "PUT"
		},
		data : JSON.stringify({
			"position" : beforePositionId,
			"target" : targetId
		}),
		success : function(data) {
			console.log(data);
		},
		error : function(data) {
			console.log(data);
		}
	});
	getPoint();
}

function getPoint(){
	$.ajax({
		url : '/chess/getPoint/',
		type : 'POST',
		async: false,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		success : function(data) {
			console.log(data.white);
			console.log(data.black);
			document.getElementById("whitepoint").innerHTML = data.white;
			document.getElementById("blackpoint").innerHTML = data.black;
		},
		error : function(data) {
			console.log(data);
		}
	});
}

$(document).ready(function(){
	getPoint();
});