public interface AbstractQueue<E> extends Iterable<E> {
    void enqueue( E element );
    E dequeue( );
    boolean isEmpty( );
}