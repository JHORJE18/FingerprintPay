# FingerprintPay
Proyecto para probar la integración con la lectura de huellas

Para este proyecto se ha usado como ejemplo una mini aplicación donde se muestran productos y se pueden comprar mediante la Huella.

# Referencias
Se ha usado la libreria de <a href="https://github.com/pro100svitlo/FingerprintAuthHelper">**FingerprintAuthHelper**</a> para la integración de Lectura de Huellas 
<pre>
  dependencies {
        compile 'com.github.pro100svitlo:fingerprintAuthHelper:1.3.0'
    }
</pre>

Se ha usado la libreria de <a href="https://github.com/bumptech/glide">**Glide**</a> para la carga de iamgenes
<pre>
repositories {
  mavenCentral()
  google()
}

dependencies {
  implementation 'com.github.bumptech.glide:glide:4.6.1'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'
}
</pre>