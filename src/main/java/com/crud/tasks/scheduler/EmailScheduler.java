package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";
    private static final String NEW_SUBJECT = "Tasks: How many a day";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String task = size > 1 ? " tasks" : " task";

        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got: " + size + task,
                        null
                )
        );
    }

    @Scheduled(cron = "0 0 12 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendCountingTasksEmail() {
        long size = taskRepository.count();
        String task = size > 1 ? " tasks" : " task";

        simpleEmailService.sendCountingMail(
                new Mail(
                        adminConfig.getAdminMail(),
                        NEW_SUBJECT,
                        "Currently you have " + size + task,
                        null
                )
        );
    }
}
