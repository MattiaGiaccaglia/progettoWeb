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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assistance")
public class AssistanceController {

    @Autowired
    AssistanceService assistanceService;

    //Restituisco tutte le assistenze fatte
    @GetMapping("/getAssistance")
    public ResponseEntity<Object> getAllAssistance(){
        List<AssistanceRecord> assistanceRecords = assistanceService.getAllAssistance();
        if(assistanceRecords.isEmpty())
            return new ResponseEntity<>("Nessun assistenza presente", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(assistanceRecords, HttpStatus.OK);
    }

    //Restituisco assistenza a partire dal suo ID
    @GetMapping("/getAssistanceById/{id}")
    public ResponseEntity<AssistanceRecord> getAssistantById(@PathVariable("id") int id){
        AssistanceRecord assistanceRecord = assistanceService.getAssistanceById(id);
        return new ResponseEntity<>(assistanceRecord, HttpStatus.OK);
    }

    //Richiedo tutte le assistenze fatte da un determinato Staff
    @GetMapping("/getAssistanceByStaff/{id}")
    public ResponseEntity<Object> getAllAssistance(@PathVariable("id") int id){
        List<AssistanceRecord> assistanceRecords = assistanceService.getAllAssistanceByStaff(id);
        if(assistanceRecords.isEmpty())
            return new ResponseEntity<>("Nessun assistenza effettuata da questo staff.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(assistanceRecords, HttpStatus.OK);
    }

    //Chiudo una chat
    @PostMapping("/closeChat/{id}")
    public ResponseEntity<Object> closeChat(@PathVariable("id") int id){
        if(assistanceService.closeChat(id))
            return new ResponseEntity<>("Chat chiusa correttamente.", HttpStatus.OK);
        return new ResponseEntity<>("La chat è gia chiusa.", HttpStatus.BAD_REQUEST);
    }


    //Elimino Assistenza
    @DeleteMapping("/deleteAssistance/{id}")
    public ResponseEntity<String> deleteAssistance(@PathVariable("id") int id){
        assistanceService.deleteAssistance(id);
        return new ResponseEntity<>("Assistenza eliminata correttamente", HttpStatus.OK);
    }

}
