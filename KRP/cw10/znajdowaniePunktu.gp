{
default(parisize, 120000000);
setrand(getwalltime());

data = read("krzywa.out");

print("a=", a);
print("b=", b);
print("p=", p);

k = 250;

/*-1-*/
print("krok-1");

	x = lift(Mod(randomprime(2^k), p));


/*-2-*/
print("krok-2");

	f = lift(Mod((x^(3) + a * x + b), p));


/*-3-*/
print("krok-3");

	while(Mod(f,p)^((p-1)/2) == Mod(-1, p),

		/*-skok-do-1-*/

		x = lift(Mod(randomprime(2^k), p));

		f = lift(Mod((x^(3) + a * x + b), p));

	);

	print("f = ", f);


/*-4-*/
print("krok-4");
print("b=", b);
print("f=", f);


	y = lift(Mod(f, p)^((p+1)/4));


/*-5-*/
print("krok-5");

print("x=", x);
print("y=", y);

/*
write("punkt.out", "x=", x);
write("punkt.out", "y=", y);
*/

/*-Test-*/
print("----->----<-----");
print("----->Test<-----");
print("----->----<-----");

print("lift(Mod(y^2, p)) = ", lift(Mod(y^2, p)));
print("lift(Mod(x^3 + a * x + b, p)) = ", lift(Mod(x^3 + a * x + b, p)));


if(Mod(y^2, p) == Mod(x^3 + a * x + b, p),

print("True");


);

}