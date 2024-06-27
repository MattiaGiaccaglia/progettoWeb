import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { Observable } from 'rxjs';
import { reviewList } from '../../List/reviewList';
import { ReviewComponent } from '../../Component/review/review.component';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  //Ottengo reviews
  public getReviews(): Observable<reviewList[]>{
    return this.Http.get<reviewList[]>(`${this.constants.baseUrl}/api/review/getReviews`)
  }

  //Ottengo review a partire dall'ID
  public getReview(ReviewID: number): Observable<reviewList>{
    return this.Http.get<reviewList>(`${this.constants.baseUrl}/api/review/getReview/${ReviewID}`)
  }

  //Aggiungo una review
  public addReview(review: any): Observable<any>{
    return this.Http.post<any>(`${this.constants.baseUrl}/api/review/addReview`, review);
  }
}
