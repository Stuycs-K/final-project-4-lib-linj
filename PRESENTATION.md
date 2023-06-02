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
(-333)
\bmod{26} = 21
$$

$$
\mathrm{(21)}\mathit{x}
\equiv
\mathrm{1} 
\pmod{26}
$$

$$ x =5
$$

$$
K^{-1}=-333^{-1}
\times
 \begin{pmatrix}
    {1} & {17} \\
    {20} & {7} 
 \end{pmatrix} 
 ^{-1} =
 (5) \times
\begin{pmatrix}
    {-7} & {17} \\
    {20} & {-1}
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
To bruteforce an encrypted message one would have to try all possible key combinations. For a 2 by 2 matrix this would be from AAAA to ZZZZ, a total of 
$26^4$
combinations. Of those 
$26^4$
combinations roughly a third of them can be used as a key to encrypt and decrypt.  

The encrypting matrix for a key only exists if
 - The matrix has an inverse which is true if and only if the determinant is not zero.
 - There exists a value $\mathit {x}$ such that 
$\mathit{ax}\equiv 1\pmod{26}$
where 
$\mathit{a}$
is the determinant of the key matrix. In other words, the determinant must not have any common factors with the modular base, in this case 26 for 26 letters, besides 1. 
   - The determinant must not be a multiple of 2 or 13 in this case.  

The key KEYS would not work since the determinant is 80 which is a multiple of 2.

$$
\begin{pmatrix}
10&4 \\
25&18
\end{pmatrix}
$$

The key ACAT would also not work since its determinant is 0 which means it has no inverse.

$$
\begin{pmatrix}
0&2 \\ 
0&19
\end{pmatrix}
$$

After decrypting with all the possible keys, the best possible solutions can be found by either comparing it to a wordlist or using frequency analysis. 

A wordlist approach, which we used, works if you know the text contains these words. However, different decryption matrices can generate the same word and so a sufficient wordlist is one that contains at least two words that the original text contains. 

A frequency analysis approach also works but fails on shorter text lengths or specially customized messages.

Bruteforcing a 3 by 3 matrix or a higher dimension matrix becomes a lot more time consuming. $26^4=456,976$ but $26^9=5,429,503,678,976$ and $26^{16}$ has 23 significant figures. Instead of bruteforcing these, a known-plaintext attack would be faster. 

 ### Known-Plaintext Attack
 The known-plaintext attack is a vulnerability that the Hill Cipher is susceptible to. Essentially, it means that if we *know* the *plaintext* of a certain part of our ciphertext (known as the *crib*), we are then able to find the key matrix that was used to encrypt the message and, therefore, how to decrypt it. The Hill Cipher is vulnerable to the attack because the matrix operations that it uses to encode and decode information is **linear**. In other words, the operations it uses are relatively easy to break down and apply general rules to. 
 
 When a message is encrypted using an *N* x *N* key matrix, it means that it may be possible to solve for the key if we have *N* pairs of  unique plaintext-ciphertext blocks containing *N* elements. 
 
 We can take our plaintext letters and turn them into an *N* x *N* matrix, which we can call **P**.
 We do the same with our ciphertext letters and call that **C**.
 And we have our key matrix, which we can call **K**.
 
 It tracks, then, that under the rules of the Hill Cipher:
 $$PK = C \pmod{26}$$
 If we take our plaintext and multiply it by the key matrix, we end up with our ciphertext.
And since we know what makes up **P** and **C**, we can solve for **K** using some algebra, so:
$$K = P^{-1}C \pmod{26}$$
So it makes sense that if we take the inverse of the crib plaintext matrix and multiply it by the crib ciphertext matrix, we can get the key matrix.
The produced key matrix does not follow the same order as our plaintext and ciphertext matrices, so it is necessary to transpose the key matrix before converting it back to text.

As an example, let's take the ciphertext `BZGF TTXM, DRME`. Let's say we know for a fact that this is a letter to someone, and therefore it's safe to assume the plaintext starts with `DEAR`. It makes sense that the message is encrypted using a **2** x **2** key matrix, because of the size of the message. So,

$$
P = \begin{pmatrix}
D & E\\ 
A & R
\end{pmatrix} \pmod{26}= \begin{pmatrix}
3 & 4\\ 
0 & 17
\end{pmatrix} \pmod{26}
$$

$$
C = \begin{pmatrix}
B & Z\\ 
G & F
\end{pmatrix} \pmod{26} = \begin{pmatrix}
1 & 25\\ 
6 & 5
\end{pmatrix} \pmod{26}
$$

$$
\begin{pmatrix}
3 & 4\\ 
0 & 17
\end{pmatrix}
K = \begin{pmatrix}
1 & 25\\ 
6 & 5
\end{pmatrix} \pmod{26}
$$

Because we are looking for the inverse of **P** in modulo 26, we are looking for the matrix modular inverse of **P** modulo 26. 

$$
P^{-1} = (\textup{det}(P))^{-1} \cdot \textup{adj}(P) \pmod{26} = (51)^{-1} \cdot \begin{pmatrix}
17 & -4\\ 
0 & 3
\end{pmatrix} \pmod{26} \\
= 25 \cdot \begin{pmatrix}
17 & -4\\ 
0 & 3
\end{pmatrix} \pmod{26} 
$$

$$
P^{-1} = \begin{pmatrix}
425 & -100\\ 
0 & 75
\end{pmatrix} \pmod{26} = \begin{pmatrix}
9 & 4\\ 
0 & 23
\end{pmatrix} \pmod{26}
$$

$$
K = \begin{pmatrix}
9 & 4\\ 
0 & 23
\end{pmatrix}\begin{pmatrix}
1 & 25\\ 
6 & 5
\end{pmatrix} \pmod{26}
= \begin{pmatrix}
33 & 245\\ 
138 & 115
\end{pmatrix} \pmod{26}
= \begin{pmatrix}
7 & 11\\ 
8 & 11
\end{pmatrix} \pmod{26} 
$$

$$
K' = \textup{transpose}(K) = \begin{pmatrix}
7 & 8\\ 
11 & 11
\end{pmatrix} \pmod{26} = \begin{pmatrix}
H & I\\ 
L & L
\end{pmatrix}
$$

In the end, we were able to break the code and get our key as `HILL`. Upon decoding the entire message with `HILL`, we get the full message is `DEAR JOHN, HEYA`.

With that said, there are still a few caveats to this method of known-plaintext attack:
- The crib plaintext matrix *MUST* be invertible.
- The determinant of the crib plaintext matrix *MUST* be coprime with 26.

Therefore, it is better to have as many plaintext-ciphertext pairs as you can to avoid any potential hiccups. Likewise, it is better to have a longer key matrix so more plaintext-ciphertext pairs are required to perform a known-plaintext attack.

It is worth noting that this same method of cracking the key matrix may also be done with a large enough ciphertext in a ciphertext-only attack, where the attacker can use strategies such as frequency analysis to guess-and-check for some plaintext-ciphertext pairs before applying the matrix inversion method.

But in general, because of the glaring vulnerabilities of the Hill Cipher, it is better to use it as **a part** of a larger cryptographic algorithm, rather than as a standalone cipher.
