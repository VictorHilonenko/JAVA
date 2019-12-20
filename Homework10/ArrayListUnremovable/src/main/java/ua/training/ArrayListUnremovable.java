package ua.training;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class ArrayListUnremovable <T> extends ArrayList <T> {
	private final String ACCESS_IS_DENIED_MESSAGE = "Access is denied. This ArrayList doesn't allow to remove elements";

	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException(ACCESS_IS_DENIED_MESSAGE);
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException(ACCESS_IS_DENIED_MESSAGE);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException(ACCESS_IS_DENIED_MESSAGE);
	}

	@Override
	public boolean removeIf(Predicate<? super T> filter) {
		throw new UnsupportedOperationException(ACCESS_IS_DENIED_MESSAGE);
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException(ACCESS_IS_DENIED_MESSAGE);
	}
}
