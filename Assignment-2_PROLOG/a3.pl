/* enforce the constraints provided in question */
/* ensure each distinct characteristic has its own tuple */
board(L) :-
L = [pizza(hawaiian, T1h, T2h, Ph),
     pizza(marcopolo, T1m, T2m, Pm),
     pizza(pepperoni, T1p, T2p, Pp),
     pizza(supersupreme, T1s, T2s, Ps),
     pizza(ninja, T1n, T2n, Pn)],

T1h = mutton,
member(Ph, ['70', '85', '100']),
T2m = tomato,
member(T1m, [mutton, prawn, salami, tuna]),
member(pizza(_, chicken, _, '85'), L),
Pp = '70',
member(T2s, [onion, corn, olive, tomato]),
member(pizza(_, tuna, corn, P), L),
member(P, ['55', '70', '85', '100']),
member(pizza(_, T1, olive, '55'), L),
member(T1, [chicken, mutton, prawn, tuna]),
member(pizza(_, _, pineapple, P2), L),
member(P2, ['55', '65', '70', '85']),

member(pizza(_, chicken, _, _), L),
member(pizza(_, mutton, _, _), L),
member(pizza(_, prawn, _, _), L),
member(pizza(_, salami, _, _), L),
member(pizza(_, tuna, _, _), L),

member(pizza(_, _, onion, _), L),
member(pizza(_, _, corn, _), L),
member(pizza(_, _, olive, _), L),
member(pizza(_, _, tomato, _), L),
member(pizza(_, _, pineapple, _), L),

member(pizza(_, _, _, '55'), L),
member(pizza(_, _, _, '65'), L),
member(pizza(_, _, _, '70'), L),
member(pizza(_, _, _, '85'), L),
member(pizza(_, _, _, '100'), L).