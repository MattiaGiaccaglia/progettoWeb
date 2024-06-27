import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StoreService } from '../../Service/store/store.service';
import { storeList } from '../../List/storeList';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrl: './stores.component.css'
})
export class StoresComponent {
  store: storeList;

  constructor(
    private route: ActivatedRoute,
    private storeService: StoreService,
  ) { }

  //Ottengo store a partire dal suo ID
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const storeID = params['id'];
      this.storeService.getStore(storeID).subscribe(store => {
        this.store = store;
      });
    });
  }

}
