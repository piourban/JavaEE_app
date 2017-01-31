package DAO;

import Models.Note;

import java.util.List;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public interface NoteDAO extends GenericDAO<Note, Long>{

    List<Note> getAll();

}
