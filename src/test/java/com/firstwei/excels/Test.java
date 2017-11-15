package com.firstwei.excels;

import java.lang.reflect.Field;

public class Test {

	public static void main(String[] args) throws Exception {
		File filed = new File();
		filed.setNumber(11);
		Class<?> claz = filed.getClass();

		Field[] fields = claz.getFields();
		for (Field field : fields) {
			System.out.println("获取所有的public及继承的字段" + field);
		}

		Field[] fieldsDecrs = claz.getDeclaredFields();
		for (Field fieldDecr : fieldsDecrs) {
			System.out.println("获取所有的字段包括private/public，但不包括继承的字段" + fieldDecr);
		}

		/* 获取指定的字段：参数传字段名称 */
		Field field1 = claz.getField("name");
		System.out.println("获取指定的public字段" + field1);

		/* 获取指定的私有字段 */
		Field fieldP = claz.getDeclaredField("age");
		System.out.println("获取指定的私有字段:" + fieldP);

		/**
		 * 为字段设值：setxxx(obj,基本类型数据)基本类型字段设值； set(obj,引用类型数据) 引用类型设值 obj为字段所在底层对象
		 * 如果是静态字段则obj可以为NULL
		 */
		/* 给字段设置值 */
		field1.set(claz.newInstance(), "张飞");
		System.out.println(field1.get(claz.newInstance()));

		/* 给私有字段设置值 */
		fieldP.setAccessible(true);
		fieldP.setInt(claz.newInstance(), 12);
		System.out.println(fieldP.getInt(claz.newInstance()));

		/**
		 * 获取字段值 ：getxxx(obj) get(obj) 如果字段为static修饰则obj可以为null
		 */
		/* 获取私有静态字段的值 */
		Field fieldd = claz.getDeclaredField("age");
		fieldd.setAccessible(true);
		System.out.println(fieldd.getInt(claz.newInstance()) + "******私有静态引用类型");

		/* 获取私有的字段的值 */
		Field fieldnum = claz.getDeclaredField("number");
		fieldnum.setAccessible(true);
		System.out.println(fieldnum.get(filed) + "******私有基本类型");

	}
}