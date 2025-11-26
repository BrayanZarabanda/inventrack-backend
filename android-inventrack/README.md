# Android Inventrack (Skeleton)

This folder contains a generated Android project skeleton for the Inventrack mobile app (Kotlin + Jetpack Compose) following MVVM + Clean Architecture.

What is included:
- `core/` utilities: `AuthInterceptor`, `DataStoreManager`, `Constants`, `Navigation`, `Resource` and Hilt module.
- `data/` remote DTOs, Retrofit module, Room `AppDatabase` and `Product` entity/DAO.
- `domain/` models, repository interfaces and basic use-cases.
- `ui/` Compose screens and ViewModels for authentication, products, warehouses, stock and scanner.

How to use:
1. Open this folder (`android-inventrack`) in Android Studio.
2. Update `Constants.BASE_URL` in `core/Constants.kt` with your Render backend URL or add a `BuildConfig` field.
3. Sync Gradle and run the app.

Notes:
- This is a skeleton with placeholders for many network calls and business logic. You must wire the missing implementations (some use-cases, repository methods and detailed UI flows).
- For production, secure tokens using encrypted storage and implement refresh-token flow.

Construir, desplegar y probar (instrucciones rápidas)

1) Preparar Android SDK

- Asegúrate de tener instalado Android Studio y un SDK compatible (SDK 34 recomendado).
- Crea o ajusta `local.properties` con tu ruta al SDK, p. ej.:

```
sdk.dir=C:\Users\YourUser\AppData\Local\Android\Sdk
```

2) Abrir en Android Studio

- Abre `android-inventrack` en Android Studio.
- Deja que Gradle sincronice dependencias.

3) Ejecutar en emulador o dispositivo

- Desde Android Studio: selecciona un emulador o dispositivo y pulsa Run.
- Desde terminal (PowerShell) en la carpeta `android-inventrack`:

```powershell
./gradlew assembleDebug
./gradlew installDebug
```

4) Probar visualmente

- Abre la app en el emulador y prueba:
	- Login / Register (usa la API del backend en Render).
	- Ver Productos (Products list), abrir detalle.
	- Crear/editar producto.
	- Probar scanner desde `Scan Barcode` (necesitarás dar permiso de cámara).
	- Crear almacén y registrar movimientos de stock.

5) Conexión con Render (backend)

- Ajusta `Constants.BASE_URL` en `core/Constants.kt` a la URL pública de tu servicio Render, por ejemplo:

```
const val BASE_URL = "https://inventrack-backend.onrender.com/api/"
```

- Asegúrate que tu FastAPI esté configurado con CORS para aceptar peticiones desde la app.
- El interceptor de refresh-token llama a `POST /auth/refresh` con JSON `{ "refresh_token": "..." }` y espera `{ "access_token": "...", "refresh_token": "..." }` en la respuesta. Ajusta si tu API usa otro contrato.

Limitaciones y siguientes pasos

- No puedo ejecutar el emulador aquí: debes compilar y probar localmente en Android Studio.
- Si quieres, puedo generar pruebas unitarias y UI tests o preparar un APK firmado para subir a Play Store/TestFlight.

