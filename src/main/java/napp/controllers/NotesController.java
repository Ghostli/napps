package napp.controllers;

import napp.dao.NoteDAO;
import napp.dao.UserDAO;
import napp.model.Note;
import napp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotesController {
    @RequestMapping(value = "/{id}/notes", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getNotes(@PathVariable("id") long id){
        User user = userDAO.findById(id);
        List<Note> notes = noteDAO.findByUser(user);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/notes", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addNewNote(@RequestBody Note note, @PathVariable("id") long id){
        User user = userDAO.findById(id);
        note.setUser(user);
        noteDAO.save(note);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/notes/{noteId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getNote(@PathVariable("id") long userId, @PathVariable("noteId") long noteId){
        Note note = noteDAO.findOne(noteId);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/notes/{noteId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updateNote(@PathVariable("id") long userId, @PathVariable("noteId") long noteId, @RequestBody Note note){
        User user = userDAO.findById(userId);
        note.setUser(user);
        noteDAO.save(note);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/notes/{noteId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteNote(@PathVariable("id") long userId, @PathVariable("noteId") long noteId){
        noteDAO.delete(noteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    NoteDAO noteDAO;

    @Autowired
    UserDAO userDAO;
}
