#lang racket
(define (make6 x y)
  (cond ((< (abs x) 100)-2)
        ((< (abs y) 100)-2)
        (else (+ (firstthree (abs x)) (lastthree (abs y))))  
  )
 )

(define (firstthree x)
  (if(< x 1000)(* x 1000)(firstthree (floor(/ x 10))))
)

(define (lastthree y)
  (modulo y 1000)

)

;; > (make6 561432 254)
;; 561254
;; > (make6  561432 -254)
;; 561254
;; > (make6 12 123)
;; -2