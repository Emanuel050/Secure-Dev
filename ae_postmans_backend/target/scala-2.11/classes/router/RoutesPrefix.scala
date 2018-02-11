
// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Pro/amit_emanuel_project/ae_postmans_backend/conf/routes
// @DATE:Sun Aug 13 19:35:28 IDT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
