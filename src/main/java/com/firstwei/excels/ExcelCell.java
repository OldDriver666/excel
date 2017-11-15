package com.firstwei.excels;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel cell 和 model field对应
 * 
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCell {
    /**
     * 在EXCEL中的列位置
     * @return
     */
	String cell();
	
	/**
	 * 在EXCEL中列名称
	 * @return
	 */
	String name();
}
