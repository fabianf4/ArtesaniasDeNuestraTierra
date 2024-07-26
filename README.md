# Artesanias de nuestra tierra

## Run the api:

Clone the repository:
```
git clone https://github.com/fabianf4/ArtesaniasDeNuestraTierra
```

Navigate to project folder:
```
cd ArtesaniasDeNuestraTierra
```

Run docker compose:
```
docker compose up --build -d
```

For stop the api:
```
docker compose down
```

## Run MySQL for develop:

Run docker compose:
```
docker compose -f docker-compose-dbtest.yaml up -d
```

## Acceder a SwaggerUI:
Para acceder a la interfaz de SwaggerUI, navega a la siguiente URL en tu navegador:
```
http://localhost:8080/swagger-ui/index.html
```

## Crear un usuario para autorización
Antes de usar los endpoints protegidos, debes crear un usuario utilizando el endpoint /api/user/signup. Aquí tienes un ejemplo de la solicitud:
```
POST http://localhost:8080/api/user/signup
```
Cuerpo de la solicitud (JSON)
```
{
    "email": "prueba@gmail.com",
    "name": "Prueba",
    "password": "123456",
    "phone": "321678901"
}
```
Una vez que hayas creado un usuario, podrás autorizarte y acceder a los endpoints protegidos en SwaggerUI.