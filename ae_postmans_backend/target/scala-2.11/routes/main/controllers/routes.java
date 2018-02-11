
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Pro/amit_emanuel_project/ae_postmans_backend/conf/routes
// @DATE:Sat Oct 14 20:49:41 IDT 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseForumController ForumController = new controllers.ReverseForumController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseRequestDeliveryController RequestDeliveryController = new controllers.ReverseRequestDeliveryController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserController UserController = new controllers.ReverseUserController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseForumController ForumController = new controllers.javascript.ReverseForumController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseRequestDeliveryController RequestDeliveryController = new controllers.javascript.ReverseRequestDeliveryController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserController UserController = new controllers.javascript.ReverseUserController(RoutesPrefix.byNamePrefix());
  }

}
