{
setrand(getwalltime());

k=2048;

q = randomprime(2^k);
p = randomprime(2^k);

while(p==g, q = randomprime(2^k); p = randomprime(2^k));

print("p = ", p);
print("q = ", q);

n = p*q;
fin = (p-1)*(q-1);

e = randomprime(2^k);
while(e>=fin || e<=1 || gcd(e, fin) != 1, e = randomprime(2^k));

d = lift((Mod(e, fin))^(-1));

print("e = ", e);
print("n = ", n);

print("d = ", d);
print("n = ", n);

write("KA-pub.txt", [e, n]);
write("kA-priv.txt", [d, n]);

write("p.txt", p);
write("q.txt", q);
}