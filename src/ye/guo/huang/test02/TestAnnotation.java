package ye.guo.huang.test02;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//����ʱ�䣬�ڳ����������ڱ���ȡ��
@Documented//���ԭע��϶�
public @interface TestAnnotation {
	int id() default 100;//��Ĭ��ֵ��ʱ��ʹ�õ�ʱ����Բ���ֵ
	String msg();
}
