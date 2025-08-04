package com.company.docreview.service;

import com.company.docreview.dto.FavoriteDTO;
import com.company.docreview.entity.Favorite;
import com.company.docreview.entity.FavoriteId;
import com.company.docreview.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository repo;

    public FavoriteService(FavoriteRepository repo) {
        this.repo = repo;
    }

    // ✅ Return DTOs instead of raw entities
    public List<FavoriteDTO> getFavoritesByUserId(Long userId) {
        List<Favorite> favorites = repo.findByUser_Id(userId);
        return favorites.stream()
                .map(fav -> new FavoriteDTO(
                        fav.getUser().getId(),
                        fav.getDoctor().getId()
                ))
                .collect(Collectors.toList());
    }

    public Optional<Favorite> getFavoriteById(FavoriteId id) {
        return repo.findById(id);
    }

    public Favorite addFavorite(Favorite favorite) {
        return repo.save(favorite);
    }

    public void removeFavorite(FavoriteId id) {
        repo.deleteById(id);
    }
}
