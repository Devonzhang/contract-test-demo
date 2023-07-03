### This is a small demo using `Netflix-DGS` `MongoDB` and `OpenFeign`

###### How to set up local mongo:

```shell
docker run -itd --name mongo-learn-dgs -p 27017:27017 mongo --auth
```

```shell
docker exec -it mongo-learn-dgs mongosh admin
```

```shell
db.createUser({ user:'admin',pwd:'root',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
db.auth("admin", "root")
use book-run
db.createUser({ user:'book-run',pwd:'book-run-pwd',roles:[ { role:'readWrite', db: 'book-run'}]});
```

###### How to use:

After set up Mongo, just boot-run two applications and then use

**POST** http://localhost:8081/books

to add `Book` data in the database,
with request body(Json format) like:

```json
{
  "title" : "Gödel, Escher, Bach: an Eternal Golden Braid",
  "releaseYear" : 1979
}
```

Then open a browser visit http://localhost:8080/graphiql to see the query editor.

###### How to set up Pact Broker:

Firstly, you should set up a pact broker server using docker-compose file.

```shell
docker compose
```

Then, build graphql to generate a pact json file for contract test.
After generate json file, you can publish it to the Pact Broker server.

```shell
cd graphql
gradle pactPublish
```

Finally, you can build learn-now to check if this provider followed the rule.

You can write consumer test in GraphqlConsumerPactTest.java and write provider test in BookProviderPactTest.java.