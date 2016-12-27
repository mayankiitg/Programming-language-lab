/* Assign */
assign(X, X).

/* isEqual */
isEqual(X, X).

/* arithmetic add*/
arith_add(X, Y) :- $X is $X + $Y.

/* Pos is tuple */
status(L, Pos, Ori) :- statusutil(L, 0, 0, east, PosX, PosY, Ori), assign(Pos, (PosX, PosY)).

statusutil([left|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, north), statusutil(T, Old_PosX, Old_PosY, west, New_PosX, New_PosY, New_Ori).

statusutil([left|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, south), statusutil(T, Old_PosX, Old_PosY, east, New_PosX, New_PosY, New_Ori).

statusutil([left|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, east), statusutil(T, Old_PosX, Old_PosY, north, New_PosX, New_PosY, New_Ori).

statusutil([left|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, west), statusutil(T, Old_PosX, Old_PosY, south, New_PosX, New_PosY, New_Ori).


statusutil([right|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, north), statusutil(T, Old_PosX, Old_PosY, east, New_PosX, New_PosY, New_Ori).

statusutil([right|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, south), statusutil(T, Old_PosX, Old_PosY, west, New_PosX, New_PosY, New_Ori).

statusutil([right|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, east), statusutil(T, Old_PosX, Old_PosY, south, New_PosX, New_PosY, New_Ori).

statusutil([right|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- 
            isEqual(Old_Ori, west), statusutil(T, Old_PosX, Old_PosY, north, New_PosX, New_PosY, New_Ori).


/* MOVE INSRUCTION */
statusutil([move|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :-
            isEqual(Old_Ori, north), Y is Old_PosY + 1, statusutil(T, Old_PosX, Y, Old_Ori, New_PosX, New_PosY, New_Ori).

statusutil([move|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :-
            isEqual(Old_Ori, south), Y is Old_PosY - 1, statusutil(T, Old_PosX, Y, Old_Ori, New_PosX, New_PosY, New_Ori).

statusutil([move|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :-
            isEqual(Old_Ori, east), X is Old_PosX + 1, statusutil(T, X, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori).

statusutil([move|T], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :-
            isEqual(Old_Ori, west), X is Old_PosX - 1, statusutil(T, X, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori).

/* BASE CASE, all instructions completed. */
statusutil([], Old_PosX, Old_PosY, Old_Ori, New_PosX, New_PosY, New_Ori) :- assign(Old_PosX, New_PosX),
                                                                            assign(Old_PosY, New_PosY),
                                                                            assign(Old_Ori, New_Ori).

