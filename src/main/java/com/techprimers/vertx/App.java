package com.techprimers.vertx;


import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class App
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();

        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);

        Route handler1 = router
                .get("/")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "text/plain");
                    response.setChunked(true);
                    response.end("Hi world");
                });

        Route handler2 = router
                .get("/hello/:name")
                .handler(routingContext -> {
                    String name = routingContext.request().getParam("name");
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "text/plain");
                    response.setChunked(true);
                    response.write("hello " + name);
                    response.end();
                });

        Route handler3 = router
                .post("/hello")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "text/plain");
                    response.setChunked(true);
                    response.write("post message");
                    response.end();
                });

        httpServer.requestHandler(router::accept)
                .listen(8091);


    }
}
