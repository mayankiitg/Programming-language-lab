/* Predicate for concatenation of two lists. */
/* concat(List1, List2, Result) -> stores concatenation of List1 & List2 in Reult List. */
concat([H|T], L2, [H|Result]) :- concat(T, L2, Result).      /* first Clause: Pick first element from first list and append in Result. */
concat([], L, L).       /* If first list is empty then result will be second list. */

/* Predicate for successor */
successor(Number, Result) :- concat(Number, [x], Result).

/* predicate for plus */
plus(Inp1, Inp2, Result) :- concat(Inp1, Inp2, Result).

/* MINUS */
minus([], L, []).
minus(L1, [], L1).
minus([H|T1], [H|T2], Result) :- minus(T1, T2, Result).


/* MULTIPLY */
multiply([H|T], L2, Result) :- multiply(T, L2, Result2), plus(L2, Result2, Result). 
multiply([], L, []).

/* ASSIGN */
assign(X, X).

/* Subtract for divide */
subtractForDivide(L1, [], L1).
subtractForDivide([H|T1], [H|T2], Result) :- subtractForDivide(T1, T2, Result).

/* NON ZERO */
nonZero([H|T]). 

divide([], Number2) :- nonZero(Number2).
divide(Number1, Number2) :- nonZero(Number2), subtractForDivide(Number1, Number2, Result), divide(Result, Number2).