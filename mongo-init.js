db.getSiblingDB('admin').auth(
    'admin',
    'root'
);
db = db.getSiblingDB('book_run');
db.createUser({user: 'book-run', pwd: 'book-run-pwd', roles: [{role: 'readWrite', db: 'book_run'}]});