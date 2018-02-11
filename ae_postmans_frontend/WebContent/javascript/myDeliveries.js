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
	
	myDeliveries.getAllUsers().then(function (data) {
		myDeliveries.createDeliveriresTable(data);
		$(".deleteButton").click(function() {
			var tr = this.closest("tr");
			myDeliveries.selectedPackRow = $(tr);
			myDeliveries.selectedPackId = $(tr).find(".id").text();
			console.log(myDeliveries.selectedPackId);
		});
		
	}).catch(function (err) {
		AppUtils.showErrorMessage(err.responseText);
		console.log(err);
	});
	
	$("#approveDelete").click(function(){
		myDeliveries.deletePack(myDeliveries.selectedPackId);
	});
	
	$("[data-toggle=tooltip]").tooltip();
	
});

var myDeliveries = {};

myDeliveries.getAllUsers = function() {
	return AppUtils.getRequestPromise(AppUtils.backEndUrl + "myDeliveries");
};

myDeliveries.createDeliveriresTable = function(allDeliveries) {
	$('tbody').html("");
	for (i in allDeliveries) {
		var row ="";
		row +="<tr><td class=\"id\">" + allDeliveries[i].id + "</td>";
		row +="<td>" + allDeliveries[i].description + "</td>";
		row +="<td>" + allDeliveries[i].weight + "</td>";
		row +="<td>" + allDeliveries[i].fromCountry + "</td>";
		row +="<td>" + allDeliveries[i].toCountry + "</td>";
		row +="<td>" + allDeliveries[i].fromCity + "</td>";
		row +="<td>" + allDeliveries[i].toCity + "</td>";
		row +="<td>" + allDeliveries[i].status + "</td>";
		//row +="<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"editButton btn btn-primary btn-xs\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#edit\"> <span class=\"glyphicon glyphicon-pencil\"></span></button> </p></td>";
		row +=" <td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"deleteButton btn btn-danger btn-xs\" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#delete\"> <span class=\"glyphicon glyphicon-trash\"></span></button></p></td>";
		row +="</tr>";
		$('tbody').append(row);
	}
};

myDeliveries.deletePack = function(packId){
	AppUtils.deleteRequestPromise(AppUtils.backEndUrl + "deletePackage/"+packId)
	.then(function(data) {
		AppUtils.showInfoMessage("Successfully Removed");
		$(myDeliveries.selectedPackRow).remove();
	}).catch(function(err) {
		console.log(err);
	});
}

