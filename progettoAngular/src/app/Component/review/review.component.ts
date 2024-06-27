import { Component } from '@angular/core';
import { reviewList } from '../../List/reviewList';
import { ReviewService } from '../../Service/review/review.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {
  public reviews: reviewList[];
  displayedColumns: string[] = ['id', 'venditore', 'utente'];

  constructor(private reviewService: ReviewService){}

  ngOnInit() {
    this.getReviews();
  }

  //Ottengo tutte le reviews
  public getReviews(): void{
    this.reviewService.getReviews().subscribe(
      (response: reviewList[]) =>{
        this.reviews = response;
      },
      (error: HttpErrorResponse) =>{
        alert("Nessuna Review presente.");
      }
    );
  }

}
