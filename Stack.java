import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;

    public Stack( ) {
        top = null;
        size = 0;
    }
    @Override
    public void push( E element ) {
        Node<E> newNode = new Node<>( element );
        if ( isEmpty( ) ) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
    }
    @Override
    public E pop( ) {
        if ( isEmpty( ) ) {
            throw new NoSuchElementException( );
        }
        E element = top.element;
        Node<E> next = top.next;
        top.next = null;
        top = next;
        size--;
        return element;
    }
    @Override
    public int size( ) {
        return size;
    }
    @Override
    public boolean isEmpty( ) {
        return top == null;
    }
    @Override
    public Iterator<E> iterator( ) {
        return new Iterator<E>( ) {
            Node<E> current = top;
            @Override
            public boolean hasNext( ) {
                return current != null;
            }

            @Override
            public E next( ) {
                if ( !hasNext( ) ) {
                    throw new NoSuchElementException( );
                }
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
    @Override
    public String toString( ) {
        StringBuilder result = new StringBuilder( "[" );
        Node<E> current = top;

        while ( current != null ) {
            result.append( current.element );
            if ( current.next != null ) {
                result.append( ", " );
            }
            current = current.next;
        }

        result.append( "]" );
        return result.toString( );
    }
    private static class Node<E> {
        private E element;
        private Node<E> next;
        public Node( E element ) {
            this.element = element;
        }
    }
}
