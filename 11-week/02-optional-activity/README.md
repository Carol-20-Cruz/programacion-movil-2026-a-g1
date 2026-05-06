# FitTrack U 

Aplicación móvil de gestión de rutinas de entrenamiento desarrollada en Android con Kotlin.

---

## Descripción

FitTrack U permite a los usuarios organizar y visualizar sus rutinas de ejercicio semanales. 
Desarrollada como proyecto del curso de Programación Móvil — **Semana 11: UX/UI en pantallas avanzadas**.

---

## Lo que se hizo en esta semana

### Problema inicial
- El botón **+ Nueva Rutina** no hacía nada
- La app no tenía módulo configurado en Android Studio (error: *Module not specified*)
- No existía la pantalla de formulario para agregar rutinas

### Soluciones aplicadas

#### 1. Error de módulo en Android Studio
- Se identificó que Gradle no había cargado el módulo `app`
- Se ejecutó **File → Sync Project with Gradle Files**
- Se seleccionó el módulo `app` en Edit Configuration

#### 2. Pantalla Nueva Rutina
- Se creó `NuevaRutinaActivity.kt` desde cero
- Se diseñó `activity_nueva_rutina.xml` con:
  - Campo de texto para el nombre de la rutina
  - Spinner con los 7 días de la semana
  - Campo numérico para número de ejercicios
  - Botón **Guardar Rutina**
  - Mensaje de error visible cuando los campos están vacíos

#### 3. Validaciones en el formulario
- Si el nombre está vacío → muestra error en el campo
- Si el número de ejercicios está vacío → muestra error en el campo
- Al guardar correctamente → Toast de confirmación y regresa a la pantalla anterior

#### 4. Mejoras UX/UI en la pantalla principal
Se rediseñó `activity_home.xml` aplicando principios de la Semana 11:

| Mejora | Descripción |
|--------|-------------|
| Buscador | Campo de búsqueda persistente en la parte superior |
| Chips de filtro | Filtros por día: Todos, Lunes, Miércoles, Viernes |
| Cards mejoradas | Padding uniforme de 20dp, texto jerárquico |
| Estado vacío | Mensaje "Sin rutinas" cuando no hay resultados |
| Botón principal | Botón rojo destacado fijo en la parte inferior |
| Espaciado consistente | Márgenes de 24dp en toda la app |

#### 5. Sistema de diseño aplicado

| Token | Valor | Uso |
|-------|-------|-----|
| Fondo principal | `#1A1A2E` | Todas las pantallas |
| Fondo card | `#16213E` | Tarjetas de rutinas |
| Fondo input | `#0F3460` | Campos de texto |
| Color acento | `#E94560` | Botones, títulos, chip activo |
| Texto principal | `#FFFFFF` | Nombres de rutinas |
| Texto secundario | `#AAAAAA` | Detalles, subtítulos |
| Texto hint | `#555555` | Placeholders |
| Tipografía título | 28sp bold | Encabezados de pantalla |
| Tipografía cuerpo | 16sp bold | Nombre de rutina en card |
| Tipografía detalle | 13sp | Ejercicios y día |
| Espaciado base | 24dp | Márgenes generales |

#### 6. Filtros funcionales
- Al tocar **Lunes** → solo se muestran rutinas del lunes
- Al tocar **Miércoles** → solo se muestran rutinas del miércoles
- Al tocar **Viernes** → solo se muestran rutinas del viernes
- Al tocar **Todos** → se muestran todas las rutinas
- El chip activo cambia a color rojo `#E94560`
- Los chips inactivos quedan en color oscuro `#16213E`

---

## Checklist UX/UI Semana 11

| Criterio | Cumplido |
|----------|----------|
| Botón principal claro por pantalla (CTA) | ✅ |
| Tipografía consistente | ✅ |
| Espaciados uniformes (24dp) | ✅ |
| Estado vacío definido | ✅ |
| Estado error definido | ✅ |
| Estado éxito definido | ✅ |
| Patrón avanzado: filtros | ✅ |
| Patrón avanzado: formulario con validación | ✅ |
| Consistencia visual entre pantallas | ✅ |
| Feedback inmediato al usuario | ✅ |

---

## Pantallas

### 1. Login
Pantalla de inicio de sesión con botón que navega a la pantalla principal.

### 2. Mis Rutinas
Pantalla principal con:
- Encabezado con título y saludo
- Buscador de rutinas
- Chips de filtro por día
- Lista de rutinas en cards
- Estado vacío cuando no hay resultados
- Botón fijo para agregar nueva rutina

### 3. Nueva Rutina
Formulario con:
- Nombre de la rutina (texto obligatorio)
- Día de la semana (Spinner con 7 días)
- Número de ejercicios (numérico obligatorio)
- Validaciones con mensajes de error
- Botón guardar con confirmación Toast


