; Booleanos. Son los únicos lambda términos no currificados.

(define true (lambda (x y) x))

(define false (lambda (x y) y))

(define neg (lambda (x) (x false true)))
                         
(define and (lambda (x y) (x y false)))

(define or (lambda (x y) (x true y)))

; Pares ordenados
              
(define par (lambda (x)
              (lambda (y)
                (lambda (f) (f x y)))))

(define primero (lambda (p) (p true)))

(define segundo (lambda (p) (p false)))

;;;;; Combinador de punto fijo

(define Y
  (lambda (f)
    ((lambda (x) (f (lambda (v) ((x x) v))))
     (lambda (x) (f (lambda (v) ((x x) v)))))))

;;;;;; Orden en naturales y test de nulidad

(define esmenoroigualnat (lambda (n)
                             (lambda (m)
                                (escero ((restanat n) m)))))
                         
(define esmayoroigualnat (lambda (n)
                            (lambda (m)
                               (escero ((restanat m) n)))))
                         
(define esmenornat (lambda (n)
                     (lambda (m)
                       (and ((esmenoroigualnat n) m) (noescero ((restanat m) n))))))

(define esmayornat (lambda (n)
                     (lambda (m)
                       (and ((esmayoroigualnat n) m) (noescero ((restanat n) m))))))

(define esigualnat (lambda (n)
                     (lambda (m)
                       (and ((esmayoroigualnat n) m) ((esmenoroigualnat n) m)))))

(define escero (lambda (n)
                 ((n (lambda (x) false)) true)))

(define noescero (lambda (n)
                    (neg (escero n))))

; Aritmética natural. Se define también "comprobar" para poder hacer pruebas. Se definen algunos naturales para hacer comprobaciones. Se escriben en francés para distinguirlos de los enteros 
; que se escribirán en español.

(define zero (lambda (f)
               (lambda (x) x)))

(define sucesor (lambda (n)
                  (lambda (f)
                    (lambda (x)
                     (f((n f) x))))))

(define un (sucesor zero))

(define deux (sucesor un))

(define trois (sucesor deux))

(define quatre (sucesor trois))

(define cinq (sucesor quatre))

(define six (sucesor cinq))

(define sept (sucesor six))

(define huit (sucesor sept))

(define neuf (sucesor huit))

(define dix (sucesor neuf))

(define onze (sucesor dix))

(define douze (sucesor onze))

(define treize (sucesor douze))

(define quatorze (sucesor treize))

(define quinze (sucesor quatorze))

(define seize (sucesor quinze))

(define dix-sept (sucesor seize))

(define dix-huit (sucesor dix-sept))

(define dix-neuf (sucesor dix-huit))

(define vingt (sucesor dix-neuf))

;; Comprobar

(define comprobar (lambda (n)
                    ((n (lambda (x) (+ 1 x))) 0)))

;; Suma naturales

(define sumnat (lambda (n)
                 (lambda (m)
                   ((n (lambda (x) (sucesor x))) m))))

;; Producto naturales

(define prodnat (lambda (n)
                   (lambda (m)
                     (lambda (f)
                       (lambda (x) ((m (n f)) x))))))
                     
(define prefn (lambda (f)
                (lambda (p)
                  ((par (f (primero p))) (primero p)))))

;; Predecesor y resta 

(define predecesor (lambda (n)
                     (lambda (f)
                       (lambda (x)
                            (segundo ((n ((lambda (g)
                                             (lambda (p) ((prefn g) p))) f)) ((par x) x)))))))
                         
(define restanat (lambda (n)
                     (lambda (m)
                        ((m (lambda (x) (predecesor x))) n))))                                                 

;; Resto de la división euclídea. Si el divisor es cero, devuelve false.

(define restonataux
    (lambda (n)
        (lambda (m)
            ((Y (lambda (f)
                 (lambda (x)
                    ((((esmayoroigualnat x) m)  
                        (lambda (no_use)
                            (f ((restanat x) m))
                        )
                        (lambda (no_use)
                            x
                        )
                    )
                        zero)    ; Pasa zero como argumento de no_use
                )
            ))
                n)  ; Pasa n como el valor inicial de x.
        )
))

(define restonat (lambda (n)
                      (lambda (m)
                        (((escero m) (lambda (no_use) false) (lambda (no_use) ((restonataux n) m))) zero))))

