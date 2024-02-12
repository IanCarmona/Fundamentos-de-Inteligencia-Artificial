%hombres Stark
hombre(rickard_stark).
hombre(eddard_stark_ned).
hombre(brandon_stark).
hombre(benjen_stark).
hombre(robb_stark).
hombre(bran_stark).
hombre(rickon_stark).
%hombres Targaryen
hombre(aerys_targaryen).
hombre(rhaeger_targaryen).
hombre(viserys_targaryen).
hombre(aegon_targaryen).
%otros
hombre(jon_snow).
%mujeres Stark
mujer(lyarra_stark).
mujer(lyanna_stark).
mujer(catelyn_stark_tully).
mujer(sansa_stark).
mujer(arya_stark).
%mujeres Targaryen
mujer(rhaella_targaryen).
mujer(elia_targaryen_martell).
mujer(daenerys_targaryen).
mujer(rhaenys_targaryen).

progenitor(rickard_stark,eddard_stark_ned).
progenitor(rickard_stark,brandon_stark).
progenitor(rickard_stark,benjen_stark).
progenitor(rickard_stark,lyanna_stark).

progenitor(lyarra_stark,eddard_stark_ned).
progenitor(lyarra_stark,brandon_stark).
progenitor(lyarra_stark,benjen_stark).
progenitor(lyarra_stark,lyanna_stark).

progenitor(eddard_stark_ned,robb_stark).
progenitor(eddard_stark_ned,sansa_stark).
progenitor(eddard_stark_ned,arya_stark).
progenitor(eddard_stark_ned,bran_stark).
progenitor(eddard_stark_ned,rickon_stark).

progenitor(catelyn_stark_tully,robb_stark).
progenitor(catelyn_stark_tully,sansa_stark).
progenitor(catelyn_stark_tully,arya_stark).
progenitor(catelyn_stark_tully,bran_stark).
progenitor(catelyn_stark_tully,rickon_stark).

progenitor(lyanna_stark,jon_snow).
progenitor(rhaeger_targaryen,jon_snow).

progenitor(aerys_targaryen,rhaeger_targaryen).
progenitor(aerys_targaryen,elia_targaryen_martell).
progenitor(aerys_targaryen,viserys_targaryen).
progenitor(aerys_targaryen,daenerys_targaryen).

progenitor(rhaeger_targaryen,rhaenys_targaryen).
progenitor(rhaeger_targaryen,aegon_targaryen).
progenitor(elia_targaryen_martell,rhaenys_targaryen).
progenitor(elia_targaryen_martell,aegon_targaryen).
           
matrimonio(rickard_stark,lyarra_stark1).
matrimonio(eddard_stark_ned,catelyn_stark_tully).
matrimonio(aerys_targaryen,rhaella_targaryen).
matrimonio(rhaeger_targaryen,elia_targaryen_martell).

%funciones
esposo(A,B):-matrimonio(A,B),hombre(A).
esposa(C,D):-matrimonio(C,D),mujer(C).
hermano(X,Y):-progenitor(Z,X),progenitor(Z,Y), not(X==Y),hombre(X).
hermana(P,Q):-progenitor(R,P),progenitor(R,Q), not(P==Q),mujer(P).
abuelo(X,Y):-progenitor(Z,Y),progenitor(X,Z), hombre(X).
abuela(X,Y):-progenitor(Z,Y),progenitor(X,Z), mujer(X).