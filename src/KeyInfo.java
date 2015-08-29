import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Enumeration;

/**
 * Created by sneethiraj on 8/29/15.
 */
public class KeyInfo {

    public static final String DEFAULT_KEYSTORE_PASSWORD = "none" ;
    public static final String DEFAULT_KEYSTORE_TYPE = "JCEKS" ;

    public static void usage() {
        usage(null) ;
    }

     public static void usage(String usageStr) {
        if (usageStr != null) {
            System.err.println(usageStr) ;
        }
        System.err.println("USAGE: java " + KeyInfo.class.getName() + " -keystore keystoreFilePath [-storepasswd keystorePassword] [-storetype keystoreType] [-keypasswd keypassword]") ;
        System.err.println("If keystorePassword is not provided, it will use '" + DEFAULT_KEYSTORE_PASSWORD + "' as keystore password.") ;
        System.err.println("If keystoreType is not provided, it will use '" + DEFAULT_KEYSTORE_TYPE + "' as keystore type.") ;
        System.err.println("If keyPassword is not provided, the keystorePassword will be used as keyPassword") ;
    }

    public static void main(String[] args) throws Throwable {

        String keystoreFileName = null ;
        String keystorePassword = DEFAULT_KEYSTORE_PASSWORD ;
        String keystoreType = DEFAULT_KEYSTORE_TYPE ;
        String keyPassword =  null ;

        if (args.length == 0) {
            usage() ;
            System.exit(1);
        }

        for(int i = 0 ; i < args.length ; i++) {
            if (args[i].startsWith("-")) {
                String param =  args[i] ;
                String val = null ;
                if (i < args.length) {
                        i++ ;
                        val = args[i] ;
                    if (param.equals("-keystore")) {
                        keystoreFileName = val ;
                    }
                    else if (param.equals("-storepassword")) {
                        keystorePassword = val ;
                    }
                    else if (param.equals("-keypassword")) {
                        keyPassword = val ;
                    }
                    else if (param.equals("-storetype")) {
                        keystoreType = val ;
                    }
                }
                else {
                    System.out.println("Unknown option: [" + param + "]") ;
                    usage() ;
                    System.exit(1) ;
                }
            }
            else {
                usage("Invalid option : " + args[i]) ;
                System.exit(1) ;
            }
        }

        File ksFile = new File(keystoreFileName) ;
        if (! ksFile.exists()) {
            System.err.println("KeyStore file " + ksFile.getAbsolutePath() + "] does not exist. Exiting ..." ) ;
            System.exit(1) ;
        }

        KeyStore ks  = KeyStore.getInstance(keystoreType) ;

        InputStream in  = new FileInputStream(new File(keystoreFileName)) ;

        char[] password = (keystorePassword == null ? null : keystorePassword.toCharArray()) ;

        ks.load(in, password) ;

        if (keyPassword == null) {
            keyPassword = keystorePassword ;
        }

        for( Enumeration<String> aliasList = ks.aliases() ; aliasList.hasMoreElements() ; ) {
            String aliasName = aliasList.nextElement() ;
            if (ks.isKeyEntry(aliasName)) {
                Key key = ks.getKey(aliasName, keyPassword.toCharArray()) ;
                System.out.println("KEY: alias: [" + aliasName + "], Algo: [" + key.getAlgorithm() + "]") ;
             }
        }

     }


}
