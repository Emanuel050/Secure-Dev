var backEndUrl = "http://localhost:9000/";
var DeliveryRequest = {};

window.load = function() {
	$(document)
			.ready(
					function() {
						$('#testform').attrvalidate();
						$('#resetBtn').click(function() {
							$('#testform').attrvalidate('reset');
						});
						$('#expandBtn')
								.click(
										function() {
											var collapsible = $('#'
													+ $(this).attr(
															'aria-controls'));
											$(collapsible)
													.attr(
															'aria-hidden',
															($(collapsible)
																	.attr(
																			'aria-hidden') === 'false'));
											$(this)
													.attr(
															'aria-expanded',
															($(this)
																	.attr(
																			'aria-expanded') === 'false'));
										});
					});

	$(function() {
		$("#datepicker").datepicker();
	});
}

$(document).ready(function() {
	includeJs("javascript/jquery/jquery.serializejson.js");

	$("#requestButton").click(function(event) {
		event.preventDefault();
		$("div").remove(".warningMessage");
		if (validateDeliveryRequestForm()) {
			DeliveryRequest.requestDelivery();
			//alert("Your order has been successfully sent")
		}
	});

	$('.form').find('input, textarea').on('keyup blur focus', function(e) {

		var $this = $(this), label = $this.prev('label');

		if (e.type === 'keyup') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.addClass('active highlight');
			}
		} else if (e.type === 'blur') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.removeClass('highlight');
			}
		} else if (e.type === 'focus') {

			if ($this.val() === '') {
				label.removeClass('highlight');
			} else if ($this.val() !== '') {
				label.addClass('highlight');
			}
		}

	});

	$('.tab a').on('click', function(e) {
		e.preventDefault();
		$(this).parent().addClass('active');
		$(this).parent().siblings().removeClass('active');
		target = $(this).attr('href');
		$('.tab-content > div').not(target).hide();
		$(target).fadeIn(600);

	});
	
	$("#csrfToken").val(document.cookie.replace("CSRF_TOKEN=", ""));
});

function includeJs(jsFilePath) {
	var js = document.createElement("script");

	js.type = "text/javascript";
	js.src = jsFilePath;

	document.body.appendChild(js);
}

DeliveryRequest.requestDelivery = function() {
	 AppUtils.postRequestPromise(AppUtils.backEndUrl + "requestDelivery", JSON.stringify($("#requestDeliveryForm").serializeJSON()))
     .then(function() {
         AppUtils.showInfoMessage("Successfully Added!");
         $(".myDeliveries").click();
     }).catch(function(err) {
         AppUtils.showErrorMessage(err.responseText);
 }   );
};

// This example displays an address form, using the autocomplete feature
// of the Google Places API to help users fill in the information.

// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script
// src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

DeliveryRequest.fromAutoComplete;
DeliveryRequest.toAutoComplete;

DeliveryRequest.toLocationForm = {
	street_number : {
		type : 'short_name',
		fieldId : 'toStreetNumber'
	},
	route : {
		type : 'short_name',
		fieldId : 'toAddress'
	},
	locality : {
		type : 'short_name',
		fieldId : 'toCity'
	},
	country : {
		type : 'long_name',
		fieldId : 'toCountry'
	},
	postal_code : {
		type : 'long_name',
		fieldId : 'toPostalCode'
	},
	lng : {
		fieldId : 'toLng'
	},
	lat : {
		fieldId : 'toLat'
	}
};

DeliveryRequest.fromLocationForm = {
	street_number : {
		type : 'short_name',
		fieldId : 'fromStreetNumber'
	},
	route : {
		type : 'short_name',
		fieldId : 'fromAddress'
	},
	locality : {
		type : 'short_name',
		fieldId : 'fromCity'
	},
	country : {
		type : 'long_name',
		fieldId : 'fromCountry'
	},
	postal_code : {
		type : 'long_name',
		fieldId : 'fromPostalCode'
	},
	lat : {
		fieldId : 'fromLat'
	},
	lng : {
		fieldId : 'fromLng'
	}
};

