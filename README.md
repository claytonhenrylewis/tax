# Tax

## Overview

This application computes sales tax and total cost for lists of items.

## Compilation Instructions

To compile the program automatically, run the following command:

```
./compile.sh
```

To compile the program manually, run the following command in the src directory:

```
javac -cp .:../bin/stdlib.jar:../bin/algs4.jar Tax.java Item.java ItemList.java Exemptions.java WordNet.java SAP.java BreadthFirstDirectedPaths.java
```

## Execution Instructions

To compile and run the program automatically, run one of the following commands:

```
./run-all.sh [sales-tax-rate] [import-tax-rate]
./run [sales-tax-rate] [import-tax-rate] [input-file] [input-file] ...
```

To run the program manually, run the following command in the src directory:
```
java -cp .:../bin/stdlib.jar:../bin/algs4.jar Tax [sales-tax-rate] [import-tax-rate] [input-file] [input-file] ...
```

To run the program with sample input (sales tax rate = 0.10, import tax rate = 0.05) and for all files in the *input* directory, run the following command:

```
./run-sample.sh
```

### Arguments

1. sales-tax-rate: The sales tax rate as a decimal number (i.e. 0.10 )
2. import-tax-rate: The import tax rate as a decimal number (i.e. 0.05 )
3. input-file: The name of the files to read. Files are assumed to be in the directory *input*. Only pass the file name. Any number of files are accepted. Executing the run-all script will read from all files in the directory *input*.

### Example execution

```
./run 0.10 0.05 input1.txt
```

## Input Instructions

### Shopping Lists

Input files should be .txt files in the *input* directory. Files should follow the following format:

```
<count> <item> at <price>
...
```

Sample input file:

```
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 imported box of chocolates at 11.25
```

### Exemptions

The user may also configure the items that are exempt from sales tax. Included is a file named *exemptions.txt*. The user may add any items to that list. Each item should occupy a new line, as follows:

```
<item>
<item>
```

## Output Information

Receipts are printed to stdout (generally the terminal) and to a file named *output.txt* in the directory *output*. Receipts are in the following format:

```
<count> <item>: <cost>
...
Sales Taxes: <total tax>
Total: <total cost>
```

Sample output:

```
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Tax: 6.70
Total: 74.68
```
