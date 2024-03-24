% Autor: Bartlomiej Maraj
% Fecha: 13/04/2020

% Gramatica española

% Tipos de oraciones

oracion(s(S,v(v_7),O,p)) --> sint_n(S,_,_), verbo(v(v_7),_), preposicion(p(prep_4)), sint_n(O,_,p).                                             % SN + Vb + SN : El verbo es amar acompañado de "a" y una persona
oracion(s(S,v(v_7),O,_)) --> sint_n(S,_,_), verbo(v(v_7),_), articulo(m(art_1),_,_), sint_n(O,_,_).                                             % SN + Vb + SN : El verbo es amar acompañado de "la" y un objeto
oracion(s(S,V,O)) --> sint_n(S,N,_), verbo(V,N), sint_n(O,_,_).                                                                                 % SN + Vb + SN : Caso general
oracion(s(S,V,O,O2)) --> sint_n(S,N,_), verbo(V,N), sint_n(O,_,_),sint_n(O2,_,_).                                                               % SN + Vb + O ( SN + SN )  : Caso general
oracion(s(S,V,S2,V2,O)) --> sint_n(S,_,_), verbo(V,_), sint_n(S2,_,_),verbo(V2,_), sint_n(O,_,_).                                               % SN + Vb + O ( SN + Vb + SN ) : Caso general
oracion(s(S,M,v(v_11),S2,V2,O)) --> sint_n(S,_,_), articulo(M,_,_),verbo(v(v_11),_), sint_n(S2,_,_),verbo(V2,_), sint_n(O,_,_).                 % SN + Vb ( Art + Vb) + O ( SN + Vb + SN )  : Caso general
oracion(s(S,V,S2,m(art_6),V2,O)) --> sint_n(S,_,_), verbo(V,_), sint_n(S2,_,_),articulo(m(art_6),_,_),verbo(V2,_), sint_n(O,_,_).               % SN + Vb + SN + y + Vb + SN   : Caso general
oracion(s(S,V,S2,S3,V2,O)) --> sint_n(S,_,_), verbo(V,_), sint_n(S2,_,_),articulo(m(art_6),_,_),sint_n(S3,_,_),verbo(V2,_), sint_n(O,_,_).      % SN + Vb + SN + y + SN + Vb + SN   : Caso general


% Tipos de sintagmas nominales

sint_n(np(S),N,T) --> nombre(S,_,N,T).                                                                                                          % Nombre : Caso general
sint_n(np(S,S2),N,T) --> nombre(S,_,N,T),nombre(S2,_,N,_).                                                                                      % Nombre + Nombre : Caso general
sint_n(np(A),N,_) --> adjetivo(A,_,N).                                                                                                          % Adjetivo : Caso general
sint_n(np(B),_,_) --> adverbio(B).                                                                                                              % Adverbio : Caso general
sint_n(np(M,S),N,T) --> articulo(M,G,N), nombre(S,G,N,T).                                                                                       % Articulo + Nombre : Caso general
sint_n(np(A,S),N,T) --> nombre(S,G,N,T), adjetivo(A,G,N).                                                                                       % Nombre + Adjetivo : Caso general
sint_n(np(M,A,S),N,T) --> articulo(M,G,N),nombre(S,G,N,T), adjetivo(A,G,N).                                                                     % Articulo + Nombre + Adjetivo : Caso general
sint_n(np(p(prep_2),S),G,l) -->  preposicion(p(prep_1)),articulo(m(art_1),G,N),nombre(S,G,N,l).                                                 % en + la/el/las/los + Nombre : Caso especifico
sint_n(np(P,M,S),N,T) -->  preposicion(P),articulo(M,G,N),nombre(S,G,N,T).                                                                      % Preposicion + Articulo + Nombre : Caso general
sint_n(np(M,S,S2),N,T) --> articulo(M,G,N),nombre(S,G,N,T),  preposicion(p(prep_3)),nombre(S2,G,N,_).                                           % Articulo + Nombre + Preposicion + Nombre : Caso general
sint_n(np(A,B),N,_) --> adverbio(B), adjetivo(A,_,N).                                                                                           % Adverbio + Adjetivo : Caso general
