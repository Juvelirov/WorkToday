package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Tags;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.repository.IntershipInfoRepository;
import com.example.worktodayproject.database.repository.TagsRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.dto.request.IntershipInfoDto;
import com.example.worktodayproject.dto.response.IntershipInfoResponse;
import com.example.worktodayproject.exception.custom.IntershipTitleNotFoundException;
import com.example.worktodayproject.exception.custom.UnauthorizedException;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Сервис работы с стажировками
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class IntershipInfoService {

    TagsRepository tagsRepository;
    IntershipInfoRepository intershipInfoRepository;
    UsersRepository usersRepository;

    /**
     * Создать стажировку
     */
    public void createIntership(IntershipInfoDto intershipInfoDto, String username) {
        IntershipsInfo intershipsInfo = new IntershipsInfo();
        Users user = usersRepository.findByLogin(username);
        intershipsInfo.setUser(user);
        saveIntership(intershipsInfo, intershipInfoDto);
    }

    /**
     * Получить стажировку по его названию
     * @return стажировка
     */
    public IntershipInfoResponse getIntership(Long id) {
        if (!intershipInfoRepository.existsById(id)) {
            throw new IntershipTitleNotFoundException(id);
        }

        Optional<IntershipsInfo> info = Optional.of(intershipInfoRepository.findById(id).orElseThrow());
        IntershipsInfo intershipsInfo = info.get();

        return new MapperUtils().mappingIntership(intershipsInfo);
    }

    /**
     * Получить все стажировки
     * @return список стажировок
     */
    public List<IntershipInfoResponse> getAllInterships() {
        List<IntershipsInfo> intershipsInfos = intershipInfoRepository.findAll();
        List<IntershipInfoResponse> intershipInfoResponses = new ArrayList<>();

        for (IntershipsInfo info : intershipsInfos) {
            IntershipInfoResponse response = new MapperUtils().mappingIntership(info);

            intershipInfoResponses.add(response);
        }

        return intershipInfoResponses;
    }

    /**
     * Обновить стажировку
     * @param id идентификатор стажировки
     * @param intershipInfoDto дто стажировки
     * @param login логин пользователя
     */
    public void updateIntership(Long id, IntershipInfoDto intershipInfoDto, String login) {
        Users user = usersRepository.findByLogin(login);
        Optional<IntershipsInfo> existingInternshipOptional = intershipInfoRepository.findById(id);

        if (existingInternshipOptional.isPresent()) {
            IntershipsInfo existingInternship = existingInternshipOptional.get();

            if (isUserCreator(existingInternship.getId(), user)) {
                saveIntership(existingInternship, intershipInfoDto);
            } else {
                throw new UnauthorizedException("У вас нет прав для изменения этой стажировки.");
            }
        } else {
            throw new UnauthorizedException("Стажировка с указанным ID не найдена.");
        }
    }

    /**
     * Удалить стажировку по id
     * @param id идентификатор
     */
    public void deleteIntership(Long id, String login) {
        Users user = usersRepository.findByLogin(login);
        if (isUserCreator(id, user)) {
            intershipInfoRepository.deleteById(id);
        } else {
            throw new UnauthorizedException("У вас нет прав для удаления этой стажировки.");
        }
    }

    /**
     * Найти стажировку по запросу
     * @param searchQuery запрос
     */
    public List<IntershipInfoResponse> searchIntership(String searchQuery) {
        MapperUtils mapper = new MapperUtils();
        List<IntershipsInfo> titleResults;
        List<IntershipsInfo> tagResults;

        if (searchQuery == null || searchQuery.isEmpty()) {
            titleResults = intershipInfoRepository.findAll();
            return mapper.mappingListIntarship(titleResults);
        }

        titleResults = intershipInfoRepository.findByTitleContaining(searchQuery);
        tagResults = intershipInfoRepository.findByTitleContaining(searchQuery);

        Set<IntershipsInfo> combinedResults = new HashSet<>(titleResults);
        combinedResults.addAll(tagResults);

        return mapper.mappingListIntarship(new ArrayList<>(combinedResults));
    }

    /**
     * Проверять создателя стажировки
     * @param intershipId id стажирвоки
     * @param user пользователь
     * @return true или false
     */
    private boolean isUserCreator(Long intershipId, Users user) {
        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(intershipId);
        if (intershipsInfoOptional.isPresent()) {
            IntershipsInfo intershipsInfo = intershipsInfoOptional.get();
            return intershipsInfo.getUser().getId().equals(user.getId());
        }
        return false;
    }

    /**
     * Сохранить стажировку
     * @param info стажировка
     * @param infoDto дто стажировки
     */
    private void saveIntership(IntershipsInfo info, IntershipInfoDto infoDto) {
        info.setTitle(infoDto.title());
        info.setDescription(infoDto.description());
        info.setFields(infoDto.fields());

        for (String tag : infoDto.tags()) {
            Tags tags = new Tags();

            if (!tagsRepository.existsByName(tag)) {
                tags.setName(tag);
                tags.getIntershipsInfos().add(info);
                info.getTags().add(tags);
                tagsRepository.save(tags);
            } else {
                info.getTags().add(tagsRepository.findByName(tag));
                tags.getIntershipsInfos().add(info);
            }
        }

        intershipInfoRepository.save(info);
    }
}
