"""
chisquareCalcs_s19.py  - CSC148 Poisson pmf & Chi-Square Ei calculations
Variable names use terminolgy related to the Chi-Square distribution fit
"""
import math

numberOfClasses = int(input("Number of Classes: "))
OiFrequencyMean = float(input("Mean of the class frequencies: "))
sumOfxiFrequencies = int(input("sum of the class frequencies: "))

print("Chi-Square class values calculations tool \n")
# The expected Poisson pmf pi values for user-input parameters
print("The pmf  values for pi = prob(X=xi), xi=0,1,2, ..., assuming NON-combined classes")
for xi in range (numberOfClasses):
    print("p"+str(xi), " is: ",(math.exp(-OiFrequencyMean)*OiFrequencyMean**xi )/math.factorial(xi) ) 
# The expected Ei values
print(" \nThe Ei  values for classes E1, E2, etc.")
for xi in range (numberOfClasses):
    print("E"+str(xi), " is: ",sumOfxiFrequencies*(math.exp(-OiFrequencyMean)*OiFrequencyMean**xi )/math.factorial(xi)) 

