package com.chlwhdtn.legacy_portableserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.netty.util.internal.logging.InternalLogger
import io.netty.util.internal.logging.InternalLoggerFactory
import io.netty.util.internal.logging.JdkLoggerFactory
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpServerOptions


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InternalLoggerFactory.setDefaultFactory(JdkLoggerFactory.INSTANCE)

        val vertx = Vertx.vertx(VertxOptions().setWorkerPoolSize(40))

        vertx.createHttpServer(HttpServerOptions().setPort(8080))
            .requestHandler { req ->
                req.response().end("YOU CONNECTED TO PHONE")
            }
            .listen(8080)
            .onSuccess {
                println("HTTP server started on port " + it.actualPort())
            }
    }
}