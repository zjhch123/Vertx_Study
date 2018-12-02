import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class Application {
    static Vertx vertx = Vertx.vertx();
    public static void main(String[] args) {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/api/test").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.putHeader("Content-Type", "text/plain")
                    .putHeader("X-Server-Name", "Jiahao");
            response.write("Hello, World~!\n");

            routingContext.vertx().setTimer(5000, tid -> routingContext.next());
        });

        router.route("/api/test").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.write("Wor~I'm 2\n");

            routingContext.vertx().setTimer(5000, tid -> routingContext.next());
        });

        router.route("/api/test").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();

            response.write("after 10s later~");
            response.end();
        });

        router.route("/api/user/:id").method(HttpMethod.GET).handler(routingContext -> {
            String id = routingContext.request().getParam("id");
            routingContext.response().end(id);
        });

        router.route("/api/user/:id").method(HttpMethod.POST).handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.end("Wow~, It's POST!\n");
        });

        router.get("/api/article").handler(routingContext -> {
            routingContext.response()
                    .putHeader("Content-Type", "application/json; charset=utf-8")
                    .end("{\"code\": 200}");
        });

        server.requestHandler(router::accept)
              .listen(8887);
    }
}
