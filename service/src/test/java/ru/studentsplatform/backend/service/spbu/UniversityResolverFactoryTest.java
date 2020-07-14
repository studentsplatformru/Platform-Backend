package ru.studentsplatform.backend.service.spbu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.studentsplatform.backend.service.SpbuScheduleResolver;
import ru.studentsplatform.backend.service.UniversityResolverFactory;
import ru.studentsplatform.backend.service.UniversityScheduleResolver;
import ru.studentsplatform.backend.service.entities.enums.University;

public class UniversityResolverFactoryTest {
    UniversityResolverFactory factory;
    SpbuScheduleResolver spbuResolver;

    @Test
    void createSpbuResolverTest() {
        spbuResolver = Mockito.mock(SpbuScheduleResolver.class);
        factory = new UniversityResolverFactory(spbuResolver);

        UniversityScheduleResolver resolver = factory.getResolver(University.SPBU);
        Assertions.assertNotNull(resolver);
    }
}
