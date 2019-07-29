package repositories;

import domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer> {

    @Query("select s from Paper s where s.author.id = ?1")
    Collection<Paper> findAllByAuthor(int authorId);

}
