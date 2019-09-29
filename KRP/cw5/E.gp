{
data = read("KA-pub.out");
pow = 1024;
M = 426080;

/*-1-*/
k = randomprime(2^pow);
/*while(x<1 || x>p-1 , k = randomprime(2^pow););*/
while(k<1 || k>p-1 , k = randomprime(2^pow););

/*-2-*/
c1 = Mod(q, p)^k;

/*-3-*/
c2 = M*Mod(y, p)^k;

/*-4-*/
write("c.out", "c1=", lift(c1));
write("c.out", "c2=", lift(c2));

print("c1=", lift(c1));
print("c2=", lift(c2));
}