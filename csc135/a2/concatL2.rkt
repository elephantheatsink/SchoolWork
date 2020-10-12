#lang racket
(define (concatL x y)
   (cond ((empty? (rest x)) (list(string-append (list2str(first x)) (list2str(first y)))))
         (else (apnd
                       (list(string-append (list2str(first x)) (list2str(first y))))
                       (concatL (rest x) (rest y))))
    )
 )

(define (list2str slst)
  (cond ((empty? (rest slst)) (symbol->string (first slst)))
        (else (string-append (symbol->string (first slst)) (list2str (rest slst))))
   )
 )

(define (apnd L a)
  (if (null? L) (list a) (cons (car L) (apnd (cdr L) a)))
  )