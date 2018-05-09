package com.Java集合;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by 周杰伦 on 2018/5/8.
 */
public class ArrayList和Vector和Stack {
    public static void main(String[] args) {
        //一般讨论集合类无非就是。这里的两种数组类型更是如此
        // 1底层数据结构
        // 2增删改查方式
        // 3线程安全与否
        // 4初始容量，扩容方式，扩容时机。
        // 5是否允许空，是否允许重复，是否有序

        ArrayList list = new ArrayList();
        //transient Object[] elementData; // non-private to simplify nested class access
        //ArrayList底层数组不会参与序列化，而是使用另外的序列化方式。
        //使用writeobject方法进行序列化,具体为什么这么做欢迎查看我之前的关于序列化的文章
        //总结一下就是只复制数组中有值的位置，其他未赋值的位置不进行序列化，可以节省空间。
//        private void writeObject(java.io.ObjectOutputStream s)
//        throws java.io.IOException{
//            // Write out element count, and any hidden stuff
//            int expectedModCount = modCount;
//            s.defaultWriteObject();
//
//            // Write out size as capacity for behavioural compatibility with clone()
//            s.writeInt(size);
//
//            // Write out all elements in the proper order.
//            for (int i=0; i<size; i++) {
//                s.writeObject(elementData[i]);
//            }
//
//            if (modCount != expectedModCount) {
//                throw new ConcurrentModificationException();
//            }
//        }
        //增删改查
//        public void add(int index, E element) {
//            rangeCheckForAdd(index);
//
//            ensureCapacityInternal(size + 1);  // Increments modCount!!
//            System.arraycopy(elementData, index, elementData, index + 1,
//                    size - index);
//            elementData[index] = element;
//            size++;
//        }
//
//        public E remove(int index) {
//            rangeCheck(index);
//
//            modCount++;
//            E oldValue = elementData(index);
//
//            int numMoved = size - index - 1;
//            if (numMoved > 0)
//                System.arraycopy(elementData, index+1, elementData, index,
//                        numMoved);
//            elementData[--size] = null; // clear to let GC do its work
//
//            return oldValue;
//        }
//
//        /**
//         * Removes all of the elements from this list.  The list will
//         * be empty after this call returns.
//         */
//        public void clear() {
//            modCount++;
//
//            // clear to let GC do its work
//            for (int i = 0; i < size; i++)
//                elementData[i] = null;
//
//            size = 0;
//        }
//        public E set(int index, E element) {
//            rangeCheck(index);
//
//            E oldValue = elementData(index);
//            elementData[index] = element;
//            return oldValue;
//        }
//
//        public E get(int index) {
//            rangeCheck(index);
//
//            return elementData(index);
//        }
//
//        private void rangeCheck(int index) {
//            if (index >= size)
//                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//        }
//        protected transient int modCount = 0;
//        private static final int DEFAULT_CAPACITY = 10;

//        public void ensureCapacity(int minCapacity) {
//            int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
//                    // any size if not default element table
//                    ? 0
//                    // larger than default for default empty table. It's already
//                    // supposed to be at default size.
//                    : DEFAULT_CAPACITY;
//
//            if (minCapacity > minExpand) {
//                ensureExplicitCapacity(minCapacity);
//            }
//        }
//        private void ensureCapacityInternal(int minCapacity) {
//            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
//                minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
//            }
//
//            ensureExplicitCapacity(minCapacity);
//        }


//        private void grow(int minCapacity) {
//            // overflow-conscious code
//            int oldCapacity = elementData.length;
//            int newCapacity = oldCapacity + (oldCapacity >> 1);
//            if (newCapacity - minCapacity < 0)
//                newCapacity = minCapacity;
//            if (newCapacity - MAX_ARRAY_SIZE > 0)
//                newCapacity = hugeCapacity(minCapacity);
//            // minCapacity is usually close to size, so this is a win:
//            elementData = Arrays.copyOf(elementData, newCapacity);
//        }

        list.add(1);
        list.remove(0);
        list.get(0);
        list.set(0, 2);

        Vector vector = new Vector();
        vector.add(1);
        vector.remove(0);
        vector.get(0);
        vector.set(0, 2);
//        protected Object[] elementData;

//        private void writeObject(java.io.ObjectOutputStream s)
//            throws java.io.IOException {
//            final java.io.ObjectOutputStream.PutField fields = s.putFields();
//            final Object[] data;
//            synchronized (this) {
//                fields.put("capacityIncrement", capacityIncrement);
//                fields.put("elementCount", elementCount);
//                data = elementData.clone();
//            }
//            fields.put("elementData", data);
//            s.writeFields();
//        }

//        public Enumeration<E> elements() {
//            return new Enumeration<E>() {
//                int count = 0;
//
//                public boolean hasMoreElements() {
//                    return count < elementCount;
//                }
//
//                public E nextElement() {
//                    synchronized (Vector.this) {
//                        if (count < elementCount) {
//                            return elementData(count++);
//                        }
//                    }
//                    throw new NoSuchElementException("Vector Enumeration");
//                }
//            };
//        }
//
//        public synchronized void ensureCapacity(int minCapacity) {
//            if (minCapacity > 0) {
//                modCount++;
//                ensureCapacityHelper(minCapacity);
//            }
//        }
//        private void ensureCapacityHelper(int minCapacity) {
//            // overflow-conscious code
//            if (minCapacity - elementData.length > 0)
//                grow(minCapacity);
//        }
//
//        private void grow(int minCapacity) {
//            // overflow-conscious code
//            int oldCapacity = elementData.length;
//            int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
//                    capacityIncrement : oldCapacity);
//            if (newCapacity - minCapacity < 0)
//                newCapacity = minCapacity;
//            if (newCapacity - MAX_ARRAY_SIZE > 0)
//                newCapacity = hugeCapacity(minCapacity);
//            elementData = Arrays.copyOf(elementData, newCapacity);
//        }
//
//    public synchronized E elementAt(int index) {
//        if (index >= elementCount) {
//            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
//        }
//
//        return elementData(index);
//    }
//
//
//    public synchronized void setElementAt(E obj, int index) {
//        if (index >= elementCount) {
//            throw new ArrayIndexOutOfBoundsException(index + " >= " +
//                    elementCount);
//        }
//        elementData[index] = obj;
//    }
//
//    /**
//     * Deletes the component at the specified index. Each component in
//     * this vector with an index greater or equal to the specified
//     * {@code index} is shifted downward to have an index one
//     * smaller than the value it had previously. The size of this vector
//     * is decreased by {@code 1}.
//     *
//     * <p>The index must be a value greater than or equal to {@code 0}
//     * and less than the current size of the vector.
//     *
//     * <p>This method is identical in functionality to the {@link #remove(int)}
//     * method (which is part of the {@link List} interface).  Note that the
//     * {@code remove} method returns the old value that was stored at the
//     * specified position.
//     *
//     * @param      index   the index of the object to remove
//     * @throws ArrayIndexOutOfBoundsException if the index is out of range
//     *         ({@code index < 0 || index >= size()})
//     */
//    public synchronized void removeElementAt(int index) {
//        modCount++;
//        if (index >= elementCount) {
//            throw new ArrayIndexOutOfBoundsException(index + " >= " +
//                    elementCount);
//        }
//        else if (index < 0) {
//            throw new ArrayIndexOutOfBoundsException(index);
//        }
//        int j = elementCount - index - 1;
//        if (j > 0) {
//            System.arraycopy(elementData, index + 1, elementData, index, j);
//        }
//        elementCount--;
//        elementData[elementCount] = null; /* to let gc do its work */
//    }
//
//    /**
//     * Inserts the specified object as a component in this vector at the
//     * specified {@code index}. Each component in this vector with
//     * an index greater or equal to the specified {@code index} is
//     * shifted upward to have an index one greater than the value it had
//     * previously.
//     *
//     * <p>The index must be a value greater than or equal to {@code 0}
//     * and less than or equal to the current size of the vector. (If the
//     * index is equal to the current size of the vector, the new element
//     * is appended to the Vector.)
//     *
//     * <p>This method is identical in functionality to the
//     * {@link #add(int, Object) add(int, E)}
//     * method (which is part of the {@link List} interface).  Note that the
//     * {@code add} method reverses the order of the parameters, to more closely
//     * match array usage.
//     *
//     * @param      obj     the component to insert
//     * @param      index   where to insert the new component
//     * @throws ArrayIndexOutOfBoundsException if the index is out of range
//     *         ({@code index < 0 || index > size()})
//     */
//    public synchronized void insertElementAt(E obj, int index) {
//        modCount++;
//        if (index > elementCount) {
//            throw new ArrayIndexOutOfBoundsException(index
//                    + " > " + elementCount);
//        }
//        ensureCapacityHelper(elementCount + 1);
//        System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
//        elementData[index] = obj;
//        elementCount++;
//    }
//
//    /**
//     * Adds the specified component to the end of this vector,
//     * increasing its size by one. The capacity of this vector is
//     * increased if its size becomes greater than its capacity.
//     *
//     * <p>This method is identical in functionality to the
//     * {@link #add(Object) add(E)}
//     * method (which is part of the {@link List} interface).
//     *
//     * @param   obj   the component to be added
//     */
//    public synchronized void addElement(E obj) {
//        modCount++;
//        ensureCapacityHelper(elementCount + 1);
//        elementData[elementCount++] = obj;
//    }
//
//    protected int elementCount;
//
//    /**
//     * The amount by which the capacity of the vector is automatically
//     * incremented when its size becomes greater than its capacity.  If
//     * the capacity increment is less than or equal to zero, the capacity
//     * of the vector is doubled each time it needs to grow.
//     *
//     * @serial
//     */
//    protected int capacityIncrement;
//
//
//    }
//    public Vector() {
//        this(10);
//    }
    }
}
