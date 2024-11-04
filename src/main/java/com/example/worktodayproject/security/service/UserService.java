package com.example.worktodayproject.security.service;

import com.example.worktodayproject.database.entity.Roles;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.repository.RoleRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.exception.custom.RoleNotFoundException;
import com.example.worktodayproject.security.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CRUD пользователя
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class UserService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    /**
     * Создать пользователя
     * @param userDto дто пользователя
     */
    public void createUser(UserDto userDto) {
        Users user = new Users();
        Roles role = roleRepository.findByRole(userDto.role());

        user.setLogin(userDto.login());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setEmail(userDto.email());
        user.setCreate(LocalDateTime.now());

        if (role != null) {
            if (roleRepository.existsByRole(role.getRole())) {
                throw new RoleNotFoundException(role.getRole());
            }
            user.getRoles().add(role);
        }

        usersRepository.save(user);
    }

    /**
     * Удалить пользователя
     * @param login имя пользователя
     */
    public void deleteUser(String login) {
        usersRepository.deleteByLogin(login);
    }

    /**
     * Получить всех пользователей
     * @return список всех пользователей
     */
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    /**
     * Получить пользователя по его имени
     * @param login имя пользователя
     * @return пользователь
     */
    public Users getUser(String login) {
        return usersRepository.findByLogin(login);
    }

    /**
     * Обновить данные пользователя
     * @param userDto дто пользователя
     */
    public void updateUser(UserDto userDto) {
        Users user = usersRepository.findByLogin(userDto.login());

        if (user != null) {
            user.setLogin(userDto.login());
            user.setPassword(userDto.password());
            user.setEmail(userDto.email());
        }

        usersRepository.save(user);
    }
}
