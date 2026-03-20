const express = require('express');
const router = express.Router();
const rutinas = require('../data/rutinas.json');

// GET /api/rutinas - Lista todas las rutinas
router.get('/', (req, res) => {
  res.json({
    error: false,
    total: rutinas.length,
    data: rutinas
  });
});

// GET /api/rutinas/:id - Detalle de una rutina
router.get('/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const rutina = rutinas.find(r => r.id === id);

  if (!rutina) {
    return res.status(404).json({
      error: true,
      message: 'Rutina no encontrada'
    });
  }

  res.json({
    error: false,
    data: rutina
  });
});

// POST /api/rutinas - Crear una rutina nueva
router.post('/', (req, res) => {
  const { nombre, dia, ejercicios } = req.body;

  if (!nombre || !dia) {
    return res.status(400).json({
      error: true,
      message: 'El nombre y el día son obligatorios'
    });
  }

  const nuevaRutina = {
    id: rutinas.length + 1,
    nombre,
    dia,
    ejercicios: ejercicios || []
  };

  rutinas.push(nuevaRutina);

  res.status(201).json({
    error: false,
    message: 'Rutina creada exitosamente',
    data: nuevaRutina
  });
});

module.exports = router;