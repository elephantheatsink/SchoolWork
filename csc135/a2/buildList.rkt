#lang racket
(define (buildList N E1 M E2)
  (append (repeatE N E1) (repeatE M E2))
 )

(define (repeatE N E)
  (cond ((> N 0)(apnd (repeatE (- N 1) E) E))
        (else '())
  )
 )

( define ( apnd L a ) 
   ( if ( null? L ) ( list a ) ( cons (car L ) ( apnd ( cdr L ) a ) ) )
 )

;; > ( buildList 5 '() 3 'B )
;; '(() () () () () B B B)
;; > ( buildList 3 'A 2 'C)
;; '(A A A C C)
;; > ( buildList 2 '(a b c ) 1 'Q)
;; '((a b c) (a b c) Q)