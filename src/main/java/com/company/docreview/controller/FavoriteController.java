package com.company.docreview.controller;

import com.company.docreview.dto.FavoriteDTO;
import com.company.docreview.entity.Favorite;
import com.company.docreview.entity.FavoriteId;
import com.company.docreview.service.FavoriteService;
 import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "") // Updated to allow all origins
public class FavoriteController {

    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    // ✅ GET all favorites by user as DTO
    @GetMapping("/user/{userId}")
    public List<FavoriteDTO> getFavoritesByUserId(@PathVariable Long userId) {
        return service.getFavoritesByUserId(userId);
    }

    // ✅ POST add a favorite (still using entity)
    @PostMapping
    public FavoriteDTO addFavorite(@RequestBody Favorite favorite) {
        Favorite saved = service.addFavorite(favorite);
        return new FavoriteDTO(
                saved.getUser().getId(),
                saved.getDoctor().getId()
        );
    }
    // ✅ DELETE a favorite (still using ID)
    @DeleteMapping
    public void deleteFavorite(@RequestBody FavoriteId id) {
        service.removeFavorite(id);
    }
}