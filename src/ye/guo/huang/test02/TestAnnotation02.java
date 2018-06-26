package ye.guo.huang.test02;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//保留时间，在程序运行周期被获取到
@Documented//这个原注解肯定
@Target(ElementType.FIELD)
public @interface TestAnnotation02 {
	
	String value() default "张三";
	
}
