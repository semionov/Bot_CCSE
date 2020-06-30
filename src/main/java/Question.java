import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.xml.ws.Response;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;


import org.apache.log4j.PropertyConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;

public class Question {

    /*
    public static String getQuestion(String message) ???{


        // Генерация  числа
        int random_number_from_300 = (int) (Math.random() * 300);

        Scanner

        //if (message.equals("Una pregunta"))
    }
    */


    /*
    Gson g = new Gson();
    String jsonString;
    Json p = g.fromJson(jsonString, Json.class);
    */

    //static String[] a = new String[] { "A", "B", "C", "D" };
    //static List<> cars = Arrays.asList(a);
            //asList("Volvo", "BMW", "Ford", "Mazda");
           // List.registerNatives("Volvo", "BMW", "Ford", "Mazda");

/*
    public void givenListOfMyClass_whenSerializing_thenCorrect() {
        List<Json> list = Arrays.asList(new Json(1, "name1"), new Json(2, "name2"));

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        String expectedString = "[{\"id\":1,\"name\":\"name1\"},{\"id\":2,\"name\":\"name2\"}]";

        assertEquals(expectedString, jsonString);
    }
*/

    /*
    public static void sendPoll(Message message, int random_number) {
        //String[] a = new String[] { "A", "B", "C", "D" };
        //List<> cars = Arrays.asList(a);


        int pull_number = random_number;
        String[] answers = { "A", "B", "C", "D" };

        SendPoll sendPoll = new SendPoll();

        sendPoll.setChatId(message.getChatId().toString());
        sendPoll.setAnonymous(true);
        sendPoll.setQuestion("Hello&?");
        sendPoll.setOptions(Arrays.asList(answers));
        sendPoll.setCorrectOptionId(2);
        sendPoll.setType("quiz");


        //sendPoll(message, pull_number);  // (message.getText() + " " + text) как вариант
    }


    public void sendPoll() {
        String question = "Question ?";
        String[] answers = {"Answer 1", "Answer 2"};
        SendPoll sendResponse = execute(
                new SendPoll(groupId, question, answers)
                        .isAnonymous(false)
                        .type("quiz")
                        .allowsMultipleAnswers(false)
                        .correctOptionId(1)
                        .isClosed(false)
                        .explanation("Some __explanation__ of poll")
                        .openPeriod(500)
        );
        try {

            execute(sendMessage.setText(text));  // (message.getText() + " " + text) как вариант
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



//findQuestion(pull_number)

   /* public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {

            execute(sendMessage.setText(text));  // (message.getText() + " " + text) как вариант
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }*/


}
