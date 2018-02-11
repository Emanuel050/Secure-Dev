var backEndUrl = "http://localhost:9000/";
var FindDelivery = {};
FindDelivery.userLocationForm;
FindDelivery.selectedMarker;
FindDelivery.userLocationForm = {
    lng:   {  fieldId: 'toLng'},
    lat:   {  fieldId: 'toLat'}
};

$(document).ready( function () {
    AppUtils.getRequestPromise(AppUtils.backEndUrl + "new_packages").then(function(packages) {
        FindDelivery.markers = packages;
    }).catch(function(err){
    	AppUtils.showErrorMessage(err.responseText);
        alert(err.statusText);
    });
    
    
    /*
    $('#submit_delivery_quote').click(FindDelivery.submitDeliveryQuote);
	*/
    
    $("#submit_delivery_quote").click(function (event) {
       event.preventDefault();
       $("div").remove(".warningMessage");
       if(validateQuote())
       {
    	   	FindDelivery.submitDeliveryQuote();
       }
    });
    
    $('#quoteForm').find('input, textarea').on('keyup blur focus', function (e) {

        var $this = $(this),
            label = $this.prev('label');

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
            }
            else if ($this.val() !== '') {
                label.addClass('highlight');
            }
        }

    });

    $("#csrfToken").val(document.cookie.replace("CSRF_TOKEN=", ""));
});

// The following example creates complex markers to indicate beaches near
// Sydney, NSW, Australia. Note that the anchor is set to (0,32) to correspond
// to the base of the flagpole.
FindDelivery.initMap = function () {
    var map = new google.maps.Map(document.getElementById('deliveries_map'), {
        zoom: 16,
        center: FindDelivery.geolocation
    });
    console.log(FindDelivery.geolocation);
    FindDelivery.setMarkers(map);
}

FindDelivery.submitDeliveryQuote = function () {
    var quoteForm =  $("#quoteForm").serializeJSON();
    quoteForm["selectedPackageId"] = FindDelivery.selectedMarker;
    AppUtils.postRequestPromise(AppUtils.backEndUrl + "submit_delivery_quote", JSON.stringify(quoteForm))
        .then(function(data) {
        	AppUtils.showInfoMessage("Successfully Added!");
            $('#quoteModal').modal('hide');
        })
        .catch(function(err) {
        	 AppUtils.showErrorMessage(err.responseText);
        });
};

