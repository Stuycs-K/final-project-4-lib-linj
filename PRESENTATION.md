# Hill Cipher
###### By Brian Li and Jason Lin

## What is Hill Cipher?
Simply put, a Hill Cipher is a polyalphabetic cipher that uses matrices as our encryption and decryption keys. 
Each letter is represented as a value modulo 26 and placed into a matrix.
- In other words, an A would correspond to the value 0, B to 1, ..., Z to 25.
- A message of HEY would be represented as the matrix 


$$
\begin{pmatrix}
    {H}\\
    {E}\\
    {Y}\\
\end{pmatrix}
\rightarrow
\begin{pmatrix}
     {7} \\ 
     {4} \\
     {24}
\end{pmatrix}
$$


 - The key BRUH would be represented as the matrix


$$
\begin{pmatrix}
    {B} & {R} \\
    {U} & {H} \\
\end{pmatrix}
\rightarrow
\begin{pmatrix}
     {1} & {17} \\ 
     {20} & {7}
\end{pmatrix}
$$


### Encryption
To encrypt a message, the message is seperated into blocks of *n* characters and multiplied by an invertible *n* by *n* matrix against modulus 26. In this case, an invertible matrix against modulus 26 is one such that the greatest common denominator between the determinant of the matrix and 26 is 1. In other words, the determinant and 26 are coprime and share no other common factors other than 1. In the case of modulus 26, the determinant must not be a multiple of 2 or 13.
 - The key matrix is denoted by *k* and the message *m* and the product of the two is the resulting encrypted matrix.


$$
\begin{pmatrix}
     k_{11} & k_{12}\\ 
     k_{21} & k_{22}
\end{pmatrix}
\times
\begin{pmatrix}
    m_{11}\\
    m_{21}
\end{pmatrix}=
\begin{pmatrix}
    k_{11}*m_{11} + k_{12}*m_{21} \\
    k_{21}*m_{11} + k_{22}*m_{21}
\end{pmatrix}
 $$


 - Using the key "BRUH" and the message "CATS" the message is encrypted as CONM


$$
 \mathit{CATS}
 \rightarrow
 \begin{pmatrix}
    {C}\\
    {A}
 \end{pmatrix}
 \mathit{,}
 \begin{pmatrix}
    {T}\\
    {S}
 \end{pmatrix}
 \rightarrow
 \begin{pmatrix}
    {2}\\
    {0}
 \end{pmatrix}
 \mathit{,}
 \begin{pmatrix}
    {19}\\
    {18}
 \end{pmatrix}
$$


$$
 \begin{pmatrix}
     {1} & {17}\\ 
     {20} & {7}
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {2}\\
    {0}
\end{pmatrix}= 
\begin{pmatrix}
    {2} \\
    {40}
\end{pmatrix}
\equiv
\begin{pmatrix}
    {2} \\
    {14}
\end{pmatrix}
\bmod{26}\rightarrow
\begin{pmatrix}
    {C} \\
    {O}
\end{pmatrix}
$$


$$
 \begin{pmatrix}
     {1} & {17}\\ 
     {20} & {7}
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {19}\\
    {18}
\end{pmatrix}= 
\begin{pmatrix}
    {325} \\
    {506}
\end{pmatrix}
\equiv
\begin{pmatrix}
    {13} \\
    {12}
\end{pmatrix}
\bmod{26}
\rightarrow
\begin{pmatrix}
    {N} \\
    {M}
\end{pmatrix}
$$

 ### Decryption
To decrypt a message, the ciphertext is turned back into a matrix and multiplied by the modular inverse matrix of the key.

 - If the key is BRUH, the modular inverse the key would be found by doing the following steps.

$$
K=
\begin{pmatrix}
{1} & {17} \\
{20} & {7}
\end{pmatrix}
$$

$$
\mathrm{DET(A)} = 7 - 340 = -333
$$

$$
\mathrm{(-333)}\mathit{x}
\equiv
\mathrm{1} 
\pmod{26}
$$

$$ x =-5
$$

$$
K^{-1}=-333^{-1}
\times
 \begin{pmatrix}
    {1} & {17} \\
    {20} & {7} 
 \end{pmatrix} 
 ^{-1} =
 (-5) \times
