# Artesanias de nuestra tierra

**Run MySQL for develop:**

First, run docker compose:
```
docker compose up
```

Second, charge the DB if you don't have it:
```
mysql -h localhost -P 3306 -u crafts -p crafts < ./DB.sql
```
and write the password crafts.