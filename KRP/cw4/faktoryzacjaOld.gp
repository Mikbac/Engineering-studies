{
setrand(getwalltime());

k = 100;

read("rsa3.out");

print("n=",n);
print("e=",e);
print("d=",d);

t=e*d-1;

print("t=",t);

while(lift(Mod(t,2)) == 0,
t=t/2;
);


/*2*/
a = randomprime(2^k);
while(a<=1 || a>=n, a = randomprime(2^k););

print("a=",a);

/*3*/
d=gcd(a,n);

/*4*/
if(d > 1,
print("-----> d= ",d);,

v=lift(Mod(a,n)^t);
if (v == Mod(1, n),
	print("Nie udalo sie znalezc v != 1"),
	while(v != 1,
	v0=v;
	v=lift(Mod(v,n)^2);
	);
	if(v0 == Mod(-1, n),
		print("Nie udalo sie znalezc v0 != -1"),
		print("-----> v0+1= ",gcd(v0+1,n));
		
	);
);
);

}
