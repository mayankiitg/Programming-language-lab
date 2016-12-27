
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
(write edges)
