package com.kmecpp.jlib.function;

import java.util.Arrays;
import java.util.Iterator;

public class AdvancedIterator<E> {

	private Iterable<E> iterable;

	public AdvancedIterator(E[] array) {
		this(Arrays.asList(array));
	}

	public AdvancedIterator(Iterable<E> iterable) {
		this.iterable = iterable;
	}

	public final void start(IterationHandle<E> handle) {
		Iterator<E> iterator = iterable.iterator();

		int index = 0;
		while (iterator.hasNext()) {
			E element = iterator.next();
			handle.next(element, index, !iterator.hasNext());
			index++;
		}

	}

	public interface IterationHandle<E> {

		void next(final E element, final int index, boolean last);

	}

}
