ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "paglabs2024",
    libraryDependencies += "dev.hnaderi" %% "lepus-client" % "0.5.3"
  )
