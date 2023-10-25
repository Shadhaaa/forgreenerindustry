/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.forGreenerIndustry.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author shadha
 */
public class Sms {
    private static final String ACCOUNT_SID = "AC979e39df3bb715880145106ccb2d8603";
    private static final String AUTH_TOKEN = "1564e1d59e9b6296ec71ae98630532f0";
   // private static final String FROM_NUMBER = "+12314651476";

    public static void sendSms( String messageText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber("+14247327907"),
                        new PhoneNumber("+21626411186"),
                        messageText)
                .create();

        System.out.println("Message SID: " + message.getSid());
    }
}

