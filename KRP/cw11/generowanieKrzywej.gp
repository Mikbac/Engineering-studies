{
default(parisize, 120000000);
k = 250;

setrand(getwalltime());

/*-1-*/
print("krok-1");

	p = randomprime(2^k);

	while(Mod(p, 4) != Mod(3, 4),

		p = randomprime(2^k);

	);


/*-2-*/
print("krok-2");

	a = lift(Mod(randomprime(2^k), p));
	b = lift(Mod(randomprime(2^k), p));


/*-3-*/
print("krok-3");

	delE = (4 * (a^3)) + (27 * (b^2));
	/*delE = (4 * a^3) + (27 * b^2);*/

/*-4-*/
print("krok-4");

	while(Mod(delE, p) == Mod(0, p), 

	/*-skok-do-2-*/

		a = lift(Mod(randomprime(2^k), p));
		b = lift(Mod(randomprime(2^k), p));


		delE = (4 * (a^3)) + (27 * (b^2));
		/*delE = (4 * a^3) + (27 * b^2);*/

	);


/*-5-*/
print("krok-5");


	print("a=", a);
	print("b=", b);
	print("p=", p);

	write("krzywa.out", "a=", a);
	write("krzywa.out", "b=", b);
	write("krzywa.out", "p=", p);



}