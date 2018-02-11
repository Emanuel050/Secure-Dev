// EVENTS declaration

$(document).ready( function () {
    $("#loginButton").click(function (event) {
        event.preventDefault();
        AppUtils.login();
    });
    
    $("#signupButton").click(function (event) {
        event.preventDefault();
    	$( "div" ).remove( ".warningMessage" );
		if (validateSignupForm()) {
		      AppUtils.signup();
		}
    });

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
});

function validateSignupForm() {
	var goodInput = true;
	var password, passwordConfirm,firstName,lastName,birthDate,email,phoneNumber,picture;
	password = document.getElementById("password").value;
	passwordConfirm = document.getElementById("passwordConfirm").value;
	firstName = document.getElementById("firstName").value;
	lastName = document.getElementById("lastName").value;
	birthDate = document.getElementById("birthDate").value;
	email = document.getElementById("email").value;
	phoneNumber = document.getElementById("phoneNumber").value,
	picture = document.getElementById("passportPicture").value;
	
	var currentDate =new Date();
	currentDate.setHours(0,0,0,0);

	if(password !="" && passwordConfirm !="" &&  firstName !="" && lastName !="" &&  birthDate !="" &&  email !="" &&  phoneNumber !="" && picture !="")
		{
		if (password == passwordConfirm) {
			document.getElementById('passwordConfirm').style.color = 'white';
			document.getElementById('password').style.color = 'white';
		}
		else
			{
				$('#passwordConfirm').after('<div class="warningMessage"><p style="color:red;">Passwords dont match!</p></div>');
				document.getElementById('passwordConfirm').style.color = 'red';
				document.getElementById('password').style.color = 'red';
				goodInput = false;	
			}
		
		if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
		{
			document.getElementById('email').style.color = 'white';
		}
		else
		{
			document.getElementById('email').style.color = 'red';
			$('#email').after('<div class="warningMessage"><p style="color:red;">Invalid email address!</p></div>');
			goodInput = false;
		}
			if (parseDate($("#birthDate").val()) < currentDate) 
			{
				document.getElementById('birthDate').style.color = 'white';
			}
		else 
			{
				document.getElementById('birthDate').style.color = 'red';
				$('#birthDate').after('<div class="warningMessage"><p style="color:red;">Date of birth must be less than current date!</p></div>');
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

function parseDate(s) {
	  var b = s.split(/\D/);
	  return new Date(b[0],--b[1], b[2]);
	}


