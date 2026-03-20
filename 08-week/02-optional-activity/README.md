# FitTrackU - Backend API

API REST construida con Node.js y Express para el 
proyecto FitTrack U de Programación Móvil.

## Tecnologías
- Node.js v22.21.0
- Express 4.x

## Cómo ejecutar

1. Clonar el repositorio
2. Entrar a la carpeta:
   cd FitTrackU-Backend
3. Instalar dependencias:
   npm install
4. Ejecutar el servidor:
   node server.js
5. Abrir en el navegador:
   http://localhost:3000

## Endpoints disponibles

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/rutinas | Lista todas las rutinas |
| GET | /api/rutinas/:id | Detalle de una rutina |
| POST | /api/rutinas | Crear rutina nueva |

## Ejemplo respuesta GET /api/rutinas/1
{
  "error": false,
  "data": {
    "id": 1,
    "nombre": "Rutina Pecho y Tríceps",
    "dia": "Lunes"
  }
}