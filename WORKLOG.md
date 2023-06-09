# Work Log

## Brian Li

### 05/19/2023

- Developed a basic program for a Matrix object to be used for the Hill Cipher.
  - Includes an identity method, matrix multiplication method, and necessary matrix manipulation methods as a prerequisite for the Hill Cipher.

### 05/22/2023

- Added cofactor, GCD methods to the Matrix object for future decodes.
- Found additional resources for conducting a [Known Plaintext Attack - WCSU](https://sites.wcsu.edu/mbxml/html/sample_hill_analysis_kpt.html).

### 05/23/2023

- Added numbers, punctuation, etc. (basically any non-letter character) functionality to encoding and decoding messages.
- Added a bruteforcer for all 2x2 key matrices.
  - Relatively optimized, checks if not coprime with 26 AND invertible.
- General bugfixing.

### 05/24/2023

- Started working on known-plaintext attack method of decryption.
  - Utilizes matrix inversion methodology.

### 05/25/2023

- Continued working on known-plaintext attack method.
- Developed method to retrieve a modular inverse matrix of an existing matrix

### 05/26/2023
 - Fixed known-plaintext attack.

### 05/29/2023
- General code cleanup, removed unnecessary files, print statements, etc.
- Developed makefile.
- Updated README.md

### 05/30/2023
- Developed TryHackMe room for homework.

### 05/31/2023
- Finished writeup for known-plaintext attack in PRESENTATION.md
- Bugfixes in code

## Jason Lin

### 05/18/2023

- [Hill Cipher Wikipedia](https://en.wikipedia.org/wiki/Hill_cipher)  
- [Known Plaintext Attack - NKU](https://www.nku.edu/~christensen/092mat483%20known%20plaintext%20attack%20of%20Hill%20cipher.pdf)
- Added a file reading method.

### 05/19/2023

- Made a basic skeleton of what the encode function should do.

### 05/22/2023

- Finished encode method in Hill to properly pad the string if not long enough.
- Added a getAlpha method to Matrix to convert the matrix back to text.
- Researched how decryption worked since it was not just the inverse of a matrix.
    - [Modular Arithmetic regarding Modular Multiplicative Inverse Wikipedia](https://en.wikipedia.org/wiki/Modular_multiplicative_inverse#Modular_arithmetic)  

### 05/23/2023
 - Found more information regarding finding a modular multiplicative inverse.
    - [Extended Euclidean Algorithm](https://www.extendedeuclideanalgorithm.com/xea.php)  
 - Working decrypt by applying the extended euclidean algorithm to find the appropriate matrix used for decrypting.
 - Fixed decoding not working in certain cases because of sign issues.
    - [Dcode Matrix Inverse](https://www.dcode.fr/matrix-inverse)  

### 05/24/2023
 - Made decode and encode case insensitive.
 - Tested on larger text and up to 5by5 matrix. Fixed bugs that resulted such as off by one due to multiplying doubles.

### 05/25/2023
 - Made bruteforce return best possibility only through wordlist.
 - General debugging.

### 05/26/2023
 - Started working on PRESENTATION.md

### 05/27/2023
 - Made bruteforce return any number of top possibilities.

### 05/30/2023
 - Worked on decrypt and bruetforce section of presentation.

### 05/31/2023
 - Bugfixing
 - Check that THM problems are solveable, add wordlist for THM.

### 06/01/2023
 - Add example files

## Resources
[Hill Cipher Wikipedia](https://en.wikipedia.org/wiki/Hill_cipher)  
[Known Plaintext Attack - NKU](https://www.nku.edu/~christensen/092mat483%20known%20plaintext%20attack%20of%20Hill%20cipher.pdf)  
[Cofactor Expansions](https://textbooks.math.gatech.edu/ila/determinants-cofactors.html)  
[Known Plaintext Attack - WCSU](https://sites.wcsu.edu/mbxml/html/sample_hill_analysis_kpt.html)  
[Modular Arithmetic regarding Modular Multiplicative Inverse Wikipedia](https://en.wikipedia.org/wiki/Modular_multiplicative_inverse#Modular_arithmetic)  
[Extended Euclidean Algorithm](https://www.extendedeuclideanalgorithm.com/xea.php)  
