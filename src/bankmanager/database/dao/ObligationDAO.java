package bankmanager.database.dao;

import java.util.List;

/**
 *
 * @author igor
 * Last edited 29-10-2017
 */
public interface ObligationDAO<T> {
    public boolean insert(T obj);
    public boolean update(T obj);
    public boolean delete(Object key);
    public T readById(Object key);
    public List<T> readAll();
    public List<T> search(String filter, String regExp);  
}
