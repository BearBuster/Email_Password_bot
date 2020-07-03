import com.function.CreateEmailAndPassword;
import com.function.CreateEmailAndPassword;
import com.function.Users;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public CreateEmailAndPassword EmPas = new CreateEmailAndPassword();
    private String s = "";
    public StringBuilder info = new StringBuilder();
    List<String> members_string = new ArrayList<String>();
    List<Users> members_obj = new ArrayList<Users>();

    Iterator <Users> users_iterator = members_obj.iterator();

    @Override
    public void onUpdateReceived(Update update) {
        User a = new User();
        Message message = update.getMessage();
        System.out.println(message.getFrom().getFirstName());
        if (message != null && message.hasText() && message.getText().equals("Email and Password")) {
            s = "Email : " + EmPas.createEmail() + "\n" + "Password : " + EmPas.createPassword();
            sendMsg(message, s );
        }
        else if(message != null && message.hasText() && message.getText().equals("Email")) {
            s = "Email : " + EmPas.createEmail();
            sendMsg(message , s);
        }
        else if(message != null && message.hasText() && message.getText().equals("Password")) {
            s = "Password : " + EmPas.createPassword();
            sendMsg(message , s);
        }
        else if(message != null && message.hasText() && message.getText().equals("Info")) {
//            Users tmp;
//            users_iterator = members_obj.iterator();
//            while (users_iterator.hasNext()) {
//                tmp = users_iterator.next();
//                info.append("Name : " + tmp.name + "\n" + " count : " + tmp.count );
//            }
            sendMsg(message , members_string.toString() );
        }else {
            s = "Приветствую господин " + message.getFrom().getFirstName();
            sendMsg(message, s );
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        Users tmp = null;
        User a = new User();
        if(members_string.contains(message.getFrom().getFirstName())){
//            while(users_iterator.hasNext()){
//                tmp = users_iterator.next();
//                if (tmp.name.equals(a.getUserName()))
//                    break;
//            }
//            if(tmp.name.equals(a.getUserName()))
//                tmp.count++;
            System.out.println(1);
        }else {
            members_string.add(message.getFrom().getFirstName());
//            members_obj.add(new Users(a.getUserName()));
        }
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowsList = new ArrayList();

        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton("Email"));
        keyboardRowFirst.add(new KeyboardButton("Password"));

        KeyboardRow keyboardRowSecond = new KeyboardRow();
        keyboardRowSecond.add(new KeyboardButton("Email and Password"));

        KeyboardRow keyboardRowThird = new KeyboardRow();
        keyboardRowThird.add(new KeyboardButton("Info"));

        keyboardRowsList.add(keyboardRowFirst);
        keyboardRowsList.add(keyboardRowSecond);
        keyboardRowsList.add(keyboardRowThird);

        replyKeyboardMarkup.setKeyboard(keyboardRowsList);

    }

    @Override
    public String getBotUsername() {
        return "Email_And_Password_Bot";
    }

    @Override
    public String getBotToken() {
        return "1345772424:AAHDpczchqavYdSaAknKrRVG_OqjZZe_Yr4";
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
