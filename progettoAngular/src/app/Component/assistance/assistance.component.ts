import { Component, OnInit } from '@angular/core';
import { assistanceList } from '../../List/assistanceList';
import { AssistanceService } from '../../Service/assistance/assistance.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-assistance',
  templateUrl: './assistance.component.html',
  styleUrl: './assistance.component.css'
})
export class AssistanceComponent implements OnInit{
  public assistances: assistanceList[];
  displayedColumns: string[] = ['id', 'staff', 'chat'];
  constructor(private assistanceService: AssistanceService){}

  
  ngOnInit(){
    this.getAllAssistance();
    }

  public getAllAssistance(): void{
    this.assistanceService.getAssistance().subscribe(
      (response: assistanceList[]) => {
        this.assistances = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
