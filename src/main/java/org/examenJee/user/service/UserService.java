package org.examenJee.user.service;


import lombok.AllArgsConstructor;
import org.examenJee.user.model.User;
import org.examenJee.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class UserService  {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
    public User getUserById(Long id) { return userRepository.findById(id).orElse(null);}
}
