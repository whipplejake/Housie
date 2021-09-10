# Housie
To run: 
1. Navigate to src/main/java/sie
1. compile: `javac *.java`
1. run: `java -cp .. sie.HousieDriver`

Note: Java 11 and Maven 3.6.3 used

# Performance and Space Complexity note:
Within Ticket.java there is a comment that talks about the dilema surrounding actually building the entire ticket. For now, the implementation builds out the 2D array for debugging and printing purposes, but for an omptimal space solution the ticket member variable should be removed because it is not necessary.
