package com.example.demo;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Component
@Profile("mock")
public class Wiremock implements DisposableBean {
    private final WireMockServer server;

    @Value("${mock.server.port}")
    private int port;

    public Wiremock() {
        server = new WireMockServer(
                options()
                        .port(port)
                        .containerThreads(10)
                        .jettyAcceptors(4)
                        .asynchronousResponseEnabled(true)
                        .asynchronousResponseThreads(10)
                        .disableRequestJournal()
        );
        server.start();
    }

    @Override
    public void destroy() {
        if (server.isRunning()) {
            server.stop();
        }
    }
}
