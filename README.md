# CacheAPI
CacheAPI allow you to easily save and manage data with an hybrid system between Redis and MySQL

# How did it work ?
When you will save data it will first save it on Redis and asynchronously send it on the database

# Code example
### Save and Get a simple String
```Java
CacheAPI.set("welcome_message", "Welcome on CacheAPI!");
System.out.println(CacheAPI.get("welcome_message"));
```
