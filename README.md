# Annotation
Java注解的学习，运用了大量反射的知识
元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种。
@Retention
Retention 的英文意为保留期的意思。当 @Retention 应用到一个注解上的时候，它解释说明了这个注解的的存活时间。
它的取值如下： 
- RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。 
- RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。 
- RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
我们可以这样的方式来加深理解，@Retention 去给一张标签解释的时候，它指定了这张标签张贴的时间。@Retention 相当于给一张标签上面盖了一张时间戳，时间戳指明了标签张贴的时间周期。
@Documented
顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去。
@Target
Target 是目标的意思，@Target 指定了注解运用的地方。
你可以这样理解，当一个注解被 @Target 注解时，这个注解就被限定了运用的场景。
类比到标签，原本标签是你想张贴到哪个地方就到哪个地方，但是因为 @Target 的存在，它张贴的地方就非常具体了，比如只能张贴到方法上、类上、方法参数上等等。@Target 有下面的取值
  ● ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
  ● ElementType.CONSTRUCTOR 可以给构造方法进行注解
  ● ElementType.FIELD 可以给属性进行注解
  ● ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
  ● ElementType.METHOD 可以给方法进行注解
  ● ElementType.PACKAGE 可以给一个包进行注解
  ● ElementType.PARAMETER 可以给一个方法内的参数进行注解
  ● ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举

@Inherited
Inherited 是继承的意思，但是它并不是说注解本身可以继承，而是说如果一个超类被 @Inherited 注解过的注解进行注解的话，那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。 
说的比较抽象。代码来解释。
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Test {}

@Test
public class A {}
public class B extends A {}

  ● 1
注解 Test 被 @Inherited 修饰，之后类 A 被 Test 注解，类 B 继承 A,类 B 也拥有 Test 这个注解。

Java 预置的注解
学习了上面相关的知识，我们已经可以自己定义一个注解了。其实 Java 语言本身已经提供了几个现成的注解。
@Deprecated
这个元素是用来标记过时的元素，想必大家在日常开发中经常碰到。编译器在编译阶段遇到这个注解时会发出提醒警告，告诉开发者正在调用一个过时的元素比如过时的方法、过时的类、过时的成员变量。
@SuppressWarnings
阻止警告的意思。之前说过调用被 @Deprecated 注解的方法后，编译器会警告提醒，而有时候开发者会忽略这种警告，他们可以在调用的地方通过 @SuppressWarnings 达到目的。
@SuppressWarnings("deprecation")
public void test1(){
    Hero hero = new Hero();
    hero.say();
    hero.speak();
}

注解的提取
注解通过反射获取。首先可以通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {}
然后通过 getAnnotation() 方法来获取 Annotation 对象。
 public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {}


或者是 getAnnotations() 方法。

public Annotation[] getAnnotations() {}

前一种方法返回指定类型的注解，后一种方法返回注解到这个元素上的所有注解。

当开发者使用了Annotation 修饰了类、方法、Field 等成员之后，这些 Annotation 不会自己生效，必须由开发者提供相应的代码来提取并处理 Annotation 信息。这些处理提取和处理 Annotation 的代码统称为 APT（Annotation Processing Tool)。
现在，我们可以给自己答案了，注解有什么用？给谁用？给 编译器或者 APT 用的。
