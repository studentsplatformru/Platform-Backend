import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.tlgrmbot.config.BotCommands;
import ru.studentsplatform.backend.tlgrmbot.dataStorage.RunningCommand;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunningCommandTest {

    RunningCommand runningCommand = new RunningCommand();

    @BeforeAll
    public static void addSomeRunningCommands(){
        RunningCommand.establishRunningCommand(1, BotCommands.SCHEDULE);
        RunningCommand.establishRunningCommand(2, BotCommands.UNKNOWN);
    }

    @Test
    public void commandRemovesAfterPulling() throws NoSuchFieldException, IllegalAccessException {
        Field data = runningCommand.getClass().getDeclaredField("currentCommandResolving");
        data.setAccessible(true);
        HashMap<Long, BotCommands> dataStorage = (HashMap<Long,BotCommands>) data.get(runningCommand);
        int runningCommandsCount = dataStorage.size();
        RunningCommand.pullRunningCommand(1);
        int afterPullingCommandsCount = dataStorage.size();
        assertEquals(afterPullingCommandsCount,runningCommandsCount - 1);
    }

    @Test
    public void seeRunningCommandReturnsRightCommandTest(){
        BotCommands command = RunningCommand.seeRunningCommand(2);
        assertEquals(command,BotCommands.UNKNOWN);
    }

    @Test
    public void establishCommandAddingCommand(){
        RunningCommand.establishRunningCommand(20,BotCommands.SCHEDULE);
        assertEquals(RunningCommand.seeRunningCommand(20), BotCommands.SCHEDULE);
    }

    @AfterAll
    public static void clearDataStorage(){
        RunningCommand.pullRunningCommand(1);
        RunningCommand.pullRunningCommand(2);
        RunningCommand.pullRunningCommand(20);
    }
}
