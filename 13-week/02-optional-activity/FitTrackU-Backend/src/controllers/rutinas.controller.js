// Base de datos en memoria
let rutinas = [
  { id: 1, nombre: "Rutina Pecho y Tríceps", ejercicios: "4", dia: "Lunes" },
  { id: 2, nombre: "Rutina Pierna", ejercicios: "5", dia: "Miércoles" },
  { id: 3, nombre: "Rutina Espalda y Bíceps", ejercicios: "4", dia: "Viernes" }
];

let nextId = 4;

// GET /api/rutinas
export async function listRutinas(req, res) {
  return res.status(200).json(rutinas);
}

// GET /api/rutinas/:id
export async function getRutina(req, res) {
  const { id } = req.params;
  const rutina = rutinas.find(r => r.id === parseInt(id));
  if (!rutina) {
    return res.status(404).json({ message: "Rutina no encontrada" });
  }
  return res.status(200).json(rutina);
}

// POST /api/rutinas
export async function createRutina(req, res) {
  const { nombre, ejercicios, dia } = req.body;

  if (!nombre || !ejercicios || !dia) {
    return res.status(400).json({
      message: "Datos invalidos",
      details: "nombre, ejercicios y dia son obligatorios"
    });
  }

  const nueva = { id: nextId++, nombre, ejercicios, dia };
  rutinas.push(nueva);
  return res.status(201).json(nueva);
}

// DELETE /api/rutinas/:id
export async function deleteRutina(req, res) {
  const { id } = req.params;
  const index = rutinas.findIndex(r => r.id === parseInt(id));
  if (index === -1) {
    return res.status(404).json({ message: "Rutina no encontrada" });
  }
  rutinas.splice(index, 1);
  return res.status(200).json({ message: "Rutina eliminada correctamente" });
}