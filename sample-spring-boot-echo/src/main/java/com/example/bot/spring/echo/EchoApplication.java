/*
 * Copyright 2018 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.util.regex.*;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }
    
    @EventMapping
    public StickerMessage handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
		
		return new StickerMessage( event.getMessage().getPackageId(), event.getMessage().getStickerId());
        //handleSticker(event.getReplyToken(), event.getMessage());
		
		
    }
    
    
    
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
	 System.out.println("event: " + event);
        
        String ad = "เพิ่ม"; 
        //String 
		Pattern patternad = Pattern.compile(ad);
		Matcher matcherad = patternad.matcher(event.getMessage().getText());
		
		String vc = "วัคซีน"; 
        //String 
		Pattern patternvc = Pattern.compile(vc);
		Matcher matchervc = patternvc.matcher(event.getMessage().getText());
		
		String p1 = "สุกร"; 
        //String 
		Pattern patternp1 = Pattern.compile(p1);
		Matcher matcherp1 = patternp1.matcher(event.getMessage().getText());
		
		String p2 = "หมู"; 
        //String 
		Pattern patternp2 = Pattern.compile(p2);
		Matcher matcherp2 = patternp2.matcher(event.getMessage().getText());
		
		String ed = "แก้ไข"; 
        //String 
		Pattern patterned = Pattern.compile(ed);
		Matcher matchered = patterned.matcher(event.getMessage().getText());
		
		String de = "ลบ"; 
        //String 
		Pattern patternde = Pattern.compile(de);
		Matcher matcherde = patternde.matcher(event.getMessage().getText());
		
		String ou = "ออกจากระบบ"; 
        //String 
		Pattern patternou = Pattern.compile(ou);
		Matcher matcherou = patternou.matcher(event.getMessage().getText());
        
        
        //return new TextMessage(event.getMessage().getText());
        if(event.getMessage().getText().equalsIgnoreCase("testPig")) {
        	return new TextMessage("Hello Pig data");
        }else if(event.getMessage().getText().equalsIgnoreCase("addPig")) {
        	return new TextMessage("Will add function in the future");
        }else if(event.getMessage().getText().equalsIgnoreCase("userid")) {
        	return new TextMessage(event.getSource().getUserId());
        }else if(event.getMessage().getText().equalsIgnoreCase("sender")) {
        	return new TextMessage(event.getSource().getSenderId());
        }else if(event.getMessage().getText().equalsIgnoreCase("time")) {
	        	return new TextMessage(event.getTimestamp().toString());
        }else if(event.getMessage().getText().equalsIgnoreCase("replytoken")) {
        	return new TextMessage(event.getReplyToken());
        }else if(event.getMessage().getText().equalsIgnoreCase("ไทย")) {
        	return new TextMessage("โอ้!! ภาษาไทย");
        }else if(event.getMessage().getText().equalsIgnoreCase("2") || (matchervc.find() && ( matcherp1.find() || matcherp2.find() ) ) ) {
        	return new TextMessage("เพิ่มข้อมูลการฉีดวัคซีนของสุกร");
        }else if(event.getMessage().getText().equalsIgnoreCase("1") || (matcherad.find() && ( matcherp1.find() || matcherp2.find() ) ) ) {
        	return new TextMessage("เพิ่มข้อมูลสุกร");
        }else if(event.getMessage().getText().equalsIgnoreCase("4") || (matchered.find()  ) ) {
        	return new TextMessage("แก้ไขข้อมูลสุกร");
        }else if(event.getMessage().getText().equalsIgnoreCase("5") || (matcherde.find()  ) ) {
        	return new TextMessage("ลบข้อมูลสุกร");
        }else if(event.getMessage().getText().equalsIgnoreCase("0") || (matcherou.find()  ) ) {
        	return new TextMessage("ออกจากระบบ");
        }
        else {
        	return new TextMessage("รายการคำสั่งที่ใช้งานได้\n" +
        						   "             \n" +
        						   "[1] เพิ่มข้อมูลสุกร\n" + 
        						   "[2] เพิ่มข้อมูลการฉีดวัคซีนของสุกร\n" +
        						   "[4] แก้ไขข้อมูลสุกร\n" + 
        						   "[5] ลบข้อมูลสุกร\n" + 
        						   "[0] ออกจากระบบ\n" + 
        						   "");
        }
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
