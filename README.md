# Hill Cipher

## Project Description
We will develop a working encoder and decoder for the Hill Cipher, a cryptography method that utilizes matrix multiplication and linear algebra. We will also develop a bruteforcer that attempts every 2 by 2 matrix key and compares it with a wordlist to return the best possible key and decrypted message. A known-plaintext attack will also be developed where we utilize the weaknesses of the Hill Cipher to figure out the n by n matrix key.

## Directions
Clone the repository with 
```
git clone git@github.com:Stuycs-K/final-project-4-lib-linj.git
```

Compile all the necessary files with
```
javac *.java
```

```
java Hill encode [plaintextFile] [keyFile]
java Hill decode [ciphertextFile] [keyFile]
java Hill bruteforce [ciphertextFile] [wordlist]
java Hill known-plaintext [cribPlainFile] [cribCipherFile] [fullCipherFile] [keySize]
```


## Links
[Presentation](https://github.com/Stuycs-K/final-project-4-lib-linj/blob/main/PRESENTATION.md)  
[Homework]()
