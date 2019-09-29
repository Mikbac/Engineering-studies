{
default(parisize, 120000000);
setrand(getwalltime());

/*-1-*/
print("krok-1");
q = randomprime(2^200);

k = random(2^800);
/*k = randomprime(2^800);*/
p = q * k + 1;

while(isprime(p)!=1 || (p-1)%q != 0, 
k = random(2^800);
p = q * k + 1;
);

/*-2-*/
print("krok-2");
a = random();
/*g = Mod(a^((p-1)/q), p);*/
g = Mod(a, p)^((p-1)/q);


while(g^q != 1,
a = random();
g = Mod(a, p)^((p-1)/q);
);

/*-3-*/
print("krok-3");
x = random();
while(x<1 || x>q,
x = random();
);

/*-4-*/
print("krok-4");
y = Mod(g, p)^x;

/*-5-*/
print("krok-5");
write("KA-pub.out", "p=", p);
write("KA-pub.out", "q=", q);
write("KA-pub.out", "g=", lift(g));
write("KA-pub.out", "y=", lift(y));

write("kA-priv.out", "x=", x);
write("kA-priv.out", "p=", p);
write("kA-priv.out", "q=", q);

write("Mh.out", "H=", 91907642836502659);

}