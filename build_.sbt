//ThisBuild / version := "0.1.0-SNAPSHOT"
//
//ThisBuild / scalaVersion := "2.13.9"
//
//val proguardSettings = Seq(
////  Proguard / proguardVersion := "7.2.2",
//  Proguard / proguard := Seq(file(".")),
//  Proguard / proguardOptions ++= Seq("-dontoptimize", "-dontnote", "-dontwarn", "-ignorewarnings"),
//  Proguard / proguardOptions  += ProguardOptions.keepMain("Main"),
//  Proguard / proguardInputs := Seq((assemblyOutputPath / assembly).value)
//)
//
//lazy val root = (project in file("."))
//  .enablePlugins(SbtProguard)
//  .settings(
//    name := "RedBook",
//    Compile / run / mainClass := Some("Main"),
//    libraryDependencies := Seq(
//      "org.scalactic" %% "scalactic" % "3.2.14",
//      "org.scalatest" %% "scalatest" % "3.2.14" % "test"
//    )
//  )
//  .settings(proguardSettings)
