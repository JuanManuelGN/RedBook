//name := "RedBook"
//organization := "gotoqcode"
//version := "1.0"
//scalaVersion := "2.13.9"
//
//// Dependencies
//libraryDependencies := Seq(
//  "org.scalactic" %% "scalactic" % "3.2.14",
//  "org.scalatest" %% "scalatest" % "3.2.14" % "test"
//)
//
//// Enable sbt-proguard plugin
//enablePlugins(SbtProguard)
//// Specify the proguard version
//Proguard / proguardVersion := "7.2.1"
//// Set javaOptions
//Proguard / proguard / javaOptions := Seq("-XX:-UseGCOverheadLimit","-Xmx4G")
//// Set proguardOptions
//// Specify the entry point
//Proguard / proguardOptions += ProguardOptions.keepMain("Main")
//// Configure proguard for scala
//Proguard / proguardOptions ++= Seq(
//  // Specifiy not to warn
//  "-dontwarn",
//  // Specify not to optimize the input class files
//  "-dontoptimize",
//  // Specify all attributes to be preserved
//  "-keepattributes **",
//  // Specify classes and class members (fields and methods) to be preserved
//  "-keep class !org.apache.spark.sql.**, ** {*;}",
//  "-keep class org.apache.spark.sql.api.** {*;}",
//  "-keep class org.apache.spark.sql.catalog.** {*;}",
//  "-keep class org.apache.spark.sql.catalyst.** {*;}",
//  "-keep class org.apache.spark.sql.execution.** {*;}",
//  "-keep class org.apache.spark.sql.expressions.** {*;}",
//  "-keep class org.apache.spark.sql.internal.** {*;}",
//  "-keep class org.apache.spark.sql.jdbc.** {*;}",
//  "-keep class org.apache.spark.sql.sources.** {*;}",
//  "-keep class org.apache.spark.sql.streaming.** {*;}",
//  "-keep class org.apache.spark.sql.test.** {*;}",
//  "-keep class org.apache.spark.sql.types.** {*;}",
//  "-keep class org.apache.spark.sql.util.** {*;}",
//  "-keep class org.apache.spark.sql.vectorized.** {*;}",
//  // Specify to exhaustively list classes and class members matched by the various -keep options
//  "-printseeds seeds.txt")
//// Set proguardInputs
//Proguard / proguardInputs := (Compile / dependencyClasspath).value.files
//// Set proguardFilteredInputs
//Proguard / proguardFilteredInputs ++= ProguardOptions.noFilter((Compile / packageBin).value)
//// Set proguardInputFilter
//Proguard / proguardInputFilter := { file =>
//  file.name match {
//    case _ => Some("!META-INF/MANIFEST.MF,!META-INF/DUMMY.DSA,!META-INF/DUMMY.SF,!com/google/protobuf25/**,!org/apache/spark/unused/UnusedStubClass.class,!org/apache/orc/storage/**,!javax/inject/**,!org/apache/hadoop/yarn/factories/package-info.class,!org/apache/hadoop/yarn/factory/providers/package-info.class,!org/apache/hadoop/yarn/util/package-info.class, !org/aopalliance/aop/**, !com/sun/activation/registries/**, !org/apache/hadoop/yarn/client/api/impl/package-info.class, !org/apache/hadoop/yarn/client/api/package-info.class, !org/aopalliance/intercept/**, !org/apache/commons/collections/FastHashMap.class, !org/apache/commons/collections/FastHashMap$Values.class, !org/apache/commons/collections/FastHashMap$CollectionView$CollectionViewIterator.class, !org/apache/commons/collections/FastHashMap$1.class, !org/apache/commons/collections/Buffer.class, !org/apache/commons/collections/BufferUnderflowException.class, !org/apache/commons/collections/FastHashMap$KeySet.class, !org/apache/commons/collections/FastHashMap$CollectionView.class, !org/apache/commons/collections/FastHashMap$EntrySet.class, !org/apache/commons/collections/ArrayStack.class")
//  }
//}