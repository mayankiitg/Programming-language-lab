Qn1:

(i) Successor: found successor by appending one x in the list.
    
    predicates used- 
    * Successor Predicate:  successor(Number, Result).
                            Compute Successor and store it in Result.
    * Concatenation Predicate:  concat(L1, L2, Result).
                                Concatenate L1 and L2 and store output in Result.    

(ii) Plus : found by concatenating two lists. 

    predicates used- 
    * plus(Inp1, Inp2, Result): add two unary numbers and stores the output in Result. 
    * Concatenation Predicate:  concat(L1, L2, Result).
                                Concatenate L1 and L2 and store output in Result. 
                                
(iii) minus : Done Recursively removing 1st element from both numbers and subtracting remaining number 1 and number 2.

    predicates used- 
    * minus(L1, L2, Result) :   Subtract Number1 (L1), from Number2 (L2) and stores the output in Result. 

(iv) Multiply : Done by recursively adding number 1 to partial result till number2 times.

    predicates used- 
    multiply(Num1, Num2, Result):   Multiply Num1 and Num2 and stores the output in Result. 

(v) Divide :    Done by recursively subtracting Number2 from Number1 till Number1 becomes <= 0.

    predicates used- 
    * divide(Num1, Num2):   Checks divisibility of Number1 by Number2 and returns True/False.
    * nonZero(X):   Checks if X is greater than zero.
    * subtractForDivide(Num1, Num2, Result):    Subtract Num1 from Num2 and stores result in Result.
                                                It has no rules for 0-X. So will give false if we reach there.

Qn2: 

Part(a): Execute the steps in the list from left to right fashion and output final position and direction.

    predicates used- 
    * assign(X, X): Assigns the value of 1st argument to the 2nd.
    * isEqual(X, X): Check if the passed arguments have same values.
    * arith_add(X, Y): add the two arguments and store the value in first.
    * status(L, Pos, Ori): Takes as arguement the list of moves (L) and determines final position and orientation.
    * statusutil(L, Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori):
            Picks first instruction from L and taking in consideration initial configuration, finds out the next configuration.

Part(b): Given a initial and final position, determines the steps to be took in order to reach there.

    predicates used- 
    * move(Src, Dest, Result): Increments Src by 1 at each step untill you reach Dest, and append 'move' in Result.
    * movementUtil(IniPos, Dir, FinPos, Result): Append the required instruction in Result.
    * movement(IniPos, Dir, FinPos): Determine the reqquired steps to move and prints the list.

Qn3: Enforce the given constraints given. Ensure that each distinct tuple exists having one of the each characteristic.

    predicates used- 
    * board(L): Tkaes input a list of predicates, and enforces the said constraints.
