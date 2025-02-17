// The Play plugin
//resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// !! Important: The Play plugin || Remember, the play plugin is the playframework.
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.9")
// !! Important

addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "5.0.2")
addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.2")
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.2")
addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.10")
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.4")
addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")
addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.2")