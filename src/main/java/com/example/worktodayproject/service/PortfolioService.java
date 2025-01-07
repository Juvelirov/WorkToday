package com.example.worktodayproject.service;

import com.dropbox.core.DbxException;
import com.example.worktodayproject.database.entity.Portfolios;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.repository.PortfoliosRepository;
import com.example.worktodayproject.database.repository.UsersInfoRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.dto.request.PortfolioDto;
import com.example.worktodayproject.dto.response.PortfolioResponse;
import com.example.worktodayproject.exception.custom.PortfolioNotFoundException;
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
 * Сервис для портфолио
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class PortfolioService {

    MapperUtils mapperUtils = new MapperUtils();

    UsersInfoService usersInfoService;
    PortfoliosRepository portfoliosRepository;
    UsersRepository usersRepository;
    UsersInfoRepository usersInfoRepository;
    DropBoxService dropBoxService;

    /**
     * Создать портфолио для текущего пользователя
     * @param username имя текущего пользователя
     * @param portfolioDto дто портфолио
     */
    public void createPortfolio(String username, PortfolioDto portfolioDto) throws IOException, DbxException {
        Users currentUser = usersRepository.findByLogin(username);
        String fileName = dropBoxService.generateFileName(portfolioDto.filePath().getOriginalFilename());
        String fileUrl = dropBoxService.uploadFile(portfolioDto.filePath(), fileName);

        Portfolios portfolio = new Portfolios();
        portfolio.setFilePath(fileUrl);
        portfolio.setUploadDate(LocalDateTime.now());
        portfolio.setUserInfo(usersInfoRepository.findByUsers(currentUser));

        usersInfoService.setPortfolioForUserInfo(portfolio, username);

        portfoliosRepository.save(portfolio);
    }

    /**
     * Получить портфолио польщователя по его id
     * @param username имя пользователя
     * @param id идентификатор портфолио
     * @return ответ портфолио
     */
    public PortfolioResponse getUserPortfolio(String username, Long id) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Portfolios portfolios = portfoliosRepository.findByIdAndUserInfo(id, usersInfo);
        if (portfolios == null) {
            throw new PortfolioNotFoundException(id);
        }

        return mapperUtils.mappingPortfolio(portfolios);
    }

    /**
     * Получить все портфолио пользователя
     * @param username имя пользователя
     * @return список портфолио
     */
    public List<PortfolioResponse> getAllUserPortfolios(String username) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        List<Portfolios> userPortfolio = portfoliosRepository.findAllByUserInfo(usersInfo);

        return mapperUtils.mappingPortfolioList(userPortfolio);
    }

    /**
     * Удалить портфолио по его id
     * @param id идентификатор портфолио
     * @param username текущий пользователь
     */
    public void deletePortfolio(Long id, String username) {
        Optional<Portfolios> portfolioOptional = portfoliosRepository.findById(id);
        if (portfolioOptional.isEmpty()) {
            throw new PortfolioNotFoundException(id);
        }

        Portfolios portfolio = portfolioOptional.get();
        Users currentUser = usersRepository.findByLogin(username);

        if (!portfolio.getUserInfo().getUsers().equals(currentUser)) {
            throw new IllegalStateException("Текущий пользователь не является автором этого портфолио.");
        }

        portfoliosRepository.delete(portfolio);
    }
}
