package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.User;

/**
 *
 * @author Jero
 *
 */
@Repository
public interface UserRepository extends GenericRepository<User, Long> {

    /**
     * Find the {@link User} by {@code sywId}.
     *
     * @param sywId
     * @return return a {@link User}.
     */
    public User findBySywId(@Param("syw_id") Long sywId);

    /**
     * Find a list of {@link User} by {@code familyId}.
     *
     * @param familyId
     * @return return a List of {@link User}.
     */
    public List<User> findByFamilyId(@Param("family_id") Long familyId);

}
