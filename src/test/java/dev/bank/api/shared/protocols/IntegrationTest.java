package dev.bank.api.shared.protocols;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class IntegrationTest {
    static protected String EMPTY_STRING = "";

    static protected Faker faker;

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    static void setup() {
        faker = new Faker();
    }

    protected MockHttpServletResponse post(String url, String json) throws Exception {
        var request = MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        return mvc.perform(request).andReturn().getResponse();
    }
}
