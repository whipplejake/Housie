# Housie
To run: 
1. Navigate to src/main/java/sie
1. compile: `javac *.java`
1. run: `java -cp .. sie.HousieDriver`

# Performance and Space Complexity note:
Within Ticket.java there is a comment that talks about the dilema surrounding actually building the entire ticket. For now, the implementation builds out the array for debugging and printing purposes, but for an omptimal space solution the ticket member variable should be removed.