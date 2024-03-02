package com.example.workshopmongo.services;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.UserDto;
import com.example.workshopmongo.repository.UserRepository;

import com.example.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void deleteById(String id){
        findById(id);
        repository.deleteById(id);
    }


    public  User update(User obj){
       User userAux = findById(obj.getId());

       updateData(userAux, obj);
       return repository.save(userAux);
    }

    private void updateData(User userAux, User obj) {
        userAux.setName(obj.getName());
        userAux.setEmail(obj.getEmail());

    }

    public User fromDto (UserDto objdto){
        return new User(objdto.getId(), objdto.getName(), objdto.getEmail());
    }


}
