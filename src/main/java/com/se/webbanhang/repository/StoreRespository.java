
package com.se.webbanhang.repository;

import com.se.webbanhang.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface StoreRespository extends JpaRepository<Store, Integer>{
    
}
