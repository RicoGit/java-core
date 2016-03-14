package com.structures;

import java.util.List;

/**
 * User: Constantine Solovev
 * Created: 13.03.16  11:32
 */


public interface Table<Key extends Comparable<Key>, Value> {

    Value get(Key key);

    void put(Key key, Value value);

    int size();

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    void delete(Key key);

    Key deleteMin();

    Key deleteMax();

    int rank(Key key);

    Key select(int index);

    List<Key> keys() ;

    List<Value> values() ;

}
