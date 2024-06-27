import { Component } from '@angular/core';
import { storeList } from '../../List/storeList';
import { StoreService } from '../../Service/store/store.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrl: './store.component.css'
})
export class StoreComponent {
  public stores: storeList[];
  displayedColumns: string[] = ['nomeNegozio', 'proprietario', 'luogo'];

  constructor(private storeService: StoreService){}

  ngOnInit() {
    this.getAllStores();
  }

  //Ottengo tutti i negozi
  public getAllStores(): void{
    this.storeService.getAllStores().subscribe(
      (response: storeList[]) =>{
        this.stores = response;
      },
      (error: HttpErrorResponse) =>{
        alert("Nessun negozio presente.");
      }
    );
  }
}
