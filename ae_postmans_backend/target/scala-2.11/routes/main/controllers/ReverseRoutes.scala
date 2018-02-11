
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Pro/amit_emanuel_project/ae_postmans_backend/conf/routes
// @DATE:Sat Oct 14 20:49:41 IDT 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:27
  class ReverseForumController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def setNewAnswer(questionId:Long): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "setAnswer/" + implicitly[PathBindable[Long]].unbind("questionId", questionId))
    }
  
    // @LINE:27
    def setNewQuestion(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "setQuestion")
    }
  
    // @LINE:30
    def getAllQuestionAnswers(questionId:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "allQuestionAnswers/" + implicitly[PathBindable[Long]].unbind("questionId", questionId))
    }
  
    // @LINE:28
    def getAllQuestions(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "allQuestions")
    }
  
  }

  // @LINE:14
  class ReverseRequestDeliveryController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def submitDeliveryQuote(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "submit_delivery_quote")
    }
  
    // @LINE:19
    def getNewPackages(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "new_packages")
    }
  
    // @LINE:17
    def requestDelivery(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "requestDelivery")
    }
  
    // @LINE:14
    def getAllDeliveries(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "myDeliveries")
    }
  
    // @LINE:15
    def deletePackage(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "deletePackage/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
  }

  // @LINE:6
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def updateUser(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "updateUser")
    }
  
    // @LINE:23
    def updateUserById(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "updateUser/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:6
    def checkServerStatus(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
    // @LINE:10
    def getUserProfile(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "profile")
    }
  
    // @LINE:9
    def signup(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "signup")
    }
  
    // @LINE:22
    def deleteUser(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "deleteUser/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:24
    def getUserProfileById(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "profile/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:25
    def changeUserToAdmin(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "changeToAdmin/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:8
    def logout(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "logout")
    }
  
    // @LINE:12
    def getAllUsers(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "allUsers")
    }
  
    // @LINE:7
    def login(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }


}
