package ye.guo.huang.test02;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//����ʱ�䣬�ڳ����������ڱ���ȡ��
@Documented//���ԭע��϶�
@Target(ElementType.FIELD)
public @interface TestAnnotation02 {
	
	String value() default "����";
	
}
