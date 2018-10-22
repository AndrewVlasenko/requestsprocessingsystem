package by.project.requestprocessingsystem.requestprocessingsystem.spring.repository;

import by.project.requestprocessingsystem.requestprocessingsystem.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Modifying
    @Query("update Request r set r.status = :text where r.id = :id")
    void updateStatus(@Param("text") String text, @Param("id") long id);
}
