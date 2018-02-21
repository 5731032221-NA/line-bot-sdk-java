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
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }
    
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        
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
        }
        else {
        	return new TextMessage(event.getMessage().getText());
        }
    
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
