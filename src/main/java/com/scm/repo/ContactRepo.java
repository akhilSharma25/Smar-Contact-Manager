package com.scm.repo;

import com.scm.model.Contacts;
import com.scm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contacts,String> {
    //custom finder
    List<Contacts> findByUser(User user);

    //custom query
    @Query("Select c from Contacts c where c.user.id=:userId")
    List<Contacts>  findByUserId(@Param("userId") String userId);


}
