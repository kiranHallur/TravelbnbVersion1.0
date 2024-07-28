package com.travelbnb.travelbnb.repository;

import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query("SELECT r FROM Reviews r where r.appUser=:user and r.property=:property")
   Reviews findReviewByUser(@Param("user") AppUser user, @Param("property") Property property);
   //as Reviews table has both appuser id and property id so, it returns Reviews obj so, we took as Reviews

    @Query("Select r from Reviews r where r.appUser=:user")
    List<Reviews> findByAppUserReviews(@Param("user") AppUser user);


}