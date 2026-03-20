const express = require('express');
const app = express();
const PORT = 3000;

// Middleware para leer JSON
app.use(express.json());

// Rutas
const rutinasRouter = require('./routes/rutinas');
app.use('/api/rutinas', rutinasRouter);

// Ruta principal
app.get('/', (req, res) => {
  res.json({
    message: '💪 FitTrack U API',
    version: '1.0.0',
    endpoints: [
      'GET /api/rutinas',
      'GET /api/rutinas/:id',
      'POST /api/rutinas'
    ]
  });
});

// Iniciar servidor
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});