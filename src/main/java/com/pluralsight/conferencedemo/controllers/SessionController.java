package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Session> get(@PathVariable Long id) {
        Optional<Session> sessionOptional = sessionRepository.findById(id);
        if (!sessionOptional.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(sessionRepository.getOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @PutMapping("{id}")
    public ResponseEntity<Session> update(@RequestBody final Session session, @PathVariable Long id){
        Optional<Session> sessionOptional = sessionRepository.findById(id);
        if (!sessionOptional.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Session sessionUpdate = sessionRepository.saveAndFlush(session);
        return ResponseEntity.ok(sessionUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Session> delete(@PathVariable Long id){
        Optional<Session> sessionOptional = sessionRepository.findById(id);
        if (!sessionOptional.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        sessionRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
