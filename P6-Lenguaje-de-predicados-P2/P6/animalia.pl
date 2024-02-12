:- discontiguous frame/3.

%reino
frame(animal, subclase_de(objeto), propiedades([puede(sentir), puede(respirar)])).

%tipo
frame(mamifero, subclase_de(animal), propiedades([puede(mamar), respira(aire)])).

%Orden
frame(artiodactilo, subclase_de(mamifero, propiedades([tiene(pesugnas_pares), comen(plantas)]))).
frame(carnivora, subclase_de(mamifero), propiedades([comen(carne)])).
frame(primates, subclase_de(mamifero), propiedades([tiene(cerebro_desarrollado)])).
frame(proboscidea, subclase_de(mamifero), propiedades([es(grande)])).
frame(perissodactyla, subclase_de(mamifero), propiedades([tiene(pesugnas_impares)])).
frame(chiroptera, subclase_de(mamifero), propiedades([tien(alas), es(roedor)])).

%Familia
frame(camelidos, subclase_de(artiodactilo), propiedades([familia_de(camellos)])).
frame(canidae, subclase_de(carnivora), propiedades([puede(comer_vegetales)])).
frame(suidae, subclase_de(artiodactilo), propiedades([son(inteligentes)])).
frame(hominidae, subclase_de(primates), propiedades([son(grandes_simios)])).
frame(felidae, subclase_de(carnivora), propiedades([son(felinos)])).
frame(elephantidae, subclase_de(proboscidea), propiedades([son(elefantes)])).
frame(equidae, subclase_de(perissodactyla), propiedades([son(compatibles)])).
frame(rhinocerotidae, subclase_de(perissodactyla), propiedades([tien(cuerno)])).
frame(ursidae, subclase_de(carnivora), propiedades([son(osos)])).
frame(noctilionoidea, subclase_de(chiroptera), propiedades([es(pescador)])).

%Ejemplares
frame(puma_concolor, subclase_de(felidae), propiedades([nombre_comun(puma), es(pequenia), emite(maullidos), imagen('puma.jpg')])).
frame(panthera_pardus, subclase_de(felidae), propiedades([nombre_comun(leopardo), es(rapido), imagen('leopardo.jpg')])).
frame(leopardus_geoffroyi, subclase_de(felidae), propiedades([nombre_comun(gato_montes), es(chiquito), imagen('montes.jpg')])).
frame(elephas_maximus, subclase_de(elephantidae), propiedades([nombre_comun(elefante_asiatico), imagen('elefante.jpg')])).
frame(equus_caballus, subclase_de(equidae), propiedades([nombre_comun(caballo), ruido(relincha), imagen('caballo.jpg')])).
frame(ceratotherium_simum, subclase_de(rhinocerotidae), propiedades([nombre_comun(rinoceronte_blanco), imagen('rino.jpg')])).
frame(ursus_arctos, subclase_de(ursidae), propiedades([nombre_comun(oso_pardo), pelaje(marron), imagen('pardo.jpg')])).
frame(ursus_maritimus, subclase_de(ursidae), propiedades([nombre_comun(oso_polar), pelaje(blanco), imagen('polar.jpg')])).
frame(ailuropoda_melanoleuca, subclase_de(ursidae), propiedades([nombre_comun(oso_panda), pelaje(blanco_y_negro), imagen('panda.jpg')])).
frame(noctilio_albiventris, subclase_de(noctilionoidea), propiedades([nombre_comun(murcielago_bulldog), imagen('murcielago.jpg')])).

que_es(X):-((instancia(X),es(Clase,X));

(subclase(X),subc(X,Clase))),Clase\=objeto,write('es '),writeln(Clase),fail.
es(Clase,Obj):- frame(Obj,instancia_de(Clase),_).
es(Clase,Obj):- frame(Obj,instancia_de(Clasep),_),subc(Clasep,Clase).

subc(C1,C2):- frame(C1,subclase_de(C2),_).
subc(C1,C2):- frame(C1,subclase_de(C3),_),subc(C3,C2).
subclase(X):-frame(X,subclase_de(_),_).

instancia(X):-frame(X,instancia_de(_),_).

propiedadesc(objeto):-!.
propiedadesc(X):-frame(X,subclase_de(Y),propiedades(Z)),imprime(Z),propiedadesc(Y).
propiedadesi(X):-frame(X,instancia_de(Y),propiedades(Z)),imprime(Z),propiedadesc(Y).
props(X):-(instancia(X),propiedadesi(X));(subclase(X),propiedadesc(X)).

imprime([]):-!.
imprime([H|T]):-writeln(H),imprime(T).
about(X):-que_es(X);props(X).
