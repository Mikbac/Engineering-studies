/* -----ELGMAL----- */
{
default(parisize, 120000000);
setrand(getwalltime());

/*-1-*/
k=1024;
p = randomprime(2^k);

/*-2.1-*/
q = randomprime(2^k);
while(isprime(2*q+1) != 1, q = randomprime(2^k););
p=2*q+1;

/*-2.2-*/
g = randomprime(2^k);
/*while(g==1 || (g^2) == Mod(1, p) || g == Mod(1, p)/g, g = randomprime(2^k););*/
while(Mod(g,p)^2 == Mod(1,p) || Mod(g,p)^q == Mod(1,p) || g ==1  || g>p, g = randomprime(2^k););

/*-3-*/
x = randomprime(2^k);
while(x<1 || x>p-1 , x = randomprime(2^k););

/*-4-*/
y = Mod(q, p)^x;

/*-5-*/
print("p=", p);
write("KA-pub.out", "p=", p);
print("q=", q);
write("KA-pub.out", "q=", q);
print("y=", lift(y));
write("KA-pub.out", "y=", lift(y));

print("x=", x);
write("kA-priv.out", "x=", x);
print("p=", p);
write("kA-priv.out", "p=", p);
}