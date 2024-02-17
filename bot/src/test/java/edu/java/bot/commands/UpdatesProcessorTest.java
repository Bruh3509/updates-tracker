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
public class UpdatesProcessorTest {
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

    @Test
    @DisplayName("Start command")
    void testStart() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(update.messageText()).thenReturn("/start");
        assertThat(method.invoke(null, update).getClass()).isEqualTo(Start.class);
    }

    @Test
    @DisplayName("Help command")
    void testHelp() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(update.messageText()).thenReturn("/help");
        assertThat(method.invoke(null, update).getClass()).isEqualTo(Help.class);
    }

    @Test
    @DisplayName("Track command")
    void testTrack() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(update.messageText()).thenReturn("/track");
        assertThat(method.invoke(null, update).getClass()).isEqualTo(Track.class);
    }

    @Test
    @DisplayName("Untrack command")
    void testUntrack() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(update.messageText()).thenReturn("/untrack");
        assertThat(method.invoke(null, update).getClass()).isEqualTo(Untrack.class);
    }

    @Test
    @DisplayName("List command")
    void testList() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(update.messageText()).thenReturn("/list");
        assertThat(method.invoke(null, update).getClass()).isEqualTo(List.class);
    }
}
