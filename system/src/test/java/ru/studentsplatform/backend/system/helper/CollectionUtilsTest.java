package ru.studentsplatform.backend.system.helper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionUtilsTest {

	@Test
	void union() {
		var union = CollectionUtils.union(List.of(1), List.of(2));
		assertEquals(2, union.size());
	}

	@Test
	void empty() {
		assertTrue(CollectionUtils.empty(null));
		assertTrue(CollectionUtils.empty(Collections.emptyList()));
		assertFalse(CollectionUtils.empty(List.of(1)));
	}

	@Test
	void notEmpty() {
		assertFalse(CollectionUtils.notEmpty(null));
		assertFalse(CollectionUtils.notEmpty(Collections.emptyList()));
		assertTrue(CollectionUtils.notEmpty(List.of(1)));
	}

	@Test
	void nonNullCount() {
		var list = new ArrayList<Long>();
		list.add(1L);
		list.add(null);
		list.add(2L);
		var result = CollectionUtils.nonNullCount(list);
		assertEquals(2, result);
	}

	@Test
	void replace() {
		var list1 = new ArrayList<Long>();
		list1.add(1L);
		var list2 = new ArrayList<Long>();
		list2.add(2L);
		CollectionUtils.replace(list1, list2);
		assertEquals(2, list1.get(0));
	}
}