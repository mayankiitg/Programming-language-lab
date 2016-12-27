
/*  move predicate */
move(Src, Src, []).
move(Src, Dest, Result) :- Src2 is Src + 1, move(Src2, Dest, Result2), append([move], Result2, Result).

/* movementUtil Predicate */
/* initial direction is east*/
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == east, X1<X2, Y1 < Y2, move(X1,X2,Result1), move(Y1, Y2, Result2),
                                                append(Result1, [left|Result2], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == east, X1<X2, move(X1,X2,Result1), move(Y2, Y1, Result2),
                                                append(Result1, [right|Result2], Result).
                                                
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == east, Y1 < Y2, move(X2,X1,Result1), move(Y1, Y2, Result2),
                                                append([left|Result2],[left|Result1], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == east, move(X2,X1,Result1), move(Y2, Y1, Result2),
                                                append([right|Result2],[right|Result1], Result).

/* initial direction is west*/
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == west, X1<X2, Y1 < Y2, move(X1,X2,Result1), move(Y1, Y2, Result2),
                                                append([right|Result2], [right|Result1], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == west, X1<X2, move(X1,X2,Result1), move(Y2, Y1, Result2),
                                                append([left|Result2], [left|Result1], Result).
                                                
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == west, Y1 < Y2, move(X2,X1,Result1), move(Y1, Y2, Result2),
                                                append(Result1,[right|Result2], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == west, move(X2,X1,Result1), move(Y2, Y1, Result2),
                                                append(Result1,[left|Result2], Result).


/* initial direction is north */
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == north, X1<X2, Y1 < Y2, move(X1,X2,Result1), move(Y1, Y2, Result2),
                                                append(Result2, [right|Result1], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == north, X1<X2, move(X1,X2,Result1), move(Y2, Y1, Result2),
                                                append([right|Result1], [right|Result2], Result).
                                                
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == north, Y1 < Y2, move(X2,X1,Result1), move(Y1, Y2, Result2),
                                                append(Result2,[left|Result1], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == north, move(X2,X1,Result1), move(Y2, Y1, Result2),
                                                append([left|Result1],[left|Result2], Result).

/* initial direction is south */                  
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == south, X1<X2, Y1 < Y2, move(X1,X2,Result1), move(Y1, Y2, Result2),
                                                append([left|Result1], [left|Result2], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == south, X1<X2, move(X1,X2,Result1), move(Y2, Y1, Result2),
                                                append(Result2, [left|Result1], Result).
                                                
movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == south, Y1 < Y2, move(X2,X1,Result1), move(Y1, Y2, Result2),
                                                append([right|Result1],[right|Result2], Result).

movementUtil((X1, Y1), Dir, (X2, Y2), Result) :- Dir == south, move(X2,X1,Result1), move(Y2, Y1, Result2),
                                                append(Result2,[right|Result1], Result).

/*  movement Predicate */
movement((X1, Y1), Dir, (X2, Y2)) :- movementUtil((X1, Y1), Dir, (X2, Y2), Result), write(Result).