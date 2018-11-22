/*
 * Copyright (C) 2018 Lightbend Inc. <http://www.lightbend.com>
 */

package com.lightbend.lagom.internal.persistence.couchbase

import com.typesafe.config.{Config, ConfigFactory}
import scala.collection.JavaConverters._

object TestConfig {

  lazy val ClusterConfigMap: Map[String, AnyRef] = Map(
    "akka.actor.provider" -> "akka.cluster.ClusterActorRefProvider",

    "akka.remote.netty.tcp.hostname" -> "127.0.0.1",
    "akka.remote.netty.tcp.port" -> "0",

    "akka.loglevel" -> "INFO",

    "akka.cluster.sharding.distributed-data.durable.keys" -> List().asJava,

    "lagom.cluster.join-self" -> "on"
  )

  def clusterConfig(): Config = ConfigFactory.parseMap(ClusterConfigMap.asJava)

  val PersistenceConfigMap: Map[String, AnyRef] = Map(
    "akka.persistence.journal.plugin" -> "couchbase-journal.write",
    "akka.persistence.snapshot-store.plugin" -> "couchbase-journal.snapshot",

    "couchbase-journal.connection.username" -> "admin",
    "couchbase-journal.connection.password" -> "admin1",

    "couchbase-journal.write.bucket" -> "akka",
    "couchbase-journal.write.persist-to" -> "none",
    "couchbase-journal.write.replicate-to" -> "none",

    "couchbase-journal.snapshot.bucket" -> "akka"
  )

  def persistenceConfig(): Config =
    ConfigFactory.parseMap(PersistenceConfigMap.asJava)
}