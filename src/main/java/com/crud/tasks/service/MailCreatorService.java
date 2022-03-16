package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Thanks for visiting.");
        context.setVariable("company_name", "CompanyName: " + adminConfig.getCompanyName());
        context.setVariable("company_email", ". Company email: " + adminConfig.getCompanyEmail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildHowManyTasksEmail(String message) {
        Context context2 = new Context();
        context2.setVariable("message", message);
        context2.setVariable("hello", "Hello, " + adminConfig.getAdminName());
        context2.setVariable("how_many_tasks", "There are " + taskRepository.count() + " tasks.");
        context2.setVariable("goodbye", "Thank you for using our app :)");
        context2.setVariable("company_name", "CompanyName: " + adminConfig.getCompanyName());
        context2.setVariable("company_email", ". Company email: " + adminConfig.getCompanyEmail());

        return templateEngine.process("mail/how-many-tasks-mail", context2);

    }
}
