import java.util.Iterator;
/**
 * The Stack interface represents a stack data structure that supports basic stack operations.
 * It extends the Iterable interface to support iteration over the elements in the stack.
 *
 * @param <E> the type of elements in the stack
 */
public interface Stack<E extends Cloneable> extends Iterable<E>, Cloneable {

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    void push(E element);

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     */
    E pop();

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     */
    E peek();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    int size();

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Creates and returns a clone of the stack.
     *
     * @return a clone of the stack
     */
    Stack<E> clone();
}

