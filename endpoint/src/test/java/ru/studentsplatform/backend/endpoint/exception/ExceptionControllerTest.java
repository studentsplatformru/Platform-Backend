package ru.studentsplatform.backend.endpoint.exception;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.studentsplatform.backend.endpoint.EndpointApplication;
import ru.studentsplatform.backend.endpoint.rest.main.MainController;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.exception.core.Fault;
import ru.studentsplatform.backend.system.exception.rest.BusinessExceptionController;

import java.nio.charset.StandardCharsets;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты для {@link BusinessExceptionController}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (31.07.2020).
 */
@SpringBootTest(classes = EndpointApplication.class)
@AutoConfigureMockMvc
class ExceptionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MainController controller;

	@Test
	void pageNotFoundTest() throws Exception {

		MvcResult mvcResult = this.mockMvc
				.perform(get("/some/wrong/path"))
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		Assert.assertNull(response.getErrorMessage());

		Assert.assertTrue(response.getContentAsString(StandardCharsets.UTF_8)
				.contains("\"message\":\"Page not found.\""));

	}

	@Test
	void serverErrorTest() throws Exception {

		given(controller.getMain())
				.willThrow(new ArrayIndexOutOfBoundsException("Test exception"));

		MvcResult mvcResult = this.mockMvc
				.perform(get("/"))
				.andExpect(status().is5xxServerError())
				.andExpect(content().contentType("application/json"))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		Assert.assertNull(response.getErrorMessage());

		Assert.assertTrue(response.getContentAsString(StandardCharsets.UTF_8)
				.contains("\"message\":\"Ошибка.\""));

	}

	@Test
	void businessExceptionTest() throws Exception {

		given(controller.getMain())
				.willThrow(new Fault("message", HttpStatus.BAD_GATEWAY));

		MvcResult mvcResult = this.mockMvc
				.perform(get("/"))
				.andExpect(content().contentType("application/json"))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		Assert.assertNull(response.getErrorMessage());

		Assert.assertTrue(response.getContentAsString(StandardCharsets.UTF_8)
				.contains("\"message\":\"message\""));

	}
}
