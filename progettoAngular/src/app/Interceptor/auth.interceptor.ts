import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //verifico se la richiesta è per un'API che richiede autorizzazione
    if (req.url.includes('/api/user/login') || req.url.includes('/api/user/registrazione')) {
      //non aggiungo il token JWT per il login o la registrazione
      return next.handle(req);
    }

    //aggiungi il token JWT per tutte le altre richieste
    const jwtToken = this.getJwtToken();
    const cloneRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${jwtToken}`
      }
    });
    return next.handle(cloneRequest);
  }

  private getJwtToken(): string | null {
    return localStorage.getItem('JWT_TOKEN');
  }
}

export const AuthInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true,
};
