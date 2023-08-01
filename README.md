### This small demo shows how to do contract test

### This small demo uses `Netflix-DGS` `MongoDB` `OpenFeign` and `Pact`

###### How to set up local env:

```shell
docker-compose up -d
```

###### How to use:

docker-compose command will auto start pact server and Mongo locally, just boot-run two applications and then use

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

###### How to do contract test with Pact:

First, build graphql to generate a pact json file for contract test, generated file locate in /graphql/target/pacts.
After generate json file, you can publish it to the Pact Broker server.

```shell
cd graphql
gradle pactPublish
```

Finally, you can run BookProviderPactTest in learn-now to check if this provider following the rule we just published.

You can write your own consumer test in GraphqlConsumerPactTest.java and write provider test in
BookProviderPactTest.java.