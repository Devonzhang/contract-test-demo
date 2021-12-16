### This is a small demo using `Netflix-DGS` and `Mongo`

###### How to set up local mongo:

```shell
docker run -itd --name mongo-learn-dgs -p 27017:27017 mongo --auth
```

```shell
docker exec -it mongo-learn-dgs mongo admin
```

```shell
db.createUser({ user:'admin',pwd:'root',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
db.auth("admin", "root")
use book-run
db.createUser({ user:'book-run',pwd:'book-run-pwd',roles:[ { role:'readWrite', db: 'book-run'}]});
use book-test
db.createUser({ user:'book-test',pwd:'book-test-pwd',roles:[ { role:'readWrite', db: 'book-test'}]});
```

###### How to use:

After set up Mongo, just start this application and then use

**POST** http://localhost:8080/shows

to add `Show` data in the database,
with request body like:

```json
{
  "title" : "Gödel, Escher, Bach: an Eternal Golden Braid",
  "releaseYear" : 1979
}
```

Then open a browser to http://localhost:8080/graphiql to see the query editor.