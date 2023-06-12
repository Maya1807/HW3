import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Objects;

public class ArrayStack <E extends Cloneable> implements Stack<E> {
    private Object[] arr;
    private int maxCapacity;
    private int top;

    public ArrayStack (int maxCapacity){
        if (maxCapacity<0){
            throw new NegativeCapacityException();
        }
        this.maxCapacity = maxCapacity;
        arr = new Object[maxCapacity];
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
    public ArrayStack<E> clone(){
    try {
        ArrayStack<E> copyStack = (ArrayStack<E>) super.clone();
        copyStack.arr = new Object[maxCapacity];
        for(int i = 0; i <= top; i++) {
            E clonedElement = (E) ((Cloneable) arr[i]).clone();
            copyStack.arr[i] = clonedElement;

        }
        return copyStack;
        
    }
    catch (CloneNotSupportedException e){
        return null;
    }
    }
    private E get(int index){
        return (E)arr[index];
    }




    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