\begin{pmatrix}
    {7} & {-17} \\
    {-20} & {1}
\end{pmatrix} =
\begin{pmatrix} 
    {17} & {7} \\
    {22} & {21}
\end{pmatrix}
\pmod{26}
$$
 
 - Now that we have the key we can decrypt the message CONM back into CATS.
$$
 \mathit{CONM}
 \rightarrow
 \begin{pmatrix}
    {C}\\
    {O}
 \end{pmatrix}
 \mathit{,}
 \begin{pmatrix}
    {N}\\
    {M}
 \end{pmatrix}
 \rightarrow
 \begin{pmatrix}
    {2}\\
    {14}
 \end{pmatrix}
 \mathit{,}
 \begin{pmatrix}
    {13}\\
    {12}
 \end{pmatrix}
$$

$$
 \begin{pmatrix}
     {17} & {7}\\ 
     {22} & {21}
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {2}\\
    {14}
\end{pmatrix}= 
\begin{pmatrix}
    {132} \\
    {338}
\end{pmatrix}
\equiv
\begin{pmatrix}
    {2} \\
    {0}
\end{pmatrix}
\pmod{26}
\rightarrow
\begin{pmatrix}
    {C} \\
    {A}
\end{pmatrix}
$$

$$
 \begin{pmatrix}
     {17} & {7}\\ 
     {22} & {21}
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {13}\\
    {12}
\end{pmatrix}= 
\begin{pmatrix}
    {305} \\
    {538}
\end{pmatrix}
\equiv
\begin{pmatrix}
    {19} \\
    {18}
\end{pmatrix}
\pmod{26}
\rightarrow
\begin{pmatrix}
    {T} \\
    {S}
\end{pmatrix}
$$

 ### Bruteforce
To bruteforce an encrypted message one would have to try all possible key combinations. For a 2 by 2 matrix 
$\bigl(\begin{smallmatrix}
a&b \\ c&d
\end{smallmatrix} \bigr)$
this would be from AAAA 

$\bigl(\begin{smallmatrix}
0&0 \\ 0&0
\end{smallmatrix} \bigr)$
to ZZZZ
$\bigl(\begin{smallmatrix}
25&25 \\ 25&25
\end{smallmatrix} \bigr)$
, a total of $ 26^4 $ combinations. Of those $ 26^4 $ combinations roughly a third of them can be used as a key to encrypt and decrypt.  

The encrypting matrix for a key only exists if
 - The matrix has an inverse which is true if and only if the determinant is not zero.
 - There exists a value $\mathit {x}$ such that 
$\mathit {ax} 
\equiv 1
    \pmod{26}
$
where $\mathit {a}$ is the determinant of the key matrix. In other words, the determinant must not have any common factors with the modular base, in this case 26 for 26 letters, besides 1. 
   - The determinant must not be a multiple of 2 or 13 in this case.  

The key KEYS 
$\bigl(\begin{smallmatrix}
10&4 \\ 25&18
\end{smallmatrix} \bigr)$
would not work since the determinant is 80 which is a multiple of 2.

The key ACAT 
$\bigl(\begin{smallmatrix}
0&2 \\ 0&19
\end{smallmatrix} \bigr)$
would also not work since its determinant is 0 which means it has no inverse.

After decrypting with all the possible keys, the best possible solutions can be found by either comparing it to a wordlist or using frequency analysis. 

A wordlist approach, which we used, works if you know the text contains these words. However, different decryption matrixes can generate the same word and so a sufficient wordlist is one that contains at least two words that the original text contains. 

A frequency analysis approach also works but fails on shorter text lengths or specially customized messages.

Bruteforcing a 
$\bigl(\begin{smallmatrix}
a&b&c \\ d&e&f \\ g&h&i
\end{smallmatrix} \bigr)$
or a higher dimension matrix becomes a lot more time consuming. $26^4=456,976$ but $26^9=5,429,503,678,976$ and $26^{16}$ has 23 significant figures. Instead of bruteforcing these, a known-plaintext attack would be faster. 

 ### Known Plaintext Attack