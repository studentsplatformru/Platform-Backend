package ru.studentsplatform.backend.notification.service;

import ru.studentsplatform.backend.notification.MessageType;

public interface EmailTemplateService {

    String getEmailTemplate(MessageType type, String ...args);

}
