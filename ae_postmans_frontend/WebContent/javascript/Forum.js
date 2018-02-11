$(document).ready(function() {
	
	$("#mytable #checkall").click(function() {
		if ($("#mytable #checkall").is(':checked')) {
			$("#mytable input[type=checkbox]").each(function() {
				$(this).prop("checked", true);
			});

		} else {
			$("#mytable input[type=checkbox]").each(function() {
				$(this).prop("checked", false);
			});
		}
	});

	
	
	
	forumQ.getAllQuestions().then(function (data) {
		forumQ.createQuestionsTable(data);
		
		$(".editButton").click(function() {
			var tr = this.closest("tr");
			forumQ.selectedUserRow = $(tr);
			AppUtils.selectedQuestionId = $(tr).find(".id").text();
			console.log(AppUtils.selectedUserId);
		});
		
		$(".answersButton").click(function() {
			var tr = this.closest("tr");
			forumQ.selectedUserRow = $(tr);
			AppUtils.selectedQuestionIdk = $(tr).find(".id").text();
			AppUtils.changeActiveTab($(".forumAnswers"), "forumAnswers.html", $("#answersDialog"), false);
			console.log(AppUtils.selectedQuestionIdk);
		});
		
		
	}).catch(function (err) {
		console.log(err);
	});
	
	$("#approveAnswer").click(function(){
		if(validateForumAnswer())
		{
		  forumQ.setAnswer(AppUtils.selectedQuestionId);
		}
	});
	// $("[data-toggle=tooltip]").tooltip();
	
});

var forumQ = {};

forumQ.getAllQuestions = function() {
	return AppUtils.getRequestPromise(AppUtils.backEndUrl + "allQuestions");
};

forumQ.createQuestionsTable = function(allQuestions) {
	$('tbody').html("");
	for (i in allQuestions) {
		var row ="";
		row +="<tr><td class=\"id\">" + allQuestions[i].id + "</td>";
		row +="<td>" + allQuestions[i].userId + "</td>";
		row +="<td>" + allQuestions[i].msg + "</td>";
		row +="<td>" + AppUtils.setDateFromJson(allQuestions[i].date) + "</td>";
		row +="<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"editButton btn btn-primary btn-xs\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#edit\"> <span class=\"glyphicon glyphicon-pencil\"></span></button> </p></td>";
		row +="<td ><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Answers\"><button class=\"answersButton btn btn-success\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#answers\"> <span class=\"glyphicon glyphicon-pencil\"></span></button> </p></td>";
		row +="</tr>";
		$('tbody').append(row);
	}
};

$("#sendQuestion").click(function (event) {
	event.preventDefault();
   if(validateForumForm())
   {
	    forumQ.setQuestions();
	  	//AppUtils.showInfoMessage("Your question has been submitted")
   }
});

forumQ.setQuestions = function() {
   AppUtils.postRequestPromise(AppUtils.backEndUrl + "setQuestion", JSON.stringify($("#askQuestion").serializeJSON())).
   then(function(data) {
   	AppUtils.showInfoMessage("Your question has been submitted!");   
   })
   .catch(function(err) {
   	 AppUtils.showErrorMessage(err.responseText);
   });
};

forumQ.setAnswer= function(userId) {
	   AppUtils.postRequestPromise(AppUtils.backEndUrl + "setAnswer/"+userId, JSON.stringify($("#answerQuestion").serializeJSON()))
		.then(function(data) {
		AppUtils.showInfoMessage("Your answer successfully submitted");
		
		}).catch(function(err) {
			console.log(err);
			AppUtils.showErrorMessage(err.responseText);
		});  
		
		$("div#edit").click();
	};

	function validateForumForm() {
		var goodInput = true;
		var msg;
		msg = document.getElementById("msg").value;
		
		if(msg !="")
		{
			goodInput = true;
		}
		else
		{
			AppUtils.showErrorMessage("Your Input is Empty!");
			goodInput = false;
		}
		
		return goodInput;
	}
	
	function validateForumAnswer() {
		var goodInput = true;
		var msg;
		msg = document.getElementById("msgAnswer").value;
		
		if(msg !="")
		{
			goodInput = true;
		}
		else
		{
			AppUtils.showErrorMessage("Your Input is Empty!");
			goodInput = false;
		}
		
		return goodInput;
	}


