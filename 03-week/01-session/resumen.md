# Flujo de Trabajo Git con Historias de Usuario (HU)

## Estructura de Ramas

```
main (producción)
  └── release (acumulador de cortes)
        └── qa (pruebas)
              └── develop (desarrollo)
```

---

## Paso a Paso: Crear Feature hacia una Rama

### 1. Posicionarse en la rama padre
```bash
git switch {ambiente}
```
- Te ubicas en la rama desde donde nacerá tu feature
- Ejemplo: `git switch develop`

### 2. Crear rama de la HU
```bash
git switch -c HU-01-{ambiente}
```
- Crea y cambia a una nueva rama basada en el padre
- Ejemplo: `git switch -c HU-01-develop`

### 3. Desarrollar la HU
- Realiza commits respetando **Conventional Commits**:
  ```bash
  git commit -m "feat(modulo): descripción corta"
  git commit -m "fix(auth): corrige validación de token"
  ```

### 4. Sincronizar con rama padre
```bash
git pull origin {ambiente}
```
- Trae cambios recientes del padre antes del MR
- Resuelve conflictos si existen

### 5. Publicar cambios
```bash
git push origin HU-01-{ambiente}
```

### 6. Crear Merge Request (MR)
- Destino: `{ambiente}` (la rama padre)
- Nunca push directo a ramas padre

---

## Flujo por Ambiente

| Ambiente | Acción |
|----------|--------|
| `develop` | Pasos 1-6, MR hacia `develop` |
| `qa` | Pasos 1-6, MR hacia `qa` |
| `release` | Pasos 1-6 + **acumular todas las HU del corte** |
| `main` | Crear MR desde `release` por cada corte |

---

## Diagrama de Flujo

```
HU-01-develop ──MR──► develop
HU-02-develop ──MR──► develop
                         │
HU-01-qa ─────MR──► qa ◄─┘ (cuando pasa a QA)
                         │
HU-01-release ─MR─► release ◄─┘ (acumula HUs del corte)
                         │
                    main ◄─┘ (MR por corte completo)
```

---

## Reglas Clave

1. **Nunca commits directos** a `develop`, `qa`, `release`, `main`
2. **Siempre `git pull`** del padre antes de MR
3. **Conventional Commits** obligatorio
4. **Ramas HU nacen del padre** correspondiente
5. **`release`** es el único acumulador de cortes