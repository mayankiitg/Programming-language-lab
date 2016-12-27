; Test1: left to right
; Test2: right to left
; Test3: Manas has all incident edges, Kameng has all outgoing edges.

; 0 "subhansri"
; 1 "I1"
; 2 "I2"
; 3  "lohit"
; 4  "dibang" 
; 5  "kapili" 
; 6  "brahmputra" 
; 7  "dihing" 
; 8  "I3" 
; 9  "I4" 
; 10  "umiam" 
; 11  "manas" 
; 12  "I5" 
; 13  "I6" 
; 14  "barak" 
; 15  "kameng"

; (setq edges (list 
; 			(list 0 1 250) 
; 			(list 1 2 210)
; 			(list 1 12 500)
; 			(list 2 3 150)
; 			(list 2 4 260)
; 			(list 2 5 260)
; 			(list 4 5 2)
; 			(list 4 6 350)
; 			(list 4 7 350)
; 			(list 5 6 350)
; 			(list 5 7 350)
; 			(list 6 7 2)
; 			(list 6 8 205)
; 			(list 7 8 205)
; 			(list 8 9 73)
; 			(list 8 11 57)
; 			(list 9 10 140)
; 			(list 11 12 173)
; 			(list 12 13 180)
; 			(list 13 9 110)
; 			(list 13 14 95)
; 			(list 13 15 95)
; 			)
; )
(setq hostels '("0 -> subhansri" "3 -> lohit" "4 -> dibang" "5 -> kapili" "6 -> brahmputra" "7 -> dihing" "10 -> umiam" "11 -> manas" "14 -> barak" "15 -> kameng"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(setq edges `())

(defun delimiterp (c) (position c " ,.;/"))

(defun my-split (string &key (delimiterp #'delimiterp))
  (loop :for beg = (position-if-not delimiterp string)
    :then (position-if-not delimiterp string :start (1+ end))
    :for end = (and beg (position-if delimiterp string :start beg))
    :when beg :collect (subseq string beg end)
    :while end))

(DEFUN read_file (fname)
	(let ((in (open fname :if-does-not-exist :error)))
	   (when in
	      (loop for line = (read-line in nil)
	      	while line do (progn
	      				(setq tempEdge (my-split line))
	      				(setf (nth 0 tempEdge) (parse-integer (nth 0 tempEdge)))
	      				(setf (nth 1 tempEdge) (parse-integer (nth 1 tempEdge)))
	      				(setf (nth 2 tempEdge) (parse-integer (nth 2 tempEdge)))
	      				(setq edges (append edges (list tempEdge)))
	      				)
	      )
	      (close in)
	   )
	)
)

(princ "Enter test case file name: ")
(setq fName (read))
(read_file fName)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(princ "Following is the list of hostels. Enter the ID corresponding to a hostel.") (terpri)
(loop for idx in hostels
	; do (setq temp (nth idx hostels))
	do (princ idx)
	do (terpri)
)

(setq nodes '("subhansri" "I1" "I2" "lohit" "dibang" "kapili" "brahmputra" "dihing" "I3" "I4" "umiam" "manas" "I5" "I6" "barak" "kameng"))



;(setq edges (list (list 0 2 50) (list 0 1 20) (list 1 2 10)))

(setq no_of_nodes 16)

(setq dist `())

(setq prev_nodes `())

(setq int_max (expt 10 9))
(princ "Enter source hostel ID: ")

(setq src_node (read))
; (terpri)
(princ "Enter destination hostel ID: ")

(setq dst_node (read))

; initialize all values of dist as INFINITE
(loop for idx from 0 to (- no_of_nodes 1)
	do (setq dist (append dist (list int_max)))
)


; initialize all values of prev_node as -1
(loop for idx from 0 to (- no_of_nodes 1)
	do (setq prev_nodes (append prev_nodes (list -1)))
)

; set dist[src] = 0
(setf (nth src_node dist) 0)

; initialize prev_node[src] = src
(setf (nth src_node prev_nodes) src_node)

; bellman-ford
(loop for idx from 0 to (- no_of_nodes 1)
	do
	(loop for edge in edges
		do (setq distV (nth (nth 1 edge) dist))
		do (setq distU (nth (nth 0 edge) dist))
		do (setq weightUV (nth 2 edge))
		IF (> distV (+ distU weightUV))
			do (setf (nth (nth 1 edge) dist) (+ distU weightUV))
		IF (> distV (+ distU weightUV))
			do (setf (nth (nth 1 edge) prev_nodes) (nth 0 edge))
		
	)
)

; (write "dist of all vertices from src: ")
; (write dist)
; (terpri)
; (write "Array of previous nodes ")
; (write prev_nodes)
; (terpri)

(setq temp_node dst_node)
(setq path (list dst_node))

(setq hasPath 1)

(loop 

	(setq prev_node (nth temp_node prev_nodes))
	
	(when (EQ prev_node -1) (setq hasPath 0) (return))

	(setq path (append (list prev_node) path))
	(setq temp_node prev_node)
	(when (EQ prev_node src_node) (return))
)


(IF (EQ hasPath 1) 

	(progn
		(princ "Cost of the path is: ")
		(write (nth dst_node dist))
		(terpri)
	)
)

(IF (EQ hasPath 1) 

	(loop for idx from 0 to (- (length path) 1) 

		do (princ (nth (nth idx path) nodes))
		IF (/= idx (- (length path) 1))
			do (princ " -> ")

	) 
	(write "No path between src and dest.")
)