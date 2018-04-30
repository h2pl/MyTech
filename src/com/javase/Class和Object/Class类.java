package com.javase.Class和Object;

/**
 * Created by 周杰伦 on 2018/4/28.
 */
public class Class类 {
    Class aClass = null;

//    private EnclosingMethodInfo getEnclosingMethodInfo() {
//        Object[] enclosingInfo = getEnclosingMethod0();
//        if (enclosingInfo == null)
//            return null;
//        else {
//            return new EnclosingMethodInfo(enclosingInfo);
//        }
//    }

    /**提供原子类操作
     * Atomic operations support.
     */
//    private static class Atomic {
//        // initialize Unsafe machinery here, since we need to call Class.class instance method
//        // and have to avoid calling it in the static initializer of the Class class...
//        private static final Unsafe unsafe = Unsafe.getUnsafe();
//        // offset of Class.reflectionData instance field
//        private static final long reflectionDataOffset;
//        // offset of Class.annotationType instance field
//        private static final long annotationTypeOffset;
//        // offset of Class.annotationData instance field
//        private static final long annotationDataOffset;
//
//        static {
//            Field[] fields = Class.class.getDeclaredFields0(false); // bypass caches
//            reflectionDataOffset = objectFieldOffset(fields, "reflectionData");
//            annotationTypeOffset = objectFieldOffset(fields, "annotationType");
//            annotationDataOffset = objectFieldOffset(fields, "annotationData");
//        }

        //提供反射信息
    // reflection data that might get invalidated when JVM TI RedefineClasses() is called
//    private static class ReflectionData<T> {
//        volatile Field[] declaredFields;
//        volatile Field[] publicFields;
//        volatile Method[] declaredMethods;
//        volatile Method[] publicMethods;
//        volatile Constructor<T>[] declaredConstructors;
//        volatile Constructor<T>[] publicConstructors;
//        // Intermediate results for getFields and getMethods
//        volatile Field[] declaredPublicFields;
//        volatile Method[] declaredPublicMethods;
//        volatile Class<?>[] interfaces;
//
//        // Value of classRedefinedCount when we created this ReflectionData instance
//        final int redefinedCount;
//
//        ReflectionData(int redefinedCount) {
//            this.redefinedCount = redefinedCount;
//        }
//    }
        //方法数组
//    static class MethodArray {
//        // Don't add or remove methods except by add() or remove() calls.
//        private Method[] methods;
//        private int length;
//        private int defaults;
//
//        MethodArray() {
//            this(20);
//        }
//
//        MethodArray(int initialSize) {
//            if (initialSize < 2)
//                throw new IllegalArgumentException("Size should be 2 or more");
//
//            methods = new Method[initialSize];
//            length = 0;
//            defaults = 0;
//        }

    //注解信息
    // annotation data that might get invalidated when JVM TI RedefineClasses() is called
//    private static class AnnotationData {
//        final Map<Class<? extends Annotation>, Annotation> annotations;
//        final Map<Class<? extends Annotation>, Annotation> declaredAnnotations;
//
//        // Value of classRedefinedCount when we created this AnnotationData instance
//        final int redefinedCount;
//
//        AnnotationData(Map<Class<? extends Annotation>, Annotation> annotations,
//                       Map<Class<? extends Annotation>, Annotation> declaredAnnotations,
//                       int redefinedCount) {
//            this.annotations = annotations;
//            this.declaredAnnotations = declaredAnnotations;
//            this.redefinedCount = redefinedCount;
//        }
//    }
}
