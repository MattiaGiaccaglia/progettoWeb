import { Component, OnInit } from '@angular/core';
import { couponList } from '../../List/couponList';
import { CouponService } from '../../Service/coupon/coupon.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-coupon',
  templateUrl: './coupon.component.html',
  styleUrl: './coupon.component.css'
})
export class CouponComponent implements OnInit{
  public coupons: couponList[];
  constructor(private couponService: CouponService){}

  ngOnInit() {
    this.getCoupons();
  }

  public getCoupons(): void{
    this.couponService.getCoupons().subscribe(
      (response: couponList[]) =>{
        this.coupons = response;
      },
      (error: HttpErrorResponse) =>{
        alert("Nessun coupon presente");
      }
    );
  }
  
}
