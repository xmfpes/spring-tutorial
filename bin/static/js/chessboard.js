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
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	ev.dataTransfer.setData("Text", ev.target.parentNode.id);
	var dragId = ev.target.parentNode.id;
	$(function() {
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	});
	console.log(dragId);
	$.ajax({
		url : '/chess/possibilityPosition/',
		type : 'POST',
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
			}
			
		},
		error : function(data) {
			alert("error" + data);
		}
	});
}

function drop(ev) {

	var data = ev.dataTransfer.getData("Text");
	var before = document.getElementById(data);

	var myPositionId = before.id;
	var targetPositionId;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	if (ev.target == before.children[0]) {
		return;
	}
	if (ev.target.tagName == 'A') {
		ev.target.parentNode.appendChild(before.children[0]);
		targetPositionId = ev.target.parentNode.id;
		ev.target.remove();
	} else {
		before.appendChild(ev.target.children[0]);
		ev.target.appendChild(before.children[0]);
		targetPositionId = ev.target.id;
	}
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	 
	$(function() {
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	});
	ev.preventDefault();
	$.ajax({
		url : '/chess/move/',
		type : 'PUT',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "PUT"
		},
		data : JSON.stringify({
			"position" : myPositionId,
			"target" : targetPositionId
		}),
		success : function(data) {
			alert('Load was performed.');
		},
		error : function(data) {
			alert("error" + data);
		}
	});
}