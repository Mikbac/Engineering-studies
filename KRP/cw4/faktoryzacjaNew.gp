{
setrand(getwalltime());
default(parisize, 120000000);

k = 1024;

data = read("rsa4.out");

print("n= ", n);
print("e= ", e);
print("d= ", d);


/* ---1-t--- */
print("->krok-1");
t = e*d-1;
while(lift(Mod(t,2)) == 0, 
	t = t/2;);

print("t= ", t);
/* ---1-t--- */


/*-2-*/
print("->krok-2");
a = randomprime(2^k);
while(a<=1 || a>=n, 
	a = randomprime(2^k););



/*-3-*/
print("->krok-3");
d = gcd(a,n);


/*-4-*/
print("->krok-4");
if(d>1, 
	print("-wynik->d= ", d); 
	if(isprime(n/d)==1,print("--->testy<---");print("-isPrime->n/ans");print("-test->(n/d)*d - n = ", (n/d)*d - n);print("--->dobry wynik<---");); 
	return;);


/*-5-*/
print("->krok-5");
v = lift(Mod(a, n)^t);


/*-6-*/
print("->krok-6");
if(v == lift(Mod(1,n)),
	print("->nic<-");
	return;);


/*-7-*/
print("->krok-7");
while(v != lift(Mod(1,n)),
	v0 = lift(Mod(v,n));
	v = lift(Mod(v,n)^2););


/*-8-*/
print("->krok-8");
if(v0 == lift(Mod(-1,n)),
	print("->nic<-");
	return;);

if(v0 != lift(Mod(-1,n)),
	ans = gcd((v0+1),n);
	print("-wynik->(v0+1,n)= ", ans);
	if(isprime(n/ans)==1,print("--->testy<---");print("-isPrime->n/ans");print("-test->(n/ans)*ans - n = ", (n/ans)*ans - n);print("--->dobry wynik<---");); 
	return;);

}