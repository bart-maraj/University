% Autor: Bartlomiej Maraj
% Fecha: 13/04/2020

%Diccionario ingles :modifiers, prepositions, nouns, verbs, adjectives and adverbs.

% Articulo : ID, c/v (empieza por cte. o vocal)

modifier(m(art_0),_) --> [some].
modifier(m(art_1),_) --> [the].
modifier(m(art_2),c) --> [a].
modifier(m(art_2),v) --> [an].
modifier(m(art_3),_) --> [they].
modifier(m(art_4),_) --> [that].
modifier(m(art_5),_) --> [my].
modifier(m(art_6),_) --> [and].
modifier(m(art_7),_) --> [we].

% Preposicion : ID

preposition(p(prep_1)) --> [in].
preposition(p(prep_2)) --> [at].

% Nombre : ID, numero, c/v (empieza por cte. o vocal), tipo(persona,lugar,objeto)

noun(n(n_1),sg,c,o) --> [stone].
noun(n(n_2),sg,c,o) --> [paper].
noun(n(n_3),pl,c,o) --> [scissors].
noun(n(n_4),sg,c,o) --> [man].
noun(n(n_5),sg,v,o) --> [apple].
noun(n(n_6),pl,v,o) --> [apples].
noun(n(n_7),pl,c,p) --> [they].
noun(n(n_8),sg,c,p) --> [you].
noun(n(n_9),sg,c,p) --> [john].
noun(n(n_10),sg,c,p) --> [mary].
noun(n(n_11),sg,c,o) --> [cat].
noun(n(n_12),sg,c,o) --> [mouse].
noun(n(n_13),sg,v,l) --> [university].
noun(n(n_14),sg,c,o) --> [student].
noun(n(n_15),sg,c,o) --> [dog].
noun(n(n_16),sg,c,o) --> [garden].
noun(n(n_17),sg,c,o) --> [neighbour].
noun(n(n_18),sg,c,o) --> [coffee].
noun(n(n_19),sg,c,o) --> [newspaper].
noun(n(n_20),sg,v,n) --> [oscar].
noun(n(n_21),sg,c,n) --> [wilde].
noun(n(n_22),sg,c,o) --> [ghost].
noun(n(n_23),sg,c,l) --> [canterville].
noun(n(n_24),sg,c,o) --> [canary].
noun(n(n_25),sg,c,o) --> [large].

% Verbo : ID, numero.

verb(v(v_1),sg) --> [cuts].
verb(v(v_2),sg) --> [wraps].
verb(v(v_3),sg) --> [breaks].
verb(v(v_1),pl) --> [cut].
verb(v(v_2),pl) --> [wrap].
verb(v(v_3),pl) --> [break].
verb(v(v_4),sg) --> [eats].
verb(v(v_5),pl) --> [eat].
verb(v(v_6),sg) --> [eat].
verb(v(v_7),sg) --> [loves].
verb(v(v_8),sg) --> [studies].
verb(v(v_9),sg) --> [chased].
verb(v(v_10),sg) --> [is].
verb(v(v_11),_) --> [saw].
verb(v(v_12),sg) --> [has].
verb(v(v_13),sg) --> [reads].
verb(v(v_14),sg) --> [wrote].
verb(v(v_15),sg) --> [sings].


% Adjetivo : ID, genero, numero

adjective(a(a_1),c) --> [red].
adjective(a(a_2),c) --> [big].
adjective(a(a_3),c) --> [grey].
adjective(a(a_4),c) --> [black].
adjective(a(a_5),c) --> [large].
adjective(a(a_6),c) --> [thin].
adjective(a(a_7),c) --> [tall].
adjective(a(a_8),c) --> [yellow].
adjective(a(a_9),c) --> [well].

% Adverbio : ID

adverb(ab(ab_1)) --> [yesterday].
adverb(ab(ab_2)) --> [very].