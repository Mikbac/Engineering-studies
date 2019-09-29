{
kApriv = read("kA-priv.txt");

d = kApriv[1];
n = kApriv[2];
c = read("c.txt");

print("d = ", d);
print("n = ", n);
print("c = ", c);

M = Mod(c, n)^d;

print("M = ", lift(M));
}