;; Cociente de la división euclídea. Al igual que el resto, devuelve false si se divide por cero.

(define cocientenataux
    (lambda (n)
        (lambda (m)
            ((Y (lambda (f)
                (lambda (x)
                    ((((esmayoroigualnat x) m)  
                        (lambda (no_use)
                            (sucesor (f ((restanat x) m)))  
                        )
                        (lambda (no_use)
                            zero
                        )
                    )
                        zero)    ; Pasa zero como argumento de no_use
                )
            ))
                n)  ; Pasa n como el valor inicial de x.
        )
    )
)

(define cocientenat (lambda (n)
                      (lambda (m)
                        (((escero m) (lambda (no_use) false) (lambda (no_use) ((cocientenataux n) m))) zero))))

;; Máximo común denominador.

(define mcdnat
    (lambda (n)
        (lambda (m)
            (((Y (lambda (f)
                   (lambda (x)
                     (lambda(y)
                      (((escero y)  
                       (lambda (no_use)
                            x
                        ) 
                       (lambda (no_use)
                            ((f y)((restonat x) y)) 
                        )
                        
                    )
                        zero)    ; Pasa zero como argumento de no_use
                ))
            ))
                n) ; Pasa n como el valor inicial de x.
          m)       ; Pasa m como el valor inicial de y.
    )
))

;;;; Paridad

(define par? (lambda (n)
               (escero ((restonat n) deux))))

(define cuadrado (lambda (n)
                   ((prodnat n) n)))


;;;;; Potencias de naturales usando algo binario.

(define potencianat
    (lambda (n)
        (lambda (m)
            ((Y (lambda (f)
                (lambda (y)
                    (((escero y)
                        (lambda (no_use)
                            un
                        )
                        (lambda (no_use)
                          (((par? y)
                           (lambda (no_use1)
                             (cuadrado (f ((cocientenat y) deux))))
                           (lambda (no_use1)
                             ((prodnat n) (f (predecesor y))))) zero)
                        )
                    )
                        zero)    ; Pasa zero como argumento de no_use
                )
            ))
                m)  ; Pasa n como el valor inicial de x.
        )
    )
)

;;;;;; Definición de algunos enteros. Se codifican los enteros mediante pares de naturales: el par (m,n) es una representación de m-n. Es obvio que varios
;;;;;; pares codifican el mismo entero. Por ejemplo, (7,5)=(9,7). Por lo tanto, los enteros se definen como el conjunto cociente de NxN mediante la relación 
;;;;;; de equivalencia R dada por
;;;;;;
;;;;;;                     (m,n) R (m',n') si y solo si m-n=m'-n'

(define cero ((par zero) zero))

(define -uno ((par zero) un))

(define -dos ((par zero) deux))

(define -tres ((par zero) trois))

(define -cuatro ((par zero) quatre))

(define -cinco ((par zero) cinq))

(define -seis ((par zero) six))

(define -siete ((par zero) sept))

(define -ocho ((par zero) huit))

(define -nueve ((par zero) neuf))

(define -diez ((par zero) dix))

(define -once ((par zero) onze))

(define -doce ((par zero) douze))

(define -trece ((par zero) treize))

(define -catorce ((par zero) quatorze))

(define -quince ((par zero) quinze))

(define -dieciseis ((par zero) seize))

(define -diecisiete ((par zero) dix-sept))

(define -dieciocho ((par zero) dix-huit))

(define -diecinueve ((par zero) dix-neuf))

(define -veinte ((par zero) vingt))

(define uno ((par un) zero))

(define dos ((par deux) zero))

(define tres ((par trois) zero))

(define cuatro ((par quatre) zero))

(define cinco ((par cinq) zero))

(define seis ((par six) zero))

(define siete ((par sept) zero))

(define ocho ((par huit) zero))

(define nueve ((par neuf) zero))

(define diez ((par dix) zero))

(define once ((par onze) zero))

(define doce ((par douze) zero))

(define trece ((par treize) zero))

(define catorce ((par quatorze) zero))

(define quince ((par quinze) zero))

(define dieciseis ((par seize) zero))

(define diecisiete ((par dix-sept) zero))

(define dieciocho ((par dix-huit) zero))

(define diecinueve ((par dix-neuf) zero))

(define veinte ((par vingt) zero))

