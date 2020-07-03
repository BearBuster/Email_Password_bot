package com.function;

import java.util.Random;

public class CreateEmailAndPassword {
    //Перевел все возможные символы для маила в массив char[]
    private static char[] alpha_numeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static Random random = new Random();
    public static String createEmail(){
        int i = 0;
        //Рандомная длина первой части пароля ( от 1 до 11 )
        int ran = random.nextInt(10)+1;
        StringBuilder eMail = new StringBuilder();
        while (i != ran){
            //Добовление символа , по рандомному id из всевозможных символов
            eMail.append(alpha_numeric[random.nextInt(alpha_numeric.length)]);
            i++;
        }
        //Разделяющий знак для второй части пароля
        eMail.append('_');
        //Обнуление прошлых значений i и ran ( рандомная длина второй части )
        i = 0;
        ran = random.nextInt(10)+1;
        while (i != ran){
            //Добовление символа , по рандомному id из всевозможных символов
            eMail.append(alpha_numeric[random.nextInt(alpha_numeric.length)]);
            i++;
        }
        //Рандомное добавление хоста
        i = random.nextInt(5)+1;
        switch (i) {
            case 1:
                eMail.append("@gmail.com");
                break;
            case 2:
                eMail.append("@mail.ru");
                break;
            case 3:
                eMail.append("@yandex.ru");
                break;
            case 4:
                eMail.append("@yhoo.com");
                break;
            case 5:
                eMail.append("@iis.utm.md");
                break;
        }
        //Привидение StringBuilder к String
        return eMail.toString();
    }
    public static String createPassword(){
        StringBuilder password = new StringBuilder();
        int ran = random.nextInt(10)+10;
        int i;
        for( i = 0 ; i < ran ; i++)
            password.append(alpha_numeric[random.nextInt(alpha_numeric.length)]);
        return password.toString();
    }
}
