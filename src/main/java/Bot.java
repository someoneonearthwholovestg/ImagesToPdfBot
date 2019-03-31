import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private final static String token = "";


    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage());
        update.getCallbackQuery();
    }

    public void onUpdatesReceived(List<Update> updates) {

    }

    public String getBotUsername() {
        return "Images2PdfBot";
    }

    public String getBotToken() {
        return token;
    }
}
