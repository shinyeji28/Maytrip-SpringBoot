package com.ssafy.maytrip.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.AttractionDetail;

public interface AttractionDetailRepository extends JpaRepository<AttractionDetail, Integer> {
	
    @Query(value = "SELECT ai.content_id, ai.content_type_id, ai.title, ai.addr1, ai.addr2, ai.zipcode, ai.tel, ai.first_image, ai.first_image2, ai.readcount "
            + "FROM attraction_info ai "
            + "INNER JOIN attraction_detail ad "
            + "ON ai.content_id = ad.content_id "
            + "WHERE ad.cat1 = :cat1 AND ad.cat2 = :cat2 AND ad.cat3 = :cat3", nativeQuery = true)
	
    List<AttractionDetail> findAllByCat1AndCat2AndCat3(
    		@Param("cat1") String cat1, 
    		@Param("cat2") String cat2, 
    		@Param("cat3") String cat3
    		);

}
