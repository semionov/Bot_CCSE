import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main extends TelegramLongPollingBot {
    private final File examen = new File("Examen.txt");
    private final File tarea1 = new File("Tarea1.txt");
    private final File tarea2 = new File("Tarea2.txt");
    private final File tarea3 = new File("Tarea3.txt");
    private final File tarea4 = new File("Tarea4.txt");
    private final File tarea5 = new File("Tarea5.txt");
    private final Statistics statistics = new Statistics();

    public Main() throws IOException {
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ApiContextInitializer.init();
        final Main mainClass = new Main();
        final TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(mainClass);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        // to myself
        // mainClass.sendMsg("290631155", "TEXT");
        // mainClass.sendPoll("290631155", mainClass.tarea5);

        final String chatID = "@CCSE_exam";
        Timer timer = new Timer();
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                        TimeUnit.SECONDS.sleep(5);
                        mainClass.sendPoll(chatID, mainClass.examen);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    mainClass.sendMsg("290631155", "error in timer");
                }
            }
        };
        timer.schedule(t, 0l, 1000 * 60 * 60 * 24);
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String chatId;
        String text = "";

        if (message != null && message.hasText()) {
            text = message.getText();
            chatId = message.getChatId().toString();

            if ("/start".equals(text)) {
                sendMsg(chatId, "Hola! Soy un bot examinador de CCSE!\n" +
                    "Prueba una pregunta aleatoria:\n" +
                    "\n" +
                    "de todo el /examen\n" +
                    "\n" +
                    "de la /tarea1\n" +
                    "de la /tarea2\n" +
                    "de la /tarea3\n" +
                    "de la /tarea4\n" +
                    "de la /tarea5\n" +
                    "\n" +
                    "Descubre más /sobremi");
            } else if ("/examen".equals(text) || "Examen".equals(text)) {
                //sendMsg(message, "Whole exam");
                try {
                    sendPoll(chatId, examen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statistics.plusOneExam();
            } else if ("/tarea1".equals(text) || "1".equals(text)) {
                sendMsg(chatId, "Tarea 1.\nGobierno, legislación y participación ciudadana");
                try {
                    sendPoll(chatId, tarea1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statistics.plusOneTarea();
            } else if ("/tarea2".equals(text) || "2".equals(text)) {
                sendMsg(chatId, "Tarea 2.\nDerechos y deberes fundamentales");
                try {
                    sendPoll(chatId, tarea2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statistics.plusOneTarea();
            } else if ("/tarea3".equals(text)  || "3".equals(text)) {
                sendMsg(chatId, "Tarea 3.\nOrganización territorial de España. Geografía física y política");
                try {
                    sendPoll(chatId, tarea3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statistics.plusOneTarea();
            } else if ("/tarea4".equals(text)  || "4".equals(text)) {
                sendMsg(chatId, "Tarea 4.\nCultura e Historia de España");
                try {
                    sendPoll(chatId, tarea4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statistics.plusOneTarea();
            } else if ("/tarea5".equals(text)  || "5".equals(text)) {
                sendMsg(chatId, "Tarea 5.\nSociedad española");
                try {
                    sendPoll(chatId, tarea5);   // (Из класса Question) отсюда вызываем метод Question передавая туда тескт сообщения??
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statistics.plusOneTarea();
            } else if ("/sobremi".equals(text)) {
                sendMsg(chatId, "Este bot usa el /manual oficial del examen CCSE del Instituto Cervantes.\n" +
                    "\n" +
                    "Los botones intuitivos en la parte inferior se recomiendan para su conveniencia.\n" +
                    "\n" +
                    "Por favor contácteme para cualquier pregunta o sugerencia!\n" +
                    "@Dimka27");
                statistics.plusOneInfo();
            } else if ("/manual".equals(text)) {
                sendDoc(chatId);
            } else if ("exit".equals(text)) {
                sendMsg(chatId, "Closed");
                System.exit(0);
            } else if ("statistics".equals(text)) {
                sendMsg(chatId, "Sobremi: " + statistics.getInfo()  + "\n" +
                    "Examenes: " + statistics.getExams()  + "\n" +
                    "Tareas: " + statistics.getTareas() + "\n" +
                    "Examenes y tares: " + (statistics.getTareas() + statistics.getExams()) + "\n" +
                    "");
            }
        }
    }

    public void sendMsg(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        //sendMessage.setReplyToMessageId(message.getMessageId());   just for replying to messages
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage.setText(text));  // (message.getText() + " " + text)
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/examen"));
        // keyboardFirstRow.add(new KeyboardButton("/sobremi"));
        keyboardSecondRow.add(new KeyboardButton("/tarea1"));
        keyboardSecondRow.add(new KeyboardButton("/tarea2"));
        keyboardSecondRow.add(new KeyboardButton("/tarea3"));
        keyboardSecondRow.add(new KeyboardButton("/tarea4"));
        keyboardSecondRow.add(new KeyboardButton("/tarea5")); //добавляем название первой линии второй кнопки
        // keyboardFirstRow.add(new KeyboardButton("/start"));

        keyboardRowLlist.add(keyboardFirstRow);  //добавляем все строки в аррай список
        keyboardRowLlist.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowLlist);  //устанавливаем список на клавиатуру
    }

    public void sendPoll(String chatId, File tarea) throws InterruptedException {
        int rangeOfnumbers = getRandomNumberFrom(tarea.getArrayLength());
        //System.out.println("random number " + rangeOfnumbers);
        String[] answers = tarea.answers(rangeOfnumbers);
        SendPoll sendPoll = new SendPoll();

        // sendPoll.setChatId(message.getChatId().toString());
        sendPoll.setChatId(chatId);
        sendPoll.setAnonymous(true);
        sendPoll.setQuestion(tarea.question(rangeOfnumbers));
        sendPoll.setOptions(Arrays.asList(answers));
        sendPoll.setCorrectOptionId(tarea.correctOption(rangeOfnumbers));
        sendPoll.setType("quiz");

        try {
            execute(sendPoll.setChatId(chatId));  // (message.getText() + " " + text) как вариант
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendDoc(String chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument("https://examenes.cervantes.es/sites/default/files/manual_ccse_2021.pdf");

        try {
            execute(sendDocument);  // (message.getText() + " " + text) как вариант
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static int getRandomNumberFrom(int range) {
        return (int) (Math.random() * range);
    }

    public String getBotUsername() {
        return "CCSE_exam_bot";
    }

    public String getBotToken() {
        return "1213851843:AAEYmoddKktWaYEd4ty4fiBjsyvoMJH8mW0";
    }
}

