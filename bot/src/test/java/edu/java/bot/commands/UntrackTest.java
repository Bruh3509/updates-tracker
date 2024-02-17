package edu.java.bot.commands;

import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.bot.UpdatesProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UntrackTest {
    @Mock
    UpdateWrapper updateSuccess;

    @Mock
    UpdateWrapper updateNoSuccess;

    Untrack untrackSuccess = new Untrack();
    Untrack untrackNoSuccess = new Untrack();


    java.util.List<String> links = UpdatesProcessor.getFOLLOWING_LINKS();

    @Test
    @DisplayName("Untrack Success")
    void testUntrackSuccess() {
        // arrange
        Mockito.when(updateSuccess.messageText()).thenReturn("github");
        Mockito.when(updateSuccess.chatId()).thenReturn(1L);
        links.addAll(java.util.List.of("stackoverflow", "github", "microsoft", "linux"));

        // act
        untrackSuccess.handle(updateSuccess); // /untrack command
        untrackSuccess.handle(updateSuccess); // user entered link

        // assert
        assertThat(links)
            .containsExactly("stackoverflow", "microsoft", "linux");

        // clear storage
        links.clear();
    }

    @Test
    @DisplayName("Untrack Not Present")
    void testUntrackNotPresent() {
        // arrange
        Mockito.when(updateNoSuccess.messageText()).thenReturn("cyberforum");
        Mockito.when(updateNoSuccess.chatId()).thenReturn(1L);
        links.addAll(java.util.List.of("stackoverflow", "github", "microsoft", "linux"));

        // act
        untrackNoSuccess.handle(updateNoSuccess); // /untrack command
        untrackNoSuccess.handle(updateNoSuccess); // user entered link

        // assert
        assertThat(links)
            .containsExactly("stackoverflow", "github", "microsoft", "linux");

        // clear storage
        links.clear();
    }
}
