export class User{
    constructor(
        public username: string,
        public id: string,
        private _token: string,
        private _expirationDate: Date
    ){}
}