/**
 * Created by Amit on 18/08/2017.
 */
window.onload =  function() {

    $(".home").click(function() {
        AppUtils.changeActiveTab($(".home"), "home.html", $(".container"), false);
    });

    $(".profile").click(function() {
        AppUtils.changeActiveTab($(".profile"), "profile.html", $(".container"), true);
    });

    $(".findDelivery").click(function() {
        AppUtils.changeActiveTab($(".findDelivery"), "findDelivery.html", $(".container"), true);
    });

    $(".requestDelivery").click(function() {
        AppUtils.changeActiveTab($(".requestDelivery"), "requestDelivery.html", $(".container"), true);
    });

    $(".forum").click(function() {
        AppUtils.changeActiveTab($(".forum"), "forum.html", $(".container"), true);
    });

    $(".myDeliveries").click(function() {
        AppUtils.changeActiveTab($(".myDeliveries"), "myDeliveries.html", $(".container"), true);
    });

    $(".login").click(function() {
        AppUtils.changeActiveTab($(".login"), "login.html", $(".container"), false);
    });
    
    $(".forumAnswers").click(function() {
        AppUtils.changeActiveTab($(".forumAnswers"), "forumAnswers.html", $(".container"), false);
    });

    $(".logout").click(function() {
        AppUtils.logout();
    })

    $(".admin").click(function() {
        AppUtils.changeActiveTab($(".admin"), "admin.html", $(".container"), true);
    });
    


    //ADD HERE NAVIGATION BUTTONS


    $(".home").click();

}



//Don't change the following method



