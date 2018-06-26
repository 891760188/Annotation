package ye.guo.huang.test02;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//保留时间，在程序运行周期被获取到
@Documented//这个原注解肯定
public @interface TestAnnotation {
	int id() default 100;//有默认值得时候，使用得时候可以不赋值
	String msg();
}
