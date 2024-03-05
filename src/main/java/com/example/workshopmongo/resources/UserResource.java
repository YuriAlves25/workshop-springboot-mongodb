package com.example.workshopmongo.resources;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.UserDto;
import com.example.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;


    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> list = service.findAll();
        List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);

    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User obj = service.findById(id);

        return ResponseEntity.ok().body(new UserDto(obj));

    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDto objDto) {
        User obj = service.fromDto(objDto);
        service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@RequestBody UserDto objDto, @PathVariable String id) {
        User obj = service.fromDto(objDto);
        obj.setId(id);

        obj = service.update(obj);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User obj = service.findById(id);

        return ResponseEntity.ok().body(obj.getPosts());

    }

}
