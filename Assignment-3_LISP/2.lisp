(DEFUN func1 (x) (* x x))
(DEFUN func2 (x) (exp x))
(DEFUN func3 (x) (- (expt x 4) (expt x 3)))


; 101 points
(setq n 100)
(setq a 2)
(setq b 4)
(setq dx (/ (- b a) n))

(DEFUN trap (y) 
	(setq sum (+ (nth 0 y) (nth 0 (last y))))
	(loop for idx from 1 to (- (length y) 2)
		do (setq sum (+ sum (* 2 (nth idx y))))
	)
	(setq sum (/ (* sum dx) 2))
	(return-from trap sum)
) 

(DEFUN simpson (y) 

	(setq sum (+ (nth 0 y) (nth 0 (last y))))
	(loop for idx from 1 to (- (length y) 2)
		IF (EQ (mod idx 2) 0) do(setq sum (+ sum (* 2 (nth idx y)))) 
		IF (EQ (mod idx 2) 1) do(setq sum (+ sum (* 4 (nth idx y))))
	)
	(setq sum (/ (* sum dx) 3))
	(return-from simpson sum)
) 

(setq y1 ())
(setq y2 ())
(setq y3 ())

(loop for incr from 0 to n
	do (setq tempx (+ a (* dx incr)))
	
	do (setq y1 (append y1 (list (func1 tempx))))
	do (setq y2 (append y2 (list (func2 tempx))))
	do (setq y3 (append y3 (list (func3 tempx))))
)

; (write y1)	(terpri) (terpri) (terpri)
; (write y2)	(terpri) (terpri) (terpri)
; (write y3)	(terpri) (terpri) (terpri)

(setq resTrap1 (trap y1))
(setq resTrap2 (trap y2))
(setq resTrap3 (trap y3))
(write resTrap1)	(terpri)
(write resTrap2)	(terpri)
(write resTrap3)	(terpri)

(IF (EQ resTrap1 (min resTrap1 resTrap2 resTrap3)) (setq funcIdTrap "function 1: 4x^2"))
(IF (EQ resTrap2 (min resTrap1 resTrap2 resTrap3)) (setq funcIdTrap "function 2: 4e^x"))
(IF (EQ resTrap3 (min resTrap1 resTrap2 resTrap3)) (setq funcIdTrap "function 3: 4(x^4-x^3)"))
(write "minCost for trapozoid method -> ")
(write-line funcIdTrap)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(setq resSimp1 (simpson y1))
(setq resSimp2 (simpson y2))
(setq resSimp3 (simpson y3))

(write resSimp1)	(terpri)
(write resSimp2)	(terpri)
(write resSimp3)	(terpri)

(IF (EQ resSimp1 (min resSimp1 resSimp2 resSimp3)) (setq funcIdSimp "function 1: 4x^2"))
(IF (EQ resSimp2 (min resSimp1 resSimp2 resSimp3)) (setq funcIdSimp "function 2: 4e^x"))
(IF (EQ resSimp3 (min resSimp1 resSimp2 resSimp3)) (setq funcIdSimp "function 3: 4(x^4-x^3)"))
(write "minCost for Simpson method -> ")
(write-line funcIdSimp)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




; SIMPSON




; (write (func1 2))
; (terpri)
; (write (func2 2))
; (terpri)
; (write (func3 2))
; (terpri)