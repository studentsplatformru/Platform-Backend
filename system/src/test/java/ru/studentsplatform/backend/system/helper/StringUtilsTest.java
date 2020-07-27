package ru.studentsplatform.backend.system.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

	@Test
	void toValidPhoneNumber() {
		var string = "89378731515";
		var phoneNumber = StringUtils.toValidPhoneNumber(string);
		assertEquals("+7(937)873-1515", phoneNumber);
	}

	@Test
	void isEmpty() {
		assertTrue(StringUtils.isEmpty(""));
		assertTrue(StringUtils.isEmpty(null));
		assertFalse(StringUtils.isEmpty("test"));
	}

	@Test
	void nonEmpty() {
		assertFalse(StringUtils.nonEmpty(""));
		assertFalse(StringUtils.nonEmpty(null));
		assertTrue(StringUtils.nonEmpty("test"));
	}

	@Test
	void compare() {
		assertEquals(0, StringUtils.compare("1", "1"));
		assertEquals(-1, StringUtils.compare("1", "2"));
		assertEquals(-1, StringUtils.compare(null, "2"));
		assertEquals(1, StringUtils.compare("1", null));
	}

	@Test
	void isNumeric() {
		assertFalse(StringUtils.isNumeric("test"));
		assertTrue(StringUtils.isNumeric("1234"));
	}
}