import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConstantsService } from '../../constants.service';
import { Observable } from 'rxjs';
import { reviewList } from '../../List/reviewList';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getReviews(): Observable<reviewList[]>{
    return this.Http.get<reviewList[]>(`${this.constants.baseUrl}/api/review/getReviews`)
  }
}
