package com.jwlee.bookshow.webapp.db.search.repository;

import com.jwlee.bookshow.webapp.db.login.model.User;
import com.jwlee.bookshow.webapp.db.search.model.SearchHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
* SearchHistoryRepository
* @author jungwoolee
* @since 2019-07-24
**/
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

	@Query("SELECT s FROM SearchHistory s WHERE s.user=?1")
    Page<SearchHistory> findByUser(User user, Pageable pageable);

    @Query(value = "SELECT ROWNUM AS RANK, A.* FROM" +
            "(SELECT COUNT(SEARCH_WORD) AS CNT, SEARCH_WORD\n" +
            "FROM SEARCH_HISTORY\n" +
            "GROUP BY SEARCH_WORD\n" +
            "ORDER BY CNT DESC , SEARCH_WORD ASC LIMIT 10) AS A",
            nativeQuery=true)
    List<Map <String, Object>> selectKeywordTopList();


}
