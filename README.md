# FingerprintPay
Proyecto para probar la integración con la lectura de huellas

Para este proyecto se ha usado como ejemplo una mini aplicación donde se muestran productos y se pueden comprar mediante la Huella.

## Metodos Interesantes
Aqui listo los metodos que para mí son más interesantes de conocer...

### Receptor de respuestas del Lector de Huellas
Automáticamente el metodo `onFingerprintStatus` nos va a devolver la información de la respuesta recibida del lector de Huellas
```java
    @Override
    public void onFingerprintStatus(boolean authSuccessful, int errorType, CharSequence errorMess) {
        // authSuccessful - boolean que contienen el resultado de la autentificación
        // errorType - Si ha fallado la autentificación de la huella, contiene el código de error
        // errorMess - Si ha fallado la autentificación de la huella, contiene el mensaje de error con la posible razón del error

        if (authSuccessful) {
            //En caso de que la autentificación de la huella sea correcta ejecuta el código de aqui
      } else if (mFAH != null) {
            //La autentificacion de la huella ha sido incorrecta, debemos filtrar los errores para saber porque ha fallado
            switch (errorType) {
                case FahErrorType.General.LOCK_SCREEN_DISABLED:
                    //Error ocasionado porque el dispositivo no esta seguro al no tener la pantalla de bloqueo protegida
                    break;
                case FahErrorType.General.NO_FINGERPRINTS:
                    //Error ocasionado porque no hay Huellas registradas en el dispositivo
                    mFAH.showSecuritySettingsDialog();      //Ventana predefinida avisando de esto
                    break;
                case FahErrorType.Auth.AUTH_NOT_RECOGNIZED:
                    //Error ocasionado porque la Huella es incorrecta
                    break;
                case FahErrorType.Auth.AUTH_TO_MANY_TRIES:
                    //Error ocasionado por intentar introducir la huella incorrecta demasiadas veces
                    break;
            }
        }
    }

```


### Carga Imagenes con Glide
Teniendo como variables:
- `Activity activity` = Actividad actual, se puede cambiar por un contexto
- `String link` = String o Uri con el enlace de la imagen a cargar
- `ImageView postImg` = ImageView donde se colocara la imagen cargada por Glide
```java
private void cargarImagen(String link, ImageView postImg) {
    //Si la iamgen esta vacia, no carges nada
    if (!link.equals("")){

        //Carga Imagen
        Glide.with(this.activity)       //Contexto o Actividad
        .load(link)                     //Enlace del recurso a cargar
        .into(postImg);                 //ImageView donde se colocara la imagen
    }
}
```

# Referencias
Se ha usado la libreria de <a href="https://github.com/pro100svitlo/FingerprintAuthHelper">**FingerprintAuthHelper**</a> para la integración de Lectura de Huellas 
```java
    dependencies {
        compile 'com.github.pro100svitlo:fingerprintAuthHelper:1.3.0'
    }
```

Se ha usado la libreria de <a href="https://github.com/bumptech/glide">**Glide**</a> para la carga de iamgenes
```java
    dependencies {
        implementation 'com.github.bumptech.glide:glide:3.8.0'
    }
```
