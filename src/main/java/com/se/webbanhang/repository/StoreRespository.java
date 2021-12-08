
package com.se.webbanhang.repository;

import com.se.webbanhang.dto.request.QueryStoreDTO;
import com.se.webbanhang.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface StoreRespository extends JpaRepository<Store, Integer>{
    @Query(name = "getQueryStoreDTO",nativeQuery = true)
    public List<QueryStoreDTO> findAllStore();
}
