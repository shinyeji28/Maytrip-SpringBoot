package com.ssafy.maytrip.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.AttractionDetail;
import com.ssafy.maytrip.domain.AttractionInfo;


public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer> {
	
	// 반경 Nkm 이내 관광지 전체 정보 조회	
    @Query("SELECT t FROM AttractionInfo t " +
            "WHERE (6371 * acos(cos(radians(:centerLatitude)) * cos(radians(t.latitude)) * cos(radians(t.longitude) - radians(:centerLongitude)) + sin(radians(:centerLatitude)) * sin(radians(t.latitude)))) <= :radiusInKm")
    List<AttractionInfo> findAllByLatAndLon(
    		@Param("lat") double centerLatitude,
            @Param("lon") double centerLongitude,
            @Param("radiusInKm") double radiusInKm);
	
	// 시도, 구군으로 관광지 정보 조회
    @Query(value = "select * from attraction_info where gugun_code = :gugunCode and sido_code = :sidoCode", nativeQuery = true)
	List<AttractionInfo> findByGugunAndSido(int sidoCode, int gugunCode);

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
    
    // 부분 관광지명으로 관광지 정보 조회
    @Query("SELECT a FROM AttractionInfo a WHERE a.title LIKE %:word%")
    List<AttractionInfo> findAllByTitle(@Param("word") String word);
    
    // 부분 도로명으로 관광지 정보 조회
    @Query("SELECT a FROM AttractionInfo a WHERE a.addr1 LIKE %:word%")
    List<AttractionInfo> findAllByAddr1(@Param("word") String word);
}
