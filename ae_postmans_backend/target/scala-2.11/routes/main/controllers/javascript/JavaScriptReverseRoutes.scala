
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Pro/amit_emanuel_project/ae_postmans_backend/conf/routes
// @DATE:Sat Oct 14 20:49:41 IDT 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:27
  class ReverseForumController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def setNewAnswer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.setNewAnswer",
      """
        function(questionId0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "setAnswer/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("questionId", questionId0)})
        }
      """
    )
  
    // @LINE:27
    def setNewQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.setNewQuestion",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "setQuestion"})
        }
      """
    )
  
    // @LINE:30
    def getAllQuestionAnswers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.getAllQuestionAnswers",
      """
        function(questionId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allQuestionAnswers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("questionId", questionId0)})
        }
      """
    )
  
    // @LINE:28
    def getAllQuestions: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.getAllQuestions",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allQuestions"})
        }
      """
    )
  
  }

  // @LINE:14
  class ReverseRequestDeliveryController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def submitDeliveryQuote: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RequestDeliveryController.submitDeliveryQuote",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "submit_delivery_quote"})
        }
      """
    )
  
    // @LINE:19
    def getNewPackages: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RequestDeliveryController.getNewPackages",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "new_packages"})
        }
      """
    )
  
    // @LINE:17
    def requestDelivery: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RequestDeliveryController.requestDelivery",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "requestDelivery"})
        }
      """
    )
  
    // @LINE:14
    def getAllDeliveries: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RequestDeliveryController.getAllDeliveries",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "myDeliveries"})
        }
      """
    )
  
    // @LINE:15
    def deletePackage: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RequestDeliveryController.deletePackage",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "deletePackage/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def updateUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.updateUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateUser"})
        }
      """
    )
  
    // @LINE:23
    def updateUserById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.updateUserById",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateUser/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:6
    def checkServerStatus: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.checkServerStatus",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:10
    def getUserProfile: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.getUserProfile",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "profile"})
        }
      """
    )
  
    // @LINE:9
    def signup: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.signup",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
        }
      """
    )
  
    // @LINE:22
    def deleteUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.deleteUser",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteUser/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:24
    def getUserProfileById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.getUserProfileById",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "profile/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:25
    def changeUserToAdmin: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.changeUserToAdmin",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "changeToAdmin/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:8
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.logout",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
        }
      """
    )
  
    // @LINE:12
    def getAllUsers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.getAllUsers",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allUsers"})
        }
      """
    )
  
    // @LINE:7
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
  }


}
