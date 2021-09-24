package com.epam.practice.teashop_spring.repos;

import com.epam.practice.teashop_spring.domain.Tea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Tea, Long> {
    Page<Tea> findAll(Pageable pageable);
    Page<Tea> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable);
    Page<Tea> findByBrand(String brand, Pageable pageable);
    Page<Tea> findByBrandOrTeaTitle(String brand, String teaTitle, Pageable pageable);
    Page<Tea> findByBrandIn (List<String> brand, Pageable pageable);

    @Query(value = "SELECT min(price) FROM Tea ")
    BigDecimal minTeaPrice();

    @Query(value = "SELECT DISTINCT brand FROM Tea ")
    List<String> getBrands();
    @Query(value = "SELECT max(price) FROM Tea ")
    BigDecimal maxTeaPrice();

    @Modifying
    @Transactional
    @Query("update Tea p set p.teaTitle = ?1, p.brand = ?2, p.country = ?3, " +
            "p.fragranceBaseNotes = ?4," +
            "p.description = ?5, p.filename = ?6, p.price = ?7, p.volume = ?8, p.type = ?9  where p.id = ?10")
    void saveProductInfoById(String teaTitle, String brand, String country,
                             String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);
}