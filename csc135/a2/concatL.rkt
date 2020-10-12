#lang racket
(define (concatL x y)
   (cond ((empty? (rest x)) (list(string-append (convert-to-string(first x)) (convert-to-string(first y)))))
         (else (append ;;using apnd resulted in '("abfff" ("cddes" ("devvvv")))
                        (list(string-append (convert-to-string(first x)) (convert-to-string(first y))))
                       (concatL (rest x) (rest y))))
    )
 )

(define (convert-to-string List) (eval (cons string-append (map symbol->string List))))

(define (apnd L a)  
  (if (null? L) (list a) (cons (car L) (apnd (cdr L) a)))
  )


;; >  ( concatL '( (a b) (c d) (d e) ) '( (f f f) (d e s) (v v v v) ))
;; '("abfff" "cddes" "devvvv")