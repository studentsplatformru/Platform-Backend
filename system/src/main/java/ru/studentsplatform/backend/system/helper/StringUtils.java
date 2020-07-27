package ru.studentsplatform.backend.system.helper;


/**
 * Класс хелпер для строк.
 *
 * @author Krylov Sergey (26.07.2020)
 */
public class StringUtils {
	private StringUtils() {
	}

	/**
	 * Превращает номер в оформленный номер.
	 *
	 * @param phone Номер телефона
	 * @return Оформленный номер телефона
	 */
	public static String toValidPhoneNumber(String phone) {
		if (phone == null) {
			return null;
		} else {
			StringBuilder result = new StringBuilder(phone.length());

			for (int i = 0; i < phone.length(); ++i) {
				char c = phone.charAt(i);
				if (Character.isDigit(c)) {
					result.append(c);
				}
			}

			if (result.length() < 10) {
				return null;
			} else {
				result.delete(0, result.length() - 10);
				result.insert(0, "+7(");
				result.insert(6, ")");
				result.insert(10, "-");
				return result.toString();
			}
		}
	}

	/**
	 * Проверка пустая или null строка.
	 *
	 * @param str Строка
	 * @return true - пустая или null, false - есть элементы
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * Проверка есть ли элементы в строке.
	 *
	 * @param str Строка
	 * @return true - есть элементы, false - нет элементов или null
	 */
	public static boolean nonEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * Сравнение строк.
	 *
	 * @param str1 Строка 1
	 * @param str2 Строка 2
	 * @return 0 - если ссылки равны, -1 если str1 == null или str2 == null
	 */
	public static int compare(String str1, String str2) {
		if (str1 == str2) {
			return 0;
		} else if (str1 == null) {
			return -1;
		} else if (str2 == null) {
			return 1;
		} else {
			return str1.compareTo(str2);
		}
	}

	/**
	 * Проверка на то что строка состоит из цифр.
	 *
	 * @param string Строка
	 * @return true - если число, false - если есть буквы или другие символы
	 */
	public static boolean isNumeric(String string) {
		if (isEmpty(string)) {
			return false;
		} else {
			int length = string.length();

			for (int i = 0; i < length; ++i) {
				if (!Character.isDigit(string.charAt(i))) {
					return false;
				}
			}

			return true;
		}
	}
}