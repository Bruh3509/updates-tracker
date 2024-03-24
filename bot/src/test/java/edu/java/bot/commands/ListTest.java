package edu.java.bot.commands;

import edu.java.bot.bot.UpdatesProcessor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ListTest {

    static Method method;
    static {
        try {
            method = List.class.getDeclaredMethod("getLinks");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
    }

    /*
    @Test
    @DisplayName("List Test")
    void testList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Command list = new List();
        try (var updatesStatic = Mockito.mockStatic(UpdatesProcessor.class)) {
            updatesStatic.when(UpdatesProcessor::getFOLLOWING_LINKS)
                .thenReturn(java.util.List.of("http://stackoverflow.com", "http://github.com"));

            String trackedLinks = (String) method.invoke(list);
            assertThat(trackedLinks).isEqualTo("`http://stackoverflow.com`\n`http://github.com`\n");
        }
    }

    @Test
    @DisplayName("List Test Empty")
    void testListEmpty() throws InvocationTargetException, IllegalAccessException {
        Command list = new List();
        try (var updatesStatic  = Mockito.mockStatic(UpdatesProcessor.class)) {
            updatesStatic.when(UpdatesProcessor::getFOLLOWING_LINKS)
                .thenReturn(java.util.List.of());

            String trackedLinks = (String) method.invoke(list);
            assertThat(trackedLinks).isEqualTo("You aren't following something!");
        }
    }

     */
}
