import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private final static String TOKEN = "";


    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage());
        SendMessage msg = new SendMessage(update.getMessage().getChatId(), "welcome!");

        try {
            System.out.println("UserName: " + getMe().getUserName());
            System.out.println("FistName: " + getMe().getFirstName());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void onUpdatesReceived(List<Update> updates) {
        try {
            User me = getMe();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Images2PdfBot";
    }

    public String getBotToken() {
        return TOKEN;
    }
}
