# Hill Cipher
###### By Brian Li and Jason Lin

## What is Hill Cipher?
Simply put, a Hill Cipher is a polyalphabetic cipher that uses matrices as our encryption and decryption keys. 
Each letter is represented as a value modulo 26 and placed into a matrix.
- In other words, an A would correspond to the value 0, B to 1, ..., Z to 25.
- A message of HEY would be represented as the matrix 
$$
 \begin{pmatrix}
     {7} \\ 
     {4} \\
     {24}
 \end{pmatrix}
$$


 - The key BRUH would be represented as the matrix
 $$
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
\end{pmatrix}
=
\begin{pmatrix}
    k_{11}*m_{11} + k_{12}*m_{21} \\
    k_{21}*m_{11} + k_{22}*m_{21}
\end{pmatrix}
 $$

 - Using the key "BRUH" and the message "CATS" the message is encrypted as CONM

 $$
 \begin{pmatrix}
    {B} & {R} \\
    {U} & {H} \\
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {C} \\
    {A}
 \end{pmatrix}
 =
 \begin{pmatrix}
     {1} & {17}\\ 
     {20} & {7}
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {2}\\
    {0}
\end{pmatrix}
= 
\begin{pmatrix}
    {2} \\
    {40}
\end{pmatrix}
\ mod\ 26 =
\begin{pmatrix}
    {2} \\
    {14}
\end{pmatrix}
= 
\begin{pmatrix}
    {C} \\
    {O}
\end{pmatrix}
 $$


 $$
 \begin{pmatrix}
    {B} & {R} \\
    {U} & {H} \\
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {T} \\
    {S}
 \end{pmatrix}
 =
 \begin{pmatrix}
     {1} & {17}\\ 
     {20} & {7}
 \end{pmatrix}
 \times
 \begin{pmatrix}
    {19}\\
    {18}
\end{pmatrix}
= 
\begin{pmatrix}
    {325} \\
    {506}
\end{pmatrix}
\ mod\ 26 =
\begin{pmatrix}
    {13} \\
    {12}
\end{pmatrix}
= 
\begin{pmatrix}
    {N} \\
    {M}
\end{pmatrix}
 $$

 ### Decryption
