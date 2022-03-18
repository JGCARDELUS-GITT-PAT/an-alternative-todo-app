package com.jgcardelus.todoApp;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    @GetMapping("/tasks")
    public ResponseEntity<ArrayList<Task>> getTasks() {
        return new ResponseEntity<ArrayList<Task>>(tasks, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> postTask(@RequestBody Task task) {
        int id = tasks.size();
        task.setId(id);
        tasks.add(task);

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{idString}")
    public ResponseEntity<ArrayList<Task>> deleteTask(@PathVariable String idString) throws Exception {
        int id = Integer.parseInt(idString);
        Task clone = new Task(id, "");
        tasks.remove(clone);
        throw new Exception();
        // return new ResponseEntity<ArrayList<Task>>(new ArrayList<Task>(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler() {
        return new ResponseEntity<String>("Ha habido un error", HttpStatus.BAD_REQUEST);
    }
}