FindDelivery.setMarkers = function (map) {
    // Adds markers to the map.

    // Marker sizes are expressed as a Size of X,Y where the origin of the image
    // (0,0) is located in the top left of the image.

    // Origins, anchor positions and coordinates of the marker increase in the X
    // direction to the right and in the Y direction down.
    var image = {
        url: 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
        // This marker is 20 pixels wide by 32 pixels high.
        size: new google.maps.Size(20, 32),
        // The origin for this image is (0, 0).
        origin: new google.maps.Point(0, 0),
        // The anchor for this image is the base of the flagpole at (0, 32).
        anchor: new google.maps.Point(0, 32)
    };
    // Shapes define the clickable region of the icon. The type defines an HTML
    // <area> element 'poly' which traces out a polygon as a series of X,Y points.
    // The final coordinate closes the poly by connecting to the first coordinate.
    var shape = {
        coords: [1, 1, 1, 20, 18, 20, 18, 1],
        type: 'poly'
    };
    for (i in FindDelivery.markers) {
        var mark = FindDelivery.markers[i];
        console.log(mark);
            var marker = new google.maps.Marker({
            position: {lat: mark["fromLat"], lng: mark["fromLng"]},
            map: map,
            icon: image,
            shape: shape,
            title: mark["description"]
        });

        var contentString =  '<h5> Delivery Information: </h5>' +
            '<h6>From:</h6>' +
            '<table class = "deliveryInformation">' +
            '<tr><td>Name: </td><td>' +mark.customer.firstName +' '+mark.customer.lastName +'</td></tr>'+
            '<tr><td>Street: </td><td>'+mark.fromAddress +'</td></tr>'+
            '<tr><td>House Number: </td><td>' + mark.fromStreetNumber +'</td></tr>'+
            '<tr><td>City: </td><td>' + mark.fromCity+'</td></tr>'+
            '<tr><td>Postal Code: </td><td>' + mark.fromPostalCode+'</td></tr>'+
            '<tr><td>Country: </td><td>' + mark.fromCountry+'</td></tr>'+
            '<tr><td>Phone: </td><td>'+mark.customer.phoneNumber+'</td></tr>'+
            '<tr><td>Mail: </td><td>' + mark.customer.mail+'</td></tr>'+
            '</table>' +
            '<br><h6>To:</h6>' +
            '<table class = "deliveryInformation">' +
            '<tr><td>Name: </td><td>' +mark.recipientName +'</td></tr>'+
            '<tr><td>Street: </td><td>'+mark.toAddress +'</td></tr>'+
            '<tr><td>House Number: </td><td>' + mark.toStreetNumber +'</td></tr>'+
            '<tr><td>City: </td><td>' + mark.toCity+'</td></tr>'+
            '<tr><td>Postal Code: </td><td>' + mark.toPostalCode+'</td></tr>'+
            '<tr><td>Country: </td><td>' + mark.toCountry+ '<tr><td>' +
            '</table>' +
            '<br><h6>Package:</h6>' +
            '<table class = "deliveryInformation">' +
            '<tr><td>Description: </td><td>' + mark.description  +'</td></tr>'+
            '<tr><td>Weight: </td><td>' + mark.weight +' gr</td></tr>'+
            '<tr><td>Quote: </td><td><a href="javascript:$(\'#quoteModal\').modal(\'show\')">SEND QUOTE NOW</a></td></tr>'+
            '</table>';

        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });
        google.maps.event.addListener(marker,'click', (function(marker, content, infowindow, id){
            return function() {
                if(!marker.open){
                    infowindow.setContent(content);
                    infowindow.open(map,marker);
                    FindDelivery.selectedMarker = id;
                    marker.open = true;
                }
                else{
                    infowindow.close();
                    marker.open = false;
                }
                google.maps.event.addListener(map, 'click', function() {
                    infowindow.close();
                    marker.open = false;
                });
            };
        })(marker,contentString,infowindow, mark.id));

    }
};


function initAutocomplete() {
    // Create the autocomplete object, restricting the search to geographical
    // location types.
    FindDelivery.userLocation = new google.maps.places.Autocomplete(
        /** @type {!HTMLInputElement} */(document.getElementById('userLocation')));
    FindDelivery.userLocation["form"] = FindDelivery.userLocationForm ;
    FindDelivery.geolocate(FindDelivery.userLocation);
    FindDelivery.userLocation.addListener('place_changed', FindDelivery.fillInAddress);
    setTimeout(FindDelivery.initMap ,500);
};

FindDelivery.fillInAddress = function() {
    // Get the place details from the autocomplete object.
    var place = this.getPlace();
    var componentForm = this.form;
    FindDelivery.geolocation = {
        lat: place.geometry.location.lat(),
        lng: place.geometry.location.lng()
    };

    FindDelivery.initMap();
};

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
FindDelivery.geolocate = function (autocomplete) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            FindDelivery.geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            console.log( FindDelivery.geolocation);
            var circle = new google.maps.Circle({
                center:  FindDelivery.geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }
};


function validateQuote() {
	var goodInput = true;
	var price,shippingTime;
	price = document.getElementById("price").value;
	shippingTime = document.getElementById("shippingTime").value;
	
	if(price !="" && shippingTime!="")
	{
		if (price > 0)
		{
			document.getElementById('price').style.color = 'white';
		} 
		else
		{
			$('#price').after('<div class="warningMessage"><p style="color:red;">The price must be greater than 0!</p></div>');
			document.getElementById('price').style.color = 'red';
			goodInput = false;
		}
	}
	else
	{
		AppUtils.showErrorMessage("All field are required!");
		goodInput = false;
	}
	
	return goodInput;
}

