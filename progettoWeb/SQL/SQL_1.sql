CREATE TABLE USER_RECORD(
    ID int IDENTITY(1,1) PRIMARY KEY ,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    username VARCHAR(20) NOT NULL,
    password_ VARCHAR(8) NOT NULL, 
    email VARCHAR(50) NOT NULL,
    telefono VARCHAR(13) NOT NULL,
    ruolo VARCHAR(7)
)

insert into USER_RECORD(nome, cognome, username, password, email, telefono, ruolo)
values('Mario', 'Rossi', 'Mario21', '12345##', 'mariorossi@gmail.com', '+393648263843', 'utente'),
('LUCA', 'Rossi', 'Luca21', '12345@@', 'lucarossi@gmail.com', '+39354629464', 'staff');