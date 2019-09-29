{
data1 = read("c.out");
data2 = read("kA-priv.out");

M = c2*Mod(c1, p)^(-x);

print("M = ", lift(M));

}