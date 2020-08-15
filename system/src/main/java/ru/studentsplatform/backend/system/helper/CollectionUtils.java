package ru.studentsplatform.backend.system.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Класс хелпер для коллекций.
 *
 * @author Krylov Sergey (26.07.2020)
 */
public class CollectionUtils {
	private CollectionUtils() {
	}

	/**
	 * Объединение коллекций.
	 *
	 * @param col1 Коллекция 1
	 * @param col2 Коллекция 2
	 * @param <T>  Тип коллекций
	 * @return Лист объединенных коллекций
	 */
	public static <T> List<T> union(Collection<T> col1, Collection<T> col2) {
		Set<T> set = new HashSet<T>();
		set.addAll(col1);
		set.addAll(col2);
		return new ArrayList<T>(set);
	}


	/**
	 * Проверяет пустая или null коллекция.
	 *
	 * @param collection Коллекция
	 * @return true - если null или пустая, false - если есть элементы в коллекции
	 */
	public static boolean empty(Collection collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Проверяет есть ли элементы в коллекции.
	 *
	 * @param collection Коллекция
	 * @return true - если есть элементы в коллекция, false - если null или пустая
	 */
	public static boolean notEmpty(Collection collection) {
		return collection != null && !collection.isEmpty();
	}

	/**
	 * Возвращаем кол-во элементов non Null в коллекции.
	 *
	 * @param collection Коллекция
	 * @param <T>        Тип коллекций
	 * @return Кол-во элементов которые non null
	 */
	public static <T> int nonNullCount(Collection<T> collection) {
		if (collection == null) {
			return 0;
		}
		return (int) collection.stream().filter(Objects::nonNull).count();
	}

	/**
	 * Добавляем элементы из источника в место назначения очищяя перед этим место назначения.
	 *
	 * @param destination Место назначения
	 * @param source      Источник
	 * @param <T>         Тип коллекций
	 */
	public static <T> void replace(Collection<T> destination, Collection<T> source) {
		destination.clear();
		destination.addAll(source);
	}
}
