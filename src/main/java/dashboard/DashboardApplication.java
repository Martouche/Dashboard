package dashboard;

import org.springframework.boot.SpringApplication;
import dashboard.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import dashboard.User.UserRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import dashboard.LinkWidgetsUser.LinkWidgetsUserRepository;
import dashboard.Widgets.WidgetsRepository;

import dashboard.Services.ServicesRepository;
import dashboard.Services.ServicesTable;

import dashboard.WidgetsController.YoutubeTrendController;


@SpringBootApplication
public class DashboardApplication {
	@Autowired
	private UserRepository userRepository;

	@Autowired
  private LinkWidgetsUserRepository linkWidgetsUserRepository;

	@Autowired
	private ServicesRepository servicesRepository;

	@Autowired
  private WidgetsRepository widgetsRepository;

	public static void main(String[] args) throws Throwable {
        SpringApplication.run(DashboardApplication.class, args);
    }
}
