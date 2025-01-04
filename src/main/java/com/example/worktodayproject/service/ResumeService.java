package com.example.worktodayproject.service;

import com.dropbox.core.DbxException;
import com.example.worktodayproject.database.entity.Resumes;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.repository.ResumesRepository;
import com.example.worktodayproject.database.repository.UsersInfoRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.dto.request.ResumeDto;
import com.example.worktodayproject.dto.response.ResumeResponse;
import com.example.worktodayproject.exception.custom.ResumeNotFoundException;
import com.example.worktodayproject.exception.custom.UnauthorizedException;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис резюме
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()
@Transactional
public class ResumeService {

    MapperUtils mapperUtils = new MapperUtils();

    UsersInfoService usersInfoService;
    UsersInfoRepository usersInfoRepository;
    UsersRepository usersRepository;
    ResumesRepository resumesRepository;
    DropBoxService dropBoxService;

    /**
     * Создать резюме для пользователя
     * @param username текущий пользователь
     * @param resumeDto дто резюме
     */
    public void createResume(String username, ResumeDto resumeDto) throws IOException, DbxException {
        Users users = usersRepository.findByLogin(username);
        String fileName = dropBoxService.generateFileName(resumeDto.filePath().getOriginalFilename());
        String fileUrl = dropBoxService.uploadFile(resumeDto.filePath(), fileName);

        Resumes resumes = new Resumes();
        resumes.setUrl(resumeDto.url());
        resumes.setFilePath(fileUrl);
        resumes.setUploadDate(LocalDateTime.now());
        resumes.setUserInfo(usersInfoRepository.findByUsers(users));

        usersInfoService.setResumeForUserInfo(resumes, username);
        resumesRepository.save(resumes);
    }

    /**
     * Получить резюме пользователя
     * @param username имя пользователя
     * @param id идентификатор id
     * @return ответ резюме
     */
    public ResumeResponse getUserResume(String username, Long id) {
        Users users = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(users);
        Resumes resumes = resumesRepository.findByIdAndUserInfo(id, usersInfo);
        if (resumes == null) {
            throw new ResumeNotFoundException(id);
        }

        return mapperUtils.mappingResume(resumes);
    }

    /**
     * Получить все резюме пользователя
     * @param username имя пользователя
     * @return все резюме
     */
    public List<ResumeResponse> getAllUserResume(String username) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        List<Resumes> resumes = resumesRepository.findAllByUserInfo(usersInfo);

        return mapperUtils.mappingResumeList(resumes);
    }

    /**
     * Удалить резюме текущего пользователя по его id
     * @param username имя пользователя
     * @param id идентификатор резюме
     */
    public void deleteResume(String username, Long id) {
        Optional<Resumes> resumesOptional = resumesRepository.findById(id);
        if (resumesOptional.isEmpty()) {
            throw new ResumeNotFoundException(id);
        }

        Resumes resumes = resumesOptional.get();
        Users currentUser = usersRepository.findByLogin(username);

        if (!resumes.getUserInfo().getUsers().equals(currentUser)) {
            throw new UnauthorizedException("Текущий пользователь не является автором этого резюме.");
        }

        resumesRepository.delete(resumes);
    }
}
