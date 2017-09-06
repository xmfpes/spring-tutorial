token = $("meta[name='_csrf']").attr("content");
header = $("meta[name='_csrf_header']").attr("content");
var turn = false;

$(function() {
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
$(document).ready(function() {

	$('#create').click(function() {
		var count = $('#carCount').val();
		$.ajax({
			url : '/car/create',
			type : 'POST',
			async : false,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			data : JSON.stringify({
				"carCount" : count
			}),

			success : function(data) {
				for (var i = 0; i < count; i++) {
					
					var blanknode = document.createElement("input");
					blanknode.setAttribute("class", "car-" + i);
					$("#carBody").append(blanknode);
				}
			},
			error : function(data) {
				alert("error" + data);
			}
		});

	});
});

var Timer = {
	isPending : false,
	second : 0,
	min : 0,
	startTimer : function() {
		if (this.isPending) {
			runAjax();
		}
		setTimeout("Timer.startTimer()", 1000);
	},
	pauseTimer : function() {
		this.isPending = false;
	},
	resetTimer : function() {
		this.isPending = false;
		this.min = 0;
		this.second = 0;
		$('#min').html("00");
		$('#sec').html("00");
	}
}
$(document).ready(function() {
	$('#start').click(function() {
		Timer.isPending = true;
		Timer.startTimer();
	});
	$('#reset').click(function() {
		Timer.resetTimer();
	});
	$('#pause').click(function() {
		Timer.pauseTimer();
	});
});

function runAjax(){
	$.ajax({
		url : '/car/run',
		type : 'POST',
		async : false,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		success : function(data) {
			$.each(data, function(key, value) {
				console.log(data[key]);
				$('.car-' + key).attr("value", data[key].location);
			});
			console.log("cycle end");
		},
		error : function(data) {
			alert("error" + data);
		}
	});
}