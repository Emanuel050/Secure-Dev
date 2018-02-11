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
	
	Admin.getAllUsers().then(function (data) {
		Admin.createUsersTable(data);
		$(".deleteButton").click(function() {
			var tr = this.closest("tr");
			Admin.selectedUserRow = $(tr);
			Admin.selectedUserId = $(tr).find(".id").text();
			console.log(Admin.selectedUserId);
		});
		
		$(".editButton").click(function() {
			var tr = this.closest("tr");
			Admin.selectedUserRow = $(tr);
			AppUtils.selectedUserId = $(tr).find(".id").text();
			AppUtils.changeActiveTab($(".profile"), "profile.html", $("#editDialog"), false);
			console.log(AppUtils.selectedUserId);
		});
		
		$(".changeButton").click(function() {
			var tr = this.closest("tr");
			Admin.selectedUserRow = $(tr);
			AppUtils.selectedUserId = $(tr).find(".id").text();
			console.log(AppUtils.selectedUserId);
		});
	}).catch(function (err) {
		AppUtils.showErrorMessage(err.responseText);
        console.log(err);
        $(".profile").click();
	});
	
	$("#approveDelete").click(function(){
		Admin.deleteUser(Admin.selectedUserId);
	});
	
	$("#approveChange").click(function(){
		Admin.changeUserToAdmin(AppUtils.selectedUserId);
	});
	
	$("[data-toggle=tooltip]").tooltip();
});

var Admin = {};

Admin.getAllUsers = function() {
	return AppUtils.getRequestPromise(AppUtils.backEndUrl + "allUsers");
};

Admin.createUsersTable = function(allUsers) {
	$('tbody').html("");
	for (i in allUsers) {
		var row ="";
		row +="<tr><td class=\"id\">" + allUsers[i].id + "</td>";
		row +="<td>" + allUsers[i].firstName + "</td>";
		row +="<td>" + allUsers[i].lastName + "</td>";
		// row +="<td>" + new Date(allUsers[i].birthDate) + "</td>";
		row +="<td>" + AppUtils.setDateFromJsonAdmin(allUsers[i].birthDate) + "</td>";
		row +="<td>" + allUsers[i].mail + "</td>";
		row +="<td>" + allUsers[i].phoneNumber + "</td>";
		row +="<td>" + allUsers[i].gender + "</td>";
		row +="<td>" + allUsers[i].type + "</td>";
		row +="<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"editButton btn btn-primary btn-xs\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#edit\"> <span class=\"glyphicon glyphicon-pencil\"></span></button> </p></td>";
		row +=" <td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"deleteButton btn btn-danger btn-xs\" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#delete\"> <span class=\"glyphicon glyphicon-trash\"></span></button></p></td>";
		row +=" <td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Change\"><button class=\"changeButton btn btn-warning btn-xs\" data-title=\"Change\" data-toggle=\"modal\" data-target=\"#changeAdmin\"> <span class=\"glyphicon glyphicon\"></span>A</button></p></td>";
		row +="</tr>";
		$('tbody').append(row);
	}
};	

Admin.deleteUser = function(userId){
	AppUtils.deleteRequestPromise(AppUtils.backEndUrl + "deleteUser/"+userId)
	.then(function(data) {
		AppUtils.showInfoMessage("Successfully Removed");
		$(Admin.selectedUserRow).remove();
	}).catch(function(err) {
		 AppUtils.showErrorMessage(err.responseText);
	      console.log(err);
	});
}

Admin.changeUserToAdmin = function(userId){
	 AppUtils.updateRequestPromise(AppUtils.backEndUrl + "changeToAdmin/"+userId)
	.then(function(data) {
		AppUtils.showInfoMessage("Successfully updated");
		$(Admin.selectedUserRow).remove();
	}).catch(function(err) {
		console.log(err);
	});
}