#lang racket
(define (DFA-Acceptor numlist Q0 Q2 P)
  (Q0 numlist)
  )

(define (Q0 numlist)
  (cond ((= (car numlist) 0)
          (Q1 (cdr numlist)))
        ((= (car numlist) 1)
          (Q0 (cdr numlist)))
   )
 )

(define (Q1 numlist)
  (cond ((= (car numlist) 0)
          (P))
        ((= (car numlist) 1)
          (Q2))
   )
 )

(define (Q2) #t)
(define (P) #f)

;; > (DFA-Acceptor '(1 1 0 1 0) Q0 '(Q2) P) 
;; #t
;; > (DFA-Acceptor '(1 0 0) Q0 '(Q2) P) 
;; #f
