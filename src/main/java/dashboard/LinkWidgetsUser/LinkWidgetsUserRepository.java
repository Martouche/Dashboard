package dashboard.LinkWidgetsUser;


import dashboard.LinkWidgetsUser.LinkWidgetsUserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LinkWidgetsUserRepository extends JpaRepository<LinkWidgetsUserTable, Integer> {


}
