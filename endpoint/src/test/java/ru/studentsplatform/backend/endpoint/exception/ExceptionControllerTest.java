package ru.studentsplatform.backend.endpoint.exception;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.studentsplatform.backend.endpoint.EndpointApplication;
import ru.studentsplatform.backend.endpoint.rest.MainController;
import ru.studentsplatform.backend.system.exception.core.BusinessExceptionController;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты для {@link BusinessExceptionController}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (30.07.2020).
 */
@SpringBootTest(classes = EndpointApplication.class)
@AutoConfigureMockMvc
public class ExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MainController controller;

    @Test
    public void pageNotFoundTest() throws Exception {

        MvcResult mvcResult = this.mockMvc
                .perform(get("/some/wrong/path"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertNull(response.getErrorMessage());

        Assert.assertTrue(response.getContentAsString(StandardCharsets.UTF_8)
                .contains("\"message\":\"Упс, страница не найдена.\""));

    }

}
