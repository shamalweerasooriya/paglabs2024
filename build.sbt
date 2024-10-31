ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "paglabs2024",
    libraryDependencies += "dev.hnaderi" %% "lepus-client" % "0.5.3"
  )

// https://mvnrepository.com/artifact/com.google.flatbuffers/flatbuffers-java
libraryDependencies += "com.google.flatbuffers" % "flatbuffers-java" % "2.0.0"

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)