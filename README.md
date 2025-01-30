# BloomFilter
Work around the bloom filter

This project is used in the terminal. To do this, follow the instructions on the screen to first choose which filter to use, and then which inputs to add or test.

On programe strat you will see the following text : 
```
Welcome to BloomFilter !
to use the simple filter input : "simple" / "s"
```
Simple bloom filter with no memory of previous input
```    
to use the simple filter input with default input : "sd"
```
Run Simple bloom filter by adding ["one", "two", "three", "four"], then testing for the presence of ["three", "four", "five"]
```    
to use the evolved filter input : "evolved" / "e"
```
Evolved bloom filter with memory of previous colision on each index
```    
to use the evolved filter input with default input  : "ed"
```
Run Evolved bloom filter by adding ["one", "two", "three", "four"], then testing for the presence of ["three", "four", "five"]
```    
to exit input : "exit"
```
Stop program

Once a filter is selected you will see : 
```
Multiple action are possible !
    to add an entry to the array input : "add yourentry" / "a yourentry"
    to test for the string presence in the array input : "test yourtest" / "t yourtest"
    to go back input : "back" / "b"
    to exit input : "exit" / "e"
```
If you go back from there, that will reset your filter

