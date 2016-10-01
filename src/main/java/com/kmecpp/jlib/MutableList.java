package com.kmecpp.jlib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import com.kmecpp.jlib.function.Converter;
import com.kmecpp.jlib.function.Predicate;
import com.kmecpp.jlib.utils.CollectionsUtil;

public class MutableList<E> {

	private ArrayList<E> list = new ArrayList<>();

	public MutableList(Iterable<E> iterable) {
		Iterator<E> iterator = iterable.iterator();

		while (iterator.hasNext()) {
			this.list.add(iterator.next());
		}
	}

	public MutableList(E[] array) {
		for (E e : array) {
			this.list.add(e);
		}
	}

	public MutableList<E> contains(Predicate<E> predicate) {
		CollectionsUtil.contains(list, predicate);
		return this;
	}

	public MutableList<E> filter(Predicate<E> predicate) {
		CollectionsUtil.filter(list, predicate);
		return this;
	}

	public MutableList<String> asStringList() {
		return convert(new Converter<E, String>() {

			@Override
			public String convert(E e) {
				return String.valueOf(e);
			}

		});
	}

	public <N> MutableList<N> convert(Converter<E, N> converter) {
		return new MutableList<>(CollectionsUtil.convert(list, converter));
	}

	@SuppressWarnings("unchecked")
	public E[] getArray(Class<?> type) {
		return list.toArray((E[]) Array.newInstance(type, list.size()));
	}

	public ArrayList<E> getList() {
		return list;
	}

}
