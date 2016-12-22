package ru.levelup.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.levelup.api.entities.BaseRequest;
import ru.levelup.api.entities.ResponseContainer;
import ru.levelup.errors.ProtocolValidationException;
import ru.levelup.errors.RequestExecutionError;
import ru.levelup.errors.TokenValidationException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetSocketAddress;

@Controller("wsHandler")
public class WSHandler extends WebSocketServer {
    private Gson gson;
    private ProtocolValidator protocolValidator;
    private TokenValidator tokenValidator;
    private RequestExecutor requestExecutor;

    @Autowired
    public WSHandler(Integer wsPort, Gson gson,
                     ProtocolValidator protocolValidator,
                     TokenValidator tokenValidator,
                     RequestExecutor requestExecutor) {
        super(new InetSocketAddress(wsPort));
        this.gson = gson;
        this.protocolValidator = protocolValidator;
        this.tokenValidator = tokenValidator;
        this.requestExecutor = requestExecutor;
    }

    @PostConstruct
    public void init() {
        start();
    }

    @PreDestroy
    public void destroy() {
        try {
            stop();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Client connected");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Client disconnected");
    }

    @Override
    public void onMessage(WebSocket conn, String jsonRequest) {
        BaseRequest baseRequest = null;
        ResponseContainer response = null;
        try {
            baseRequest = gson.fromJson(jsonRequest, BaseRequest.class);
            protocolValidator.validate(baseRequest);
            String userId = tokenValidator.validate(baseRequest.getMethod(), baseRequest.getToken());
            response = requestExecutor.execute(jsonRequest, baseRequest.getMethod(), userId);
            response.setCode(200);
        } catch (JsonSyntaxException e) {
            response = new ResponseContainer<>(451, "JSON_PARSE_ERROR");
        } catch (ProtocolValidationException e) {
            response = new ResponseContainer<>(452, "PROTOCOL_VALIDATION_ERROR");
        } catch (TokenValidationException e) {
            response = new ResponseContainer<>(453, "INVALID_TOKEN");
        } catch (RequestExecutionError e) {
            response = new ResponseContainer<>(454, e.getMessage());
        }

        if (baseRequest != null) {
            response.setRequestId(baseRequest.getRequestId());
        }
        String answer = gson.toJson(response);
        System.out.println(answer);
        conn.send(answer);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }
}
