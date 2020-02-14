package com.example.demo;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = { "wiremock.port=9090", "todos.service.url=http://localhost:${wiremock.port}/todos-service" })
@AutoConfigureMockMvc
public abstract class IntegrationTest {
    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(options().port(9090));
}
