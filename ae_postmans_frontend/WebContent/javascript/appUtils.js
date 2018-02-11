/**
 * Created by Amit on 05/10/2017.
 */
var AppUtils = {};

AppUtils.backEndUrl = "http://localhost:9000/";

AppUtils.setDate = function(date, targetDateInput) {
    var dd = date.getDate();
    var mm = date.getMonth()+1;
    mm = mm < 10  ? '0'+mm : mm;
    dd = dd < 10  ? '0'+dd : dd;
    var yyyy = date.getFullYear();

    $(targetDateInput).val(yyyy+'-'+mm+'-'+dd);
};

AppUtils.getRequestPromise = function (getUrl) {
    var pr = new Promise(function (resolve, reject) {
        $.ajax({
            type: "GET",
            xhrFields: {
                withCredentials: true
            },
            contentType: "application/json; charset=utf-8",
            url: getUrl,
            success: function (data) {
                resolve(data);
            },
            error: function (err, status) {
                reject(err);
            }
        });
    });

    return pr;
};

AppUtils.postRequestPromise = function (postUrl, data) {
    var pr = new Promise (function(resolve, reject) {
        $.ajax({
            type: "POST",
            data: data,
            xhrFields: {
                withCredentials: true
            },
            accept: "text/html",
            contentType: "application/json; charset=utf-8",
            url: postUrl,
            success: function (data, status, xhr) {
                console.log(data + " " + status + " " + xhr);
                resolve(data);
            },
            error: function (err, status) {
                console.log(err);
                reject(err);
            }
        });
    });

    return pr;
};

AppUtils.deleteRequestPromise = function (deleteUrl) {

    var pr = new Promise (function(resolve, reject) {
        $.ajax({
            type: "DELETE",
            xhrFields: {
                withCredentials: true
            },
            accept: "text/html",
            contentType: "application/json; charset=utf-8",
            url: deleteUrl,
            success: function (data, status, xhr) {
                console.log(data + " " + status + " " + xhr);
                // AppUtils.showInfoMessage("Success");
                resolve(data);
            },
            error: function (err, status) {
                console.log(err);
                AppUtils.showErrorMessage(err.responseText);
                reject(err);
            }
        });
    });

    return pr;
};

AppUtils.updateRequestPromise = function (postUrl) {

    var pr = new Promise (function(resolve, reject) {
        $.ajax({
            type: "POST",
            xhrFields: {
                withCredentials: true
            },
            data: JSON.stringify({foo : null}),
            contentType: "application/json",
            url: postUrl,
            success: function (data, status, xhr) {
                console.log(data + " " + status + " " + xhr);
                // AppUtils.showInfoMessage("Success");
                resolve(data);
            },
            error: function (err, status) {
                console.log(err);
                AppUtils.showErrorMessage(err.responseText);
                reject(err);
            }
        });
    });

    return pr;
};

AppUtils.sessionKeepAlive = function() {
    var profile = AppUtils.getRequestPromise(AppUtils.backEndUrl + "profile");
    profile.then(function (data) {
        AppUtils.profile = data;
        $('.login').hide();
        $('.logout').show();
        $('.logout').children()[0].text = "Logout(" + data.firstName + " " + data.lastName + ")";
    }).catch(function () {
        $('.login').show();
        $('.logout').hide();
    });
};

AppUtils.initApplication = function () {
    AppUtils.sessionKeepAlive();
};


$(document).ready( function () {
    AppUtils.initApplication();

});

AppUtils.changeActiveTab = function(newTab, newTabHtml, targetDiv, sessionRequired) {
    if (!sessionRequired) {
        AppUtils.changeTab(newTab, newTabHtml, targetDiv);
    } else {
        var profile = AppUtils.getRequestPromise(AppUtils.backEndUrl + "profile");
        profile.then(function (data) {
            AppUtils.profile = data;
            AppUtils.changeTab(newTab, newTabHtml, targetDiv);
            $('.login').hide();
            $('.logout').show();
            $('.logout').children()[0].text = "Logout(" + data.firstName + " " + data.lastName + ")";
        }).catch(function (err) {
        	AppUtils.showErrorMessage(err.responseText);
            $('.login').show();
            $('.logout').hide();
        });
    }
};

