min: 20y1 + 40y2 + 10y3 + 70y4 + 
	7x1 + 4x2 + 8x3 + 1x4;

Quantidade: x1 + x2 + x3 + x4 = 1000;

x1 <= 500 y1;
x2 <= 200 y2;
x3 <= 400 y3;
x4 <= 600 y4;
x1 > 0;
x2 > 0;
x3 > 0;
x4 > 0;

y1 + y2 + y3 + y4 <= 3;

bin y1,y2,y3,y4;
