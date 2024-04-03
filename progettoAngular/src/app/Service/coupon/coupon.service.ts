import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { couponList } from '../../List/couponList';
import { Observable } from 'rxjs';
import { ConstantsService } from '../../constants.service';

@Injectable({
  providedIn: 'root'
})
export class CouponService {

  constructor(private Http: HttpClient, private constants: ConstantsService) { }

  public getCoupons(): Observable<couponList[]>{
    return this.Http.get<couponList[]>(`${this.constants.baseUrl}/api/coupon/getCoupons`)
  }
}
