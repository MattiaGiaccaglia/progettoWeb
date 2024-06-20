export class User{
    constructor(
        public username: string,
        public id: number,
        private _token: string,
        private _expirationDate: Date
    ){}
}