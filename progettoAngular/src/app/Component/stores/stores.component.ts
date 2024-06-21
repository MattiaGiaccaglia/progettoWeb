import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StoreService } from '../../Service/store/store.service';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrl: './stores.component.css'
})
export class StoresComponent {
  store: any;

  constructor(
    private route: ActivatedRoute,
    private storeService: StoreService,
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const storeID = params['id'];
      this.storeService.getStore(storeID).subscribe(store => {
        this.store = store;
      });
    });
  }

}
