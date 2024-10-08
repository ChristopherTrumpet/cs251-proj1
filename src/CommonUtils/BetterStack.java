package CommonUtils;

import java.util.EmptyStackException;

/**
 * @implNote Implement a stack using an array with initial capacity 8.
 *
 * Implement BetterStackInterface and add a constructor
 *
 * You are explicitly forbidden from using java.util.Stack and any
 * other java.util.* library EXCEPT java.util.EmptyStackException and java.util.Arrays.
 * Write your own implementation of a Stack.
 *
 *
 * @param <E> the type of object this stack will be holding
 */
public class BetterStack<E> implements BetterStackInterface<E> {

    /**
     * Initial size of stack.  Do not decrease capacity below this value.
     */
    private final int INIT_CAPACITY = 8;

    /**
     * If the array needs to increase in size, it should be increased to
     * old capacity * INCREASE_FACTOR.
     *
     * If it cannot increase by that much (old capacity * INCREASE_FACTOR > max int),
     * it should increase by CONSTANT_INCREMENT.
     *
     * If that can't be done either throw OutOfMemoryError()
     *
     */
    private final int INCREASE_FACTOR = 2;
    private final int CONSTANT_INCREMENT = 1 << 5; // 32


    /**
     * If the number of elements stored is < capacity * DECREASE_FACTOR, it should decrease
     * the capacity of the UDS to max(capacity * DECREASE_FACTOR, initial capacity).
     *
     */
    private final double DECREASE_FACTOR = 0.5;


    /**
     * Array to store elements in (according to the implementation
     * note in the class header comment).
     */
    private E[] stack;

    /**
     * The last item on the top of the stack.
     */
    private int top = -1; //


    /**
     * The total number of elements in the stack.
     */
    private int size = 0;


    /**
     * Constructs an empty stack
     */
    @SuppressWarnings("unchecked")
    public BetterStack(){

        // Allocate initial stack capacity.
        this.stack = (E[]) new Object[INIT_CAPACITY];
    }


    /**
     * Push an item onto the top of the stack
     *
     * @param item item to push
     * @throws OutOfMemoryError if the underlying data structure cannot hold any more elements
     */
    @Override
    public void push(E item) throws OutOfMemoryError {

        // Attempt to resize stack to accommodate new elements.
        if (size() == stack.length) {

            int newCapacity = stack.length * INCREASE_FACTOR;

            // check for overflow, indicates size exceeded max capacity.
            if (newCapacity < 0) {

                // Switch stack size to one additional slot instead of increasing by factor.
                newCapacity = stack.length + CONSTANT_INCREMENT;

                // Indicates there is no more memory left for stack.
                if (newCapacity < 0) {
                    throw new OutOfMemoryError();
                }
            }

            E[] tempStack = (E[]) new Object[newCapacity];

            // Copy over elements to larger temporary stack O(n).
            for (int i = 0; i < stack.length; i++) {
                tempStack[i] = stack[i];
            }

            stack = tempStack;
        }
        
        stack[++top] = item;

        size++;
    }

    /**
     * Remove and return the top item on the stack
     *
     * @return the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    @Override
    public E pop() {
        
        if (!isEmpty()) {
            
            E poppedItem = peek();
            
            top--;
            size--;
            
            return poppedItem;
        }
        
        throw new EmptyStackException(); // No items in stack.
    }

    /**
     * Returns the top of the stack (does not remove it).
     *
     * @return the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    @Override
    public E peek() {
        
        if (!isEmpty()) {
            return stack[top];
        }
        
        throw new EmptyStackException();
    }

    /**
     * Returns whether the stack is empty
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Indicates highest item is -1, A.K.A empty stack.
        return top < 0;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return integer representing the number of elements in the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(java.awt.Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the stack how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
