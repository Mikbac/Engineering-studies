{

default(parisize, 120000000);
k = 250;

setrand(getwalltime());

data = read("P1.out");
data = read("P2.out");
data = read("krzywa.out");


	print("x1=", x1);
	print("y1=", y1);

	print("x2=", x2);
	print("y2=", y2);
	
	/*dla testu*/
		x2 = x1;
		y2 = y1;
	
	print("a=", a);
	print("b=", b);
	print("p=", p);	


/*Przypadek-1-*/
print("Przypadek-1");
if(x1 != x2,
	print("wybrano ----> Przypadek-1");
	
m = Mod(((y2-y1)/(x2-x1)), p);
x3 = Mod((m^2 - x1 - x2),p);
y3 = Mod((m*(x1-x3)-y1),p);

	x3 = lift(x3);
	y3 = lift(y3);

	print("x3=", x3);
	print("y3=", y3);
	
	
	print("----->----<-----");
	print("----->Test<-----");
	print("----->----<-----");

	print("lift(Mod(y3^2, p)) = ", lift(Mod(y3^2, p)));
	print("lift(Mod(x3^3 + a * x3 + b, p)) = ", lift(Mod(x3^3 + a * x3 + b, p)));
	
if(Mod(y3^2, p) == Mod(x3^3 + a * x3 + b, p),

print("True");

);
	

);

/*Przypadek-2-*/
print("Przypadek-2");
if(x1 == x2 && y1 == y2 && y1 != 0,
	print("wybrano ----> Przypadek-2");
m = Mod(((3 * x1^2 + a)/(2*y1)),p);

x3 = Mod((m^2 - 2*x1), p);
y3 = Mod((m * (x1 - x3) - y1), p);

	print("x3=", lift(x3));
	print("y3=", lift(y3));
	
	
	print("----->----<-----");
	print("----->Test<-----");
	print("----->----<-----");

	print("lift(Mod(y3^2, p)) = ", lift(Mod(y3^2, p)));
	print("lift(Mod(x3^3 + a * x3 + b, p)) = ", lift(Mod(x3^3 + a * x3 + b, p)));
	
if(Mod(y3^2, p) == Mod(x3^3 + a * x3 + b, p),

print("True");

);	

	

);

/*Przypadek-3-*/
print("Przypadek-3");
if(x1 == x2 && y1 != y2,
	print("wybrano ----> Przypadek-3");

	print("None");

);

/*Przypadek-4-*/
print("Przypadek-4");
if(x1 == x2 && y1 == y2 && y1 == 0,
	print("wybrano ----> Przypadek-4");



);



}