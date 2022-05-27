package com.chlwhdtn.legacy_portableserver

import io.vertx.core.Handler
import io.vertx.core.http.HttpServerRequest

class MainHandler : Handler<HttpServerRequest> {

    override fun handle(event: HttpServerRequest?) {
        if(event != null)
            println(event.remoteAddress())
    }
}