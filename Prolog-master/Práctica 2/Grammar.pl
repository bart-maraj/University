% Autor: Bartlomiej Maraj
% Fecha: 13/04/2020

% Tipos de oraciones
                                                                                                                                         % SN + Vb + SN : El verbo es amar acompañado de "a" y una persona
sentence(s(S,v(v_7),O,T)) --> nom_p(S,N,_), verb(v(v_7),N), nom_p(O,_,T).                                                                % SN + Vb + SN : El verbo es amar acompañado de "la" y un objeto
sentence(s(S,V,O)) --> nom_p(S,N,_), verb(V,N), nom_p(O,_,_).                                                                            % SN + Vb + SN : Caso general
sentence(s(S,V,O,O2)) --> nom_p(S,N,_), verb(V,N), nom_p(O,_,_),nom_p(O2,_,_).                                                           % SN + Vb + O ( SN + SN )  : Caso general
sentence(s(S,V,S2,V2,O)) --> nom_p(S,_,_), verb(V,_), nom_p(S2,_,_),verb(V2,_), nom_p(O,_,_).                                            % SN + Vb + O ( SN + Vb + SN ) : Caso general
sentence(s(S,M,V,S2,V2,O)) --> nom_p(S,_,_), modifier(M,_), modifier(m(art_7),_), verb(V,_), nom_p(S2,_,_), verb(V2,_), nom_p(O,_,_).    % SN + Vb ( Art + Vb) + O ( SN + Vb + SN )  : Caso general
sentence(s(S,V,S2,m(art_6),V2,O)) --> nom_p(S,_,_), verb(V,_), nom_p(S2,_,_),modifier(m(art_6),_),verb(V2,_), nom_p(O,_,_).              % SN + Vb + SN + y + Vb + SN   : Caso general
sentence(s(S,V,S2,S3,V2,O)) --> nom_p(S,_,_), verb(V,_), nom_p(S2,_,_),modifier(m(art_6),_),nom_p(S3,_,_),verb(V2,_), nom_p(O,_,_).      % SN + Vb + SN + y + SN + Vb + SN   : Caso general

% Tipos de sintagmas nominales

nom_p(np(S),pl,o) --> modifier(m(art_0),X),noun(S,pl,X,o).                                                                               % Some + Nombre : Caso específico
nom_p(np(S),N,T) --> noun(S,N,_,T).                                                                                                      % Nombre : Caso general
nom_p(np(S,S2),N,_) --> noun(S,N,_,_),noun(S2,N,_,_).                                                                                    % Nombre + Nombre : Caso general
nom_p(np(A),_,_) --> adjective(A,_).                                                                                                     % Adjetivo : Caso general
nom_p(np(B),_,_) --> adverb(B).                                                                                                          % Adverbio : Caso general
nom_p(np(M,S),N,T) --> modifier(M,X), noun(S,N,X,T).                                                                                     % Articulo + Nombre : Caso general
nom_p(np(A,S),N,T) --> adjective(A,X), noun(S,N,X,T).                                                                                    % Nombre + Adjetivo : Caso general
nom_p(np(M,A,S),N,T) -->  modifier(M,X),adjective(A,X),noun(S,N,_,T).                                                                    % Articulo + Nombre + Adjetivo : Caso general
nom_p(np(p(prep_1),m(art_1),S),N,l) -->  preposition(p(prep_2)),noun(S,N,_,l).                                                           % "at" + Nombre : Caso especifico
nom_p(np(P,M,S),N,T) -->  preposition(P),modifier(M,X),noun(S,N,X,T).                                                                    % Preposicion + Articulo + Nombre : Caso general
nom_p(np(M,S,S2),N,T) --> modifier(M,X),noun(S2,N,X,T),noun(S,N,X,_).                                                                    % Articulo + Nombre + Nombre : Caso general
nom_p(np(A,B),_,_) --> adverb(B),adjective(A,_).                                                                                         % Adverbio + Adjetivo : Caso general

