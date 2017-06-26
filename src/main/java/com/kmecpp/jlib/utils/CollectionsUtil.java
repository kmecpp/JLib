package com.kmecpp.jlib.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.kmecpp.jlib.function.Converter;
import com.kmecpp.jlib.function.Predicate;

/**
 * A temporary class that implements some of Java 8's Stream functionality while
 * server is still running version 7
 */
public abstract class CollectionsUtil {

	/**
	 * Clones the list and then appends the specified elements to the end of of
	 * the new list.
	 * 
	 * @return a new list with the given values added to it
	 */
	public static <E> LinkedList<E> add(LinkedList<E> list, E value) {
		LinkedList<E> copy = new LinkedList<>(list);
		copy.add(value);
		return copy;
	}

	/**
	 * Tests whether or not there is an element in the Collection matching the
	 * given test
	 * 
	 * @param collection
	 *            the collection
	 * @param predicate
	 *            the test to run
	 * @return true if an element in the collection passes the test and false
	 *         otherwise
	 */
	public static <C extends Collection<T>, T> boolean contains(C collection, Predicate<T> predicate) {
		for (T t : collection) {
			if (predicate.test(t)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Converts the given array to an {@link ArrayList}
	 * 
	 * @param array
	 *            the array to convert
	 * @return an ArrayList containing the elements of the array
	 */
	public static <E> ArrayList<E> toList(E[] array) {
		return new ArrayList<>(Arrays.asList(array));
	}

	/**
	 * Filters the given Collection based on the given predicate and returns the
	 * altered Collection
	 * 
	 * @param c
	 *            the collection
	 * @param predicate
	 *            the test to filter with
	 * @return the filtered Collection
	 */
	public static <C extends Collection<T>, T> C filter(C collection, Predicate<T> predicate) {
		Iterator<T> iterator = collection.iterator();

		while (iterator.hasNext()) {
			if (!predicate.test(iterator.next())) {
				iterator.remove();
			}
		}

		return collection;
	}

	/**
	 * Filters an array, with the given {@link Predicate} and returns an
	 * {@link ArrayList} containing the result
	 * 
	 * @param array
	 *            the array to filter
	 * @param predicate
	 *            the filter for the array
	 * @return the filtered array as an ArrayList
	 */
	public static <E> ArrayList<E> filter(E[] array, Predicate<E> predicate) {
		return filter(toList(array), predicate);
	}

	/**
	 * Converts the given list to an {@link ArrayList} of strings
	 * 
	 * @param list
	 *            the list to convert
	 * @return a list of all the original elements converted to strings
	 */
	public static <E> ArrayList<String> toString(List<E> list) {
		return convert(list, new Converter<E, String>() {

			@Override
			public String convert(Object o) {
				return String.valueOf(o);
			}

		});
	}

	/**
	 * Converts the list to an {@link ArrayList} with a new type using the given
	 * converter
	 * 
	 * @param list
	 *            the list to convert
	 * @return a list of all the original elements converted to strings
	 */
	public static <O, N> ArrayList<N> convert(List<O> list, Converter<O, N> converter) {
		ArrayList<N> converted = new ArrayList<>();
		for (O element : list) {
			converted.add(converter.convert(element));
		}
		return converted;
	}

	/**
	 * Converts the array to an {@link ArrayList} with a new type using the
	 * given converter
	 * 
	 * @param list
	 *            the list to convert
	 * @return a list of all the original elements converted to strings
	 */
	public static <O, N> ArrayList<N> convert(O[] array, Converter<O, N> converter) {
		return convert(toList(array), converter);
	}

	/**
	 * Flattens the collection into a one dimensional list of objects of the
	 * given type.
	 * 
	 * @param collection
	 *            the collection to flatten
	 * @param cls
	 *            the type of the flattened list
	 * @return the flattened collection
	 */
	public static <T> Collection<T> flatten(Collection<?> collection, Class<T> cls) {
		ArrayList<T> flat = new ArrayList<>();
		for (Object element : collection) {
			if (element instanceof Collection<?>) {
				flat.addAll(flatten((Collection<?>) element, cls));
			} else {
				flat.add(cls.cast(element));
			}
		}
		return flat;
	}

}
