name := """wasatch-social"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.scalatestplus" %% "play" % "1.2.0" % "test",
  "org.scaldi" %% "scaldi-play" % "0.4.1"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
