a. Time Complexity of encode(), decode() and equal() methods in terms of Big-O
notation.
encode(): O(n)
decode(): O(n^2)
equal(): O(n)

b. What are the advantages and disadvantages of RLE? What are the applications of
RLE?
Advantages of RLE is it's ability to compress long repeating values.  A disadvantage with the RLE is that it's time complexity is O(n) it could take a very long time to run depending on the amount of objects to process.
This class would be helpful for storing long repeating data, like images.

c. Amount of time you spent on implementing the assignment? Challenges you faced
while implementing the assignment? How did you overcome these challenges?
It took me around 16 hours to complete this assignment.  Some challenges I faced is detecting double-digit numbers when decoding compressed bits, and knowing when to throw errors.
I overcame my challenges by simply carefully reassessing my code using the debugger tool.
