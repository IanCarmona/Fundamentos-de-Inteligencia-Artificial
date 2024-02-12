menu :-
    write('Menú Principal: '), nl,
    write('1. Calcular índice de masa corporal '), nl,
    write('2. Recomendar una dieta saludable'), nl,
    write('3. Salir'), nl,
    write('Seleccione una opción: '),
    read(Opcion),
    opcion(Opcion).

diagnostico_imc(mujer, IMC) :-
    
    ( IMC >= 16, IMC < 20 ->
      write('Diagnóstico: usted padece bajo peso')
    ; IMC < 16 ->
      write('Diagnóstico: usted padece desnutrición')
    ; IMC >= 20, IMC < 24 ->
      write('Diagnóstico: usted está normal')
    ; IMC >= 24, IMC < 29 ->
      write('Diagnóstico: usted padece ligero sobrepeso')
    ; IMC >= 29, IMC < 37 ->
      write('Diagnóstico: usted padece obesidad severa')
    ; IMC >= 37 ->
      write('Diagnóstico: usted padece obesidad morbida')
    ).
diagnostico_imc(hombre, IMC) :-
    ( IMC >= 17, IMC < 20 ->
      write('Diagnóstico: usted padece bajo peso')
    ; IMC < 17 ->
      write('Diagnóstico: usted padece desnutrición')
     ;IMC >= 20, IMC < 25 ->
      write('Diagnóstico: usted está normal')
     ;IMC >= 25, IMC < 30 ->
      write('Diagnóstico: usted padece ligero sobrepeso')
    ;IMC >= 30, IMC < 40 ->
      write('Diagnóstico: usted padece obesidad severa')
    ;IMC >= 40 ->
      write('Diagnóstico: usted padece obesidad morbida')
    ).

metabolismo_basal_hombre(Peso, Edad, MB) :-
    ( Edad <25 ->  
        MB is (Peso*24)+300
    ; Edad <45 ->  
        MB is Peso*24
    ; Edad <55 ->  
        MB is (Peso*24)-100
    ; Edad <65 ->  
        MB is (Peso*24)-200
    ; Edad <75 ->  
        MB is (Peso*24)-300
    ; Edad <85 ->  
        MB is (Peso*24)-400
    ; Edad <95 ->  
        MB is (Peso*24)-500
    ; 
        MB is (Peso*24)-600
    ).

metabolismo_basal_mujer(Peso, Edad, MB) :-
    ( Edad <25 ->  
        MB is (Peso*21.6)+300
    ; Edad <45 ->  
        MB is Peso*21.6
    ; Edad <55 ->  
        MB is (Peso*21.6)-100
    ; Edad <65 ->  
        MB is (Peso*21.6)-200
    ; Edad <75 ->  
        MB is (Peso*21.6)-300
    ; Edad <85 ->  
        MB is (Peso*21.6)-400
    ; Edad <95 ->  
        MB is (Peso*21.6)-500
    ; 
        MB is (Peso*21.6)-600
    ).
%Frutas
fruta(arandanos, 41).
fruta(caqui, 64).
fruta(cereza, 47).
fruta(chirimoya, 78).
fruta(ciruela, 44).
fruta(ciruela_seca, 290).
fruta(coco, 646).
fruta(damasco, 44).
fruta(datil, 279).
fruta(datil_seco, 306).
fruta(durazno, 52).
fruta(durazno_almibar, 84).
fruta(frambuesa, 40).
fruta(fresas, 36).
fruta(granada, 65).
fruta(grosella, 37).
fruta(higos, 80).
fruta(higos_secos, 275).
fruta(kiwi, 51).
fruta(limon, 39).
fruta(mandarina, 40).
fruta(mango, 57).
fruta(manzana, 52).
fruta(melon, 31).
fruta(mora, 37).
fruta(naranja, 44).
fruta(nectarina, 64).
fruta(nisperos, 97).
fruta(palta, 167).
fruta(papaya, 45).
fruta(pera, 61).
fruta(pina, 51).
fruta(pina_almibar, 84).
fruta(platano, 90).
fruta(pomelo, 30).
fruta(sandia, 30).
fruta(uva, 81).
fruta(uva_pasa, 324).

