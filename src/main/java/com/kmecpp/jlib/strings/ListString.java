package com.kmecpp.jlib.strings;

/**
 * Class for creating string representations of lists and arrays
 */
public class ListString {

	private StringBuilder sb = new StringBuilder("[");

	/**
	 * Adds an element to the list
	 * 
	 * @param element
	 *            the element to add
	 */
	public void add(Object element) {
		sb.append(element + ", ");
	}

	@Override
	public String toString() {
		sb.setLength(sb.length() - 2);
		return sb.append("]").toString();
	}

}
