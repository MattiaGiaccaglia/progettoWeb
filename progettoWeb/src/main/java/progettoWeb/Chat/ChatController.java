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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    ChatService chatService;

    //Restituisco tutte le chat
    @GetMapping("/getChats")
    public ResponseEntity<Object> getChats() {
        List<ChatRecord> chatRecords = chatService.getAllChats();
        if (chatRecords.isEmpty())
            return new ResponseEntity<>("Nessuna Chat presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(chatRecords, HttpStatus.OK);
    }

    //Restituisco una specifica Chat
    @GetMapping("/getChat/{id}")
    public ResponseEntity<Object> getChat(@PathVariable int id) {
        ChatRecord chatRecord = chatService.getChat(id);
        return new ResponseEntity<>(chatRecord, HttpStatus.OK);
    }
}
