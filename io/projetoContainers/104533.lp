/*
	Dicionário	I := 4 (d4)
			K := 3 (s3)


	S = {7,10,11};
	D = {2,3,4,5};
	R = {2,3,4,5,6,7,8,9}
	
	S n D == 0 (\fi)

	N2 = 13
	N3 = 10
	N4 = 11 
	N5 = 5
	
*/

min: 	7 y74 + 7 y75 - 7 y92 + //custo de usar contentores tamanho 7 e item 4 e 5 e contentor tamanho 9(custo feito com y112) e item 2(resto 7)
		10 y102 + 3 y103 + 10 y104 + 10 y105 + //custo de usar contentores tamanho 10 e item 2, 3, 4 e 5
		11 y112 + 11 y113 + 4 y114 + 11 y115; //custo de usar contentores tamanho 11 e item 2, 3, 4 e 5


//Restrições aos contentores standard
y74 + y75 - y103 - y114 - y92 <= 4; // Todos os cortes a partir de comprimento standard 7 excepto os cortes obtidos de cortes previos ([10,3] -> 7 | [11,4] -> 7 | [9,2] -> 7) podem ser no máximo 4
y105 + y104 + y103 + y102 <= 5; // Todos os cortes a partir de comprimento standard 10 podem ser no máximo 5
y74 + y75 - y103 - y114 - y92 >= 0; // Todos os cortes a partir de comprimento standard 7 excepto os cortes obtidos de cortes previos ([10,3] -> 7 | [11,4] -> 7 | [9,2] -> 7) não podem ser menor que 0
y105 + y104 + y103 + y102 >= 0; // Todos os cortes a partir de comprimento standard 10 não podem ser menor que 0
y115 + y114 + y113 + y112 >= 0; // Todos os cortes a partir de comprimento standard 11 não podem ser menor que 0

//Restrições aos contentores obtidos pelo resto exceto os de tamanho 7
y105 - y53 - y54 >= 0; // Todos os cortes a partir de comprimento standard 10 com residuo 5 não podem ser menor que 0
y104 + y115 - y65 - y64 - y63 >= 0; // Todos os cortes a partir de comprimento standard 10 e 11 com residuo 6 não podem ser menor que 0
y102 + y113 - y85 - y84 - y82 >= 0; // Todos os cortes a partir de comprimento standard 10 e 11 com residuo 8 não podem ser menor que 0


y112 - y95 - y93 - y92 >= 0; // Todos os cortes a partir de comprimento standard 11 com residuo 9 não podem ser menor que 0

y84 + y95 - y43 - y42 >= 0; // Todos os cortes a partir de qualquer comprimento com residuo 4 não podem ser menor que 0



y32 + 2 y42 + y53 + y64 + y75 + y82 + y92 + y102 + y112 = 13; // Todos os cortes cujo item ou residuo é 2 têm que ser igual a 13

y43 + y53 + 2 y63 + y74+ y85 + y93 + y103 + y113 - y32 = 10; // Todos os cortes cujo item ou residuo é 3 menos os cortes a partir de comprimento 3 têm que ser igual a 10

y54 + y64 + y74 + 2 y84 + y95 + y104 + y114 - y43 - y42 = 11; // Todos os cortes cujo item ou residuo é 4 menos os cortes a partir de comprimento 4 têm que ser igual a 11

y65 + y75 + y85 + y95 + 2 y105 + y115 - y54 - y53 = 5;  // Todos os cortes cujo item ou residuo é 5 menos os cortes a partir de comprimento 5 têm que ser igual a 5


// Todos os valores sao inteiros positivos
    
y32 >= 0;
y42 >= 0;
y43 >= 0;
y53 >= 0;
y54 >= 0;
y63 >= 0;
y64 >= 0;
y65 >= 0;
y74 >= 0;
y75 >= 0;
y82 >= 0;
y84 >= 0;
y85 >= 0;
y92 >= 0;
y93 >= 0;
y95 >= 0;
y102 >= 0;
y103 >= 0;
y104 >= 0;
y105 >= 0; 
y112 >= 0;
y113 >= 0;
y114 >= 0;
y115 >= 0;

int y32 y42 y43 y53 y54 y63 y64 y65 y74 y75 y82 y84 y85 y92 y93 y95 y102 y103 y104 y105 y112 y113 y114 y115;