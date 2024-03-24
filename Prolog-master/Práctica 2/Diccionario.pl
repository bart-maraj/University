% Autor: Bartlomiej Maraj
% Fecha: 13/04/2020

%Diccionario español : articulos,preposiciones,nombres,verbos,adjetivos y adverbios.

% Articulo : ID, genero, numero

articulo(m(art_1),f,sg) --> [la].
articulo(m(art_1),m,sg) --> [el].
articulo(m(art_1),f,pl) --> [las].
articulo(m(art_2),f,sg) --> [una].
articulo(m(art_2),m,sg) --> [un].
articulo(m(art_3),f,pl) --> [ellas].
articulo(m(art_3),m,pl) --> [ellos].
articulo(m(art_4),_,_) --> [que].
articulo(m(art_5),_,_) --> [mi].
articulo(m(art_6),_,_) --> [y].

% Preposicion : ID

preposicion(p(prep_1)) --> [en].
preposicion(p(prep_3)) --> [de].
preposicion(p(prep_4)) --> [a].

% Nombre : ID, genero, numero, tipo(persona,lugar,otro)

nombre(n(n_1),f,sg,o) --> [piedra].
nombre(n(n_2),m,sg,o) --> [papel].
nombre(n(n_3),f,pl,o) --> [tijeras].
nombre(n(n_4),m,sg,o) --> [hombre].
nombre(n(n_5),f,sg,o) --> [manzana].
nombre(n(n_6),f,pl,o) --> [manzanas].
nombre(n(n_7),m,pl,p) --> [ellos].
nombre(n(n_7),f,pl,p) --> [ellas].
nombre(n(n_8),_,sg,p) --> [tu].
nombre(n(n_9),m,sg,p) --> [juan].
nombre(n(n_10),f,sg,p) --> [maria].
nombre(n(n_11),m,sg,o) --> [gato].
nombre(n(n_12),m,sg,o) --> [raton].
nombre(n(n_13),f,sg,l) --> [universidad].
nombre(n(n_14),m,sg,o) --> [alumno].
nombre(n(n_15),m,sg,o) --> [perro].
nombre(n(n_16),m,sg,o) --> [jardin].
nombre(n(n_17),m,sg,o) --> [vecino].
nombre(n(n_18),m,sg,o) --> [cafe].
nombre(n(n_19),m,sg,o) --> [periodico].
nombre(n(n_20),m,sg,p) --> [oscar].
nombre(n(n_21),m,sg,p) --> [wilde].
nombre(n(n_22),m,sg,o) --> [fantasma].
nombre(n(n_23),m,sg,l) --> [canterville].
nombre(n(n_24),m,sg,o) --> [canario].
nombre(n(n_25),m,sg,o) --> [grande].

% Verbo : ID, numero.

verbo(v(v_1),sg) --> [corta].
verbo(v(v_2),sg) --> [envuelve].
verbo(v(v_3),sg) --> [rompe].
verbo(v(v_1),pl) --> [cortan].
verbo(v(v_2),pl) --> [envuelven].
verbo(v(v_3),pl) --> [rompen].
verbo(v(v_4),sg) --> [come].
verbo(v(v_5),pl) --> [comen].
verbo(v(v_6),sg) --> [comes].
verbo(v(v_7),sg) --> [ama].
verbo(v(v_8),sg) --> [estudia].
verbo(v(v_9),sg) --> [persiguio].
verbo(v(v_10),sg) --> [es].
verbo(v(v_11),pl) --> [vimos].
verbo(v(v_12),sg) --> [toma].
verbo(v(v_13),sg) --> [lee].
verbo(v(v_14),sg) --> [escribio].
verbo(v(v_15),sg) --> [canta].

% Adjetivo : ID, genero, numero

adjetivo(a(a_1),f,sg) --> [roja].
adjetivo(a(a_2),_,sg) --> [grande].
adjetivo(a(a_3),_,sg) --> [gris].
adjetivo(a(a_4),m,sg) --> [negro].
adjetivo(a(a_5),_,sg) --> [grande].
adjetivo(a(a_6),m,sg) --> [delgado].
adjetivo(a(a_7),f,sg) --> [alta].
adjetivo(a(a_8),m,sg) --> [amarillo].
adjetivo(a(a_9),_,sg) --> [bien].

% Adverbio : ID

adverbio(ab(ab_1)) --> [ayer].
adverbio(ab(ab_2)) --> [muy].