;;;;; Orden, valor absoluto y tests de nulidad, positividad y negatividad. 
;;;
;;; m-n > m'-n' si y solo si m+n' > m'+n e igual con el resto

(define esmayoroigualent (lambda (r)
                           (lambda (s)
                             ((esmayoroigualnat ((sumnat (primero r)) (segundo s))) ((sumnat (primero s)) (segundo r)))))) 

(define esmenoroigualent (lambda (r)
                           (lambda (s)
                             ((esmenoroigualnat ((sumnat (primero r)) (segundo s))) ((sumnat (primero s)) (segundo r))))))

(define esmayorent (lambda (r)
                           (lambda (s)
                             ((esmayornat ((sumnat (primero r)) (segundo s))) ((sumnat (primero s)) (segundo r))))))

(define esmenorent (lambda (r)
                           (lambda (s)
                             ((esmenornat ((sumnat (primero r)) (segundo s))) ((sumnat (primero s)) (segundo r))))))

(define esigualent (lambda (r)
                           (lambda (s)
                             ((esigualnat ((sumnat (primero r)) (segundo s))) ((sumnat (primero s)) (segundo r))))))

(define absoluto (lambda (r)
                    (((esmayoroigualnat (primero r)) (segundo r)) ((par ((restanat (primero r)) (segundo r))) zero) ((par ((restanat (segundo r)) (primero r))) zero))))

(define negativo (lambda (r)
                   ((esmenorent r) cero)))

(define positivo (lambda (r)
                   ((esmayorent r) cero)))

(define esceroent (lambda (r)
                     ((esigualnat (primero r)) (segundo r))))
                      
(define noesceroent (lambda (r)
                       (neg (esceroent r))))

;;;;; Reducción a representante canónico de la clase de equivalencia.

(define reducir (lambda (r)
                  (((esmayoroigualnat (primero r)) (segundo r)) 
                        ((par ((restanat (primero r)) (segundo r))) zero)
                        ((par zero) ((restanat (segundo r)) (primero r))))))

;;;;; Aritmética entera. La respuesta está siempre dada por el representante canónico de la clase de equivalencia. 

(define testenteros (lambda (r)
                      (- (comprobar (primero r)) (comprobar (segundo r)))))

(define sument (lambda (r)
                  (lambda (s)
                    (reducir ((par ((sumnat (primero r)) (primero s))) ((sumnat (segundo r)) (segundo s)))))))

(define prodent (lambda (r)
                  (lambda (s)
                    (reducir ((par ((sumnat ((prodnat (primero r)) (primero s))) ((prodnat (segundo r)) (segundo s))))
                          ((sumnat ((prodnat (primero r)) (segundo s))) ((prodnat (segundo r)) (primero s))))))))                       

(define restaent (lambda (r)
                   (lambda (s)
                     (reducir ((par ((sumnat (primero r)) (segundo s))) ((sumnat (segundo r)) (primero s)))))))

(define opuesto (lambda (r)
                  ((par (segundo r)) (primero r))))

;; Lo siguiente reduce la división de enteros a división de naturales. Si m mayor o igual que 0 y n > 0, y si q y r son cociente y resto de la división de m entre n, se tiene
;;  m  = q       * n        + r
;;  m  = (-q)    * (-n)     + r
;; -m  = (-(q+1))* n        + (n-r)
;; -m  = (q+1)   * (-n)     + (n-r),
;; siempre y cuando el resto no sea cero. Cuando el divisor es cero, la función cocienteent devuelve false.

(define cocienteent_aux (lambda (r)
                          (lambda (s)
                            ((cocientenat (primero (absoluto r))) (primero (absoluto s))))))

; Caso1: resto cero. Si m= q*n, entonces -m= (-q)*n, -m = q* (-n) y m= (-q)*(-n).

(define cocienteentaux-caso1 (lambda (r)
                               (lambda (s)
                                  ((or (and ((esmayoroigualent r) cero) (positivo s)) (and (negativo r) (negativo s))) ((par ((cocientenat (primero (absoluto r))) (primero (absoluto s)))) zero)
                                                                                                                       ((par zero) ((cocientenat (primero (absoluto r))) (primero (absoluto s))))))))
                              
; Caso 2: resto no nulo

