public interface AbstractStack<E> extends Iterable<E> {
    void push( E element );
    E pop( );
    int size( );
    boolean isEmpty( );
}
