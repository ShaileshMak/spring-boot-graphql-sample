# springboot-graphql-sample
GraphQL CRUD using Spring Boot and Postgresh

### Sample request body
- Fragment of User

```

fragment userDetails on User {
  id
  name
  userName
  password
  phone
  website
  address {
    street
    suit
    city
    zipcode
    geo {
      lng
      lat
    }
  }
  company {
    name
    bs
    catchPhrase
  }
}

```
- Get all Users

```
query {
    users {
        ...userDetails
    }
}
```

- Get User by ID


```
query {
    user(id: 2) {
        ...userDetails
    }
}
```

- Create new User


```
mutation {
    addUser(userRequest : {
        name: "John DOe"
        userName: "johnDoe"
        password: "password"
        phone: "987654321"
        website: "www.google.com"
        street: "Peterson Road"
        suit: "212"
        city: "Frisco"
        zipcode: "75025"
        lat: "34.6463"
        lng: "-165.8889"
        companyName: "Adidas"
        catchPhrase: "Where evalucation take place"
        bs: "Make the difference"
    }){
        ...userDetails
    }
}
```

- Update User for ID


```
mutation {
    updateUserForId(id: "1", userRequest : {
        name: "John DOe"
        userName: "johnDoe"
        password: "password"
        phone: "987654321"
        website: "www.google.com"
        street: "Peterson Road"
        suit: "212"
        city: "Frisco"
        zipcode: "75025"
        lat: "34.6463"
        lng: "-165.8889"
        companyName: "Adidas"
        catchPhrase: "Where evalucation take place"
        bs: "Make the difference"
    }){
        ...userDetails
    }
}
```

- Delete User for ID


```
mutation {
    deleteUserForId(id: "1")
}
```

- Subscription on User update

```
subscription {
  onUserUpdate(userId: 5) {
    ...userDetails
  }
}

```
