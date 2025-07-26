package com.devopsdemo.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A utility class for string manipulations and conversions.
 */
public class StringUtilities {

	private static final String COMMA_SEPARATOR = ",";
	private static final String PARAM_SEPARATOR = "=";
	private static final String TYPE_SEPARATOR = ";";
	private static final String DATEFORMAT_SEPARATOR = "@";
	private static final String CONVERTOR_METHOD_NAME = "valueOf";
	private static final String DATE_TYPE = "date";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String STRING_TYPE = "string";

	private static final Logger LOG = LoggerFactory.getLogger(StringUtilities.class);

	private static final HashMap<String, Class<?>> PRIMITIVE_NAME_TYPE_MAP = new HashMap<>();

	static {
		PRIMITIVE_NAME_TYPE_MAP.put("boolean", Boolean.class);
		PRIMITIVE_NAME_TYPE_MAP.put("int", Integer.class);
		PRIMITIVE_NAME_TYPE_MAP.put("long", Long.class);
		PRIMITIVE_NAME_TYPE_MAP.put("float", Float.class);
		PRIMITIVE_NAME_TYPE_MAP.put("double", Double.class);
	}

	/**
	 * Checks if a string is null or empty.
	 * @param input the string to check
	 * @return true if null or empty, false otherwise
	 */
	public static boolean isNullOrEmpty(String input) {
		return input == null || input.isEmpty();
	}

	/**
	 * Reverses the given string.
	 * @param input the string to reverse
	 * @return the reversed string
	 */
	public static String reverseString(String input) {
		if (input == null) return null;
		return new StringBuilder(input).reverse().toString();
	}

	/**
	 * given a comma separated string and type, returns an ArrayList of specific types 
	 * @param strParamValueList The string (assumed to be comma separated). Usually meant for use in creating 
	 *                          parameter values for passing in IN Clauses
	 * @param type The type of the Arraylist passed
	 * @return ArrayList if passed value is not null or empty, null otherwise
	 */
	public static List<Object> convertStringToList(String strParamValueList,String type){
		if (strParamValueList == null || strParamValueList.isBlank()) {
			return null;
		}

		var list = new ArrayList<Object>();
		var arr = strParamValueList.trim().split(COMMA_SEPARATOR);
		for (var tmpString : arr) {
			list.add(convert(tmpString, type));
		}
		return list;
	}
	
	/**
	 * given a variable list of String parameters, forms a hash map 
	 * @param strParamValueList Variable list of arguments each of format: key=kevalue;type.
	 *                          For the type date,you can even pass the dateformat value as key=keyvalue@dateformat;type.
	 *                          If dateformat is not passed default format yyyy-MM-dd HH:mm:ss will be taken.
	 * @return HashMap  if no arguments are passed, returns an empty list, else populated hashmap
	 * support only int, string, boolean, float, double, long, date
	 */
	public static HashMap<String, Object> createParameterList(String... strParamValueList){
		var hMap = new HashMap<String, Object>();
		for (var strArg : strParamValueList) {
			String type = null;
			if (strArg.contains(TYPE_SEPARATOR)) {
				var parts = strArg.split(TYPE_SEPARATOR);
				type = parts[1];
				strArg = parts[0];
			}
			if (strArg.contains(PARAM_SEPARATOR)) {
				var arr = strArg.split(PARAM_SEPARATOR);
				if (arr[1].contains(COMMA_SEPARATOR)) {
					hMap.put(arr[0], convertStringToList(arr[1], type));
				} else {
					hMap.put(arr[0], convert(arr[1], type));
				}
			}
		}
		return hMap;
	}
	
	/**
	 * Converts the given String value to the intended type of Object
	 * @param value The String value to be converted
	 * @param type The type to which the value needs to be converted
	 * @return Object Returns values as such if type or value is empty or null,else returns the converted Object
	 */
	private static Object convert(String value, String types) {

		if (value == null || value.isBlank() || types == null || types.isBlank() || types.equalsIgnoreCase(STRING_TYPE)) {
			return value;
		}

		var type = types.toLowerCase();
		if (type.equals(DATE_TYPE)) {
			return convertStringToDate(value);
		}

		var finalClass = PRIMITIVE_NAME_TYPE_MAP.get(type);
		try {
			if (finalClass != null) {
				Method method = finalClass.getMethod(CONVERTOR_METHOD_NAME, String.class);
				if (Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
					return method.invoke(null, value);
				}
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			LoggerStackTraceUtil.printErrorMessage(e);
		}

		return value;
	}

	/**
	 * Convert the given date value in string to date object
	 * @param dateString The date to be formatted
	 * @return Object Returns the corresponding Date object
	 */
	private static Object convertStringToDate(String dateString) {
		var dateFormat = dateString.contains(DATEFORMAT_SEPARATOR)
				? dateString.split(DATEFORMAT_SEPARATOR)[1]
				: DATE_FORMAT;
		var dateStringVal = dateString.contains(DATEFORMAT_SEPARATOR)
				? dateString.split(DATEFORMAT_SEPARATOR)[0]
				: dateString;

		try {
			var formatter = DateTimeFormatter.ofPattern(dateFormat);
			return LocalDateTime.parse(dateStringVal, formatter);
		} catch (Exception e) {
			LoggerStackTraceUtil.printErrorMessage(e);
		}
		return null;
	}
}
