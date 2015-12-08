package com.pinpos.swing;

public interface IUpdatebleView<E> {
	void initView(E e);
	boolean updateModel(E e);
}
