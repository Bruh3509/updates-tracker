package hw1;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.bot.UpdatesProcessor;
import edu.java.bot.commands.Unknown;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UnknownCommandTest {
    @Mock
    Update update;
    @Mock
    Message message;

    static Method method;

    static {
        try {
            method = UpdatesProcessor.class.getDeclaredMethod("identifyCommand", Update.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Unknown command")
    void testUnknown() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(message.text()).thenReturn("/something");
        assertThat(method.invoke(null, update).getClass()).isEqualTo(Unknown.class);
    }
}
