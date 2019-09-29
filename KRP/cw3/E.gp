{
KApub = read("KA-pub.txt");

print("e = ", KApub[1]);
print("n = ", KApub[2]);

e = KApub[1];
n = KApub[2];

m = 426080;

if(m<n,
c = Mod(m, n)^e;
print("c = ", lift(c));
write("c.txt", lift(c));
)
}