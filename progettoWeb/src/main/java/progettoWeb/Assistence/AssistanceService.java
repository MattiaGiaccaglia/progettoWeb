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

package progettoWeb.Assistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progettoWeb.Chat.ChatRecord;
import progettoWeb.Chat.ChatService;
import progettoWeb.User.Role;
import progettoWeb.User.UserRecord;
import progettoWeb.User.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssistanceService {
    @Autowired
    AssistanceRepository assistanceRepository;
    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;

    //Restituisco tutte le Assistenze che sono state fatte
    public List<AssistanceRecord> getAllAssistance(){
        List<AssistanceRecord> assistanceRecords = new ArrayList<>();
        assistanceRepository.findAll().forEach(assistanceRecords::add);
        return assistanceRecords;
    }

    //Restituisco una'assistenze cercandola col suo ID
    public AssistanceRecord getAssistanceById(int id){
        return assistanceRepository.findById(id)
                .orElseThrow(() -> new AssistanceException.AssistanceExceptionNotFound("Nessun assistenza presente con il seguente id: " + id));
    }

    //Restituisco tutte le Assistenze che sono state fatte da un determinato Staff
    public List<AssistanceRecord> getAllAssistanceByStaff(int id) {
        UserRecord user = userService.getUser(id);
        if (!user.getRuolo().equals(Role.staff))
            throw new IllegalArgumentException("L'utente inserito non è uno Staff.");
        return getAllAssistance().stream().filter(a -> a.getStaff().getId() == id).collect(Collectors.toList());
    }

    //Metodo che permette di chiudere una chat
    public boolean closeChat(int id){
        ChatRecord chatRecord = chatService.getChat(id);
        //Se già chiusa, restituisco false
        if(chatRecord.isClose())
            return false;
        boolean close = true;
        chatRecord.setClose(close);
        chatService.addChat(chatRecord);
        return true;
    }

    //Salvo l'assistenza
    public void addAssistance(AssistanceRecord assistanceRecord){
        assistanceRepository.save(assistanceRecord);
    }

    //Elimino una assistenza
    public void deleteAssistance(int id){
        assistanceRepository.delete(getAssistanceById(id));
    }
}
