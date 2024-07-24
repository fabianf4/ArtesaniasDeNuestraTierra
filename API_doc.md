# API Documentación

## Endpoint: GET api/health

**Descripción**: Prueba de api sin seguridad

**Request**:
- Method: GET
- URL: api/health

**Response**:
- Status: 200 OK
- Content-Type: text/plain
- Body:
  "Api Rest is working"

## Endpoint: GET api/health/secure

**Descripción**: Prueba de api con seguridad

**Request**:
- Method: GET
- URL: api/health/secure
- Authorization: Basic phone password

**Response**:
- Status: 200 OK
- Content-Type: text/plain
- Body:
  "Api Rest is working with security"

## Endpoint: POST api/user/signup

**Descripción**: Registro de un usuario

**Request**:
- Method: POST
- URL: api/user/signup
- Content-Type: application/json
- Body:
    {
        "phone": "123456789",
        "password": "test123",
        "name": "test"
    }

**Response**:
- Status: 201 CREATED

## Endpoint: DELETE /api/user/delete

**Descripción**: Eliminación de un usuario.

**Request**:
- Method: DELETE
- URL: /api/user/delete
- Content-Type: application/json
- Authorization: Basic phone password

**Response**:
- Status: 200 OK

## Endpoint: GET /api/user/me

**Descripción**: Obtener información del usuario autenticado.

**Request**:
- Method: GET
- URL: /api/user/me
- Content-Type: application/json
- Authorization: Basic phone password

**Response**:
- Status: 200 OK
- Content-Type: application/json
- Body:
    {
        "id":1,
        "phone": "1234567890",
        "password": true,
        "name": "test123"
    }