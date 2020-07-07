package ru.studentsplatform.backend.tlgrmbot;

import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.tlgrmbot.config.BotCommands;

import static org.junit.jupiter.api.Assertions.*;

public class BotCommandsTest {

    BotCommands validCommandWithRequiringParameter = BotCommands.SCHEDULE;
    BotCommands invalidCommand = BotCommands.UNKNOWN;

    @Test
    public void getValidCommandMessageTest(){
        String message = validCommandWithRequiringParameter.getCommandMessage();
        assert message.equals("/shedule");
    }

    @Test
    public void getInvalidCommandMessageTest(){
        String message = invalidCommand.getCommandMessage();
        assert message.equals("unknown");
    }

    @Test
    public void isCommandRequiredParametersTest(){
        assertTrue(validCommandWithRequiringParameter.isRequiredParameters());
    }

    @Test
    public void isInvalidCommandNotRequiredCommandsTest(){
        assertFalse(invalidCommand.isRequiredParameters());
    }

    @Test
    public void transformValidCommandToCommandObjectTest(){
        assertEquals(BotCommands.transformMessageToCommand("/shedule"), BotCommands.SCHEDULE);
    }

    @Test
    public void transformInvalidCommandToCommandObjectTest(){
        assertEquals(BotCommands.transformMessageToCommand("qrwrqwrqw"), BotCommands.UNKNOWN);
    }



}
