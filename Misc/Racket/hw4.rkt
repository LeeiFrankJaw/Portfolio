
#lang racket

(provide (all-defined-out)) ;; so we can put tests in a second file

;; put your code below

(define (map-stream f s)
  (let ([ans (s)])
    (λ () (cons (f (car ans)) 
                (map-stream f (cdr ans))))))

(define (new-stream f x)
  (λ () (cons x (new-stream f (f x)))))

(define (inc n) (+ n 1))

(define nats (new-stream inc 1))

;; Integer * Integer * Integer -> Integer List
(define (sequence low high stride)
  (letrec ([iter (lambda (cur acc)
                   (if (> cur high)
                       (reverse acc)
                       (iter (+ cur stride) (cons cur acc))))])
    (iter low null)))

;; String List * String -> String List
(define (string-append-map xs s)
  (map (lambda (x) (string-append x s)) xs))

;; 'a List * Integer -> 'a
(define (list-nth-mod xs n)
  (cond [(< n 0) (error "list-nth-mod: negative number")]
        [(null? xs) (error "list-nth-mod: empty list")]
        [#t (car (list-tail xs (remainder n (length xs))))]))

;; 'a Stream * Integer -> 'a List
(define (stream-for-n-steps s n)
  (letrec ([iter (λ (s cur acc)
                   (if (= cur n)
                       (reverse acc)
                       (let ([ans (s)])
                         (iter (cdr ans) (inc cur)
                               (cons (car ans) acc)))))])
    (iter s 0 null)))

;; Void -> Integer Stream
;;(define funny-number-stream
;;  (letrec ([next (λ (n)
;;                   (let ([n (+ (abs n) 1)])
;;                     (if (= (remainder n 5) 0)
;;                         (- n)
;;                         n)))]
;;           [make-stream (λ (n)
;;                          (λ ()
;;                            (cons n (make-stream (next n)))))])
;;    (make-stream 1)))

(define funny-number-stream
  (let ([f (λ (n)
             (if (= (remainder n 5) 0)
                 (- n)
                 n))])
    (map-stream f nats)))

;; Void -> String Stream
;;(define dan-then-dog
;;  (letrec ([make-dan (λ () (cons "dan.jpg" make-dog))]
;;           [make-dog (λ () (cons "dog.jpg" make-dan))])
;;    make-dan))

(define dan-then-dog
  (let ([f (λ (n)
             (if (= (remainder n 2) 1)
                 "dan.jpg"
                 "dog.jpg"))])
    (map-stream f nats)))

;; 'a Stream -> (Integer * 'a) Stream
;;(define (stream-add-zero s)
;;  (letrec ([make-stream (λ (s)
;;                          (λ ()
;;                            (let ([ans (s)])
;;                              (cons (cons 0 (car ans))
;;                                    (make-stream (cdr ans))))))])
;;    (make-stream s)))

(define (stream-add-zero s)
  (let ([f (λ (v) (cons 0 v))])
    (map-stream f s)))

;; 'a List * 'b List -> ('a * 'b) Stream
;;(define (cycle-lists xs ys)
;;  (letrec ([make-stream (λ (n)
;;                          (λ ()
;;                            (cons
;;                             (cons (list-nth-mod xs n)
;;                                   (list-nth-mod ys n))
;;                             (make-stream (inc n)))))])
;;    (make-stream 0)))

(define (cycle-lists xs ys)
  (let ([f (λ (n) (cons (list-nth-mod xs n)
                       (list-nth-mod ys n)))])
    (map-stream f (λ () (cons 0 nats)))))

;; 'a * Vector -> ('a * 'b) | Boolean
(define (vector-assoc v vec)
  (letrec ([len (vector-length vec)]
           [next (λ (cur)
                   (if (< cur len)
                       (let ([ans (vector-ref vec cur)])
                         (if (and (pair? ans) 
                                  (equal? (car ans) v))
                             ans
                             (next (inc cur))))
                       #f))])
    (next 0)))

; ('a * 'b) List * Integer -> 'a -> ('a * 'b) | Boolean
(define (cached-assoc xs n)
  (let* ([cur 0]
         [cache (make-vector n #f)])
    (λ (v)
      (let ([find-in-cache (vector-assoc v cache)])
        (if find-in-cache
            find-in-cache
            (let ([found (assoc v xs)])
              (if found
                  (begin 
                    (vector-set! cache cur found)
                    (set! cur (remainder (inc cur) n))
                    found)
                  #f)))))))

(define-syntax while-less
  (syntax-rules (do)
    [(while-less e1 do e2)
     (let ([v1 e1])
       (letrec ([loop (λ (v2)
                        (if (< v2 v1)
                            (loop e2)
                            #t))])
         (loop e2)))]))