db = db.getSiblingDB('admin')
db.auth(
    'admin',
    'root'
);
db = db.getSiblingDB('book')
db.createUser(
    {
        user: 'book',
        pwd: 'book-pwd',
        roles: [{role: 'readWrite', db: 'book'}]
    });
db.auth(
    'book',
    'book-pwd'
);