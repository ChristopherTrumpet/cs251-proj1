package CommonUtils;

import java.awt.*;
import java.util.NoSuchElementException;

/**
 * @implNote implement a queue using a circular array with initial capacity 8.
 *
 * Implement BetterQueueInterface and add a constructor
 *
 * You are explicitly forbidden from using java.util.Queue and any subclass
 * (including LinkedList, for example) and any other java.util.* library EXCEPT java.util.Objects.
 * Write your own implementation of a Queue.
 *
 * Another great example of why we are implementing our own queue here is that
 * our queue is actually FASTER than Java's LinkedList (our solution is 2x faster!). This is due
 * to a few reasons, the biggest of which are 1. the overhead associated with standard library
 * classes, 2. the fact that Java's LinkedList doesn't store elements next to each other, which
 * increases memory overhead for the system, and 3. LinkedList stores 2 pointers with each element,
 * which matters when you store classes that aren't massive (because it increases the size of each
 * element, making more work for the system).
 *
 * @param <E> the type of object this queue will be holding
 */
public class BetterQueue<E> implements BetterQueueInterface<E> {

    /**
     * Initial size of queue.  Do not decrease capacity below this value.
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
     *
     * Circular arrays work as follows:
     *
     *   1. Removing an element increments the "first" index
     *   2. Adding an element inserts it into the next available slot. Incrementing
     *      the "last" index WRAPS to the front of the array, if there are spots available
     *      there (if we have removed some elements, for example).
     *   3. The only way to know if the array is full is if the "last" index
     *      is right in front of the "first" index.
     *   4. If you need to increase the size of the array, put the elements back into
     *      the array starting with "first" (i.e. "first" is at index 0 in the new array).
     *   5. No other implementation details will be given, but a good piece of advice
     *      is to draw out what should be happening in each operation before you code it.
     *
     *   hint: modulus might be helpful
     */
    private E[] queue;

    private int head;
    private int tail;
    private int size;
    
    /**
     * Constructs an empty queue
     */
    @SuppressWarnings("unchecked")
    public BetterQueue(){
        //todo
         
        // -1 indicates queue is empty
        this.head = -1;
        this.tail = -1;
        
        this.queue = (E[]) new Object[INIT_CAPACITY];
    }

    /**
     * Add an item to the back of the queue
     *
     * @param item item to push
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void add(E item) {

        if (item == null) {
            throw new NullPointerException();
        }

        // Attempt resizing of queue if tail is next to head [0,0,T,H]
        if ( (tail + 1) % queue.length == head) {

            int newQueueSize = queue.length * INCREASE_FACTOR;

            // Indicates overflow, attempts incremental increase.
            if (newQueueSize < 0) {

                newQueueSize = queue.length + CONSTANT_INCREMENT;

                // Incremental approach failed, no memory left.
                if (newQueueSize < 0) {
                    throw new OutOfMemoryError();
                }
            }

            E[] tempQueue = (E[]) new Object[newQueueSize];

            int i = 0;
            int j = head;

            // Copy elements to temporary queue.
            do {
                tempQueue[i++] = queue[j];
                j = (j + 1) % queue.length;
            } while (j != head);

            head = 0;
            tail = queue.length - 1;

            queue = tempQueue;
        }

        else if (isEmpty()) {
            head++;
        }

        tail = (tail + 1) % queue.length;
        queue[tail] = item;

        size++;
    }

    /**
     * Returns the front of the queue (does not remove it) or <code>null</code> if the queue is empty
     *
     * @return front of the queue or <code>null</code> if the queue is empty
     */
    @Override
    public E peek() {

        if (isEmpty()) {
            return null;
        }
        
        return queue[head];
    }

    /**
     * Returns and removes the front of the queue
     *
     * @return the head of the queue, or <code>null</code> if this queue is empty
     */
    @Override
    public E remove() {

        if (isEmpty()) {
            // Can't remove from empty queue.
            return null;
        }
        
        E temp = peek();
        
        // Only occurs when there is one element in queue, reset pointers to empty queue.
        if (head == tail) {
            head = -1;
            tail = -1;
        }
        
        else {
            head = (head + 1) % queue.length;
        }

        size--;

        // Returns previous head.
        return temp;
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return integer representing the number of elements in the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the queue is empty
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Head with index -1 indicates empty queue.
        return head == -1;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the queue how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