AppUtils.changeTab =  function(newTab, newTabHtml, targetDiv) {
    if (!newTab.hasClass('active')) {
        targetDiv.fadeTo(200, 0, function () {
            targetDiv.load(newTabHtml, function () {
                targetDiv.fadeTo(200, 1);
            });
        });
        $('.active').removeClass('active');
        newTab.addClass('active');
    } else {
        targetDiv.load(newTabHtml);
    }
}

AppUtils.getCurrentUser = function () {
    AppUtils.profile = null;
    AppUtils.sessionKeepAlive();
    return AppUtils.profile;
}


AppUtils.login = function() {
    AppUtils.postRequestPromise(AppUtils.backEndUrl + "login", JSON.stringify($("#loginForm").serializeJSON())).then (function(data){
        $('.login').hide();
        $('.logout').show();
        $('.logout').children()[0].text = "Logout("+data.firstName+" "+data.lastName+")";
        $('.home').click();
    }).catch(function(err){
    	AppUtils.showErrorMessage(err.responseText);
        $('.logout').hide();
        $('.login').show();
        console.log(err);
    });
};

AppUtils.signup = function() {
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
            accept: "text/html",
            url: AppUtils.backEndUrl + 'signup',
            success: function (data, status, xhr) {
                console.log(data + " " + status + " " + xhr);
                $('#login_tab').click();
                AppUtils.showInfoMessage("Signup success!");

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

AppUtils.logout = function() {
	var CSRF_TOKEN = "@{play.filters.csrf.CSRF.getToken(request)}";
    $.ajax({
        type :  "GET",
        xhrFields: {
            withCredentials: true
        },
        contentType: "application/json; charset=utf-8",
        url  :  AppUtils.backEndUrl + "logout",
        success: function(data, status, xhr){
            console.log(data);
            $('.logout').hide();
            $('.login').show();
            $(".home").click();
        },
        error: function(err, status){
            console.log(err);
        }
    });

};

AppUtils.showInfoMessage = function (bodyText) {
    AppUtils.showMessage('', 'btn-info', 'Info', bodyText);
};

AppUtils.showErrorMessage = function (bodyText) {
    AppUtils.showMessage('modal_header_error', 'btn-error', 'Error!', bodyText);
};

AppUtils.showMessage = function (headerClass, buttonClass, titleText, bodyText) {
        $('#loadedPopup').remove();
        var popupHtml = $("<div id='loadedPopup'></div>").load("popupMessage.html", function () {
        var popup = popupHtml.find('#messageModal');

        var header = popup.find('.modal-header');
        header.removeClass();
        header.addClass('modal-header ' + headerClass);

        var title = popup.find('.modal-title');
        title.text(titleText);

        var body = popup.find('.popupBody');
        body.text(bodyText);

        var button = popup.find('.btn');
        button.removeClass();
        button.addClass('btn ' + buttonClass);

        $('body').append(popupHtml);
        $('#messageModal').modal('show');

    });

};

AppUtils.setDateFromJson = function(dateFromJson)
{
	
	var date = new Date(parseInt(dateFromJson)) ;
    return ('0' + date.getDate()).slice(-2) + '-'
         + ('0' + (date.getMonth()+1)).slice(-2) + '-'
         + date.getFullYear() + ' ' + date.getHours() +':'+ ('0' + date.getMinutes()).slice(-2);
}

AppUtils.setDateFromJsonAdmin = function(dateFromJson)
{
	
	var date = new Date(parseInt(dateFromJson)) ;
    return ('0' + date.getDate()).slice(-2) + '-'
         + ('0' + (date.getMonth()+1)).slice(-2) + '-'
         + date.getFullYear();
}


