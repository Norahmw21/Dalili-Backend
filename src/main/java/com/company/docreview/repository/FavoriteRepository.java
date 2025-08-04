package com.company.docreview.repository;


import com.company.docreview.entity.Favorite;
import com.company.docreview.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    List<Favorite> findByUser_Id(Long userId);
}