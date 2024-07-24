# Artesanias de nuestra tierra

**Run the api:**

Clone the repository:
```
git clone https://github.com/fabianf4/ArtesaniasDeNuestraTierra
```

Navigate to project folder
```
cd ArtesaniasDeNuestraTierra
```

Run docker compose:
```
docker compose up --build -d
```

For stop the API:
```
docker compose down
```

**Run MySQL for develop:**

Run docker compose:
```
docker compose -f docker-compose-dbtest.yaml up -d
```