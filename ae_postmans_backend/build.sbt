name := "ae_postmans"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final", // replace by your jpa implementation
  "javax.persistence" % "persistence-api" % "1.0.2",
  "mysql" % "mysql-connector-java" % "5.1.16",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

libraryDependencies += filters

//fork in run := true

