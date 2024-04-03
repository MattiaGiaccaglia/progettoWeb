import { userList } from "./userList";

export interface couponList{
    id: number,
    valore: number,
    vendorCoupon: userList,
    userCoupon: userList
}