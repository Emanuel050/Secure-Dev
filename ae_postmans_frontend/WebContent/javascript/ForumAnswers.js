
$(document).ready(function() {
	$("#myAnswerstable #checkall").click(function() {
		if ($("#myAnswerstable #checkall").is(':checked')) {
			$("#myAnswerstable input[type=checkbox]").each(function() {
				$(this).prop("checked", true);
			});

		} else {
			$("#myAnswerstable input[type=checkbox]").each(function() {
				$(this).prop("checked", false);
			});
		}
	});

	

	
	forumQuestionAnswer.getAllAnswers(AppUtils.selectedQuestionIdk).then(function (data) {
		forumQuestionAnswer.createAnswersTable(data);
				
		
	}).catch(function (err) {
		console.log(err);
	});
	
	//$("[data-toggle=tooltip]").tooltip();
});

var forumQuestionAnswer = {};

forumQuestionAnswer.getAllAnswers = function(questionId) {
	return AppUtils.getRequestPromise(AppUtils.backEndUrl + "allQuestionAnswers/"+questionId);
};

forumQuestionAnswer.createAnswersTable = function(allAnswers) {
	$('#answersQ').html("");
	for (i in allAnswers) {
		var row ="";
		row +="<tr><td class=\"id2\">" + allAnswers[i].id + "</td>";
		row +="<td>" + allAnswers[i].userId + "</td>";
		row +="<td>" + allAnswers[i].msg + "</td>";
		//row +="<td>" + new Date(allAnswers[i].date) + "</td>";
		row +="<td>" + AppUtils.setDateFromJson(allAnswers[i].date) + "</td>";
		row +="</tr>";
		$('#answersQ').append(row);
	}
};



