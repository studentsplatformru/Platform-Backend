package ru.studentsplatform.backend.service.spbu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.UniversityResolverFactory;
import ru.studentsplatform.backend.service.UniversityScheduleResolver;
import ru.studentsplatform.backend.service.entities.enums.University;

public class UniversityResolverFactoryTest {
    UniversityResolverFactory factory;

    @Test
    void createSpbuResolverTest() {
        factory = new UniversityResolverFactory();

        UniversityScheduleResolver resolver = factory.getResolver(University.SPBU);
        Assertions.assertNotNull(resolver);
    }
}
