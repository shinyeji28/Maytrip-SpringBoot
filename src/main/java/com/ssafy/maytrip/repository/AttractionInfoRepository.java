package com.ssafy.maytrip.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.AttractionDetail;
import com.ssafy.maytrip.domain.AttractionInfo;


public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer> {
	
	// 관관지 전체 정보 조회
	List<AttractionInfo> findAll();
	
	// 시도, 구군으로 관광지 정보 조회
	List<AttractionInfo> findByGugunGugunIdGugunCodeAndGugunGugunIdSidoSidoCode(int sidoCode, int gugunCode);

	// 카테고리로 관광지 정보 조회
    @Query(value = "select *"
			+ "FROM attraction_info ai "
            + "INNER JOIN attraction_detail ad "
            + "ON ai.content_id = ad.content_id "
            + "WHERE ad.cat1 = :cat1 AND ad.cat2 = :cat2 AND ad.cat3 = :cat3", nativeQuery = true)
    List<AttractionInfo> findAllByCat1AndCat2AndCat3(
    		@Param("cat1") String cat1, 
    		@Param("cat2") String cat2, 
    		@Param("cat3") String cat3
    		);
    
    @Query(value = "select *"
			+ "FROM attraction_info ai "
            + "INNER JOIN attraction_detail ad "
            + "ON ai.content_id = ad.content_id "
            + "WHERE ad.cat1 = :cat1 AND ad.cat2 = :cat2", nativeQuery = true)
    List<AttractionInfo> findAllByCat1AndCat2(@Param("cat1") String cat1, @Param("cat2") String cat2);
    
    @Query(value = "select *"
			+ "FROM attraction_info ai "
            + "INNER JOIN attraction_detail ad "
            + "ON ai.content_id = ad.content_id "
            + "WHERE ad.cat1 = :cat1", nativeQuery = true)
    List<AttractionInfo> findAllByCat1(@Param("cat1") String cat1);
}