%Cereales
cereal(arroz_blanco, 354).
cereal(arroz_integral, 350).
cereal(avena, 367).
cereal(cebada, 373).
cereal(centeno, 350).
cereal(cereales_con_chocolate, 358).
cereal(cereales_desayuno_con_miel, 386).
cereal(copos_de_maiz, 350).
cereal(harina_de_maiz, 349).
cereal(harina_de_trigo_integral, 340).
cereal(harina_de_trigo_refinada, 353).
cereal(pan_de_centeno, 241).
cereal(pan_de_trigo_blanco, 255).
cereal(pan_de_trigo_integral, 239).
cereal(pan_de_trigo_molde_blanco, 233).
cereal(pan_de_trigo_molde_integral, 216).
cereal(pasta_al_huevo, 368).
cereal(pasta_de_semola, 361).
cereal(polenta, 358).
cereal(semola_de_trigo, 368).
cereal(yuca, 338).

%jugos
jugo(zumo_naranja,42).
jugo(zumo_fruta,45).
%Huevo
huevo(huevo_entero, 162).
huevo(huevo_duro,147).
huevo(yema,368).
huevo(clara,48).

%Carne
carnico(bacon, 665).
carnico(butifarra_cocida, 390).
carnico(salchicha_fresca, 326).
carnico(cabrito, 127).
carnico(chuleta_de_cerdo, 330).
carnico(higado_de_cerdo, 153).
carnico(lomo_de_cerdo, 208).
carnico(charqui, 110).
carnico(chicharron, 601).
carnico(chorizo, 468).
carnico(ciervo, 120).
carnico(codorniz_y_perdiz, 114).
carnico(conejo_o_liebre, 162).
carnico(cordero_lechon, 105).
carnico(cordero_pierna, 98).
carnico(cordero_costillas, 215).
carnico(cordero_higado, 132).
carnico(faisan, 144).
carnico(foie_gras, 518).
carnico(gallina, 369).
carnico(hamburguesa, 230).
carnico(jabali, 107).
carnico(jamon, 380).
carnico(jamon_cocido, 126).
carnico(jamon_crudo, 296).
carnico(jamon_york, 289).
carnico(lengua_de_vaca, 191).
carnico(lomo_embuchado, 380).
carnico(mortadela, 265).
carnico(pato, 200).
carnico(muslo_de_pavo, 186).
carnico(pechuga_de_pavo, 134).
carnico(perdiz, 120).
carnico(pies_de_cerdo, 290).
carnico(higado_de_pollo, 129).
carnico(muslo_de_pollo, 186).
carnico(pechuga_de_pollo, 134).
carnico(salamin, 325).
carnico(salchicha_frankfurt, 315).
carnico(salchichon, 294).
carnico(bistec_de_ternera, 181).
carnico(chuleta_de_ternera, 168).
carnico(higado_de_ternera, 140).
carnico(lengua_de_ternera, 207).
carnico(rinon_de_ternera, 86).
carnico(sesos_de_ternera, 125).
carnico(solomillo_de_ternera, 290).
carnico(tira_de_asado, 401).
carnico(tripas, 100).
%Pastas

pasta(pasta_huevo, 368).
pasta(pasta_semola, 361).

%Postre
postre(flan_vainilla, 102).
postre(flan_huevo, 126).
postre(arroz_con_leche,123).
postre(berlín, 588).
postre(buñuelos,123).
postre(canelones,127).
postre(churros, 348).
postre(caramelos,378).
postre(chocolate_con_leche,550).
postre(crema_chocolate_con_avellanas,549).
postre(dulce_de_membrillo,215).
postre(gomas_de_fruta,172).