function initAutocomplete() {
	// Create the autocomplete object, restricting the search to geographical
	// location types.
	DeliveryRequest.fromAutoComplete = new google.maps.places.Autocomplete(
	/** @type {!HTMLInputElement} */
	(document.getElementById('fromAutoComplete')));
	DeliveryRequest.fromAutoComplete["form"] = DeliveryRequest.fromLocationForm;
	$('#fromAutoComplete').focus(
			DeliveryRequest.geolocate(DeliveryRequest.fromAutoComplete));

	DeliveryRequest.toAutoComplete = new google.maps.places.Autocomplete(
	/** @type {!HTMLInputElement} */
	(document.getElementById('toAutoComplete')));
	DeliveryRequest.toAutoComplete["form"] = DeliveryRequest.toLocationForm;
	// When the user selects an address from the dropdown, populate the address
	// fields in the form.
	$('#toAutoComplete').focus(
			DeliveryRequest.geolocate(DeliveryRequest.toAutoComplete));

	DeliveryRequest.fromAutoComplete.addListener('place_changed',
			DeliveryRequest.fillInAddress)
	DeliveryRequest.toAutoComplete.addListener('place_changed',
			DeliveryRequest.fillInAddress)

}

DeliveryRequest.fillInAddress = function() {
	// Get the place details from the autocomplete object.
	var place = this.getPlace();
	var componentForm = this.form;
	for ( var component in componentForm) {
		document.getElementById(componentForm[component].fieldId).value = '';
		document.getElementById(componentForm[component].fieldId).disabled = false;
	}

	// Get each component of the address from the place details
	// and fill the corresponding field on the form.
	for (var i = 0; i < place.address_components.length; i++) {
		var addressType = place.address_components[i].types[0];
		if (componentForm[addressType]) {
			var val = place.address_components[i][componentForm[addressType].type];
			var input = document
					.getElementById(componentForm[addressType].fieldId);
			// input.disabled = true;
			input.value = val;
			$(input).trigger("keyup");
		}
	}

	document.getElementById(componentForm["lng"].fieldId).value = place.geometry.location
			.lng();
	document.getElementById(componentForm["lat"].fieldId).value = place.geometry.location
			.lat();
};

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
DeliveryRequest.geolocate = function(autocomplete) {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var geolocation = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};
			var circle = new google.maps.Circle({
				center : geolocation,
				radius : position.coords.accuracy
			});
			autocomplete.setBounds(circle.getBounds());
		});
	}
};

function validateDeliveryRequestForm() {
	var goodInput = true;
	var fromAutoComplete, senderName, fromAddress, fromStreetNumber, fromPostalCode, fromCity, fromCountry, toAutoComplete, recipientName, toAddress, toStreetNumber, toPostalCode, toCity, toCountry, weight, description;
	
	fromAutoComplete = document.getElementById("fromAutoComplete").value;
	senderName = document.getElementById("senderName").value;
	fromAddress = document.getElementById("fromAddress").value;
	fromStreetNumber = document.getElementById("fromStreetNumber").value;
	fromPostalCode = document.getElementById("fromPostalCode").value;
	//fromCity = document.getElementById("birthDate").value;
	fromCountry = document.getElementById("fromCountry").value;

	toAutoComplete = document.getElementById("toAutoComplete").value;
	recipientName = document.getElementById("recipientName").value;
	toAddress = document.getElementById("toAddress").value;
	toStreetNumber = document.getElementById("toStreetNumber").value;
	toPostalCode = document.getElementById("toPostalCode").value;
	//toCity = document.getElementById("toCity").value;
	toCountry = document.getElementById("toCountry").value;
	weight = document.getElementById("weight").value;
	description = document.getElementById("description").value;


	if (senderName != "" && fromAddress != "" && fromStreetNumber != "" && fromPostalCode != ""  && fromCountry != "" && recipientName != "" && recipientName != "" && toStreetNumber != ""&& toPostalCode != "" &&  toCountry !="" && weight != "" && description != "") 
	{
		
		if (weight > 0)
		{
			document.getElementById('weight').style.color = 'white';
		} 
		else
		{
			$('#weight').after('<div class="warningMessage"><p style="color:red;">The weight must be greater than 0!</p></div>');
			document.getElementById('weight').style.color = 'red';
			goodInput = false;
		}
		
	} 
	else 
	{
		AppUtils.showErrorMessage("All fields are required!");
		goodInput = false;
	}
	

	return goodInput;
	
}
