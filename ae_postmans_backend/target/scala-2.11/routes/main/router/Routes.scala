
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Pro/amit_emanuel_project/ae_postmans_backend/conf/routes
// @DATE:Sat Oct 14 20:49:41 IDT 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  UserController_1: controllers.UserController,
  // @LINE:14
  RequestDeliveryController_2: controllers.RequestDeliveryController,
  // @LINE:27
  ForumController_0: controllers.ForumController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    UserController_1: controllers.UserController,
    // @LINE:14
    RequestDeliveryController_2: controllers.RequestDeliveryController,
    // @LINE:27
    ForumController_0: controllers.ForumController
  ) = this(errorHandler, UserController_1, RequestDeliveryController_2, ForumController_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, UserController_1, RequestDeliveryController_2, ForumController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.UserController.checkServerStatus"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.UserController.login"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """logout""", """controllers.UserController.logout"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signup""", """controllers.UserController.signup"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profile""", """controllers.UserController.getUserProfile"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateUser""", """controllers.UserController.updateUser"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """allUsers""", """controllers.UserController.getAllUsers"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """myDeliveries""", """controllers.RequestDeliveryController.getAllDeliveries"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deletePackage/""" + "$" + """id<[^/]+>""", """controllers.RequestDeliveryController.deletePackage(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """requestDelivery""", """controllers.RequestDeliveryController.requestDelivery"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """submit_delivery_quote""", """controllers.RequestDeliveryController.submitDeliveryQuote"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """new_packages""", """controllers.RequestDeliveryController.getNewPackages"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deleteUser/""" + "$" + """id<[^/]+>""", """controllers.UserController.deleteUser(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateUser/""" + "$" + """id<[^/]+>""", """controllers.UserController.updateUserById(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profile/""" + "$" + """id<[^/]+>""", """controllers.UserController.getUserProfileById(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """changeToAdmin/""" + "$" + """id<[^/]+>""", """controllers.UserController.changeUserToAdmin(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """setQuestion""", """controllers.ForumController.setNewQuestion"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """allQuestions""", """controllers.ForumController.getAllQuestions"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """setAnswer/""" + "$" + """questionId<[^/]+>""", """controllers.ForumController.setNewAnswer(questionId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """allQuestionAnswers/""" + "$" + """questionId<[^/]+>""", """controllers.ForumController.getAllQuestionAnswers(questionId:Long)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_UserController_checkServerStatus0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_UserController_checkServerStatus0_invoker = createInvoker(
    UserController_1.checkServerStatus,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "checkServerStatus",
      Nil,
      "GET",
      """ An example controller showing a sample home page""",
      this.prefix + """"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_UserController_login1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_UserController_login1_invoker = createInvoker(
    UserController_1.login,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "login",
      Nil,
      "POST",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_UserController_logout2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("logout")))
  )
  private[this] lazy val controllers_UserController_logout2_invoker = createInvoker(
    UserController_1.logout,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "logout",
      Nil,
      "GET",
      """""",
      this.prefix + """logout"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_UserController_signup3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup")))
  )
  private[this] lazy val controllers_UserController_signup3_invoker = createInvoker(
    UserController_1.signup,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "signup",
      Nil,
      "POST",
      """""",
      this.prefix + """signup"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_UserController_getUserProfile4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profile")))
  )
  private[this] lazy val controllers_UserController_getUserProfile4_invoker = createInvoker(
    UserController_1.getUserProfile,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getUserProfile",
      Nil,
      "GET",
      """""",
      this.prefix + """profile"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_UserController_updateUser5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateUser")))
  )
  private[this] lazy val controllers_UserController_updateUser5_invoker = createInvoker(
    UserController_1.updateUser,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "updateUser",
      Nil,
      "POST",
      """""",
      this.prefix + """updateUser"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_UserController_getAllUsers6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("allUsers")))
  )
  private[this] lazy val controllers_UserController_getAllUsers6_invoker = createInvoker(
    UserController_1.getAllUsers,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getAllUsers",
      Nil,
      "GET",
      """""",
      this.prefix + """allUsers"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_RequestDeliveryController_getAllDeliveries7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("myDeliveries")))
  )
  private[this] lazy val controllers_RequestDeliveryController_getAllDeliveries7_invoker = createInvoker(
    RequestDeliveryController_2.getAllDeliveries,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RequestDeliveryController",
      "getAllDeliveries",
      Nil,
      "GET",
      """""",
      this.prefix + """myDeliveries"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_RequestDeliveryController_deletePackage8_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deletePackage/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RequestDeliveryController_deletePackage8_invoker = createInvoker(
    RequestDeliveryController_2.deletePackage(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RequestDeliveryController",
      "deletePackage",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """deletePackage/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_RequestDeliveryController_requestDelivery9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("requestDelivery")))
  )
  private[this] lazy val controllers_RequestDeliveryController_requestDelivery9_invoker = createInvoker(
    RequestDeliveryController_2.requestDelivery,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RequestDeliveryController",
      "requestDelivery",
      Nil,
      "POST",
      """""",
      this.prefix + """requestDelivery"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_RequestDeliveryController_submitDeliveryQuote10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("submit_delivery_quote")))
  )
  private[this] lazy val controllers_RequestDeliveryController_submitDeliveryQuote10_invoker = createInvoker(
    RequestDeliveryController_2.submitDeliveryQuote,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RequestDeliveryController",
      "submitDeliveryQuote",
      Nil,
      "POST",
      """""",
      this.prefix + """submit_delivery_quote"""
    )
  )

  // @LINE:19
  private[this] lazy val controllers_RequestDeliveryController_getNewPackages11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("new_packages")))
  )
  private[this] lazy val controllers_RequestDeliveryController_getNewPackages11_invoker = createInvoker(
    RequestDeliveryController_2.getNewPackages,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RequestDeliveryController",
      "getNewPackages",
      Nil,
      "GET",
      """""",
      this.prefix + """new_packages"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_UserController_deleteUser12_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deleteUser/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_deleteUser12_invoker = createInvoker(
    UserController_1.deleteUser(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "deleteUser",
      Seq(classOf[Long]),
      "DELETE",
      """ADMIN OPERATIONS CHECK IF CURRENT USER IS ADMIN""",
      this.prefix + """deleteUser/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:23
  private[this] lazy val controllers_UserController_updateUserById13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateUser/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_updateUserById13_invoker = createInvoker(
    UserController_1.updateUserById(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "updateUserById",
      Seq(classOf[Long]),
      "POST",
      """""",
      this.prefix + """updateUser/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:24
  private[this] lazy val controllers_UserController_getUserProfileById14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profile/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_getUserProfileById14_invoker = createInvoker(
    UserController_1.getUserProfileById(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getUserProfileById",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """profile/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:25
  private[this] lazy val controllers_UserController_changeUserToAdmin15_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("changeToAdmin/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_changeUserToAdmin15_invoker = createInvoker(
    UserController_1.changeUserToAdmin(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "changeUserToAdmin",
      Seq(classOf[Long]),
      "POST",
      """""",
      this.prefix + """changeToAdmin/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:27
  private[this] lazy val controllers_ForumController_setNewQuestion16_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("setQuestion")))
  )
  private[this] lazy val controllers_ForumController_setNewQuestion16_invoker = createInvoker(
    ForumController_0.setNewQuestion,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "setNewQuestion",
      Nil,
      "POST",
      """""",
      this.prefix + """setQuestion"""
    )
  )

  // @LINE:28
  private[this] lazy val controllers_ForumController_getAllQuestions17_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("allQuestions")))
  )
  private[this] lazy val controllers_ForumController_getAllQuestions17_invoker = createInvoker(
    ForumController_0.getAllQuestions,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "getAllQuestions",
      Nil,
      "GET",
      """""",
      this.prefix + """allQuestions"""
    )
  )

  // @LINE:29
  private[this] lazy val controllers_ForumController_setNewAnswer18_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("setAnswer/"), DynamicPart("questionId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumController_setNewAnswer18_invoker = createInvoker(
    ForumController_0.setNewAnswer(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "setNewAnswer",
      Seq(classOf[Long]),
      "POST",
      """""",
      this.prefix + """setAnswer/""" + "$" + """questionId<[^/]+>"""
    )
  )

  // @LINE:30
  private[this] lazy val controllers_ForumController_getAllQuestionAnswers19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("allQuestionAnswers/"), DynamicPart("questionId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumController_getAllQuestionAnswers19_invoker = createInvoker(
    ForumController_0.getAllQuestionAnswers(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "getAllQuestionAnswers",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """allQuestionAnswers/""" + "$" + """questionId<[^/]+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_UserController_checkServerStatus0_route(params) =>
      call { 
        controllers_UserController_checkServerStatus0_invoker.call(UserController_1.checkServerStatus)
      }
  
    // @LINE:7
    case controllers_UserController_login1_route(params) =>
      call { 
        controllers_UserController_login1_invoker.call(UserController_1.login)
      }
  
    // @LINE:8
    case controllers_UserController_logout2_route(params) =>
      call { 
        controllers_UserController_logout2_invoker.call(UserController_1.logout)
      }
  
    // @LINE:9
    case controllers_UserController_signup3_route(params) =>
      call { 
        controllers_UserController_signup3_invoker.call(UserController_1.signup)
      }
  
    // @LINE:10
    case controllers_UserController_getUserProfile4_route(params) =>
      call { 
        controllers_UserController_getUserProfile4_invoker.call(UserController_1.getUserProfile)
      }
  
    // @LINE:11
    case controllers_UserController_updateUser5_route(params) =>
      call { 
        controllers_UserController_updateUser5_invoker.call(UserController_1.updateUser)
      }
  
    // @LINE:12
    case controllers_UserController_getAllUsers6_route(params) =>
      call { 
        controllers_UserController_getAllUsers6_invoker.call(UserController_1.getAllUsers)
      }
  
    // @LINE:14
    case controllers_RequestDeliveryController_getAllDeliveries7_route(params) =>
      call { 
        controllers_RequestDeliveryController_getAllDeliveries7_invoker.call(RequestDeliveryController_2.getAllDeliveries)
      }
  
    // @LINE:15
    case controllers_RequestDeliveryController_deletePackage8_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RequestDeliveryController_deletePackage8_invoker.call(RequestDeliveryController_2.deletePackage(id))
      }
  
    // @LINE:17
    case controllers_RequestDeliveryController_requestDelivery9_route(params) =>
      call { 
        controllers_RequestDeliveryController_requestDelivery9_invoker.call(RequestDeliveryController_2.requestDelivery)
      }
  
    // @LINE:18
    case controllers_RequestDeliveryController_submitDeliveryQuote10_route(params) =>
      call { 
        controllers_RequestDeliveryController_submitDeliveryQuote10_invoker.call(RequestDeliveryController_2.submitDeliveryQuote)
      }
  
    // @LINE:19
    case controllers_RequestDeliveryController_getNewPackages11_route(params) =>
      call { 
        controllers_RequestDeliveryController_getNewPackages11_invoker.call(RequestDeliveryController_2.getNewPackages)
      }
  
    // @LINE:22
    case controllers_UserController_deleteUser12_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_deleteUser12_invoker.call(UserController_1.deleteUser(id))
      }
  
    // @LINE:23
    case controllers_UserController_updateUserById13_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_updateUserById13_invoker.call(UserController_1.updateUserById(id))
      }
  
    // @LINE:24
    case controllers_UserController_getUserProfileById14_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_getUserProfileById14_invoker.call(UserController_1.getUserProfileById(id))
      }
  
    // @LINE:25
    case controllers_UserController_changeUserToAdmin15_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_changeUserToAdmin15_invoker.call(UserController_1.changeUserToAdmin(id))
      }
  
    // @LINE:27
    case controllers_ForumController_setNewQuestion16_route(params) =>
      call { 
        controllers_ForumController_setNewQuestion16_invoker.call(ForumController_0.setNewQuestion)
      }
  
    // @LINE:28
    case controllers_ForumController_getAllQuestions17_route(params) =>
      call { 
        controllers_ForumController_getAllQuestions17_invoker.call(ForumController_0.getAllQuestions)
      }
  
    // @LINE:29
    case controllers_ForumController_setNewAnswer18_route(params) =>
      call(params.fromPath[Long]("questionId", None)) { (questionId) =>
        controllers_ForumController_setNewAnswer18_invoker.call(ForumController_0.setNewAnswer(questionId))
      }
  
    // @LINE:30
    case controllers_ForumController_getAllQuestionAnswers19_route(params) =>
      call(params.fromPath[Long]("questionId", None)) { (questionId) =>
        controllers_ForumController_getAllQuestionAnswers19_invoker.call(ForumController_0.getAllQuestionAnswers(questionId))
      }
  }
}
