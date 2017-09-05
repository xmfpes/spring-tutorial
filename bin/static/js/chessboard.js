token = $("meta[name='_csrf']").attr("content");
header = $("meta[name='_csrf_header']").attr("content");
var turn = false;

$(function() {
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	
	var possibilityPosition;
	var dragId = ev.target.parentNode.id;
	ev.dataTransfer.setData("myId", dragId);
	
	$.ajax({
		url : '/chessrest/possibilityPosition',
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
				document.getElementById(data[i].position).style.border = "2px inset blue";
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
	var beforePositionId = ev.dataTransfer.getData("myId");
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
	
	if (ev.target === before.children[0]) {
		return;
	}
	
	if(isPossiblePosition == false){
		alert("이동 할 수 없는 위치입니다.");
		return;
	}
	
	$.ajax({
		url : '/chessrest/move/',
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
			if(data.status == true){
				document.getElementById("whitepoint").innerHTML = data.whitepoint;
				document.getElementById("blackpoint").innerHTML = data.blackpoint;
				var blanknode = document.createElement("a");
				blanknode.setAttribute("draggable", "true");
				blanknode.setAttribute("ondragstart", "drag(event)");
				blanknode.setAttribute("class", "chess-item");
				
				if (ev.target.tagName == 'A') {
					console.log("a move");
					before.appendChild(blanknode);
					ev.target.parentNode.appendChild(before.children[0]);
					ev.target.remove();
				} else {
					console.log(ev.target.childNodes.length);
					for(var i = ev.target.childNodes.length-1; i>=0; i--){
						ev.target.childNodes[i].remove();
					}
					before.appendChild(blanknode);
					ev.target.appendChild(before.children[0]);
				}
				 
				ev.preventDefault();
				
				console.log("move complete");
				setTurn();
			}else{
				alert("니 턴이 아닙니다.");
				return ;
			}
		},
		error : function(data) {
			console.log(data);
		}
	});
	
	
}

function setTurn(){
	if(turn == true){
		document.getElementById("turn").innerHTML = "백이 둘 차례";
		document.getElementById("turn").setAttribute('class', "btn btn-md");
		turn = false;
	}
	else{
		document.getElementById("turn").innerHTML = "흑이 둘 차례";
		document.getElementById("turn").setAttribute('class', 'btn btn-md btn-filled');
		turn = true;
	}
}