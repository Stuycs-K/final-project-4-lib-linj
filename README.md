# Hill Cipher
## Team Bing Chilling -- Brian Li and Jason Lin

## Project Description
This is a working encoder and decoder for the Hill Cipher, a cryptography method that utilizes matrix multiplication and linear algebra. Also developed is a bruteforcer that attempts every 2 by 2 matrix key and compares it with a wordlist to return the best possible key and decrypted message. A known-plaintext attack is also developed where we utilize the weaknesses of the Hill Cipher to figure out and decode a given message with a n by n matrix key.

## Directions
Clone the repository with
```
git clone git@github.com:Stuycs-K/final-project-4-lib-linj.git
```

`cd` to the `code` directory to use the Hill Cipher methods.
```
cd code
```

Using the included makefile, you can use the developed methods via the following:
```
make encode ARGS="[plaintextFile] [keyFile]"
make decode ARGS="[ciphertextFile] [keyFile]"
make bruteforce ARGS="[ciphertextFile] [wordlistFile] [number of best attempts to print out]"
make known-plaintext ARGS="[cribPlaintextFile] [cribCiphertextFile] [fullCiphertextFile] [size of block]"
```

## Links
[Presentation](https://github.com/Stuycs-K/final-project-4-lib-linj/blob/main/PRESENTATION.md)  
[Homework](https://github.com/Stuycs-K/final-project-4-lib-linj/blob/main/HOMEWORK.md)
