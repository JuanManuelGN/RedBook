import java.nio.file.FileSystems

enablePlugins(SbtProguard)

scalaVersion := "2.13.6"
name := "redbook"

Proguard / proguardOptions ++= Seq("-dontoptimize", "-dontnote", "-dontwarn", "-ignorewarnings")
Proguard / proguardOptions += ProguardOptions.keepMain("Main")

Proguard / proguardInputs := (Compile / dependencyClasspath).value.files

Proguard / proguardFilteredInputs ++= ProguardOptions.noFilter((Compile / packageBin).value)