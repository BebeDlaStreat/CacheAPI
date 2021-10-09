# CacheAPI
CacheAPI allow you to easily save and manage data with an hybrid system between Redis and MySQL

## How did it work ?
When you will save data it will first save it on Redis and asynchronously send it on the database

## Code example
### Save and Get a simple String
```Java
CacheAPI.set("welcome_message", "Welcome on CacheAPI!");
System.out.println(CacheAPI.get("welcome_message"));
```
### Save and Get numbers
```Java
CacheAPI.set("BebeDlaStreat/solde", String.valueOf(10));
int solde = Integer.parseInt(CacheAPI.get("BebeDlaStreat/solde"));
```
> I will probably add further other database storage like h2 and MongoDB and add directy Object saving by converting Object to String in json format