%Lacteo
lacteo(cuajada, 92).
lacteo(helados_lacteos, 167).
lacteo(leche_condensada_c_azucar, 350).
lacteo(leche_condensada_s_azucar, 160).
lacteo(leche_de_cabra, 72).
lacteo(leche_de_oveja, 96).
lacteo(leche_descremada, 36).
lacteo(leche_en_polvo_descremada, 373).
lacteo(leche_en_polvo_entera, 500).
lacteo(leche_entera, 68).
lacteo(leche_semi_descremada, 49).
lacteo(mousse, 177).
lacteo(nata_o_crema_de_leche, 298).
lacteo(queso_blanco_desnatado, 70).
lacteo(queso_brie, 263).
lacteo(queso_camembert, 312).
lacteo(queso_cheddar, 381).
lacteo(queso_crema, 245).
lacteo(queso_de_bola, 349).
lacteo(queso_de_burgos, 174).
lacteo(queso_de_oveja, 245).
lacteo(queso_edam, 306).
lacteo(queso_emmental, 415).
lacteo(queso_fundido_untable, 285).
lacteo(queso_gruyere, 391).
lacteo(queso_manchego, 376).
lacteo(queso_mozzarella, 245).
lacteo(queso_parmesano, 393).
lacteo(queso_ricota, 400).
lacteo(queso_roquefort, 405).
lacteo(requeson, 96).
lacteo(yogur_desnatado, 45).
lacteo(yogur_desnatado_con_frutas, 82).
lacteo(yogur_enriquecido_con_nata, 65).
lacteo(yogur_natural, 62).
lacteo(yogur_natural_con_fruta, 100).

%Colacion
colacion(donut, 456).
colacion(pastel_manzana, 311).
colacion(yogur_natural_fruta, 280).
colacion(barra_de_chocolate, 441).
colacion(maiz_palomitas, 592).
colacion(maiz_tiras_fritas, 459).
colacion(papas_fritas, 544).
%Regla para armar el desayuno
desayuno(D1, D2, D3, D4, KCal):-fruta(D1,K1), cereal(D2, K2), jugo(D3, K3), huevo(D4, K4), KCal is K1+K2+K3+K4.

%Regla para armar la comida
comida(C1, C2, C3, KCal):-carnico(C1,K1), pasta(C2, K2), postre(C3, K3), KCal is K1+K2+K3.

%Regla para armar la merienda
merienda(M1, M2, KCal):-lacteo(M1, K1), colacion(M2, K2), KCal is K1+K2.

% Regla que recibe como entrada las KCalorías del Gasto metabólico,
% recupera un desayuno, comida y merienda, y muestra el menú
% si cumple con que esté en el rago Gasto-10% y Gasto+10%
dieta(MB):-desayuno(D1,D2,D3,D4,K1),
              comida(C1,C2,C3,K2),
              merienda(M1,M2,K3),
              KCal is K1+K2+K3,
              Inferior is MB-(MB*0.1),
              Superior is MB+(MB*0.1),
              KCal >= Inferior, KCal=< Superior,
              format("\nDesayuno: ~s, ~s, ~s y ~s", [D1,D2,D3,D4]),
              format("\nComida  : ~s, ~s y ~s", [C1,C2,C3]),
              format("\nMerienda: ~s y ~s", [M1,M2]),
              format("\nTotal de KCalorías: ~d",KCal).

opcion(1) :-
    write('Calculo del indice de masa corporal'), nl,
    write('Ingrese su peso en kg'), nl,
    read(Peso),
    write('Ingrese su estatura en m'), nl,
    read(Estatura),
    write('Ingrese su genero (hombre o mujer) '), nl,
    read(Genero),
    IMC=Peso/(Estatura^2),
    write('Su índice de masa corporal es: '),
    format('~2f', [IMC]), nl,
    diagnostico_imc(Genero,IMC).

opcion(2) :-
    write('Dieta saludable'), nl,
    write('Ingrese su genero (hombre o mujer) '), nl,
    read(Genero),
    write('Ingrese su peso en kg'), nl,
    read(Peso),
    write('Ingrese su edad'), nl,
    read(Edad),
    ( Genero == hombre ->
        metabolismo_basal_hombre(Peso, Edad, MB),
        write('Usted necesita consumir mínimo: '), write(MB), write(' Kcal'),nl
    ; Genero == mujer ->
        metabolismo_basal_mujer(Peso, Edad, MB),
        write('Usted necesita consumir mínimo: '), write(MB), write(' Kcal'),nl
    ; 
        write('Genero inválido')),
		 
    write('¿Desea que se le proporcione un menú de acuerdo con las Kcal que necesita? (si/no)'), nl,
		read(Desc),
        (   Desc==si ->  
     			dieta(MB)
        ;   Desc==no ->  
        	write('Gracias por utilizar el programa')
        ).

opcion(3) :-
    write('¡Hasta pronto! Gracias por utilizar el programa.'), nl.

opcion(_) :-
    write('Opción no válida. Por favor, seleccione una opción válida.'), nl.


