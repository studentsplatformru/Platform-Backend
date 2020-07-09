package ru.studentsplatform.backend.service;

public interface ScheduleUrlFinder {
    String findScheduleLink(String studyName, String groupName);
}
