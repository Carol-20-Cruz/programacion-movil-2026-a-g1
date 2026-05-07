import { Router } from "express";
import {
  listRutinas,
  getRutina,
  createRutina,
  deleteRutina
} from "../controllers/rutinas.controller.js";

const router = Router();

router.get("/", listRutinas);
router.get("/:id", getRutina);
router.post("/", createRutina);
router.delete("/:id", deleteRutina);

export default router;