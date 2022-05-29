package com.chlwhdtn.legacy_portableserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.netty.util.internal.logging.InternalLogger
import io.netty.util.internal.logging.InternalLoggerFactory
import io.netty.util.internal.logging.JdkLoggerFactory
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpClientOptions
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router


class MainActivity : AppCompatActivity() {

    private val port = 8080
    private val vertx: Vertx = Vertx.vertx(VertxOptions().setWorkerPoolSize(40))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InternalLoggerFactory.setDefaultFactory(JdkLoggerFactory.INSTANCE)


        createPortable(vertx, "Earth")
        createPortable(vertx, "Solar")


    }

    private fun createPortable(vertx: Vertx, address: String) : Portable {
        val route: Route = Router.router(vertx).route(address)

        route.handler {
            val response: HttpServerResponse = it.response()
            response.putHeader("content-type", "text/plane")
            response.end("Hello $address")
        }
        return Portable(route)
    }




}

