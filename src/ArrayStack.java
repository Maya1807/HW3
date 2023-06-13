import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Objects;
import java.lang.reflect.Method;

public class ArrayStack <E extends Cloneable> implements Stack<E> {
    private Cloneable[] arr;
    private int maxCapacity;
    private int top;

    public ArrayStack (int maxCapacity){
        if (maxCapacity<0){
            throw new NegativeCapacityException();
        }
        this.maxCapacity = maxCapacity;
        arr = new Cloneable[maxCapacity];
        top = -1;
    }
    @Override
    public void push(E element) {
        if (top+1 == maxCapacity){
            throw new StackOverflowException();
        }
        else{
            top++;
            arr[top] = element;
        }

    }

    @Override
    public E pop() {
        if (top==-1){
            throw new EmptyStackException();
        }
        else{
            E temp = (E) arr[top];
            arr[top] = null;
            top--;
            return temp;
        }
    }

    @Override
    public E peek() {
        if (top==-1){
            throw new EmptyStackException();
        }
        else{
            return (E) arr[top];
        }
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> copyStack;
        try {
            copyStack = (ArrayStack<E>) super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }

        copyStack.arr = new Cloneable [this.maxCapacity];
        for (int i = 0; i < this.size(); i++) {
            try {
                Method cloneMethod = this.arr[i].getClass().getMethod("clone");
                copyStack.arr[i] = (Cloneable) cloneMethod.invoke(this.arr[i]);
            }
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }
        return copyStack;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        private int i = size() - 1;
        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public E next() {
            E nextElement = (E) arr[i];
            i--;
            return nextElement;
        }
    }
}
