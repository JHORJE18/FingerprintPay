# FingerprintPay
Proyecto para probar la integración con la lectura de huellas

Para este proyecto se ha usado como ejemplo una mini aplicación donde se muestran productos y se pueden comprar mediante la Huella.

# Metodos Interesantes
Aqui listo los metodos que para mí son más interesantes de conocer...

## Carga Imagenes con Glide
Teniendo como variables:
- activity
  - Actividad actual, se puede cambiar por un contexto
- link
  - String o Uri con el enlace de la imagen a cargar
- postImg
  - ImageView de la imagen a cargar
```java
private void cargarImagen(String link, ImageView postImg) {
    //Si la iamgen esta vacia, no carges nada
    if (!link.equals("")){

        //Carga Imagen
        Glide.with(this.activity)
        .load(link)
        .into(postImg); 
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
