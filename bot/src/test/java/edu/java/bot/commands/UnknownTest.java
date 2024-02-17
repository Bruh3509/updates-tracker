package edu.java.bot.commands;

import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.bot.UpdatesProcessor;
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
public class UnknownTest {
    @Mock
    UpdateWrapper update;

    static Method method;

    static {
        try {
            method = UpdatesProcessor.class.getDeclaredMethod("identifyCommand", UpdateWrapper.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Unknown command")
    void testUnknown() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(update.messageText()).thenReturn("/something");
        assertThat(method.invoke(null, update).getClass()).isEqualTo(Unknown.class);
    }
}
