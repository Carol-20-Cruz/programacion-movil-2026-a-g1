# FitTrack U 

Aplicación móvil de gestión de rutinas de entrenamiento desarrollada en Android con Kotlin.

---

## Descripción

FitTrack U permite a los usuarios organizar y visualizar sus rutinas de ejercicio semanales, tomar fotos de perfil y obtener su ubicación actual. Desarrollada como proyecto del curso de Programación Móvil — **Semanas 11 y 12**.

---

## Semana 11: UX/UI en pantallas avanzadas

### Problema inicial
- El botón + Nueva Rutina no hacía nada
- La app no tenía módulo configurado en Android Studio (error: Module not specified)
- No existía la pantalla de formulario para agregar rutinas

### Soluciones aplicadas

#### 1. Error de módulo en Android Studio
- Se identificó que Gradle no había cargado el módulo app
- Se ejecutó File → Sync Project with Gradle Files
- Se seleccionó el módulo app en Edit Configuration

#### 2. Pantalla Nueva Rutina
- Se creó NuevaRutinaActivity.kt desde cero
- Se diseñó activity_nueva_rutina.xml con:
  - Campo de texto para el nombre de la rutina
  - Spinner con los 7 días de la semana
  - Campo numérico para número de ejercicios
  - Botón Guardar Rutina
  - Mensaje de error visible cuando los campos están vacíos

#### 3. Validaciones en el formulario
- Si el nombre está vacío → muestra error en el campo
- Si el número de ejercicios está vacío → muestra error en el campo
- Al guardar correctamente → Toast de confirmación y regresa a la pantalla anterior

#### 4. Mejoras UX/UI en la pantalla principal

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
| Fondo principal | #1A1A2E | Todas las pantallas |
| Fondo card | #16213E | Tarjetas de rutinas |
| Fondo input | #0F3460 | Campos de texto |
| Color acento | #E94560 | Botones, títulos, chip activo |
| Texto principal | #FFFFFF | Nombres de rutinas |
| Texto secundario | #AAAAAA | Detalles, subtítulos |
| Tipografía título | 28sp bold | Encabezados de pantalla |
| Tipografía cuerpo | 16sp bold | Nombre de rutina en card |
| Tipografía detalle | 13sp | Ejercicios y día |
| Espaciado base | 24dp | Márgenes generales |

#### 6. Filtros funcionales
- Al tocar Lunes → solo se muestran rutinas del lunes
- Al tocar Miércoles → solo se muestran rutinas del miércoles
- Al tocar Viernes → solo se muestran rutinas del viernes
- Al tocar Todos → se muestran todas las rutinas

---

## Semana 12: Plugins y componentes avanzados

### Funcionalidades integradas

#### 1. Cámara — Foto de Perfil
- Caso de uso: el usuario puede tomar una foto de perfil desde la pantalla principal
- Flujo: toca el botón → se solicita permiso → se abre la cámara → se muestra la foto tomada
- Estados implementados:
  - Loading: "Abriendo cámara..."
  - Éxito: "Foto tomada correctamente" (texto verde)
  - Error: "No se tomó ninguna foto" (texto gris)
  - Permiso denegado: "Permiso de cámara denegado. Ve a Configuración para habilitarlo." (texto rojo)

#### 2. GPS — Mi Ubicación
- Caso de uso: el usuario puede obtener sus coordenadas actuales para encontrar gimnasios cercanos
- Flujo: toca el botón → se solicita permiso → se obtienen coordenadas → se muestran en pantalla
- Estados implementados:
  - Loading: "Obteniendo ubicación..."
  - Éxito: muestra Latitud y Longitud (texto verde)
  - Error: "No se pudo obtener la ubicación. Activa el GPS." (texto rojo)
  - Permiso denegado: "Permiso de ubicación denegado. Ve a Configuración para habilitarlo." (texto rojo)

### Permisos agregados al AndroidManifest.xml
- android.permission.CAMERA
- android.permission.READ_EXTERNAL_STORAGE
- android.permission.READ_MEDIA_IMAGES
- android.permission.ACCESS_FINE_LOCATION
- android.permission.ACCESS_COARSE_LOCATION

### Dependencias agregadas
- com.google.android.gms:play-services-maps:18.2.0
- com.google.android.gms:play-services-location:21.2.0

---

## Checklist Semana 11

| Criterio | Cumplido |
|----------|----------|
| Botón principal claro por pantalla | ✅ |
| Tipografía consistente | ✅ |
| Espaciados uniformes | ✅ |
| Estado vacío definido | ✅ |
| Estado error definido | ✅ |
| Estado éxito definido | ✅ |
| Patrón avanzado: filtros | ✅ |
| Patrón avanzado: formulario con validación | ✅ |
| Consistencia visual entre pantallas | ✅ |
| Feedback inmediato al usuario | ✅ |

---

## Checklist Semana 12

| Criterio | Cumplido |
|----------|----------|
| Permiso solicitado en momento correcto | ✅ |
| Manejo de permiso denegado | ✅ |
| Estado loading | ✅ |
| Estado éxito | ✅ |
| Estado error | ✅ |
| Fallback cuando no hay GPS/cámara | ✅ |
| Cámara funcional | ✅ |
| GPS funcional | ✅ |

