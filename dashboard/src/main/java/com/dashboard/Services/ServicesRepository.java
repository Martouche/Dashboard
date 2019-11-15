package dashboard.Services;


import dashboard.Services.ServicesTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<ServicesTable, Integer> {



}
