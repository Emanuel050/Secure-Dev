var Profile = {};

Profile.getUserProfile = function () {
	console.log(AppUtils.selectedUserId);
	if (AppUtils.selectedUserId) {
		return AppUtils.getRequestPromise(AppUtils.backEndUrl + "profile/"+AppUtils.selectedUserId);
	}
	 
	return AppUtils.getRequestPromise(AppUtils.backEndUrl + "profile");
};

Profile.buildProfileForm = function () {
	
    Profile.getUserProfile().then( function(profile) {
        $('#signupForm #firstName').val(profile.firstName).trigger('keyup');
        $('#signupForm #lastName').val(profile.lastName).trigger('keyup');
        $('#signupForm #gender').val(profile.gender).change();
        $('#signupForm #gender').trigger('keyup');
        $('#signupForm #email').val(profile.email).trigger('keyup');
        $('#signupForm #phoneNumber').val(profile.phoneNumber).trigger('keyup');
        $("#csrfToken").val(document.cookie.replace("CSRF_TOKEN=", ""));
        var date = new Date(profile.birthDate);
        AppUtils.setDate(date,  $('#signupForm #birthDate'));
        $('#signupForm #birthDate').trigger('keyup');
    }).catch(function(err) {
    	AppUtils.showErrorMessage(err.responseText);
        console.log(err);
    });
}

Profile.updateProfile = function (){
	if (AppUtils.selectedUserId) {
		var postUrl = AppUtils.backEndUrl + "updateUser/" + AppUtils.selectedUserId;
	} else {
		var postUrl = AppUtils.backEndUrl + "updateUser";
	}
	 
	 var fdata = new FormData();
	    fdata.append("form", JSON.stringify($("#signupForm").serializeJSON()));
	    fdata.append("file", $("#passportPicture")[0].files[0]);

	    var pr = new Promise (function(resolve, reject) {
	        $.ajax({
	            type: "POST",
	            data: fdata,
	            xhrFields: {
	                withCredentials: true
	            },
	            contentType: false,
	            processData: false,
	            url: postUrl,
	            success: function (data, status, xhr) {
	                console.log(data + " " + status + " " + xhr);
	                $('#login_tab').click();
	                AppUtils.showInfoMessage("User update successfully!");
	                resolve(data);
	            },
	            error: function (err, status) {
	                console.log(err);
	                AppUtils.showErrorMessage(err.responseText);
	                reject(err);
	            }
	        });
	    });
};

$(document).ready( function () {
    $('.form').find('input, textarea').on('keyup blur focus', function (e) {

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

    $('.tab a').on('click', function (e) {
        e.preventDefault();
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');
        target = $(this).attr('href');
        $('.tab-content > div').not(target).hide();
        $(target).fadeIn(600);

    });

    $('#testform').attrvalidate();
    $('#resetBtn').click(function(){ $('#testform').attrvalidate('reset'); });
    $('#expandBtn').click(function(){
        var collapsible = $('#' + $(this).attr('aria-controls'));
        $(collapsible).attr('aria-hidden', ($(collapsible).attr('aria-hidden') === 'false'));
        $(this).attr('aria-expanded', ($(this).attr('aria-expanded') === 'false'));
    });
    
    $("#updateButton").click(function (event) {
        event.preventDefault();
       	Profile.updateProfile();
    });

    Profile.buildProfileForm();
});




