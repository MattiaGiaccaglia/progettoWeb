import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ConstantsService {
  readonly baseUrl = 'http://localhost:8080';
}
