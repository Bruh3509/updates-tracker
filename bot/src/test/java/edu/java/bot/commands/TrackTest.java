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
public class TrackTest {
    @Mock
    UpdateWrapper updateSuccess;

    @Mock
    UpdateWrapper updatePresent;

   /*
    Track trackSuccess = new Track();
    Track trackPresent = new Track();

    @Test
    @DisplayName("Track Test Success")
    void testTrackSuccess() {
        // arrange
        Mockito.when(updateSuccess.messageText()).thenReturn("github");
        Mockito.when(updateSuccess.chatId()).thenReturn(1L);
        UpdatesProcessor.getFOLLOWING_LINKS().add("stackoverflow");

        // act
        trackSuccess.handle(updateSuccess); // /track command
        trackSuccess.handle(updateSuccess); // user entered link

        // assert
        assertThat(UpdatesProcessor.getFOLLOWING_LINKS()).containsExactly("stackoverflow", "github");

        // clear storage
        UpdatesProcessor.getFOLLOWING_LINKS().clear();
    }

    @Test
    @DisplayName("Track Test Present")
    void testTrackPresent() {
        // arrange
        Mockito.when(updatePresent.messageText()).thenReturn("github");
        Mockito.when(updatePresent.chatId()).thenReturn(1L);
        UpdatesProcessor.getFOLLOWING_LINKS().addAll(java.util.List.of("stackoverflow", "github", "microsoft"));

        // act
        trackPresent.handle(updatePresent); // /track command
        trackPresent.handle(updatePresent); // user entered link

        // assert
        assertThat(UpdatesProcessor.getFOLLOWING_LINKS())
            .containsExactly("stackoverflow", "github", "microsoft");

        // clear storage
        UpdatesProcessor.getFOLLOWING_LINKS().clear();
    }

     */
}
