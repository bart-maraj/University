% Autor: Bartlomiej Maraj
% Fecha: 13/04/2020

% Traductor español a ingles :  ejemplo(numero entre 1 y 14,OrEsp), oracion(X,OrEsp,[]),sentence(X,OrIngles,[]),!.
% Traductor ingles a español :  example(numero entre 1 y 14,OrIngles), sentence(X,OrIngles,[]),oracion(X,OrEsp,[]),!.


:- consult(oraciones).                                                           % Contiene las oraciones
:- consult(diccionario).                                                         % Contiene el diccionario español
:- consult(dictionary).                                                          % Contiene el diccionario ingles
:- consult(gramatica).                                                           % Contiene la gramatica española
:- consult(grammar).                                                             % Contiene la gramatica inglesa


