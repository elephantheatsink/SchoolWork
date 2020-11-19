#lang racket
(define (selectN n)
  (lambda (numList) (removeTail numList n))
  )

(define (removeTail numList n)
   (if(= n 0) numList
              (removeTail (reverse(cdr (reverse numList))) (- n 1))
    )
  )
 
;; > (define First (selectN 3))
;; (First '(4 8 2 9 -1 13))
;; '(4 8 2)
;; > (First '(-2 3 -4 8 9 1 7))
;; '(-2 3 -4 8)
;; >   