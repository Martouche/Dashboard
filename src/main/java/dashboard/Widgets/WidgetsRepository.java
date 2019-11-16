package dashboard.Widgets;


import dashboard.Widgets.WidgetsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import java.util.Optional;

@Repository
public interface WidgetsRepository extends JpaRepository<WidgetsTable, Integer> {
}
