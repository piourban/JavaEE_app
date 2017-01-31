package DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public interface GenericDAO <T, PK extends Serializable> {

    //CRUD
    T create(T newObject);
    T read(PK primaryKey);
    boolean update(T updateObject);
    boolean delete(PK key);
    List<T> getAll();
}
