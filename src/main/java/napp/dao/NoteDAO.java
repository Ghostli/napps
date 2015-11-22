package napp.dao;

import napp.model.Note;
import napp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoteDAO extends CrudRepository<Note, Long> {
    List<Note> findByUser(User userId);
    @Transactional
    void deleteNotesByUser(User user);
}