(define cocienteentaux-caso2 (lambda (r)
                                (lambda (s)
                                    (((esmayoroigualent r) cero) ((positivo s) ((par ((cocienteent_aux r) s)) zero) ((par zero) ((cocienteent_aux r) s)))
                                                                 ((positivo s) ((par zero) (sucesor ((cocienteent_aux r) s))) ((par (sucesor ((cocienteent_aux r) s))) zero))))))
; Cociente cuando no hay división por cero

(define cocienteentaux (lambda (r)
                         (lambda (s)
                           ((escero ((restonat (primero (absoluto r))) (primero (absoluto s)))) ((cocienteentaux-caso1 r) s) ((cocienteentaux-caso2 r) s)))))

; Cociente considerando la división por cero

(define cocienteent (lambda (r)
                      (lambda (s)
                        (((esceroent s) (lambda (no_use) false) (lambda (no_use) ((cocienteentaux r) s))) zero))))

; Resto. Si se divide por cero, devuelve false

(define restoentaux1 (lambda (r)
                        (lambda (s)
                          ((or (and ((esmayoroigualent r) cero) (positivo s)) (and ((esmayoroigualent r) cero) (negativo s))) ((par ((restonat (primero (absoluto r))) (primero (absoluto s)))) zero)
                                                                                                           ((par ((restanat (primero (absoluto s)))((restonat (primero (absoluto r))) (primero (absoluto s))))) zero)))))

(define restoentaux (lambda (r)
                       (lambda (s)
                          ((escero ((restonat (primero (absoluto r))) (primero (absoluto s)))) cero ((restoentaux1 r) s)))))

(define restoent (lambda (r)
                      (lambda (s)
                        (((esceroent s) (lambda (no_use) false) (lambda (no_use) ((restoentaux r) s))) zero))))

;; Como mcd (r,s)=mcd(|r|,|s|), se tiene

(define mcdent (lambda (r)
                 (lambda (s)
                   ((par ((mcdnat (primero (absoluto r))) (primero (absoluto s)))) zero))))

;; Mínimo común múltiplo

(define mcment (lambda (r)
                 (lambda (s)
                   ((cocienteent ((prodent r) s)) ((mcdent r) s)))))


;;RACIONALES

;El conjunto de los números racionales es el conjunto cociente de Z mediante la relación
;de equivalencia R: (n, m) R (n′, m′) si y solo si n ∗ m′ = m ∗ n′

;Comprobar racionales.

(define test_racionales (lambda (r)
                          (list (testenteros (primero r)) (testenteros (segundo r)))))

;Reducción a representante canónico. Para reducir un racional a su representante canónico,
;calculamos el mínimo común divisor del par de números que lo forman. Después dividimos
;cada número del par por este mcd, obteniendo así el resultado deseado

(define reducir_racionales (lambda (r)
                              ((par ((cocienteentaux-caso1 (primero r))((mcdent (primero r))(segundo r)))) ((cocienteentaux-caso1 (segundo r))((mcdent (primero r))(segundo r))))))

;;Relaciones de orden e igualdad.

;Dado R(n,m) = n/m, sabemos que es cero si y solo si n = 0.

(define escero_racional(lambda (r)
                             (esceroent (primero r))))

;Dado R(n,m) y S(i,j), sabemos que son iguales si y solo si n * j = m * i.

(define esigual_racional(lambda (r)
                          (lambda (s)
                             ((esigualent((prodent (primero r)) (segundo s))) ((prodent (primero s)) (segundo r))))))

;Dado R(n,m) y S(i,j), sabemos que R es mayor si y solo si n * j > m * i.

(define mayor_racional(lambda (r)
                          (lambda (s)
                             ((esmayorent((prodent (primero r)) (segundo s))) ((prodent (primero s)) (segundo r))))))

;Dado R(n,m) y S(i,j), sabemos que R es menor si y solo si n * j < m * i.

(define menor_racional(lambda (r)
                          (lambda (s)
                             ((esmenorent((prodent (primero r)) (segundo s))) ((prodent (primero s)) (segundo r))))))

;;Aritmética: suma, producto, resta de racionales y cálculo de inverso.

;Suma de racionales. Dado R(n,m) y S(i,j),la suma de R y S es equivalente a T(n*j + i*m, m*j).
;T después se reduce a representante canónico

