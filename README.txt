KeyInfo utility will list out all keys from a specified keystore file along with the key algorithm (which is not getting displayed using the standard java keytool command).

KeyInfo Usage:
    $ java KeyInfo -keystore keystoreFilePath [-storepasswd keystorePassword] [-storetype keystoreType] [-keypasswd keypassword]

    $ java KeyInfo <keystoreFilePath> [keystorePassword] [keystoreType]

        KeystorePath:       location of the keystore file
        KeystorePassword:   password for the keystore file (default:  "none")
        KeystoreType:       Type of the keystore (default:  "JCEKS")
        keypassword:        KeyPassword to get key information (default: keystorePassword)


Steps to build the KeyInfo utility:
===================================
1. Download the src/KeyInfo.java into a directory:

2. Run the following command to compile the Java Code:

    $ javac KeyInfo.java

Steps to run the KeyInfo utility:
=================================

1. Copy the keystore file to the folder where the KeyInfo utility is downloaded.

    $ ls -l cred.jceks
    -rwxr--r-- 1 me mygroup 1442 Aug 29 16:29 cred.jceks

2. Run the KeyInfo utility as shown below:

    $ java KeyInfo -keystore cred.jceks
    KEY: alias: [key01], Algo: [AES]
    KEY: alias: [key02], Algo: [AES]
    KEY: alias: [key03], Algo: [AES]

