import express from "express";
import cors from "cors";
import rutinasRoutes from "./routes/rutinas.routes.js";

const app = express();
const PORT = 3000;

// CORS — permite el emulador Android
app.use(cors({
  origin: (origin, callback) => {
    if (!origin) return callback(null, true);
    return callback(null, true);
  },
  methods: ["GET", "POST", "PUT", "DELETE"],
  allowedHeaders: ["Content-Type", "Authorization"]
}));

app.use(express.json());

// Rutas
app.use("/api/rutinas", rutinasRoutes);

// Health check
app.get("/api/health", (req, res) => {
  res.status(200).json({ status: "OK", message: "FitTrack U API funcionando" });
});

app.listen(PORT, () => {
  console.log(`FitTrack U API corriendo en http://localhost:${PORT}`);
});