(define suma_racionales(lambda (r)
                          (lambda (s)
                              (reducir_racionales((par ((sument((prodent (primero r)) (segundo s))) ((prodent (primero s)) (segundo r)))) ((prodent (segundo r)) (segundo s)))))))

;Resta de racionales. Dado R(n,m) y S(i,j),la resta de R y S es equivalente a T(n*j - i*m, m*j).
;T después se reduce a representante canónico

(define resta_racionales(lambda (r)
                          (lambda (s)
                              (reducir_racionales((par ((restaent((prodent (primero r)) (segundo s))) ((prodent (primero s)) (segundo r)))) ((prodent (segundo r)) (segundo s)))))))

;Producto de racionales. Dado R(n,m) y S(i,j),la suma de R y S es equivalente a T(n*i, m*j).
;T después se reduce a representante canónico

(define prod_racionales(lambda (r)
                          (lambda (s)
                              (reducir_racionales((par ((prodent (primero r)) (primero s))) ((prodent (segundo r)) (segundo s)))))))

;Inverso de un racional. Dado R(n,m),R inverso es equivalente a R'(m,n).
;T después se reduce a representante canónico

(define inverso_racionales(lambda (r)
                              (reducir_racionales((par (segundo r)) (primero r)))))

;;MATRICES RACIONALES

;Las matrices son 2x2, formadas por racionales y construida por columnas

; Matriz = |a c|
;          |b d|

;;Funciones auxiliares. Devuelven los valores de la matriz.

;(define matriz_a (lambda (m)
;                        (primero (primero m))))
;(define matriz_b (lambda (m)
;                        (segundo (primero m))))
;(define matriz_c (lambda (m)
;                        (primero (segundo m))))
;(define matriz_d (lambda (m)
;                        (segundo (segundo m))))


;Definir matriz.

(define definir_matriz (lambda (a)
                         (lambda (b)
                           (lambda (c)
                             (lambda (d)
                               ((par ((par a) b)) ((par c) d)))))))

;Comprobar matriz.

(define test_matriz (lambda (m)
                        (list (list (test_racionales (primero (primero m))) (test_racionales (segundo (primero m))))
                              (list (test_racionales (primero (segundo m))) (test_racionales (segundo (segundo m))))
                        )
                      )
)

;Matrices prueba, identidad y nula.

(define identidad ((((definir_matriz ((par uno) uno)) ((par cero) uno)) ((par cero) uno)) ((par uno) uno)))
(define matriz_nula ((((definir_matriz ((par cero) uno)) ((par cero) uno)) ((par cero) uno)) ((par cero) uno)))
(define matriz_prueba1 ((((definir_matriz ((par dos) cuatro)) ((par cuatro) cuatro)) ((par -uno) cuatro)) ((par cinco) cuatro)))
(define matriz_prueba2 ((((definir_matriz ((par uno) cuatro))   ((par -cuatro) seis))     ((par dos) ocho)) ((par -dos) tres)))
(define matriz_prueba3 ((((definir_matriz ((par uno) dos))   ((par -cuatro) dos))     ((par dos) dos)) ((par -tres) dos)))

;Booleano de inversa de una matriz.
;Una matriz tiene inversa si y solo si su determinante es distinto de 0.

(define inversa? (lambda (m)
                        (neg(escero_racional((resta_racionales((prod_racionales(primero (primero m)))(segundo (segundo m))))((prod_racionales (primero (segundo m)))(segundo (primero m))))))))

;Determinante de una matriz
;Dada una matriz M = |a c|, el determinante D = (a*d - b*c)
;                    |b d|

(define determinante (lambda (m)
                        (reducir_racionales((resta_racionales((prod_racionales(primero (primero m)))(segundo (segundo m))))((prod_racionales (primero (segundo m)))(segundo (primero m)))))))

;Producto de matrices.
;Dada una matriz M = |a c| , y una matriz S = |e g|
;                    |b d|                    |f h|
; M * S = |(a*e + c*f)  (a*g + c*h)|
;         |(b*e + d*f)  (b*g + d*h)|

