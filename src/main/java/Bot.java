import org.apache.log4j.PropertyConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        //PropertyConfigurator.configure(System.getProperty("Users/semen/IdeaProjects/Bot CCSE/") + "rc/resources/log4j.properties");

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(Message message, String text) {
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
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String textt = message.getText();
            if ("/help".equals(textt)) {
                sendMsg(message,"Чуо?");

            } else if ("/settings".equals(textt)) {
                sendMsg(message,"Чуо настраивать бум?");
            } else if ("/start".equals(textt)) {
                sendMsg(message, "Buenos dias! Soy bot examinador de CCSE, quiere cuestion aleatoria o pasar test de 24 preguntas?" );
            } else if ("Una pregunta".equals(textt)) {
                sendMsg(message, "Aqui ten una pregunta" );
                try {
                    sendPoll(message);   // Из класса Question вызываем метод Question передавая туда тескт сообщения??
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if ("TEST CCSE".equals(textt)) {
                sendMsg(message, "Aqui ten test completo" );

            }
        }

    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);   // связывает сообщение с клавиатурой
        replyKeyboardMarkup.setSelective(true); // клавиатура всем пользователям (не определенным)
        replyKeyboardMarkup.setResizeKeyboard(true); //подгонка клавы больше или меньше
        replyKeyboardMarkup.setOneTimeKeyboard(true); // скрывать или  не скрывать клаву после нажатия кнопки

        // создаем теперь кнопки
        List<KeyboardRow> keyboardRowLlist = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();  // создали первую строку клавиатуры

        keyboardFirstRow.add(new KeyboardButton("Una pregunta"));  //добавляем название первой кнопки
        keyboardFirstRow.add(new KeyboardButton("TEST CCSE"));  //добавляем название первой линии второй кнопки
        // keyboardFirstRow.add(new KeyboardButton("/start"));  //3

        keyboardRowLlist.add(keyboardFirstRow);  //добавляем все строки в аррай список
        replyKeyboardMarkup.setKeyboard(keyboardRowLlist);  //устанавливаем список на клавиатуру
    }





/*
    public void sendMyPoll(int from_chat_id, int message_id, Message message) {
        ForwardMessage forwardMessage = new ForwardMessage();
        forwardMessage.setChatId(message.getChatId().toString());
        forwardMessage.setFromChatId(0000);
        forwardMessage.setMessageId(485738475837458);

        try {
            execute(forwardMessage.setMessageId(30498));  //execute(forwardMessage());  // (message.getText() + " " + text) как вариант
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    */




    public void sendPoll(Message message) throws InterruptedException {
        String[] answers = { "A", "B", "C", "D" };

        SendPoll sendPoll = new SendPoll();

        sendPoll.setChatId(message.getChatId().toString());
        sendPoll.setAnonymous(false);
        sendPoll.setQuestion("Hello?");
        sendPoll.setOptions(Arrays.asList(answers));
        sendPoll.setCorrectOptionId(2);
        sendPoll.setType("quiz");

        for (int i = 3; i > 0; i--) {
            try {
                execute(sendPoll.setChatId(message.getChatId().toString()));  // (message.getText() + " " + text) как вариант
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            TimeUnit.SECONDS.sleep(2);
        }
    }




    public String getBotUsername() {

        return "CCSE_exam_bot";
    }

    public String getBotToken() {

        return "1213851843:AAEYmoddKktWaYEd4ty4fiBjsyvoMJH8mW0";
    }
}

