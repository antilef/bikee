# bikeer


## Docker container for Postgresql

```
docker run -d --name postgres -e POSTGRES_PASSWORD=mysecretpassword -e PGDATA=/var/lib/postgresql/data/pgdata -e POSTGRES_DB=bikeer -v /data/postgresql:/var/lib/postgresql/data -p 5432:5432 postgres:17
```