(define prod_matrices (lambda (m)
                        (lambda (s)
                          ((((definir_matriz
                               (reducir_racionales((suma_racionales((prod_racionales(primero (primero m)))(primero (primero s))))((prod_racionales(primero (segundo m)))(segundo (primero s))))))
                               (reducir_racionales((suma_racionales((prod_racionales(segundo (primero m)))(primero (primero s))))((prod_racionales(segundo (segundo m)))(segundo (primero s))))))
                               (reducir_racionales((suma_racionales((prod_racionales(primero (primero m)))(primero (segundo s))))((prod_racionales(primero (segundo m)))(segundo (segundo s))))))
                               (reducir_racionales((suma_racionales((prod_racionales(segundo (primero m)))(primero (segundo s))))((prod_racionales(segundo (segundo m)))(segundo (segundo s))))))
                         )
                       )
)

;Cuadrado de matriz.
;Dada una matriz M = |a c| el cuadrado de la matriz es el siguiente:
;                    |b d|                    
; M * M = |(a*a + c*b)  (a*c + c*d)|
;         |(b*a + d*b)  (b*c + d*d)|

(define cuadrado_matrices (lambda (m)
                          ((((definir_matriz
                               (reducir_racionales((suma_racionales((prod_racionales(primero (primero m)))(primero (primero m))))((prod_racionales(primero (segundo m)))(segundo (primero m))))))
                               (reducir_racionales((suma_racionales((prod_racionales(segundo (primero m)))(primero (primero m))))((prod_racionales(segundo (segundo m)))(segundo (primero m))))))
                               (reducir_racionales((suma_racionales((prod_racionales(primero (primero m)))(primero (segundo m))))((prod_racionales(primero (segundo m)))(segundo (segundo m))))))
                               (reducir_racionales((suma_racionales((prod_racionales(segundo (primero m)))(primero (segundo m))))((prod_racionales(segundo (segundo m)))(segundo (segundo m))))))
                         )
)

;Suma de matrices.
;Dada una matriz M = |a c| , y una matriz S = |e g|
;                    |b d|                    |f h|
; M + S = |(a + e)  (c + g)|
;         |(b + f)  (d + h)|


(define suma_matrices (lambda (m)
                        (lambda (s)
                          ((((definir_matriz
                               (reducir_racionales((suma_racionales(primero (primero m)))(primero (primero s)))))
                               (reducir_racionales((suma_racionales(segundo (primero m)))(segundo (primero s)))))
                               (reducir_racionales((suma_racionales(primero (segundo m)))(primero (segundo s)))))
                               (reducir_racionales((suma_racionales(segundo (segundo m)))(segundo (segundo s)))))
                         )
                       )
)

;Inversa de matriz.
;Dada una matriz M = |a c|, calculamos el determinante det y obtenemos la matriz inversa:
;                    |b d|                    
; M inversa = |(d/det)  -(b/det)|
;             |-(c/det)  (a/det)|
(define inversa (lambda (m)
                          ((((definir_matriz
                               (reducir_racionales((prod_racionales(segundo (segundo m)))(inverso_racionales (determinante m)))))
                               ((prod_racionales((par -uno) uno))(reducir_racionales((prod_racionales(segundo (primero m)))(inverso_racionales (determinante m))))))
                               ((prod_racionales((par -uno) uno))(reducir_racionales((prod_racionales(primero (segundo m)))(inverso_racionales (determinante m))))))
                               (reducir_racionales((prod_racionales(primero (primero m)))(inverso_racionales (determinante m)))))
                       )
)

;Potencia de matrices.(M^n)
;Para la potencia de matrices necesitamos la recursividad
;para multiplicar n veces la matriz consigo misma

(define potencia_matrices
  (lambda(m) ;Argumento 1 (matriz)
    (lambda(n) ;Argumento 2 (exponente)
      ((Y (lambda(f)
           (lambda(y); Argumento 1 auxiliar
             (((escero y); Condicion 1, el exponente es zero
               (lambda (no_use); Caso base: matriz identidad
                 identidad
               )
               (lambda (no_use); Caso recursivo
                 (((par? y); Condicion 2, el exponente es par
                   (lambda (no_use1); Llamada recursiva 1
                      (cuadrado_matrices (f ((cocientenat y) deux)))) ; Cuadrado de la matriz y exponente dividido entre 2
                   (lambda (no_use1); Llamada recursiva 2  
                      ((prod_matrices m) (f (predecesor y)))) ;Multiplicación de la matriz con la matriz predecesora
                  )
                   zero) ; Pasa zero como argumento de no_use1
                )
             )
                   zero) ; Pasa zero como argumento de no_use
         )
      ))
         n)
    )
  )
)
 
                        

