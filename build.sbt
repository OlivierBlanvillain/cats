scalaVersion  in ThisBuild := "2.12.1"
scalacOptions in ThisBuild := Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:_",
  "-unchecked",
  // "-Xfatal-warnings", cuz stainless-library
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard")

lazy val root      = project.in(file(".")).aggregate(core, kernel, instances, proofs, `stainless-library`).settings(submoduleChecks)
lazy val kernel    = project
lazy val core      = project.dependsOn(kernel)
lazy val proofs    = project.dependsOn(kernel, core, instances, `stainless-library`)
lazy val instances = project.dependsOn(kernel, core, `stainless-library`)
                       .settings(addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3"))

// The stainless-library is not publish anywhere so it's pulled in as a git
// submodule with the fork of the main stainless repo where the library has
// been isolated and moved to fulfill the standard scala project structure.

lazy val `stainless-library` = project

lazy val submoduleChecks = onLoad in Global := (onLoad in Global).value andThen { state =>
  val submodules = List(new File("stainless-library"), new File("stainless-prover"))
  if (!submodules.forall(f => f.exists && f.listFiles().nonEmpty)) {
    sLog.value.log(Level.Error,
      """Please initialize submodules:
         |  $ git submodule update --init
      """.stripMargin)
  }
  state
}

// This currently the recommended way to call into the prover. To speed up the
// process, we find the all the laws with grep and filter it via --functions=.

lazy val prove = taskKey[Unit]("Prove type class laws using stripMargin")
prove := { "sh prove.sh" ! }
