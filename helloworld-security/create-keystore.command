keytool -v -genkey -alias wildfly.ssl -keyalg RSA -keysize 2048 -sigalg SHA1withRSA -keystore wildfly.ssl.keystore

keytool -list -v -keystore wildfly.ssl.keystore

