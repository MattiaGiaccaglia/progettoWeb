/*
 * Copyright 2024 Mattia Giaccaglia
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package progettoWeb.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.Messages.MessagesRecord;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatRepository ChatRepository;

    //Restituisco tutte le chat
    public List<ChatRecord> getAllChats(){
        List<ChatRecord> chatRecords = new ArrayList<>();
        ChatRepository.findAll().forEach(chatRecords::add);
        return chatRecords;
    }

    //Restituisco una chat a partire da un ID
    public ChatRecord getChat(int id) {
        return ChatRepository.findById(id)
                .orElseThrow(() -> new ChatException.ChatExceptionNotFound
                        ("Nessuna Chat associata a questo id: " + id));
    }

    //Aggiungo chat
    public void addChat(ChatRecord chatRecord){
        ChatRepository.save(chatRecord);
    }
}
