import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class JwtService {
  tokenKey = 'jwtToken';

  getToken(): String {
    return window.localStorage[this.tokenKey];
  }

  saveToken(token: String) {
    console.log('saving token');
    console.log(token);
    window.localStorage[this.tokenKey] = token;
  }

  destroyToken() {
    window.localStorage.removeItem(this.tokenKey);
  }
}
