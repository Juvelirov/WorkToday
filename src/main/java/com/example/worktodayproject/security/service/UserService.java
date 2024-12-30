package com.example.worktodayproject.security.service;

import com.example.worktodayproject.database.entity.Roles;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.repository.IntershipInfoRepository;
import com.example.worktodayproject.database.repository.RoleRepository;
import com.example.worktodayproject.database.repository.UsersInfoRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.security.dto.request.UserDto;
import com.example.worktodayproject.security.dto.response.UserResponse;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CRUD пользователя
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class UserService {

    MapperUtils mapperUtils = new MapperUtils();
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    UsersInfoRepository usersInfoRepository;
    IntershipInfoRepository intershipInfoRepository;


    /**
     * Создать пользователя
     * @param userDto дто пользователя
     */
    public void createUser(UserDto userDto) {
        Users user = new Users();
        Roles role = roleRepository.findByRole(userDto.role());

        user.setFio(userDto.fio());
        user.setLogin(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setEmail(userDto.email());
        user.setCreate(LocalDateTime.now());

        if (role != null) {
//            if (roleRepository.existsByRole(role.getRole())) {
//                throw new RoleNotFoundException(role.getRole());
//            }
            user.getRoles().add(role);
        }

        setFioOnBio(userDto.fio(), createBio(user));

        usersRepository.save(user);
    }

    /**
     * Создать профиль сразу после регистрации
     */
    private UsersInfo createBio(Users user) {
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.setUsers(user);

        usersInfoRepository.save(usersInfo);
        return usersInfo;
    }

    /**
     * Отправить ФИО в профиль
     */
    private void setFioOnBio(String fio, UsersInfo usersInfo) {
        String[] splitFio = fio.split(" ");
        usersInfo.setName(splitFio[0]);
        usersInfo.setSurname(splitFio[1]);
        usersInfo.setPatronymic(splitFio[2]);
    }

    /**
     * Удалить пользователя
     * @param login имя пользователя
     */
    public void deleteUser(String login) {
        Users user = usersRepository.findByLogin(login);

        usersInfoRepository.deleteByUsers(user);
        intershipInfoRepository.deleteByUser(user);

        usersRepository.deleteByLogin(login);
    }

    /**
     * Получить всех пользователей
     * @return список всех пользователей
     */
    public List<UserResponse> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return mapperUtils.mappingUsersList(users);
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
        Users user = usersRepository.findByLogin(userDto.email());

        if (user != null) {
            user.setLogin(userDto.email());
            user.setPassword(userDto.password());
            user.setEmail(userDto.email());
        }

        usersRepository.save(user);
    }
}
